package main.util;

import static main.engine.Engine.ABS_PATH;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.engine.Engine;
import main.game.map.Map;
import main.game.map.TileSet;

public class ResourceLoader {

	public static final int TILE_WIDTH = 128;
	public static final int TILE_HEIGHT = 128;

	public static final HashMap<String, Image> SPRITES = new HashMap<String, Image>();
	public static final HashMap<String, SpriteSheet> SPRITE_SHEETS = new HashMap<String, SpriteSheet>();

	public static final HashMap<String, Map> MAPS = new HashMap<String, Map>();
	public static final HashMap<String, TileSet> TILE_SETS = new HashMap<String, TileSet>();

	public static Image missing;
	public static SpriteSheet missingSS;

	public static final int thingsToLoad = 7;
	private static int loaded = 0;

	public static Image loadImage(String dir) {
		Image i;
		loaded++;
		try {
			i = new Image(ABS_PATH + dir + ".png");
			System.out.println("Loaded " + dir + ".png");
			return i;
		} catch (SlickException e) {
			System.err.println("Failed to load image at: " + dir);
			e.printStackTrace();
			return missing;
		} catch (RuntimeException e) {
			System.err.println("Failed to load image: " + dir);
			return missing;
		}
	}

	public static Image loadImageFromSS(SpriteSheet ss, int x, int y) {
		ss.startUse();
		Image i = ss.getSprite(x, y);
		ss.endUse();
		loaded++;
		return i;
	}

	public static SpriteSheet loadSpriteSheet(String dir, int tw, int th) {
		SpriteSheet ss;
		loaded++;
		try {
			ss = new SpriteSheet(ABS_PATH + dir + ".png", tw, th);
			System.out.println("Loaded " + dir + ".png as Sprite Sheet with Tile: " + tw + "x" + th);
			return ss;
		} catch (SlickException e) {
			System.err.println("Failed to load Sprite Sheet at: " + dir + " (SlickException)");
			e.printStackTrace();
			return missingSS;
		} catch (RuntimeException e) {
			System.err.println("Failed to load Sprite Sheet at: " + dir + " (RuntimeException)");
			return missingSS;
		}
	}

	public static Map loadMap(String dir) {
		File file = new File(ABS_PATH + dir);
		BufferedReader br;
		ArrayList<String> mapData = new ArrayList<String>();
		
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				mapData.add(st);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		loaded++;
		return new Map("grass", mapData);
	}

	public static void initResources() {
		
		try {
			missing = new Image(ABS_PATH + "res\\missingtex.png");
			missingSS = new SpriteSheet(ABS_PATH + "res\\missingtex.png", 48, 48);
		} catch (SlickException e) {
			System.err.println("Failed to load missingtex.png");
			e.printStackTrace();
		}
		// Don't change the load order please
		loadTiles();
		report();
		loadMaps();
		System.err.println(MAPS.values());
		report();
		loadSprites();
		report();

		Engine.setCurrentState(Engine.gameState);
	}

	private static void report() {
		System.out.print("Loaded: ");
		System.out.print(loaded);
		System.out.print("/");
		System.out.println(thingsToLoad);
	}

	public static float getLoaded() {
		return loaded / thingsToLoad;
	}

	public static void loadTiles() {

		// Load grassLand tile set
		TILE_SETS.put("grass", TileSet.loadTileSet("plains"));
	}

	public static void loadSprites() {

		// LOAD GOLEM SPRITE AND WALK
		SPRITES.put("spr_golem", loadImage("res\\mobs\\Golem"));
		SPRITE_SHEETS.put("golem_walk", loadSpriteSheet("res\\mobs\\golemwalk_ss", 32, 32));

		// Axeman
		SPRITES.put("axeman", loadImage("res\\mobs\\vikeaxe"));
		SPRITE_SHEETS.put("axeman_down", loadSpriteSheet("res\\mobs\\vikeaxe_down", 48, 48));

		// Houses
		SPRITES.put("vikehut", loadImage("res\\buildings\\vikehut"));
		// Report loaded resources
		int resourceCount = SPRITES.size() + SPRITE_SHEETS.size();
		System.out.println("Loaded " + resourceCount + " Resources!");
	}

	public static void loadMaps() {
		MAPS.put("map1", loadMap("maps\\map1")); // Arguments currently do nothing but in future will be based on map data
	}
}
