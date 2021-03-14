package main.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;

import main.game.map.Map;
import main.game.map.TileSet;
import main.graphics.res.Sprite;

import static main.GameConstants.ERROR_MISSING_TEXTURE;

public class ResourceLoader {

	private String currentDir;
	public static final int FONT_SIZE = 60;
	
	// TODO: Move onto separate thread
	
	private final HashMap<String, Sprite> SPRITES = new HashMap<String, Sprite>();
	private final HashMap<String, Map> MAPS = new HashMap<String, Map>();
	private final HashMap<String, TileSet> TILE_SETS = new HashMap<String, TileSet>();
	private final HashMap<String, TrueTypeFont> FONTS = new HashMap<String, TrueTypeFont>();

	private static Sprite missingSprite;
	private static SpriteSheet missing_ss;
	/**
	 * Initialize Resource Loader
	 */
	public void init() {
		try {
			missing_ss = new SpriteSheet("res\\sprites\\missingtex.png", 48, 48);
		} catch (SlickException e) {
			System.err.println("Failed to load missingtex.png");
			e.printStackTrace();
			System.exit(ERROR_MISSING_TEXTURE);
		}
		
		// Create missingSprite object
		missingSprite = Sprite.createSprite(missing_ss);
	}
	
	/**
	 * Load a sprite from the current directory with a given name
	 * @param name - name of the file to load (will set sprite name to this)
	 */
	public void addSprite(String name) {
		addSprite(name, name);
	}
	
	/**
	 * Add sprite to Sprites with given name at given path.
	 * @param name - Sprite name to assign
	 * @param path - Local Path to the sprite (see ResourceLoader.setCurrentDir())
	 */
	public void addSprite(String name, String path) {
		Sprite sprite = loadSprite(path);
		SPRITES.put(name, sprite);
		// TODO: Add name to sprite
	}
	
	/**
	 * add a preloaded sprite to the sprite list
	 * @param name - name of sprite
	 * @param sprite - Sprite object to add
	 */
	public void addSprite(String name, Sprite sprite) {
		SPRITES.put(name, sprite);
	}
	
	// Resource loading functions
	public static TrueTypeFont loadFont(String font, int size) {
		InputStream is = org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res\\font\\" + font);
		TrueTypeFont ttf = null;
		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, is);
			f = f.deriveFont(size * 1f);
			ttf = new TrueTypeFont(f, false);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		return ttf;
	}

	// TODO: Unit Tests
	/**
	 * Load sprite from path.
	 * Returns null if file is missing or corrupt
	 * @param path - Path to the requested sprite
	 * @return a new sprite based on the supplied path.
	 */
	public Sprite loadSprite(String path) {
		File file = new File(currentDir + path);
		if(!file.isFile())
			return null;
		else {
			Image ref_img = loadImage(currentDir + path);
			return Sprite.createSprite(ref_img);
		}
	}
	
	public Sprite loadSprite(String path, int tw, int th, int duration) {
		Image ref_img = loadImage(path);
		return Sprite.createSprite(ref_img, tw, th, duration);
	}
	
	/**
	 * load Sprite from SpriteSheet with duration from current working directory
	 * @param s_sheet SpriteSheet to create Sprite from
	 * @param duration
	 * @return a Sprite based on the supplied SpriteSheet and given duration (int)
	 */
	public Sprite loadSprite(SpriteSheet s_sheet, int duration) {
		return Sprite.createSprite(s_sheet, duration);
	}
	
	public void addSpriteSheet(String name, String path, int tw, int th) {
		SpriteSheet ss;
		
		try {
			ss = new SpriteSheet(currentDir + path, tw, th);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			ss = missing_ss;
		}
		
		this.addSprite(name,loadSprite(ss, Sprite.DEFAULT_DURATION));
	}
	
	/**
	 * 
	 * @param path
	 * @return an Image from the path returns missing image otherwise
	 */
	private Image loadImage(String path) {
		Image i;
		try {
			i = new Image(path);
			System.out.println("Loaded " + path);
			i.setFilter(Image.FILTER_NEAREST);
			i.clampTexture();
			return i;
		} catch (Exception e) {
			System.err.println("Failed to load image at: " + path);
			e.printStackTrace();
			return null;
		}
	}

	public SpriteSheet loadSpriteSheet(String dir, int tw, int th) {
		SpriteSheet ss;
		try {
			ss = new SpriteSheet(dir, tw, th);
			System.out.println("Loaded " + dir + " as Sprite Sheet with Tile: " + tw + "x" + th);
			ss.setFilter(Image.FILTER_NEAREST);
			ss.clampTexture();
			return ss;
		} catch (SlickException e) {
			System.err.println("Failed to load Sprite Sheet at: " + dir + " (SlickException)");
			e.printStackTrace();
			return missing_ss;
		} catch (RuntimeException e) {
			System.err.println("Failed to load Sprite Sheet at: " + dir + " (RuntimeException)");
			return missing_ss;
		}
	}
	
	/**
	 * Set current working directory to load resources from
	 * @param dir - Directory load resources from
	 * @param relative - is this directory relative? (true/false)
	 */
	public void setCurrentDir(String dir, boolean relative) {
		if(relative) {
			this.currentDir += dir;
		} else this.currentDir = dir;
	}
	
	/**
	 * Set current absolute working directory to load resources from
	 * @param dir - Directory load resources from
	 */
	public void setCurrentDir(String dir) {
		this.setCurrentDir(dir, false);
	}

	/**
	 * Get sprite from sprite list. Must be added to list with addSprite first.
	 * @param name - name of sprite to retrieve
	 * @return returns missingSprite if no sprite is found under the name
	 */
	public Sprite getSprite(String name) {
		Sprite sprite = SPRITES.get(name);
		if(sprite == null) {
			System.err.println("Sprite missing: " + name);
			return missingSprite;
		} else return sprite;
	}

}
