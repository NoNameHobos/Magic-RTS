package main.entities;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity {

	protected Point pos;
	protected Point origin; //Set an origin for sprite relative to position
	
	protected Image sprite;
	protected SpriteSheet ss;

	protected int[] alarm = new int[10];
	
	public final static ArrayList<Entity> ENTITIES = new ArrayList<Entity>();
	
	protected Rectangle collider;
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public Entity(Point _pos, Image sprite) {
		this.pos = _pos;
		this.sprite = sprite;
		ENTITIES.add(this);
		origin = new Point(sprite.getWidth() / 2, sprite.getHeight() / 2); //Set origin to centre of image
		
		collider = new Rectangle(pos.getX() - origin.getX(), pos.getY() - origin.getY(), sprite.getWidth(), sprite.getHeight());
		
		initAlarms();
	}

	
	public float getPointDirection(Point target) {
		float deltaX = target.getX() - pos.getX();
		float deltaY = target.getY() - pos.getY();
		float disTo = getDistanceTo(target);
		float angle = (float) Math.toDegrees(Math.acos(deltaX/disTo));
		if(deltaY > 0) {
			return angle;
		} else return 360 - angle;
	}
	
	public float getDistanceTo(Point target) {
		float delta2X = (float)Math.pow(target.getX() - pos.getX(), 2);
		float delta2Y = (float)Math.pow(target.getY() - pos.getY(), 2);
		return (float)Math.pow(delta2X + delta2Y, 0.5);
	}


	public void pollEntity() {
		updateAlarms();
		tick();
	}
	
	public void renderEntity(Graphics g) {
		g.setColor(Color.red);
		g.draw(collider);
		render(g);
	}
	
	protected void updateAlarms() {
		for(int i = 0; i < alarm.length; i++) {
			if(alarm[i] != -1) {
				alarm[i] -= 1;
			}
		}
	}
	
	private void initAlarms() {
		for(int i = 0; i < alarm.length; i++) {
			alarm[i] = -1;
		}
	}
	
	public Point getPos() {
		return pos;
	}
	
	public void setPos(Point pos) {
		this.pos = pos;
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public static ArrayList<Entity> getEntities() {
		return ENTITIES;
	}

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}
}
