package main.entities.buildings;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.entities.Building;
import main.game.player.Player;

public class Townhall extends Building {

	public Townhall(Player player, Point _pos) {
		super(player, _pos, player.getFaction().getSprite("townhall"));
		
		System.out.println("Created a th");
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

}
