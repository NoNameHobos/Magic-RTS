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
import main.util.Utils;

public class Camera {

	private Map map;

	private Rectangle renderRect, viewRect;

	private int tile_buffer = 2;

	private float minZoom, zoom, maxZoom, targetZoom, previousZoom;
	private Point zoomPoint;
	
	private float bounds[];
	
	private UI ui;

	private Point targetPos;
	

	public Camera(Map m, Point pos, float width, float height) {

		map = m;

		viewRect = new Rectangle(pos.getX(), pos.getY(), width, height);
		targetPos = new Point(0, 0);
		viewRect.setX(targetPos.getX());
		viewRect.setY(targetPos.getY());

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
		
		zoomPoint = new Point(0,0);
		
	}

	public void update() {
		// Update UI
		if (ui != null)
			ui.tick();

		/// Zoom code
		Point mouse = Engine.getMouse().getScreenPos();
		int mouseWheel = (int) Math.signum(Mouse.getDWheel());
		targetZoom += mouseWheel * 0.1f * targetZoom;
		if (mouseWheel != 0) zoomPoint.setX(mouse.getX());zoomPoint.setY(mouse.getY());

		if (targetZoom < minZoom) targetZoom = Utils.lerp(targetZoom, minZoom, 0.1f);
		if (targetZoom > maxZoom) targetZoom = Utils.lerp(targetZoom, maxZoom, 0.1f);

		previousZoom = zoom;
		zoom = Utils.lerp(zoom, targetZoom, 0.1f);
		
		float diffX = viewRect.getWidth() * zoom - viewRect.getWidth() * previousZoom;
		float diffY = viewRect.getHeight() * zoom - viewRect.getHeight() * previousZoom;
		float curPerX = 2f - ((Engine.getWIDTH()-zoomPoint.getX())/(Engine.getWIDTH()/2));
		float curPerY = 2f - ((Engine.getHEIGHT()-zoomPoint.getY())/(Engine.getHEIGHT()/2));
		
		bounds = new float[]{0f, 0f, zoom * TW_RENDER * map.getMapWidth() - viewRect.getWidth(),
			      zoom * TH_RENDER * map.getMapHeight() - viewRect.getHeight() + 32 };
		// TODO: Replace 32 with a constant to represent bottom bar height ------^
		
		if (targetPos.getX() + diffX * curPerX < bounds[0]) curPerX = 0;
		if (targetPos.getY() + diffY * curPerY < bounds[1]) curPerY = 0;
		if (targetPos.getX() + diffX * curPerX > bounds[2]) curPerX = 1;
		if (targetPos.getY() + diffY * curPerY > bounds[3]) curPerY = 1;
		
		System.out.println(diffX * curPerX);
		
		targetPos.setX(targetPos.getX() + diffX * curPerX);
		targetPos.setY(targetPos.getY() + diffY * curPerY);
		
		// Movement Code
		Point dir = pollInput();
		
		move(dir.getX()*zoom, dir.getY()*zoom);

		while (targetPos.getX() != viewRect.getX()) {
			viewRect.setX(targetPos.getX());

		}
		while (targetPos.getY() != viewRect.getY()) {
			viewRect.setY(targetPos.getY());
		}

		renderRect.setX(viewRect.getX() - tile_buffer * zoom * TW_RENDER);
		renderRect.setY(viewRect.getY() - tile_buffer * zoom * TH_RENDER);
		renderRect.setWidth(viewRect.getWidth() + tile_buffer * zoom * 2 * TW_RENDER);
		renderRect.setHeight(viewRect.getHeight() + tile_buffer * zoom * 2 * TW_RENDER);

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

		float boundSpeed = 0.1f;
		

		if (targetPos.getX() < bounds[0])
			targetPos.setX(Utils.lerp(targetPos.getX(), bounds[0], boundSpeed));
		if (targetPos.getY() < bounds[1])
			targetPos.setY(Utils.lerp(targetPos.getY(), bounds[1], boundSpeed));
		if (targetPos.getX() > bounds[2])
			targetPos.setX(Utils.lerp(targetPos.getX(), bounds[2], boundSpeed));
		if (targetPos.getY() > bounds[3])
			targetPos.setY(Utils.lerp(targetPos.getY(), bounds[3], boundSpeed));

		targetPos.setX(targetPos.getX() + xDir);
		targetPos.setY(targetPos.getY() + yDir);
	}

	public void moveToPoint(Point pos, float speed) {

		if (pos.getX() < bounds[0])
			pos.setX(bounds[0]);
		if (pos.getY() < bounds[1])
			pos.setY(bounds[1]);
		if (pos.getX() < bounds[2])
			pos.setX(bounds[2]);
		if (pos.getY() < bounds[3])
			pos.setY(bounds[3]);

		targetPos.setX(Utils.lerp(targetPos.getX(), pos.getX(), speed));
		targetPos.setY(Utils.lerp(targetPos.getY(), pos.getY(), speed));

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