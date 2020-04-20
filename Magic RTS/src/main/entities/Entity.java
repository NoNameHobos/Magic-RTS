package main.entities;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.game.map.Map;
import main.game.player.Player;

public abstract class Entity {

	protected String type = "Entity";
	protected Player player;

	protected Image sprite;
	
	protected int depth;
	
	protected SpriteSheet ss;

	protected Point pos; // A Point as defined *IN THE GAME* NOT THE UI! use Game.pGameToUI() for its UI
							// Counterpart
	protected Point origin; //Origin of the sprite
	
	protected boolean selectable = false;

	protected int[] alarm = new int[10];

	public final static ArrayList<Entity> ENTITIES = new ArrayList<Entity>();

	protected Rectangle collider;

	protected Map map;
	
	public Entity(Player p, Map map, Point pos, Image sprite) {
		this.sprite = sprite;
		this.pos = pos;
		this.map = map;
		this.player = p;

		//Set origin to centre of image
		origin = new Point(sprite.getWidth() / 2, sprite.getHeight() / 2); 
		
		//Create a collider based off "off-screen" coordinates
		collider = new Rectangle(pos.getX() - origin.getX(), pos.getY() -
				origin.getY(), sprite.getWidth(), sprite.getHeight());
		
		//Initialize the alarms and finally add this to the list of entities
		initAlarms();
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
		
		Point p = new Point(x, y);
		collider.setX(p.getX());
		collider.setY(p.getY());
		
		//Tick down the alarms once per tick
		for (int i = 0; i < alarm.length; i++) {
			if (alarm[i] != -1) {
				alarm[i] -= 1;
			}
		}
		
		depth = -(int)Math.round(pos.getY());
	}

	public void render(Graphics g) {
	}

	private void initAlarms() {
		for (int i = 0; i < alarm.length; i++) {
			alarm[i] = -1;
		}
	}
	
	
	// Getters and Setters
	public Image getSprite() {
		return sprite;
	}

	public static ArrayList<Entity> getEntities() {
		return ENTITIES;
	}

	public Rectangle getCollider() {
		return collider;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public String getType() {
		return type;
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
}
