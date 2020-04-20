package main.game.player;

import static main.GameConstants.TH_RENDER;
import static main.GameConstants.TW_RENDER;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.map.Map;
import main.game.ui.UI;
import main.game.ui.elements.Bar;
import main.util.ResourceLoader;
import main.util.Utils;

public class Camera {

	private Map map;

	private Rectangle renderRect, viewRect;

	private int tile_buffer = 2;

	private float zoom = 1f, targetZoom = zoom;

	private float minZoom = 0.5f, maxZoom = 10f;

	private Point zoomOffset;

	private UI ui;

	private Point cameraPos;

	private float rectOffsetX, rectOffsetY;

	private float baseWidth, baseHeight;
	
	private float uiOffsetLower = 0f;

	public Camera(Map m, Point pos, float width, float height) {

		map = m;

		baseWidth = width;
		baseHeight = height;
		zoom = 1f;

		cameraPos = pos;
		zoomOffset = new Point(0, 0);

		// Calculate offset for camera
		rectOffsetX = (width / 2) / zoom;
		rectOffsetY = (height / 2) / zoom;

		// Create the view rectangle
		viewRect = new Rectangle(cameraPos.getX() - rectOffsetX, cameraPos.getY() - rectOffsetY, width, height);

		// Create the rendering rectangle
		renderRect = new Rectangle(viewRect.getX() - tile_buffer * TW_RENDER, viewRect.getY() - tile_buffer * TH_RENDER,
				viewRect.getWidth() + tile_buffer * 2 * TW_RENDER, viewRect.getHeight() + tile_buffer * 2 * TH_RENDER);

		if (map.getMapWidth() <= map.getMapHeight()) {
			minZoom = viewRect.getWidth() / (TW_RENDER * map.getMapWidth());
		} else {
			minZoom = viewRect.getHeight() / (TW_RENDER * map.getMapHeight());
		}

	}

	public void update() {
		// Update UI
		if (ui != null)
			ui.tick();

		/// Zoom code
		int mouseWheel = (int) Math.signum(Mouse.getDWheel());
		targetZoom += mouseWheel * 0.5f;

		if (targetZoom < minZoom)
			targetZoom = minZoom;
		if (targetZoom > maxZoom)
			targetZoom = maxZoom;

		float previousZoom = zoom;
		zoom = Utils.lerp(zoom, targetZoom, 0.075f);

		float diffX = baseWidth * (previousZoom - zoom);
		float diffY = baseHeight * (previousZoom - zoom);

		zoomOffset.setX((float) (diffX * 0.5f / Math.pow((previousZoom + zoom) / 2, 2)));
		zoomOffset.setY((float) (diffY * 0.5f / Math.pow((previousZoom + zoom) / 2, 2)));

		if (zoom != previousZoom) {
			viewRect.setX(viewRect.getX() - zoomOffset.getX());
			viewRect.setY(viewRect.getY() - zoomOffset.getY());
		}

		// Movement Code
		Point dir = pollInput();
		move(dir.getX(), dir.getY());

		// Keep camera in bounds
		float[] bounds = { 0f, 0f, map.getMapWidth() * TW_RENDER - viewRect.getWidth(), map.getMapHeight() * TH_RENDER
				- viewRect.getHeight() + uiOffsetLower / zoom };

		if (viewRect.getX() < bounds[0])
			viewRect.setX(Utils.lerp(viewRect.getX(), bounds[0], 0.1f));
		if (viewRect.getY() < bounds[1])
			viewRect.setY(Utils.lerp(viewRect.getY(), bounds[1], 0.1f));
		if (viewRect.getX() > bounds[2])
			viewRect.setX(Utils.lerp(viewRect.getX(), bounds[2], 0.1f));
		if (viewRect.getY() > bounds[3])
			viewRect.setY(Utils.lerp(viewRect.getY(), bounds[3], 0.1f));

		updateRectangles();

		int size = ui.getElements().size();
		for (int i = 0; i < size; i++) {
			if (ui.getElements().get(i) instanceof Bar)
				((Bar) ui.getElements().get(i)).setProgress(zoom / maxZoom); // TODO REMOVE THIS
		}

	}

	public void updateRectangles() {

		viewRect.setWidth(baseWidth / zoom);
		viewRect.setHeight(baseHeight / zoom);

		rectOffsetX = (viewRect.getWidth() / 2);
		rectOffsetY = (viewRect.getHeight() / 2);

		cameraPos.setX(viewRect.getX() + rectOffsetX);
		cameraPos.setY(viewRect.getY() + rectOffsetY);

		renderRect.setX(viewRect.getX() - tile_buffer * zoom * TW_RENDER);
		renderRect.setY(viewRect.getY() - tile_buffer * zoom * TH_RENDER);
		renderRect.setWidth(viewRect.getWidth() + 2 * tile_buffer * zoom * TW_RENDER);
		renderRect.setHeight(viewRect.getHeight() + 2 * tile_buffer * zoom * TH_RENDER);
	}

	// Poll Inputs for movement
	public Point pollInput() {
		int dir = 0;

		float xDir = 0, yDir = 0;

		float mspeed = 10;

		Input input = Engine.getInput();

		if (input.isKeyDown(Input.KEY_D))
			dir |= 1;
		if (input.isKeyDown(Input.KEY_A))
			dir |= 2;
		if (input.isKeyDown(Input.KEY_W))
			dir |= 4;
		if (input.isKeyDown(Input.KEY_S))
			dir |= 8;

		if ((dir & 1) != 0)
			xDir = mspeed;
		else if ((dir & 2) != 0)
			xDir = -mspeed;
		if ((dir & 4) != 0)
			yDir = -mspeed;
		else if ((dir & 8) != 0)
			yDir = mspeed;

		return new Point(xDir, yDir);
	}

	// Move Code

	public void move(float xDir, float yDir) {

		viewRect.setX(viewRect.getX() + xDir);
		viewRect.setY(viewRect.getY() + yDir);
	}

	// Getters and Setters
	public Map getMap() {
		return map;
	}

	public Rectangle getRenderRect() {
		return renderRect;
	}

	public Rectangle getViewRect() {
		return viewRect;
	}

	public Point getPos(boolean center) {
		if (center)
			return cameraPos;
		else
			return new Point(viewRect.getX(), viewRect.getY());
	}

	public float getZoom() {
		return zoom;
	}

	public UI getUI() {
		return ui;
	}

	public void setUI(UI ui) {
		this.ui = ui;
		uiOffsetLower = ui.getPlayer().getFaction().getSprite("ui_bottombar").getHeight();
	}
}