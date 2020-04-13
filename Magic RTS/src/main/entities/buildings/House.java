package main.entities.buildings;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.entities.Building;
import main.player.Player;

public class House extends Building {

	public House(Player player, Point pos) {
		super(player, pos, player.getFaction().getSprite("house"));
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		float width = sprite.getWidth();
		float height = sprite.getHeight();
		sprite.draw(pos.getX() - origin.getX(), pos.getY() - origin.getY(), width, height);
	}

	@Override
	public void tick() {
		super.tick();
	}

}
