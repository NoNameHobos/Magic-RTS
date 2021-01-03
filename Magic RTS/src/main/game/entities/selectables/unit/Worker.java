package main.game.entities.selectables.unit;

import static main.GameConstants.STAT_ACC;
import static main.GameConstants.STAT_ATTACK;
import static main.GameConstants.STAT_ATTACK_RANGE;
import static main.GameConstants.STAT_HEALTH;
import static main.GameConstants.STAT_HEALTH_MAX;
import static main.GameConstants.STAT_MAG_DEF;
import static main.GameConstants.STAT_PHYS_DEF;

import org.newdawn.slick.Graphics;

import main.game.entities.selectables.Unit;
import main.game.player.Player;
import main.util.ResourceLoader;

public class Worker extends Unit {
	
	public Worker(Player player, float x, float y) {
		super(player, x, y, ResourceLoader.SPRITES.get("worker_right").copy());

		// Init Stats
		stats[STAT_HEALTH] = 1f;
		stats[STAT_HEALTH_MAX] = 1f;
		stats[STAT_ACC] = 0.1f;
		stats[STAT_PHYS_DEF] = 0f;
		stats[STAT_MAG_DEF] = 0f;
		
		max_speed = 5f;
		
		// Attack stuff
		stats[STAT_ATTACK] = 1;
		// Attack range in tiles
		stats[STAT_ATTACK_RANGE] = 1;
	}

	@Override
	public void draw(Graphics g) {

		float drawX = pos.getX() - origin.getX();
		float drawY = pos.getY() - origin.getY();

		g.drawImage(currentSprite, drawX, drawY);

	}

	@Override
	public void step() {
		
	}

}
