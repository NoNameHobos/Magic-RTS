package main.player;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public class Camera {

	private Point viewPos;
	private Rectangle viewRect;
	
	public Camera(float x, float y, float width, float height) {
		viewPos = new Point(x, y);
		viewRect = new Rectangle(x, y, width, height);
	}

	public Point getPos() {
		return viewPos;
	}
	
	public Rectangle getView() {
		return viewRect;
	}
	
}
