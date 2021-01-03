package main.game;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import main.game.entities.Entity;

public abstract class GameObject {

	public static final int ALARM_COUNT = 9;
	public static final ArrayList<GameObject> OBJECTS = new ArrayList<GameObject>();
	
	protected AlarmObject alarm[] = new AlarmObject[ALARM_COUNT];
	
	protected abstract void step();
	
	public GameObject() {
		for(int i = 0; i < ALARM_COUNT; i++){
			alarm[i] = new AlarmObject(null);
		}
		OBJECTS.add(this);
	}
	
	private void update() {
		for(AlarmObject a : alarm) {
			a.update();
		}
		
		step();
	}
	
	
	public static void updateAll() {
		for(GameObject object : OBJECTS) {
			object.update();
		}
	}
	
	public static void renderAll(Graphics g) {
		for(GameObject object : OBJECTS) {
			if(object instanceof Entity)
				((Entity)object).render(g);
		}
	}
	
}


interface Alarm {
	void event();
}

class AlarmObject {
	
	private Alarm alarm;
	private int timer;
	
	public AlarmObject(Alarm alarm) {
		this.alarm = alarm;
		timer = -1;
	}
	
	public void setEvent(Alarm alarm) {
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
