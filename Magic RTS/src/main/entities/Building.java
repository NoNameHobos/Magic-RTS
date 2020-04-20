package main.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.game.player.Player;

public abstract class Building extends SelectableEntity {

	protected Player player;
	
	public Building(Player player, Point _pos, Image sprite) {
		super(player, _pos, sprite);
		type = "Building";
	}
	
	public void render(Graphics g) {
		super.render(g);
		float width = sprite.getWidth();
		float height = sprite.getHeight();
		sprite.draw(pos.getX() - origin.getX(), pos.getY() - origin.getY(), width, height);
	}

}
