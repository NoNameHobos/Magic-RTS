package main.entities.unit;

import org.newdawn.slick.Graphics;

import main.game.player.Player;
import main.util.ResourceLoader;

public class Worker extends Unit {
	
	public Worker(Player player, float x, float y) {
		super(player, x, y, ResourceLoader.SPRITES.get("worker_right"));
		// TODO Auto-generated constructor stub

		//Init Stats
		health = 80;
		move_speed = 3f;
		direction = 120;
		acc = 0.5f;
		phys_def = 12;
		mag_def = 9;
		health_max = health;
	}

	@Override
	public void draw(Graphics g) {

		float drawX = pos.getX() - origin.getX();
		float drawY = pos.getY() - origin.getY();

		g.drawImage(sprite, drawX, drawY);

	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
	}

}
