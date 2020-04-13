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
		sprite.draw(pos.getX() - origin.getX(), pos.getY() - origin.getY());
	}

	@Override
	public void tick() {
		super.tick();
	}
	
}
