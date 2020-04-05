package main.game.map;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import main.util.ResourceLoader;

public class TileSet {

	private SpriteSheet spritesheet;
	
	private Image[][] tiles;
	private final HashMap<String, Image> tileNames = new HashMap<String, Image>();
	
	public TileSet(SpriteSheet spritesheet) {
		this.spritesheet = spritesheet;
		
		loadTiles();
	}
	
	public void loadTiles() {
		 
		tiles = new Image[spritesheet.getHorizontalCount()][spritesheet.getVerticalCount()];
		spritesheet.startUse();
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = spritesheet.getSubImage(i, j);
			}
		}
		spritesheet.endUse();
	}
	
	public void addKey(String key, int tx, int ty) {
		tileNames.put(key, tiles[tx][ty]);
	}
	
	public Image getTile(String key) {
		Image t = tileNames.get(key);
		if(t == null) {
			System.err.println("No Such Tile: " + key);
			return ResourceLoader.missing;
		}
		else return t;
	}
	
	public Image getTile(int x, int y) {
		try {
			return tiles[x][y];
		}
		catch(IndexOutOfBoundsException e) {
			int maxX = spritesheet.getHorizontalCount();
			int maxY = spritesheet.getVerticalCount();
			System.err.println("(" + Integer.toString(x) + "," + Integer.toString(y) + ") is not a valid coordinate.");
			System.err.println("Max x, y is: (" + Integer.toString(maxX) + "," + Integer.toString(maxY) + ")");
			return ResourceLoader.missing;
		}
	}
}
