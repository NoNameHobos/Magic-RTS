package main.game.player.factions;

import main.game.player.Faction;

public class Steampunk extends Faction {
	
	public Steampunk() {
		super("Steampunk");
	}
	
	//Put Unit stuff in here, add to unit hashmap
	@Override
	public void loadUnits() { 
		sprites.put("axeman", RES.getSprite("axeman"));
		units.add("axeman");
	}

	@Override
	public void loadBuildings() {
		sprites.put("house", RES.getSprite("vike_hut"));
		sprites.put("townhall", RES.getSprite("steam_th"));
	}
	
	public void loadUI() {
		sprites.put("ui_bottombar", RES.getSprite("steam_bottom"));
		sprites.put("ui_minimap", RES.getSprite("steam_minimap"));
	}
	
}
