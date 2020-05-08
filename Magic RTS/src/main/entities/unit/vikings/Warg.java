package main.entities.unit.vikings;

import org.newdawn.slick.Graphics;

import main.entities.Unit;
import main.game.player.Player;

import static main.GameConstants.STAT_ACC;
import static main.GameConstants.STAT_ATTACK;
import static main.GameConstants.STAT_ATTACK_RANGE;
import static main.GameConstants.STAT_HEALTH;
import static main.GameConstants.STAT_HEALTH_MAX;
import static main.GameConstants.STAT_MAG_DEF;
import static main.GameConstants.STAT_PHYS_DEF;
import static main.util.ResourceLoader.SPRITES;

public class Warg extends Unit {

	public Warg(Player player, float x, float y) {
		super(player, x, y, SPRITES.get("warg_right").copy());

		// Init Stats
		stats[STAT_HEALTH] = 80f;
		stats[STAT_HEALTH_MAX] = 80f;
		stats[STAT_ACC] = 1f;
		stats[STAT_PHYS_DEF] = 4f;
		stats[STAT_MAG_DEF] = 3f;
		
		max_speed = 3f;
		
		// Attack stuff
		stats[STAT_ATTACK] = 1;
		// Attack range in tiles
		stats[STAT_ATTACK_RANGE] = 1.5f;
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
