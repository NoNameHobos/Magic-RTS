package main.entities;

import java.util.ArrayList;

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
	protected Boolean selected;

	public SelectableEntity(Player player, Point pos, Image sprite) {
		super(player, player.getMap(), pos, sprite);
		selectable = true;
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
	}

	public boolean mouseOver() {
		Camera c = player.getCamera();
		if (c != null) {
			Point mousePos = Game.UIToObject(Engine.getMouse().getScreenPos(), c);

			boolean safe = boundingbox.contains(mousePos.getX(), mousePos.getY());
			return (safe);
		} else {
			return false;
		}
	}
}
