package main.game.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.game.map.Map;
import main.game.player.Player;
import main.util.ResourceLoader;

public class Minimap extends UIElement {

	private Map map;
	
	private Player player;
	
	private Rectangle rectMinimap;
	
	public Minimap(UI ui, Point pos) {
		super(ui, pos, ResourceLoader.missing);
		this.player = ui.getPlayer();
		this.map = player.getMap();

		float width = 300;
		float height = 300;
		rectMinimap = new Rectangle(pos.getX(), pos.getY(), width, height);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.darkGray);
		g.fill(rectMinimap);
		g.setColor(Color.white);
		g.drawString(player.getName(), rectMinimap.getX(), rectMinimap.getY()+10);
	}
	
	@Override
	public void tick() {
		
	}
	
	
	// Getters and Setters
	public Map getMap() {
		return map;
	}


}
