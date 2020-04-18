package main.entities;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.Game;
import main.game.player.Camera;
import main.game.player.Player;

public abstract class SelectableEntity extends Entity {

	public static final ArrayList<SelectableEntity> SE = new ArrayList<SelectableEntity>();

	protected int width, height;
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

	public void tick() {
		super.tick();
	}

	public void render(Graphics g) {
		super.render(g);
		if (map.getControlledPlayer() == player) {
			
			selected = player.getSelected().contains(this);
			
			if (mouseOver() || selected)
				g.setColor(Color.green);
			else
				g.setColor(Color.red);
			g.draw(collider);
		}
	}

	public boolean mouseOver() {
		Camera c = player.getCamera();
		if (c != null) {
			Point mousePos = Game.UIToObject(Engine.getMouse().getScreenPos(), c);

			boolean safe = collider.contains(mousePos.getX(), mousePos.getY());
			return (safe);
		} else {
			return false;
		}
	}

	public Player getPlayer() {
		return player;
	}
}
