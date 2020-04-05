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
	public void render(Graphics g, float xOffset, float yOffset) {
		super.render(g, xOffset, yOffset);
		sprite.draw(pos.getX() - origin.getX() - xOffset, pos.getY() - origin.getY() - yOffset);
	}

	@Override
	public void tick() {
		super.tick();
		// TODO Auto-generated method stub
	}
	
}
