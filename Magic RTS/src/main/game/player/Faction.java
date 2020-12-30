package main.game.player;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import main.game.player.factions.*;
import main.util.ResourceLoader;

public abstract class Faction {

	public static final HashMap<String, Faction> FACTIONS = new HashMap<String, Faction>();
	
	protected HashMap<String, Image> sprites; //Hashmap for sprites
	protected HashMap<String, SpriteSheet> sprite_sheets; //Hashmap for spritesheets
	protected ArrayList<String> units;
	protected ArrayList<String> buildings;
	//TODO: Change arraylists to maps of units
	
	protected String name;

	public abstract void loadUnits();
	public abstract void loadBuildings();
	public abstract void loadUI();
	
	public Faction(String name) {
		sprites = new HashMap<String, Image>();
		sprite_sheets = new HashMap<String, SpriteSheet>();
		
		units = new ArrayList<String>();
		buildings = new ArrayList<String>();
		
		this.name = name;
		loadUnits();
		loadBuildings();
		loadUI();
	}
	
	public static void initFactions() {
		FACTIONS.put("steampunk", new Steampunk());
		FACTIONS.put("vikings", new Viking());
	}
	
	
	public ArrayList<String> getUnits() {
		return units;
	}
	
	public ArrayList<String> getBuildings() {
		return buildings;
	}
	
	public Image getSprite(String index) {
		if(sprites.get(index) != null)
			return sprites.get(index);
		else {
			System.err.println(name + " failed to get " + index + " sprite");
			return ResourceLoader.missing;
		}
	}
	
	public SpriteSheet getSpriteSheet(String index) { 
		return sprite_sheets.get(index);
	}

	public String getName() {
		return name;
	}
}
