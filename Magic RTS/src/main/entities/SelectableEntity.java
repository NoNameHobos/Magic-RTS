package main.entities;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.map.Map;
import main.input.Mouse;
import main.player.Player;

public abstract class SelectableEntity extends Entity {

	public static final ArrayList<SelectableEntity> SE = new ArrayList<SelectableEntity>();
	
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
		Point mousePos = Engine.getMouse().getPos();
		return collider.contains(mousePos.getX(), mousePos.getY());
	}
	
	public void tick() {
		super.tick();
		Mouse m = Engine.getMouse();
		if(m.getButton()[0]) {
			if(mouseOver()) {
				player.setSelected(this);
			} else if(!m.overEntity())
				player.setSelected(null);
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		if(map.getControlledPlayer() == player) {
			if(!mouseOver()) g.setColor(Color.red);
			else g.setColor(Color.blue);
			g.draw(collider);
		}
	}
}
