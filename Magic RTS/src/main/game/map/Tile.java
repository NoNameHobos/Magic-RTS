package main.game.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

public class Tile {
	
	private Image image;
	private Point pos;
	
	private float depth;
		
	public Tile(Image tileSprite, float x, float y, Map map) {
		image = tileSprite;
		this.pos = new Point(x, y);
		depth = -x - y * map.getMapWidth();
	}
	
	public Point getPos() {
		return pos;
	}
	
	public Image getImage() {
		return image;
	}
	
	public float getDepth() {
		return depth;
	}
}
