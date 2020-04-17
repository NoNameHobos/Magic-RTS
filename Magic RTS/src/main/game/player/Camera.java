package main.game.player;

import static main.GameConstants.TH_RENDER;
import static main.GameConstants.TW_RENDER;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.map.Map;
import main.game.ui.UI;

public class Camera {
	
	private Map map;
	
	private Rectangle renderRect, viewRect;
	
	private int tile_buffer = 2;
	
	private float minZoom, zoom, maxZoom;
	
	private UI ui;
	
	public Camera(Map map, Point pos, float width, float height) {
		viewRect = new Rectangle(pos.getX(), pos.getY(), width, height);
		
		// Create the rendering rectangle based off the view rectangle plus a tile buffer
		renderRect = new Rectangle(
				viewRect.getX() - tile_buffer / 2 * TW_RENDER,
				viewRect.getY() - tile_buffer / 2 * TH_RENDER,
				viewRect.getWidth() + tile_buffer * TW_RENDER,
				viewRect.getHeight() + tile_buffer * TH_RENDER
		);
	}
	
	public void update() {
		//Update UI
		if(ui != null)
			ui.tick();
		
		//Movement Code
		Point dir = pollInput();
		
		move(dir.getX(), dir.getY());
		

		renderRect.setX(viewRect.getX() - tile_buffer / 2 * TW_RENDER);
		renderRect.setY(viewRect.getY() - tile_buffer / 2 * TH_RENDER);
	}
	
	//Poll movement code
	public Point pollInput() {
		int dir = 0;
		
		float xDir = 0, yDir = 0;
		
		float mspeed = 10;
		
		Input input = Engine.getInput();

		if(input.isKeyDown(Input.KEY_D))
			dir |= 1;
		if(input.isKeyDown(Input.KEY_A))
			dir |= 2;
		if(input.isKeyDown(Input.KEY_W))
			dir |= 4;
		if(input.isKeyDown(Input.KEY_S))
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
	
	//Move Code (include boundaries)
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
	
	public Point getPos() {
		return new Point(viewRect.getX(), viewRect.getY());
	}
	
	public float getZoom() {
		return 1f;
	}

	public UI getUI() {
		return ui;
	}

	public void setUI(UI ui) {
		this.ui = ui;
	}
}