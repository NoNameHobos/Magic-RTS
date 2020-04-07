package main.game.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import main.game.map.Map;
import main.player.Camera;
import main.player.Player;

public class Minimap extends UIElement {

	private Map map;
	
	private Player player;
	
	private Camera camera;
	
	private Rectangle rectMinimap;
	
	public Minimap(UI ui) {
		super(ui);
		this.player = ui.getPlayer();
		this.map = player.getMap();
		
		this.camera = player.getCamera();

		float width = 300;
		float height = 300;
		float x = camera.getPos().getX();
		float y = camera.getCamView().getHeight() - height -1;
		rectMinimap = new Rectangle(x, y, width, height);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fill(rectMinimap);
		g.setColor(Color.white);
		g.drawString(player.getName(), rectMinimap.getX(), rectMinimap.getY()+10);
	}

	@Override
	public void tick() {
		
	}
	
	public Map getMap() {
		return map;
	}

}
