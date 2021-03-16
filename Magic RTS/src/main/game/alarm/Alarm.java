package main.game.alarm;

public class Alarm {
	
	private AlarmEvent alarm;
	private int timer;
	
	public Alarm(AlarmEvent alarm) {
		this.alarm = alarm;
		timer = -1;
	}
	
	public void setEvent(AlarmEvent alarm, int timer) {
		this.alarm = alarm;
		this.set(timer);
	}
	
	public void set(int time) {
		timer = time;
	}
	
	public void update() {
		if(timer > 0)
			timer -= 1;
		else {
			if(this.alarm != null) {
				alarm.event();
				timer = -1;
			}
		}
	}
}
