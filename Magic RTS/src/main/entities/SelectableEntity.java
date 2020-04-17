package main.entities;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.Game;
import main.game.map.Map;
import main.game.player.Camera;
import main.game.player.Player;

public abstract class SelectableEntity extends Entity {

	public static final ArrayList<SelectableEntity> SE = new ArrayList<SelectableEntity>();

	protected int width, height;
	protected Map map;
	protected Player player;
	protected Boolean selected;

	public SelectableEntity(Player player, Point pos, Image sprite) {
		super(player.getMap(), pos, sprite);
		selectable = true;
		this.player = player;
		width = sprite.getWidth();
		height = sprite.getHeight();
		selected = false;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean mouseOver() {
		Camera c = player.getCamera();
		if (c != null) {
			Point mousePos = Game.UIToObject(Engine.getMouse().getPos(), c);

			boolean safe = collider.contains(mousePos.getX(), mousePos.getY());
			return (safe);
		} else
			return false;
	}

	public void tick() {
		super.tick();
	}

	public void render(Graphics g) {
		super.render(g);
		if (selected) {
			g.setColor(Color.green);
			g.draw(collider);
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void select(boolean s) {
		selected = s;

	}

	public void select() {
		selected = true;

	}

}
