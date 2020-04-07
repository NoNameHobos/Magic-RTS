package main.player;

import static main.util.ResourceLoader.TILE_HEIGHT;
import static main.util.ResourceLoader.TILE_WIDTH;

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
	
	private Map map;
	
	private UI ui;
	
	public Camera(Map map, Point pos, float width, float height) {
		if(pos.getX() < 0) pos.setX(0);
		if(pos.getY() < 0) pos.setY(0);
		viewPos = pos;
		cameraRect = new Rectangle(viewPos.getX(), viewPos.getY(), width + TILE_WIDTH + 1, height + TILE_HEIGHT + 1);
		viewRect = new Rectangle(viewPos.getX(), viewPos.getY(), width, height);
		
		this.map = map;
		
		this.input = Engine.getInput();
	}
	
	public void move(float xDir, float yDir) {
		float mapWidth = (map.getMapWidth() + 1) * TILE_WIDTH;
		float mapHeight = (map.getMapHeight() + 1) * TILE_HEIGHT;
		
		boolean safeLeft = (viewPos.getX() + xDir > 0);
		boolean safeRight = (viewPos.getX() + cameraRect.getWidth() + xDir <= mapWidth);
		boolean safeUp = (viewPos.getY() + yDir > 0);
		boolean safeDown = (viewPos.getY() + cameraRect.getHeight() + yDir <= mapHeight);
		
		if(safeLeft && safeRight)
			viewPos.setX(viewPos.getX() + xDir);
		else {
			if(!safeLeft)
				viewPos.setX(0);
			if(!safeRight)
				viewPos.setX(mapWidth - cameraRect.getWidth());
		}
		
		if(safeDown && safeUp)
			viewPos.setY(viewPos.getY() + yDir);
		else {
			if(!safeUp)
				viewPos.setY(0);
			if(!safeDown)
				viewPos.setY(mapHeight - cameraRect.getHeight());
		}
	}
	
	public void update() {
		if(ui != null)
			ui.tick();
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
		cameraRect.setX(viewPos.getX() - TILE_WIDTH - 1);
		cameraRect.setY(viewPos.getY() - TILE_HEIGHT - 1);
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
	
}
