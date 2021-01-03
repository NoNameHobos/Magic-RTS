package main.game.entities.resources;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.game.entities.SelectableEntity;
import main.game.map.Map;

public abstract class Resource extends SelectableEntity {

	public Resource(Map map, Point pos, Image sprite) {
		super(map.getNeutralPlayer(), pos, sprite);
	}
	
	public void draw(Graphics g) {
		currentSprite.draw(pos.getX() - origin.getX(), pos.getY() - origin.getY());
	}
}
