package main.game.entities;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.game.Game;
import main.game.GameObject;
import main.game.map.Map;
import main.game.player.Player;
import main.graphics.AnimObj;

public abstract class Entity extends GameObject {
	
	public final static ArrayList<Entity> ENTITIES = new ArrayList<Entity>();

	protected Player player;
	protected Game game;
	protected Map map;

	protected Image currentSprite;
	protected AnimObj animations[];
	
	// Rendering depth
	// TODO: Implement this
	protected int depth;
	
	// TODO: Make a function for this
	protected boolean selectable = false;
	
	// Keep track of when to remove from entities list
	protected boolean _dispose = false;
	
	protected SpriteSheet ss;
	protected Rectangle boundingbox;

	protected Point pos; // A Point as defined *IN THE GAME* NOT THE UI! use Game.pGameToUI() for its UI
	// Counterpart
	protected Point origin; //Origin of the sprite
	
	public abstract void draw(Graphics g);

	public Entity(Player p, Map map, Point pos, Image sprite) {
		this.currentSprite = sprite;
		this.pos = pos;
		this.map = map;
		game = map.getGame();
		this.player = p;

		//Set origin to centre of image
		origin = new Point(sprite.getWidth() / 2, sprite.getHeight() / 2); 
		
		//Create a collider based off "off-screen" coordinates
		boundingbox = new Rectangle(pos.getX() - origin.getX(), pos.getY() -
				origin.getY(), sprite.getWidth(), sprite.getHeight());
		
		ENTITIES.add(this);
	}

	public float getPointDirection(Point target) {
		float deltaX = target.getX() - pos.getX();
		float deltaY = target.getY() - pos.getY();
		float disTo = getDistanceTo(target);
		float angle = (float) Math.toDegrees(Math.acos(deltaX / disTo));
		if (deltaY > 0) {
			return angle;
		} else
			return 360 - angle;
	}

	public float getDistanceTo(Point target) {
		float x1 = pos.getX();
		float y1 = pos.getY();

		float x2 = target.getX();
		float y2 = target.getY();

		float dX = (float)Math.pow(x1 - x2, 2);
		float dY = (float)Math.pow(y1 - y2, 2);
		
		return (float)Math.pow(dX + dY, 0.5);
	}

	public void tick() {
		float x = pos.getX() - origin.getX();
		float y = pos.getY() - origin.getY();
		boundingbox.setX(x);
		boundingbox.setY(y);
		
		depth = -(int)Math.round(pos.getY() + currentSprite.getHeight() * .8);
		step();
	}

	// Entity 
	public void render(Graphics g) {
		draw(g);
	}
		
	// Getters and Setters
	public Image getSprite() {
		return currentSprite;
	}

	public static ArrayList<Entity> getEntities() {
		return ENTITIES;
	}

	public Rectangle getCollider() {
		return boundingbox;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public Point getPos() {
		return pos;
	}
	public Player getPlayer() {
		return player;
	}

	public int getDepth() {
		return depth;
	}
	
	public Map getMap() {
		return map;
	}
	
	public Game getGame() {
		return game;
	}
	
	// Disposal handling (Mainly for particle system)
	public boolean toDispose() {
		return _dispose;
	}
	
	public void dispose() {
		_dispose = true;
	}
}
