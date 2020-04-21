package main.game.ui.elements.core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.entities.Entity;
import main.game.player.Player;
import main.game.ui.UI;
import main.game.ui.UIElement;

public class UnitInfo extends UIElement {

	private Player player;
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;
	
	public UnitInfo(UI ui, Point pos) {
		super(ui, pos, WIDTH, HEIGHT);
		this.player = ui.getPlayer();
	}
	@Override
	public void draw(Graphics g) {
		boolean hasSelected = player.getSelected().size() > 0;
		
		float marginX = 24;
		float marginY = 24;
		
		if(hasSelected) {
			for(int i = 0; i < player.getSelected().size(); i++) {
				Entity e = player.getSelected().get(i);
				
				float x = bounding.getX() + (e.getSprite().getWidth() + marginX) * i;
				float y = bounding.getY() + marginY;
				
				g.drawImage(e.getSprite(), x, y);
			}
		}
	}
	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	
	
}
