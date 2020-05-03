package main.entities.projectiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.entities.Entity;
import main.game.map.Map;
import main.game.player.Player;

public abstract class Projectile extends Entity {
	
	protected float speed;
	
	protected Point target;

	public Projectile(Player p, Map map, Point pos, Image sprite, Point target) {
		super(p, map, pos, sprite);
		this.target = target;
	}
	
	public void tick() {
		super.tick();;
	}

}
