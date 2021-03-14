package main.game;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.game.alarm.Alarm;
import main.game.entities.Entity;

public abstract class GameObject {

	public static final int ALARM_COUNT = 10;

	public static final ArrayList<GameObject> OBJECTS = new ArrayList<GameObject>();
	
	protected Alarm alarm[] = new Alarm[ALARM_COUNT];

	// Keep track of when to remove from entities list
	protected boolean _dispose = false;
	protected Point pos;
	protected Rectangle boundingbox;
	
	public GameObject(Point pos) {
		this.pos = pos;
		
		for(int i = 0; i < ALARM_COUNT - 1; i++){
			alarm[i] = new Alarm(null);
		}
		OBJECTS.add(this);
	}

	protected abstract void step();
	
	private void update() {
		for(Alarm a : alarm) {
			a.update();
		}
		
		step();
	}

	public static void updateAll() {
		for(GameObject object : OBJECTS) {
			object.update();
			
			// Check if object is an entity, if so - check if it needs to be removed
			if(object instanceof Entity) {
				Entity entity = (Entity)object;
				if(object.getPos().getX() < 0 || entity.getPos().getY() < 0) {
					object.dispose();
					continue;
				}
			}
		}
	}
	
	public static void renderAll(Graphics g) {
		for(GameObject object : OBJECTS) {
			if(object instanceof Renderable)
				((Renderable)object).render(g);
		}
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
	
	// Disposal handling (Mainly for particle system)
	public boolean toDispose() {
		return _dispose;
	}
	
	public void dispose() {
		_dispose = true;
	}

	public Point getPos() {
		return pos;
	}
}
