package main.entities;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.map.Map;
import main.input.Mouse;
import main.player.Camera;
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
		Camera c = map.getControlledPlayer().getCamera();
		Point mousePos = Engine.getMouse().getPos();
		Rectangle scaledCollider = new Rectangle(collider.getX() * c.getZoom(), collider.getY() * c.getZoom(), collider.getWidth() * c.getZoom(), collider.getHeight() * c.getZoom());
		return scaledCollider.contains(mousePos.getX(), mousePos.getY());
	}
	
	public void tick() {
		super.tick();
		Mouse m = Engine.getMouse();

		collider.setX(pos.getX() - origin.getX());
		collider.setY(pos.getY() - origin.getY());
		
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
			g.drawRect(collider.getX(), collider.getY(), 
					collider.getWidth(), collider.getHeight());
		}
	}
}
