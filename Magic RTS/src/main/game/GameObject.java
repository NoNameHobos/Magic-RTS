package main.game;

import java.util.ArrayList;
import java.util.Comparator;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.game.alarm.Alarm;

public abstract class GameObject {

	public static final int ALARM_COUNT = 10;

	private static final ArrayList<GameObject> OBJECTS = new ArrayList<GameObject>();
	
	protected Alarm alarm[] = new Alarm[ALARM_COUNT];

	// Keep track of when to remove from entities list
	protected boolean _dispose = false;
	protected Point mapPos;
	protected int depth;
	protected Rectangle collider;
	
	public GameObject(Point pos) {
		this.mapPos = pos;
		
		for(int i = 0; i < ALARM_COUNT - 1; i++){
			alarm[i] = new Alarm(null);
		}
		
		OBJECTS.add(this);
	}

	/**
	 * Step event. Gets called once per tick after object is updated
	 */
	protected abstract void step();
	
	/**
	 * Update the game object and alarms
	 */
	private void update() {
		//for(Alarm a : alarm) {
		//	a.update();
		//}
		depth = -(int)Math.round(mapPos.getY());
		step();
	}

	/**
	 * Update all objects and dispose entities outside of the map
	 */
	public static void updateAll() {
		for(GameObject object : OBJECTS) {
			if(object instanceof Entity) {
				if(object.getPos().getX() < 0 || object.getPos().getY() < 0) {
					object.dispose();
					continue;
				}
			}
			
			object.update();
		}
	}

	
	public float getPointDirection(Point target) {
		float deltaX = target.getX() - mapPos.getX();
		float deltaY = target.getY() - mapPos.getY();
		float disTo = getDistanceTo(target);
		float angle = (float) Math.toDegrees(Math.acos(deltaX / disTo));
		if (deltaY > 0) {
			return angle;
		} else
			return 360 - angle;
	}

	public float getDistanceTo(Point target) {
		float x1 = mapPos.getX();
		float y1 = mapPos.getY();

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
		return mapPos;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public Rectangle getCollider() {
		return collider;
	}
	
	public static ArrayList<GameObject> getObjects() {
		return OBJECTS;
	}

	//Comparators
	private class SortByDepth implements Comparator<GameObject> {

		@Override
		public int compare(GameObject obj1, GameObject obj2) {
			return (obj2.getDepth() - obj1.getDepth());
		}
		
	}
}

