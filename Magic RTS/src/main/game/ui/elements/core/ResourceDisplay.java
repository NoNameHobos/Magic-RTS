package main.game.ui.elements.core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.game.ui.UI;
import main.game.ui.UIElement;
import main.game.ui.elements.Bar;
import main.util.ResourceLoader;

public class ResourceDisplay extends UIElement {

	public final static int WIDTH = 110;
	public final static int HEIGHT = 40;
	
	private Bar[] bars;
	
	
	public ResourceDisplay(UI ui, Point pos) {
		super(ui, pos, WIDTH, HEIGHT);
		bars = new Bar[3];
		
		//Load resource bars
		Image[] sprites = {
			ResourceLoader.UI.get("UIManaBar"),	
			ResourceLoader.UI.get("UIStoneBar"),
			ResourceLoader.UI.get("UIMithrilBar")
		};
		for(int i = 0; i < bars.length; i++) {
			bars[i] = new Bar(ui, new Point(10, 10 + i * 10), sprites[i], 100);
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(new Color(255, 255, 255, ui.getAlpha()));
		g.fill(bounding);
		for(Bar bar : bars) {
			bar.render(g);
		}
	}

	@Override
	public void step() {
		for(Bar bar : bars) {
			bar.tick();
		}
	}

}
