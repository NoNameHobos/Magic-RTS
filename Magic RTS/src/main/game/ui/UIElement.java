package main.game.ui;

import org.newdawn.slick.Graphics;

public abstract class UIElement {

	protected UI ui;
	
	public UIElement(UI ui) {
		this.ui = ui;
		ui.getElements().add(this);
	}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public UI getUI() {
		return ui;
	}
}
