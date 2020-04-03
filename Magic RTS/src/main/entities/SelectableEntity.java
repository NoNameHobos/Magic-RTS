package main.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

public abstract class SelectableEntity extends Entity {

	protected int width, height;
	
	public SelectableEntity(Point pos, Image sprite) {
		super(pos, sprite);
		
		width = sprite.getWidth();
		height = sprite.getHeight();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
