package main.game.ui.elements;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.ui.UI;
import main.game.ui.UIElement;
import main.util.ResourceLoader;

public class Frame extends UIElement {

	private Image sprite;
	
	private int width;
	
	public Frame(UI ui, Point pos) {
		super(ui, pos);
		
		sprite = ResourceLoader.UI.get("UIBottomBar");
	}

	@Override
	public void draw(Graphics g) {

		int sW = sprite.getWidth();

		width = Engine.getWIDTH() / sW;
		

		for (int x = 0; x < width; x++) {
			
				float xx = pos.getX() + (x * sW);
				float yy = pos.getY();
				g.drawImage(sprite, xx, yy);
		}
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

}
