package main.game.menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.Renderable;
import main.input.Mouse;

public abstract class MenuElement extends Renderable {

	protected Menu menu;
	protected Rectangle collider;
	
	public MenuElement(Menu menu, String label, Point pos, Point size) {
		super(pos);
		this.menu = menu;
		collider = new Rectangle(pos.getX(), pos.getY(), size.getX(), size.getY());
	}
	
	public abstract void render(Graphics g);
	
	public boolean mouseOver() {
		Mouse m = Engine.getMouse();
		return collider.contains(m.getPos());
	}
	
	public Rectangle getCollider() {
		return collider;
	}
	
	public Point getPos() {
		return mapPos;
	}
	
}