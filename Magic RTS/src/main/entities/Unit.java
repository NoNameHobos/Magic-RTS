package main.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.player.Player;

public abstract class Unit extends SelectableEntity {

	protected float health, health_max;
	protected float move_speed;
	protected float speed;
	protected float direction;
	protected float acc;
	protected float phys_def;
	protected float mag_def;
	
	protected Point des;
	
	public Unit(Player player, float x, float y, Image sprite) {
		super(player, new Point(x, y), sprite);
	}
	
	public void move(float spd, float angle) {
		float sin = (float)Math.sin(Math.toRadians(angle));
		float cos = (float)Math.cos(Math.toRadians(angle));
		pos.setX(pos.getX() + spd * cos);
		pos.setY(pos.getY() + spd * sin);
	}
	
	public void moveTo(Point target) {
		if(getDistanceTo(target) >= sprite.getWidth()/2) {
			direction = getPointDirection(target);
			if(speed < move_speed) speed += acc;
		} else speed = 0;
	}
	
	@Override
	public void render(Graphics g, float xOffset, float yOffset) {
		super.render(g, xOffset, yOffset);
		sprite.draw(pos.getX() - origin.getX() - xOffset, pos.getY() - origin.getY() - yOffset);
		draw(g, xOffset, yOffset);
	}

	public abstract void draw(Graphics g, float xOffset, float yOffset);
	
	public int getFacing() {
		float dir = Math.abs(360 - direction);
		if(dir > 45 && dir < 135) {
			return 1; //Up (For Now)
		} else if(dir <= 45 || (dir >= 305 && dir <= 360)) {
			return 2; //Right (For Now)
		} else if(dir >= 135 && dir <= 205) {
			return 3; //Left (For Now)
		} else {
			return 0; //Down (For Now)
		}
	}
	
	public float getHealth() {
		return health;
	}

	public void setHealth(float health, boolean rel) {
		if(!rel)
			this.health = health;
		else this.health += health;
	}
	public void setHealth(float health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed, boolean rel) {
		if(!rel)
			this.speed = speed;
		else this.speed += speed;
	}

	public float getAcc() {
		return acc;
	}

	public void setAcc(float acc, boolean rel) {
		if(!rel)
			this.acc = acc;
		else this.acc += acc;
	}

	public float getHealthMax() {
		return health_max;
	}
	public float getMoveSpeed() {
		return move_speed;
	}

	public Player getPlayer() {
		return player;
	}
	
	
}
