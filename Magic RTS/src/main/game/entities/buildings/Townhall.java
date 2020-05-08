package main.game.entities.buildings;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.game.entities.Building;
import main.game.entities.selectables.unit.Worker;
import main.game.player.Player;

public class Townhall extends Building {

	public Townhall(Player player, Point _pos) {
		super(player, _pos, player.getFaction().getSprite("townhall").copy());

		for(int i = -2; i < 1; i++) {
			// Spawn Workers
			new Worker(player, pos.getX() + i * 60, pos.getY() + (i + 3) * 60);
		}
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	@Override
	public void step() {
		
	}

}
