package main.entities.buildings;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.entities.Building;
import main.game.player.Player;

public class Townhall extends Building {

	public Townhall(Player player, Point _pos) {
		super(player, _pos, player.getFaction().getSprite("townhall"));
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	@Override
	public void step() {
		
	}

}
