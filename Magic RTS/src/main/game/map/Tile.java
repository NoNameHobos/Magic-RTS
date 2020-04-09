package main.game.map;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

public class Tile {
	
	private Image image;
	private Point pos;
	
	public Tile(Image tileSprite, float x, float y) {
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
