package main.util;

import static main.engine.Engine.ABS_PATH;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;

import main.engine.Engine;
import main.game.map.Map;
import main.game.map.MapLoader;
import main.game.map.TileSet;

public class ResourceLoader {

	
	public static final int FONT_SIZE = 60;

	public static final HashMap<String, Image> SPRITES = new HashMap<String, Image>();
	public static final HashMap<String, SpriteSheet> SPRITE_SHEETS = new HashMap<String, SpriteSheet>();

	public static final HashMap<String, Map> MAPS = new HashMap<String, Map>();
	public static final HashMap<String, TileSet> TILE_SETS = new HashMap<String, TileSet>();
	public static final HashMap<String, TrueTypeFont> FONTS = new HashMap<String, TrueTypeFont>();
	
	
	public static Image missing;
	public static SpriteSheet missingSS;

	public static final int thingsToLoad = 7;
	private static int loaded = 0;
	
	public static void loadMenuSprites() {
		SPRITE_SHEETS.put("menu_button", loadSpriteSheet("res\\sprites\\menu\\button_anim", 220, 60));
		SPRITE_SHEETS.put("menu_buttonR", loadSpriteSheet("res\\sprites\\menu\\button_animR", 220, 60));
	}
	
	public static void loadTiles() {

		// Load grassLand tile set
		TILE_SETS.put("grass", TileSet.loadTileSet("plains"));
		TILE_SETS.put("setons", TileSet.loadTileSet("setons"));
	}

	public static void loadSprites() {
		String path = "res\\sprites\\";
		// LOAD GOLEM SPRITE AND WALK
		SPRITES.put("spr_golem", loadImage(path + "mobs\\Golem"));
		SPRITE_SHEETS.put("golem_walk", loadSpriteSheet(path + "mobs\\golemwalk_ss", 32, 32));

		// Axeman
		SPRITES.put("axeman", loadImage(path + "mobs\\vikeaxe"));
		SPRITE_SHEETS.put("axeman_down", loadSpriteSheet(path + "mobs\\vikeaxe_down", 48, 48));

		// Houses
		SPRITES.put("vikehut", loadImage(path + "buildings\\vikehut"));
		
		// Resources Icons
		SPRITES.put("manaIcon", loadImage(path + "UI\\icons\\manaIcon"));
		SPRITES.put("mithrilIcon", loadImage(path + "UI\\icons\\mithrilIcon"));
		SPRITES.put("stoneIcon", loadImage(path + "UI\\icons\\stoneIcon"));
		
		//Frames
		SPRITES.put("UIBottomBar", loadImage(path + "UI\\frames\\UIBottomBar"));
		
		// Report loaded resources
		int resourceCount = SPRITES.size() + SPRITE_SHEETS.size();
		System.out.println("Loaded " + resourceCount + " Resources!");
	}

	public static void loadFonts() {
		FONTS.put("Menu", loadFont("FantaisieArtistique.ttf", 45));
	}
	
	public static void loadMaps() {
		MAPS.put("Seton's Clutch", MapLoader.loadMap("maps\\setons"));
		MAPS.put("Test Map 1", MapLoader.loadMap("maps\\m"));
		MAPS.put("Test Map 2", MapLoader.loadMap("maps\\map1"));
		MAPS.put("Test Map 3", MapLoader.loadMap("maps\\map2"));
		MAPS.put("Zoom Test", MapLoader.loadMap("maps\\zoomTest"));
		MAPS.put("Mountain Pass", MapLoader.loadMap("maps\\mountainpass"));
	}
	
	public static void initResources() {
		
		try {
			missing = new Image(ABS_PATH + "res\\sprites\\missingtex.png");
			missingSS = new SpriteSheet(ABS_PATH + "res\\sprites\\missingtex.png", 48, 48);
		} catch (SlickException e) {
			System.err.println("Failed to load missingtex.png");
			e.printStackTrace();
		}
		// Don't change the load order please
		loadFonts();
		loadTiles();
		report();
		loadMaps();
		System.err.println(MAPS.values());
		report();
		loadMenuSprites();
		loadSprites();
		report();

		Engine.setCurrentState(Engine.menuState);
	}
	
	public static TrueTypeFont loadFont(String font, int size) {
		InputStream is = org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res\\font\\" + font);
	    TrueTypeFont ttf = null;
		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, is);
			f = f.deriveFont(size * 1f);
			ttf = new TrueTypeFont(f, false);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return ttf;
	}
	
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

	private static void report() {
		System.out.print("Loaded: ");
		System.out.print(loaded);
		System.out.print("/");
		System.out.println(thingsToLoad);
	}

	public static float getLoaded() {
		return loaded / thingsToLoad;
	}
}
