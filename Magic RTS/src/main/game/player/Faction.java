package main.game.player;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import main.game.player.factions.*;
import main.graphics.AnimSet;
import main.util.ResourceLoader;

public abstract class Faction {

	public static final HashMap<FType, Faction> FACTIONS = new HashMap<FType, Faction>();
	
	protected HashMap<String, AnimSet> sprites; //Hashmap for sprites
	
	
	// TODO: Make Static
	protected ArrayList<String> units;
	protected ArrayList<String> buildings;
	
	protected String name;

	public abstract void loadUnits();
	public abstract void loadBuildings();
	public abstract void loadUI();
	
	public Faction(String name) {
		sprites = new HashMap<String, AnimSet>();
		
		units = new ArrayList<String>();
		buildings = new ArrayList<String>();
		
		this.name = name;
		
		loadUnits();
		loadBuildings();
		loadUI();
	}
	
	public static void initFactions() {
		FACTIONS.put(FType.Steampunk, new Steampunk());
		FACTIONS.put(FType.Viking, new Viking());
		FACTIONS.put(FType.Wild, new Steampunk());
	}
	
	
	public ArrayList<String> getUnits() {
		return units;
	}
	
	public ArrayList<String> getBuildings() {
		return buildings;
	}
	
	public AnimSet getSprite(String index) {
		if(sprites.get(index) != null)
			return sprites.get(index);
		else {
			System.err.println(name + " failed to get " + index + " sprite");
			return ResourceLoader.missing;
		}
	}

	public String getName() {
		return name;
	}
}
