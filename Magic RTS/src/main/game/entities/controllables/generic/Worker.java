package main.game.entities.controllables.generic;

import main.game.entities.controllables.Unit;
import main.game.entities.controllables.UnitStat;
import main.game.player.Player;

public class Worker extends Unit {
	
	public Worker(Player player, float x, float y) {
		super(player, x, y, RES.getSprite("worker_right").copy());

		// Init Stats
		stats[UnitStat.HP.val()] = 1f;
		stats[UnitStat.HP_MAX.val()] = 1f;
		stats[UnitStat.ACCELERATION.val()] = 0.1f;
		stats[UnitStat.PHYS_DEF.val()] = 0f;
		stats[UnitStat.MAG_DEF.val()] = 0f;
		
		max_speed = 5f;
		
		// Attack stuff
		stats[UnitStat.ATTACK.val()] = 1;
		// Attack range in tiles
		stats[UnitStat.ATTACK_RANGE.val()] = 1;
	}

	@Override
	public void step() {
		
	}

}
