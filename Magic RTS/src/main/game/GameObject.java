package main.game;

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

public abstract class GameObject {

	public static final int ALARM_COUNT = 9;
	
	protected AlarmObject alarm[] = new AlarmObject[ALARM_COUNT];
	
	public GameObject() {
		for(int i = 0; i < ALARM_COUNT; i++){
			alarm[i] = new AlarmObject(null);
		}
	}
	
	public void tick() {
		for(AlarmObject a : alarm) {
			a.update();
		}
	}
	
}
