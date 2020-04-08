package main.game.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public abstract class UIElement {

	protected UI ui;
	protected Point pos;
	
	public UIElement(UI ui, Point pos) {
		this.ui = ui;
		this.pos = pos;
		
		ui.getElements().add(this);
	}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public UI getUI() {
		return ui;
	}
	
	public Point getPos() {
		return pos;
	}
	
	public void setPos(Point pos) {
		this.pos = pos;
	}
}
