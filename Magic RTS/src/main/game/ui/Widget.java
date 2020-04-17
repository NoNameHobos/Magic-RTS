package main.game.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

public class Widget extends UIElement {
	
	protected UIElement parent;
	
	public Widget(UI ui, Point pos, Image spr, UIElement par) {
		super(ui, pos, spr);
		parent = par;
	
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	public UIElement getParent() {
		return parent;
	
	}

}
