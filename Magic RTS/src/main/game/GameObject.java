package main.game;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import main.game.entities.Entity;

public abstract class GameObject {

	public static final int ALARM_COUNT = 10;
	public static final ArrayList<GameObject> OBJECTS = new ArrayList<GameObject>();
	
	protected Alarm alarm[] = new Alarm[ALARM_COUNT];
	
	protected abstract void step();
	
	public GameObject() {
		for(int i = 0; i < ALARM_COUNT - 1; i++){
			alarm[i] = new Alarm(null);
		}
		OBJECTS.add(this);
	}
	
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
				
				if(entity.getPos().getX() < 0 || entity.getPos().getY() < 0) {
					entity.dispose();
					continue;
				}
			}
		}
	}
	
	public static void renderAll(Graphics g) {
		for(GameObject object : OBJECTS) {
			if(object instanceof Entity)
				((Entity)object).draw(g);
		}
	}
	
}


interface AlarmEvent {
	void event();
}

class Alarm {
	
	private AlarmEvent alarm;
	private int timer;
	
	public Alarm(AlarmEvent alarm) {
		this.alarm = alarm;
		timer = -1;
	}
	
	public void setEvent(AlarmEvent alarm) {
		this.alarm = alarm;
	}
	
	public void set(int time) {
		timer = time;
	}
	
	public void update() {
		if(timer > 0)
			timer -= 1;
		else {
			alarm.event();
			timer = -1;
		}
	}
}
