package main.game.entities.resources;

import org.newdawn.slick.geom.Point;

import main.game.Renderable;
import main.game.map.Map;
import main.graphics.res.Sprite;

public abstract class Resource extends Renderable {

	public Resource(Map map, Point pos, Sprite anims) {
		super(pos);
	}
}
