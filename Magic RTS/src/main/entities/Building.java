package main.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.player.Player;

public abstract class Building extends SelectableEntity {

	protected Player player;
	
	public Building(Player player, Point _pos, Image sprite) {
		super(_pos, sprite);
	}

}
