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
import main.util.Utils;

public class Camera {

	private Point viewPos;
	private Rectangle cameraRect, viewRect;

	private Input input;

	private float minZoom, maxZoom;
	private float zoom = 1f;
	private float targetZoom = 1f;

	private Map map;

	private UI ui;

	public Camera(Map map, Point pos, float width, float height) {
		float mapWidth = map.getMapWidth() * TW_RENDER;
		float mapHeight = map.getMapHeight() * TH_RENDER;

		// Check boundaries
		if (pos.getX() < 0)
			pos.setX(0);
		if (pos.getY() < 0)
			pos.setY(0);

		if (pos.getX() + width > mapWidth * zoom) {
			pos.setX(mapWidth * zoom - width);
		}
		if (pos.getY() + height > mapHeight * zoom) {
			pos.setY(mapHeight * zoom - height);
		}

		viewPos = pos;
		viewRect = new Rectangle(viewPos.getX(), viewPos.getY(), width, height);

		cameraRect = new Rectangle(viewPos.getX() - TW_RENDER, viewPos.getY() - TH_RENDER, width + 2 * TW_RENDER,
				height + 2 * TH_RENDER);

		if (mapWidth < mapHeight) {
			minZoom = 2 * (float) Engine.getWIDTH() / mapWidth;
		} else
			minZoom = 2 * (float) Engine.getHEIGHT() / mapHeight;
		maxZoom = 1.5f;

		viewPos = pos;

		this.map = map;
		this.input = Engine.getInput();
	}

	public void move(float xDir, float yDir, boolean useBounds) {
		float mapWidth = map.getMapWidth() * TW_RENDER;
		float mapHeight = map.getMapHeight() * TH_RENDER;

		boolean safeLeft = true, safeRight = true, safeUp = true, safeDown = true;

		float[] safeBounds = { 
				0, // TOP
				0, // LEFT
				(mapWidth + TW_RENDER * 5), // Right
				mapHeight + TH_RENDER * 5 + 32 // Down + 32 to cater for border of UI
		};

		if (useBounds) {
			safeUp = (viewPos.getY() + yDir > safeBounds[0]);
			safeLeft = (viewPos.getX() + xDir > safeBounds[1]);
			safeRight = (viewPos.getX() + viewRect.getWidth() + xDir <= safeBounds[2]);
			safeDown = (viewPos.getY() + viewRect.getHeight() + yDir <= safeBounds[3]);
		}

		if (safeLeft && safeRight)
			viewPos.setX(viewPos.getX() + xDir);
		else {
			if (!safeLeft)
				viewPos.setX(safeBounds[1]);
			if (!safeRight)
				viewPos.setX(safeBounds[2] - viewRect.getWidth());
		}

		if (safeDown && safeUp)
			viewPos.setY(viewPos.getY() + yDir);
		else {
			if (!safeUp)
				viewPos.setY(safeBounds[0]);
			if (!safeDown)
				viewPos.setY(safeBounds[3] - viewRect.getHeight());
		}
	}

	public void update() {
		// Update Rectangles
		updateRectangles();
		// Update the UI
		if (ui != null)
			ui.tick();

		// Move code
		int mspeed = 10;

		int dir = 0;
		if (input.isKeyDown(Input.KEY_D))
			dir |= 1;
		if (input.isKeyDown(Input.KEY_A))
			dir |= 2;
		if (input.isKeyDown(Input.KEY_W))
			dir |= 4;
		if (input.isKeyDown(Input.KEY_S))
			dir |= 8;

		float xDir = 0, yDir = 0;

		if ((dir & 1) != 0)
			xDir = mspeed;
		else if ((dir & 2) != 0)
			xDir = -mspeed;
		if ((dir & 4) != 0)
			yDir = -mspeed;
		else if ((dir & 8) != 0)
			yDir = mspeed;

		move(xDir, yDir, true);

		/// Zoom code
		int mouseWheel = (int) Math.signum(Mouse.getDWheel());

		targetZoom += mouseWheel * 0.0625f;

		if (targetZoom < minZoom)
			targetZoom = minZoom;
		if (targetZoom > maxZoom)
			targetZoom = maxZoom;

		zoom = Utils.lerp(zoom, targetZoom, 0.1f);

	}

	public void updateRectangles() {

		// Render rectangle
		cameraRect.setX(viewPos.getX() - 2 * TW_RENDER);
		cameraRect.setY(viewPos.getY() - 2 * TH_RENDER);
		cameraRect.setWidth(viewRect.getWidth() + 3 * TW_RENDER);
		cameraRect.setHeight(viewRect.getHeight() + 3 * TH_RENDER);
	}

	// Getters and Setters
	public void setUI(UI ui) {
		this.ui = ui;
	}

	public UI getUI() {
		return ui;
	}

	public Point getPos() {
		return viewPos;
	}

	public Rectangle getRenderRect() {
		return cameraRect;
	}

	public Rectangle getCamView() {
		return viewRect;
	}

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float z) {
		zoom = z;
	}
}
