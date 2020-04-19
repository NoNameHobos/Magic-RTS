package main.game.player;

import static main.GameConstants.TH_RENDER;
import static main.GameConstants.TW_RENDER;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.Game;
import main.game.map.Map;
import main.game.ui.UI;
import main.util.ResourceLoader;
import main.util.Utils;

public class Camera {

	private Map map;

	private Rectangle renderRect, viewRect;

	private int tile_buffer = 2;

	private float minZoom, zoom, maxZoom, targetZoom;
	
	private float bounds[];
	
	private UI ui;
	
	private Point cameraPos;
	
	private float rectOffsetX, rectOffsetY;
	

	public Camera(Map m, Point pos, float width, float height) {

		map = m;

		cameraPos = pos;
		rectOffsetX = width/2;
		rectOffsetY = height/2;
		
		viewRect = new Rectangle(cameraPos.getX()-rectOffsetX, cameraPos.getY()-rectOffsetY, width, height);

		// Create the rendering rectangle based off the view rectangle plus a tile
		// buffer
		renderRect = new Rectangle(viewRect.getX() - tile_buffer * TW_RENDER, viewRect.getY() - tile_buffer * TH_RENDER,
				viewRect.getWidth() + tile_buffer * 2 * TW_RENDER, viewRect.getHeight() + tile_buffer * 2 * TH_RENDER);

		if (map.getMapWidth() <= map.getMapHeight()) {
			minZoom = viewRect.getWidth() / (TW_RENDER * map.getMapWidth());
		} else {
			minZoom = viewRect.getHeight() / (TW_RENDER * map.getMapHeight());
		}

		maxZoom = 10f;

		zoom = minZoom;
		targetZoom = zoom;
		
	}

	public void update() {
		// Update UI
		if (ui != null)
			ui.tick();

		/// Zoom code
		Point mouse = Engine.getMouse().getScreenPos();
		int mouseWheel = (int) Math.signum(Mouse.getDWheel());
		targetZoom += mouseWheel * 0.1f * targetZoom;
		zoom = targetZoom;
		
		bounds = new float[] {0f, 0f, zoom * TW_RENDER * map.getMapWidth() - viewRect.getWidth(),
			      zoom * TH_RENDER * map.getMapHeight() - viewRect.getHeight() + ResourceLoader.UI.get("UIBottomBar") .getHeight()};
		
		// Movement Code
		Point dir = pollInput();
		move(dir.getX(),dir.getY());
		
		rectOffsetX = (viewRect.getWidth())/2;
		rectOffsetY = (viewRect.getHeight())/2;
		
		viewRect.setX(cameraPos.getX()-rectOffsetX);
		viewRect.setY(cameraPos.getY()-rectOffsetY);
		
		renderRect.setX(viewRect.getX() - tile_buffer * zoom * TW_RENDER);
		renderRect.setY(viewRect.getY() - tile_buffer * zoom * TH_RENDER);
		renderRect.setWidth(viewRect.getWidth() + tile_buffer * zoom * 2 * TW_RENDER);
		renderRect.setHeight(viewRect.getHeight() + tile_buffer * zoom * 2 * TW_RENDER);
		
		System.out.println(rectOffsetX);

	}

	// Poll movement code
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

	// Move Code (include boundaries)

	public void move(float xDir, float yDir) {

		cameraPos.setX(cameraPos.getX() + xDir);
		cameraPos.setY(cameraPos.getY() + yDir);
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

	public Point getPos() {
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
	}
}