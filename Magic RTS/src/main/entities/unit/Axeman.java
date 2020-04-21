package main.entities.unit;

import static main.util.ResourceLoader.SPRITE_SHEETS;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import main.entities.Unit;
import main.game.Game;
import main.game.player.Player;

public class Axeman extends Unit {

	//TODO: Implement State machine
	
	private boolean walking = false;
	
	private static final SpriteSheet[] ANIM_SS = { 
			SPRITE_SHEETS.get("axeman_down"), //0
			SPRITE_SHEETS.get("axeman_up") //1
	};
	
	private static Animation up, down, left, right;
	
	public Axeman(Player player, float x, float y) {
		super(player, x, y, ANIM_SS[0].getSubImage(0, 0));
		
		//Init Stats
		health = 80;
		move_speed = 1f;
		direction = 120;
		acc = 0.5f;
		phys_def = 12;
		mag_def = 9;
		health_max = health;

		down = new Animation(ANIM_SS[0], (int)(67 * (move_speed + 0.5f)));
		down.setLooping(true);
		
		up = new Animation(ANIM_SS[1], (int)(67 * (move_speed + 0.5f)));
		up.setLooping(true);
	}

	@Override
	public void draw(Graphics g) {
		
		float drawX = pos.getX() - origin.getX();
		float drawY = pos.getY() - origin.getY();
		
		if (getFacing() == 0) {
			if(walking)
				g.drawAnimation(down, drawX, drawY);
			else
				g.drawImage(down.getImage(0), drawX, drawY);
		} else if (getFacing() == 1) {
			if(walking)
				g.drawAnimation(up, drawX, drawY);
			else
				g.drawImage(up.getImage(0), drawX, drawY);
		}
	}

	@Override
	public void step() {
		if(speed != 0)
			walking = true;
		else walking = false;
		if(Game.getCurrentView() == player.getCamera()) {
			move(speed, direction);
		}
		
	}
}
