package main.game.entities.resources;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.game.map.Map;
import main.util.ResourceLoader;

public class ManaNode extends Resource {

	public ManaNode(Map map, Point pos) {
		super(map, pos, ResourceLoader.SPRITES.get("node_mana").copy());
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	@Override
	public void step() {

	}
}
