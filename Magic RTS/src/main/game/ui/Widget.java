package main.game.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public class Widget extends UIElement {
	
	protected UIElement parent;
	
	public Widget(UI ui, Point pos, UIElement par) {
		super(ui, pos);
		parent = par;
	
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	public UIElement getParent() {
		return parent;
	
	}

}
