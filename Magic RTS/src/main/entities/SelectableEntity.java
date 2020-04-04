package main.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
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
	
	public boolean mouseOver() {
		Point mousePos = ENGINE.getMouse().getPos();
		return collider.contains(mousePos.getX(), mousePos.getY());
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		if(!mouseOver()) g.setColor(Color.red);
		else g.setColor(Color.blue);
		g.draw(collider);
	}
}
