package main.game.entities;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.game.Game;
import main.game.GameObject;

public abstract class Entity extends GameObject {
	protected Game game;
	
	protected boolean selectable = false,
					  selected = false;
	
	protected Rectangle boundingBox;

	// A Point as defined *IN THE GAME* NOT THE UI! use Game.pGameToUI()
	// for its UI counterpart
	protected Point mapPos; 
	
	public Entity(Point pos) {
		super(pos);
		this.mapPos = pos;
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