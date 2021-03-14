package main.game.entities;

import org.newdawn.slick.geom.Point;

import main.game.Renderable;
import main.game.map.Map;
import main.game.player.Player;
import main.graphics.res.Sprite;

public abstract class Projectile extends Renderable {
	
	// TODO: Consider vector2D
	protected float speed, direction;
	protected Point target;

	public Projectile(Player p, Map map, Point pos, Sprite sprite, Point target) {
		super(pos);
		this.target = target;
	}
	
}
