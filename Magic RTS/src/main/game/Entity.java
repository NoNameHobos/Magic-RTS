package main.game;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity extends GameObject {
	protected Game game;
	
	protected boolean selectable = false,
					  selected = false;
	
	protected Rectangle boundingBox;
	
	public Entity(Point pos) {
		super(pos);
	}

	public Rectangle getCollider() {
		return boundingBox;
	}
	
	public Game getGame() {
		return game;
	}
	
	public Point getMapPos() {
		return mapPos;
	}
}