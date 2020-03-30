package main.util;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import static main.engine.Engine.ABS_PATH;

public class ResourceLoader {

	public static HashMap<String, Image> SPRITES = new HashMap<String, Image>();
	public static HashMap<String, SpriteSheet> SPRITE_SHEETS = new HashMap<String, SpriteSheet>();
	
	public static Image missing;
	public static SpriteSheet missingSS;
	
	/*public static HashMap<String, Animation> ANIMATIONS = new HashMap<String, Animation>();
	
	public static Animation loadAnimation(String dir, int duration) {
		
	}
	
	public static Animation loadAnimation(SpriteSheet ss, int duration) {
		
	}*/
	public static Image loadImage(String dir) {
		Image i;
		try {
			i = new Image(ABS_PATH + dir + ".png");
			System.out.println("Loaded " + dir + ".png");
			return i;
		} catch(SlickException e) {
			System.err.println("Failed to load image at: " + dir);
			e.printStackTrace();
			return missing;
		} catch(RuntimeException e) {
			System.err.println("Failed to load image: " + dir);
			return missing;
		}
	}	

	public static Image loadImageFromSS(SpriteSheet ss, int x, int y) {
		ss.startUse();
		Image i = ss.getSprite(x, y);
		ss.endUse();
		return i;
	}
	
	public static SpriteSheet loadSpriteSheet(String dir, int tw, int th) {
		SpriteSheet ss;
		try {
			ss = new SpriteSheet(ABS_PATH + dir + ".png", tw, th);
			System.out.println("Loaded " + dir + ".png as Sprite Sheet with Tile: " + tw + "x" + th);
			return ss;
		} catch(SlickException e) {
			System.err.println("Failed to load Sprite Sheet at: " + dir);
			e.printStackTrace();
			return missingSS;
		} catch(RuntimeException e) {
			System.err.println("Failed to load Sprite Sheet at: " + dir);
			return missingSS;
		}
	}
	
	public static void initResources() {
		
		try {
			missing = new Image(ABS_PATH + "res\\missingtex.png");
			missingSS = new SpriteSheet(ABS_PATH + "res\\missingtex.png", 48, 48);
		} catch (SlickException e) {
			System.err.println("Failed to load missingtex.png");
			e.printStackTrace();
		}
		
		//LOAD GOLEM SPRITE AND WALK
		SPRITES.put("spr_golem", loadImage("res\\mobs\\Golem"));
		SPRITE_SHEETS.put("golem_walk", loadSpriteSheet("res\\mobs\\golemwalk_ss", 32, 32));
		
		//Axeman
		SPRITES.put("axeman", loadImage("res\\mobs\\vikeaxe"));
		SPRITE_SHEETS.put("axeman_down", loadSpriteSheet("res\\mobs\\vikeaxe_down", 48, 48));
		
		//Report loaded resources
		int resourceCount = SPRITES.size() + SPRITE_SHEETS.size();
		System.out.println("Loaded " + resourceCount + " Resources!");
	}
	
}
