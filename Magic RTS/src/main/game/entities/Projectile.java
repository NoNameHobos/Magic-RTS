package main.game.entities;

import org.newdawn.slick.geom.Point;

import main.game.map.Map;
import main.game.player.Player;
import main.graphics.AnimSet;

public abstract class Projectile extends Entity {
	
	protected float speed, direction;
	
	protected Point target;

	public Projectile(Player p, Map map, Point pos, AnimSet set, Point target) {
		super(p, map, pos, set);
		this.target = target;
	}
	
}
