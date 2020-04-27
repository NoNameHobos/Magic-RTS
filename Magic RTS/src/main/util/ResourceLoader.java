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

	public static final HashMap<String, Image> UI = new HashMap<String, Image>();
	
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
		SPRITE_SHEETS.put("axeman_down", loadSpriteSheet(path + "mobs\\axeman\\axeman_down", 48, 48));
		SPRITE_SHEETS.put("axeman_up", loadSpriteSheet(path + "mobs\\axeman\\axeman_up", 48, 48));
		SPRITE_SHEETS.put("axeman_left", loadSpriteSheet(path + "mobs\\axeman\\axeman_left", 48, 48));
		SPRITE_SHEETS.put("axeman_right", loadSpriteSheet(path + "mobs\\axeman\\axeman_right", 48, 48));

		// Vikings
		SPRITES.put("vike_hut", loadImage(path + "buildings\\viking\\hut"));
		SPRITES.put("vike_th", loadImage(path + "buildings\\viking\\th"));
		
		// Steampunk
		SPRITES.put("steam_th", loadImage(path + "buildings\\steampunk\\th"));
		
		//Resource Nodes
		SPRITES.put("node_mana", loadImage(path+"objects\\resources\\mana"));
		SPRITES.put("node_stone", loadImage(path+"objects\\resources\\stone"));
		SPRITES.put("node_mithril", loadImage(path+"objects\\resources\\mithril"));
		
		// Resources Icons
		UI.put("manaIcon", loadImage(path + "UI\\icons\\manaIcon"));
		UI.put("mithrilIcon", loadImage(path + "UI\\icons\\mithrilIcon"));
		UI.put("stoneIcon", loadImage(path + "UI\\icons\\stoneIcon"));
		
		//Progress bars
		UI.put("UIManaBar", loadImage(path + "UI\\bars\\manaBar"));
		UI.put("UIStoneBar", loadImage(path + "UI\\bars\\stoneBar"));
		UI.put("UIMithrilBar", loadImage(path + "UI\\bars\\mithrilBar"));
		
		//--Load UI Sprites--//
		//Frames
		UI.put("viking_bottom", loadImage(path + "UI\\frames\\bottombar_vike"));
		UI.put("viking_minimap", loadImage(path + "UI\\minimap\\minimap_vike"));
		UI.put("steam_bottom", loadImage(path + "UI\\frames\\bottombar_steam"));
		UI.put("steam_minimap", loadImage(path + "UI\\minimap\\minimap_steam"));
		
		// Report loaded resources
		int resourceCount = SPRITES.size() + SPRITE_SHEETS.size();
		System.out.println("Loaded " + resourceCount + " Resources!");
	}

	public static void loadFonts() {
		FONTS.put("Menu", loadFont("FantaisieArtistique.ttf", 45));
	}
	
	public static void loadMaps() {
		MAPS.put("Seton's Clutch", MapLoader.loadMap("maps\\setons"));
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
			i.setFilter(Image.FILTER_NEAREST);
			i.clampTexture();
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
		i.setFilter(Image.FILTER_NEAREST);
		i.clampTexture();
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
			ss.setFilter(Image.FILTER_NEAREST);
			ss.clampTexture();
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
