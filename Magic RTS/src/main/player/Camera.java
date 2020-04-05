package main.player;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;

import static main.util.ResourceLoader.TILE_WIDTH;
import static main.util.ResourceLoader.TILE_HEIGHT;

public class Camera {

	private Point viewPos;
	private Rectangle viewRect;

	private Input input;
	
	public Camera(float x, float y, float width, float height) {
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		viewPos = new Point(x, y);
		viewRect = new Rectangle(x, y, width + TILE_WIDTH + 1, height + TILE_HEIGHT + 1);
		
		this.input = Engine.getInput();
	}

	public Point getPos() {
		return viewPos;
	}
	
	public Rectangle getView() {
		return viewRect;
	}
	
	public void move(float xDir, float yDir) {
		viewPos.setX(viewPos.getX() + xDir);
		viewPos.setY(viewPos.getY() + yDir);
	}
	
	public void update() {
		int xDir = 0;
		int yDir = 0;
		if(input.isKeyDown(input.KEY_S))
			yDir = 1;
		else if(input.isKeyDown(input.KEY_W))
			yDir = -1;
		
		if(input.isKeyDown(input.KEY_D))
			xDir = 1;
		else if(input.isKeyDown(input.KEY_A))
			xDir = -1;
		move(xDir, yDir);
		viewRect.setX(viewPos.getX() - TILE_WIDTH - 1);
		viewRect.setY(viewPos.getY() - TILE_HEIGHT - 1);
	}
	
}
