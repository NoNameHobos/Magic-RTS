package main.player;

import static main.GameConstants.TH_RENDER;
import static main.GameConstants.TW_RENDER;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.map.Map;
import main.game.ui.UI;

public class Camera {

	private Point viewPos;
	private Rectangle cameraRect, viewRect;

	private Input input;
	private float minZoom, maxZoom;
	private float zoom = 1f;
	
	private Map map;
	
	private UI ui;
	
	public Camera(Map map, Point pos, float width, float height) {
		if(pos.getX() < 0) pos.setX(0);
		if(pos.getY() < 0) pos.setY(0);
		
		if(map.getMapWidth() < map.getMapHeight()) {
			minZoom  = 2 * (float)Engine.getWIDTH() / (float)(map.getMapWidth() * TW_RENDER);
		} else 
			minZoom = 2 * (float)Engine.getHEIGHT() / (float)(map.getMapHeight() * TH_RENDER);
		System.err.println(map.getMapWidth() + " " + map.getMapHeight());
		maxZoom = 1.5f;
		
		viewPos = pos;
		
		cameraRect = new Rectangle(viewPos.getX(), viewPos.getY(), width + TW_RENDER + 1, height + TH_RENDER + 1);
		viewRect = new Rectangle(viewPos.getX(), viewPos.getY(), width * zoom, height * zoom);
		
		this.map = map;
		this.input = Engine.getInput();
	}
	
	public void move(float xDir, float yDir) {
		float mapWidth = (map.getMapWidth() + 1) * TW_RENDER;
		float mapHeight = (map.getMapHeight() + 1) * TH_RENDER;
		
		boolean safeLeft = (viewPos.getX() + xDir > 0);
		boolean safeRight = (viewPos.getX() + cameraRect.getWidth() + xDir <= mapWidth * zoom);
		boolean safeUp = (viewPos.getY() + yDir > 0);
		boolean safeDown = (viewPos.getY() + cameraRect.getHeight() + yDir <= mapHeight * zoom);
		
		if(safeLeft && safeRight)
			viewPos.setX(viewPos.getX() + xDir);
		else {
			if(!safeLeft)
				viewPos.setX(0);
			if(!safeRight)
				viewPos.setX(mapWidth * zoom - cameraRect.getWidth());
		}
		
		if(safeDown && safeUp)
			viewPos.setY(viewPos.getY() + yDir);
		else {
			if(!safeUp)
				viewPos.setY(0);
			if(!safeDown)
				viewPos.setY(mapHeight * zoom - cameraRect.getHeight());
		}
	}
	
	public void update() {
		int mspeed = 10;
		if(ui != null)
			ui.tick();
		int xDir = 0;
		int yDir = 0;
		
		if(input.isKeyDown(Input.KEY_S))
			yDir = mspeed;
		else if(input.isKeyDown(Input.KEY_W))
			yDir = -mspeed;
		
		if(input.isKeyDown(Input.KEY_D))
			xDir = mspeed;
		else if(input.isKeyDown(Input.KEY_A))
			xDir = -mspeed;
		move(xDir, yDir);
		cameraRect.setX(viewPos.getX() - TW_RENDER - 1);
		cameraRect.setY(viewPos.getY() - TH_RENDER - 1);

		
		///Zoom code
		int mouseWheel = (int)Math.signum(Mouse.getDWheel());
		zoom += mouseWheel * 0.0625f;
		System.out.println(minZoom + " " + zoom + " " + maxZoom);
		
		if(zoom < minZoom) zoom = minZoom;
		if(zoom > maxZoom) zoom = maxZoom;
		
	}
	
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
