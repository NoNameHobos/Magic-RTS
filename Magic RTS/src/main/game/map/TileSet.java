package main.game.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import main.engine.Engine;

public class TileSet {

	private SpriteSheet spritesheet;
	
	private final HashMap<String, Image> tileNames = new HashMap<String, Image>();
	
	public static TileSet loadTileSet(String p) {
		System.err.println("Loading set: " + p);
		String path = "res\\sprites\\tilesets\\" + p;
		System.err.println("From: " + path);
		
		File tile_info = new File(Engine.ABS_PATH + path + "\\inf");
		BufferedReader br;
		ArrayList<String> tileData = new ArrayList<String>();
		
		try {
			br = new BufferedReader(new FileReader(tile_info));
			String st;
			while ((st = br.readLine()) != null) {
				tileData.add(st);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Missing or corrupt inf file");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Load tiles
		SpriteSheet tiles = Engine.RES.loadSpriteSheet(path + "\\tiles.png", 33, 33);
		
		//Create a new TileSet
		TileSet ts = new TileSet(tiles);
		
		int width = tiles.getHorizontalCount();
		int height = tiles.getVerticalCount();
		
		//Now the tileData is loaded
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				try {
					ts.addKey(tileData.get(i * width + j), j, i);
				} catch(IndexOutOfBoundsException e) {
					System.err.println("Missing point in inf: " + Integer.toString(j) + "," + Integer.toString(i));
					ts.addKey("blank", j, i);
				}
			}
		}
		
		return ts;
	}
	
	private TileSet(SpriteSheet spritesheet) {
		this.spritesheet = spritesheet;
	}
	
	public void addKey(String key, int tx, int ty) {
		tileNames.put(key, spritesheet.getSubImage(tx, ty));
	}
	
	public Image getTile(String key) {
		Image t = tileNames.get(key);
		if(t == null) {
			System.err.println("No Such Tile: " + key);
			return Engine.RES.getMissingImage();
		}
		else return t;
	}
	
	public Image getTile(int x, int y) {
		try {
			return spritesheet.getSubImage(x, y);
		}
		catch(IndexOutOfBoundsException e) {
			int maxX = spritesheet.getHorizontalCount();
			int maxY = spritesheet.getVerticalCount();
			System.err.println("(" + Integer.toString(x) + "," + Integer.toString(y) + ") is not a valid coordinate.");
			System.err.println("Max x, y is: (" + Integer.toString(maxX) + "," + Integer.toString(maxY) + ")");
			return Engine.RES.getMissingImage();
		}
	}
}
