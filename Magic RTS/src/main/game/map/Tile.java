package main.game.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

public class Tile {

	public static final float TILE_WIDTH = 64;
	public static final float TILE_HEIGHT= 64;
	
	private Map map;
	
	private Image image;
	private Point pos;
	
	public Tile(Map map, Image tileSprite, float x, float y) {
		this.map = map;
		image = tileSprite;
		this.pos = new Point(x, y);
	}
	
	public Point getPos() {
		return pos;
	}
	
	public Image getImage() {
		return image;
	}
}
