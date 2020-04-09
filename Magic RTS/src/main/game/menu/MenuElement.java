package main.game.menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.game.states.MenuState;
import static main.engine.Engine.ENGINE;
import main.input.Mouse;

public abstract class MenuElement {

	protected MenuState menuState;
	protected Rectangle collider;
	
	protected Point pos;
	
	public MenuElement(MenuState menuState, Point pos, Point size) {
		this.menuState = menuState;
		this.pos = pos;
		
		collider = new Rectangle(pos.getX(), pos.getY(), size.getX(), size.getY());
		
		menuState.getElements().add(this);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public boolean mouseOver() {
		Mouse m = ENGINE.getMouse();
		return collider.contains(m.getPos());
	}
	
	public Rectangle getCollider() {
		return collider;
	}
	
	public Point getPos() {
		return pos;
	}
	
}
