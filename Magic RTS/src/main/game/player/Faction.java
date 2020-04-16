package main.game.player;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public abstract class Faction {

	protected HashMap<String, Image> sprites; //Hashmap for sprites
	protected HashMap<String, SpriteSheet> sprite_sheets; //Hashmap for spritesheets
	protected ArrayList<String> units;
	protected ArrayList<String> buildings;
	
	protected String name;
	
	public Faction(String name) {
		sprites = new HashMap<String, Image>();
		sprite_sheets = new HashMap<String, SpriteSheet>();
		
		units = new ArrayList<String>();
		buildings = new ArrayList<String>();
		
		this.name = name;
	}
	
	public abstract void loadUnits();
	public abstract void loadBuildings();
	
	public ArrayList<String> getUnits() {
		return units;
	}
	
	public ArrayList<String> getBuildings() {
		return buildings;
	}
	
	public Image getSprite(String index) {
		System.out.println("Loaded: " + index);
		return sprites.get(index);
	}
	
	public SpriteSheet getSpriteSheet(String index) { 
		return sprite_sheets.get(index);
	}

	public String getName() {
		return name;
	}
}
