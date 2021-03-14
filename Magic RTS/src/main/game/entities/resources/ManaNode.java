package main.game.entities.resources;

import org.newdawn.slick.geom.Point;

import main.game.map.Map;

public class ManaNode extends Resource {

	public ManaNode(Map map, Point pos) {
		super(map, pos, RES.getSprite("node_mana").copy());
	}
	
	public void step() {}
}
