package main.player;

import static main.util.ResourceLoader.TILE_HEIGHT;
import static main.util.ResourceLoader.TILE_WIDTH;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.map.Map;

public class Camera {

	private Point viewPos;
	private Rectangle viewRect;

	private Input input;
	
	private Map map;
	
	public Camera(Map map, float x, float y, float width, float height) {
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		viewPos = new Point(x, y);
		viewRect = new Rectangle(x, y, width + TILE_WIDTH + 1, height + TILE_HEIGHT + 1);
		
		this.map = map;
		
		this.input = Engine.getInput();
	}

	public Point getPos() {
		return viewPos;
	}
	
	public Rectangle getView() {
		return viewRect;
	}
	
	public void move(float xDir, float yDir) {
		float mapWidth = map.getMapWidth() * TILE_WIDTH;
		float mapHeight = map.getMapHeight() * TILE_HEIGHT;
		
		boolean safeLeft = (viewPos.getX() >= 0);
		boolean safeRight = (viewPos.getX() + viewRect.getWidth() <= mapWidth);
		boolean safeUp = (viewPos.getY() >= 0);
		boolean safeDown = (viewPos.getY() + viewRect.getHeight() <= mapHeight);
		
		if(safeLeft && safeRight)
			viewPos.setX(viewPos.getX() + xDir);
		else {
			if(!safeLeft)
				viewPos.setX(0);
			if(!safeRight)
				viewPos.setX(mapWidth - viewRect.getWidth());
		}
		
		if(safeDown && safeUp)
			viewPos.setY(viewPos.getY() + yDir);
		else {
			if(!safeUp)
				viewPos.setY(0);
			if(!safeDown)
				viewPos.setY(mapHeight - viewRect.getHeight());
		}
	}
	
	public void update() {
		int xDir = 0;
		int yDir = 0;
		if(input.isKeyDown(Input.KEY_S))
			yDir = 1;
		else if(input.isKeyDown(Input.KEY_W))
			yDir = -1;
		
		if(input.isKeyDown(Input.KEY_D))
			xDir = 1;
		else if(input.isKeyDown(Input.KEY_A))
			xDir = -1;
		move(xDir, yDir);
		viewRect.setX(viewPos.getX() - TILE_WIDTH - 1);
		viewRect.setY(viewPos.getY() - TILE_HEIGHT - 1);
	}
	
}
