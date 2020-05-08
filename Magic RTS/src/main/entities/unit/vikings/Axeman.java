package main.entities.unit.vikings;

import static main.GameConstants.STAT_ACC;
import static main.GameConstants.STAT_ATTACK;
import static main.GameConstants.STAT_ATTACK_RANGE;
import static main.GameConstants.STAT_HEALTH;
import static main.GameConstants.STAT_HEALTH_MAX;
import static main.GameConstants.STAT_MAG_DEF;
import static main.GameConstants.STAT_PHYS_DEF;
import static main.util.ResourceLoader.SPRITE_SHEETS;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import main.entities.Unit;
import main.game.player.Player;

public class Axeman extends Unit {

	//TODO: Implement State machine
	
	private boolean walking = false;
	
	private static final SpriteSheet[] ANIM_SS = { 
			SPRITE_SHEETS.get("axeman_down"), //0
			SPRITE_SHEETS.get("axeman_up"), //1
			SPRITE_SHEETS.get("axeman_right"), //2
			SPRITE_SHEETS.get("axeman_left") //3
	};
	
	private static Animation[] animations;
	
	public Axeman(Player player, float x, float y) {
		super(player, x, y, ANIM_SS[0].getSubImage(0, 0).copy());
		
		animations = new Animation[4];
		
		// Init Stats
		stats[STAT_HEALTH] = 1f;
		stats[STAT_HEALTH_MAX] = 1f;
		stats[STAT_ACC] = 0.0001f;
		stats[STAT_PHYS_DEF] = 0f;
		stats[STAT_MAG_DEF] = 0f;
		
		
		// Attack stuff
		stats[STAT_ATTACK] = 1;
		// Attack range in tiles
		stats[STAT_ATTACK_RANGE] = 1;

		animations[0] = new Animation(ANIM_SS[0], (int)(67 * (max_speed + 0.5f)));
		animations[0].setLooping(true);
		
		animations[1] = new Animation(ANIM_SS[1], (int)(67 * (max_speed + 0.5f)));
		animations[1].setLooping(true);
		
		animations[2] = new Animation(ANIM_SS[2], (int)(67 * (max_speed + 0.5f)));
		animations[2].setLooping(true);
		
		animations[3] = new Animation(ANIM_SS[3], (int)(67 * (max_speed + 0.5f)));
		animations[3].setLooping(true);
	}

	@Override
	public void draw(Graphics g) {
		
		float drawX = pos.getX() - origin.getX();
		float drawY = pos.getY() - origin.getY();
		
		if(walking) {
			g.drawAnimation(animations[getFacing()], drawX, drawY);
		} else g.drawImage(animations[getFacing()].getImage(0), drawX, drawY);
	}

	@Override
	public void step() {
		if(speed != 0)
			walking = true;
		else walking = false;
		
	}
}
