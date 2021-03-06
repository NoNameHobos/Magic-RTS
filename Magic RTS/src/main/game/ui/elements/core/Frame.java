package main.game.ui.elements.core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.ui.UI;
import main.game.ui.UIElement;
import main.graphics.res.Sprite;

public class Frame extends UIElement {

	private Sprite sprite;
	
	private int width;
	
	public Frame(UI ui, Point pos) {
		super(ui, pos, Engine.getWIDTH() ,64);
		
		sprite = player.getFaction().getSprite("ui_bottombar");
	}

	@Override
	public void draw(Graphics g) {

		int sW = sprite.getWidth();

		width = (int)Math.ceil((float)Engine.getWIDTH() / (float)sW);
		

		for (int x = 0; x < width; x++) {
			
				float xx = bounding.getX() + (x * sW);
				float yy = bounding.getY();
				
				// TODO: Refactor drawing for frame
				// Create a renderer?
				sprite.draw(xx, yy);
		}
	}

	@Override
	public void step() {

	}

}
