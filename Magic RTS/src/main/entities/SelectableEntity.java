package main.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.game.map.Map;
import main.player.Player;

public abstract class SelectableEntity extends Entity {

	protected int width, height;
	protected Map map;
	protected Player player;
	
	public SelectableEntity(Player player, Point pos, Image sprite) {
		super(pos, sprite);
		this.player = player;
		map = player.getMap();
		width = sprite.getWidth();
		height = sprite.getHeight();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean mouseOver() {
		Point mousePos = ENGINE.getMouse().getPos();
		return collider.contains(mousePos.getX(), mousePos.getY());
	}
	
	public void render(Graphics g, float xOffset, float yOffset) {
		super.render(g, xOffset, yOffset);
		if(map.getControlledPlayer() == player) {
			if(!mouseOver()) g.setColor(Color.red);
			else g.setColor(Color.blue);
			g.draw(collider);
		}
	}
}
