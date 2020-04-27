package main.entities.resources;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.entities.Resource;
import main.game.map.Map;
import main.util.ResourceLoader;

public class ManaNode extends Resource {

	public ManaNode(Map map, Point pos) {
		super(map, pos, ResourceLoader.SPRITES.get("node_mana"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.draw(g);
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}
}
