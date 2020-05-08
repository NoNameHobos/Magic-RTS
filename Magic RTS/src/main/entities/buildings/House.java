package main.entities.buildings;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.game.player.Player;

public class House extends Building {

	public House(Player player, Point pos) {
		super(player, pos, player.getFaction().getSprite("house").copy());
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	@Override
	public void step() {
		
	}

}
