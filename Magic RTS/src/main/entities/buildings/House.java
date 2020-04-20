package main.entities.buildings;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.entities.Building;
import main.game.player.Player;

public class House extends Building {

	public House(Player player, Point pos) {
		super(player, pos, player.getFaction().getSprite("house"));
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
