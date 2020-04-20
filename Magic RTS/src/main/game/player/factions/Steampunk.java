package main.game.player.factions;

import static main.util.ResourceLoader.*;

import main.game.player.Faction;

public class Steampunk extends Faction {
	
	public Steampunk() {
		super("Steampunk");
	}

	@Override
	public void loadUnits() { //Put Unit stuff in here, add to unit hashmap
		sprites.put("axeman", SPRITES.get("axeman"));
		units.add("axeman");
	}

	@Override
	public void loadBuildings() {
		sprites.put("house", SPRITES.get("vikehut"));
	}
	
	public void loadUI() {
		sprites.put("ui_bottombar", UI.get("steam_bottom"));
		sprites.put("ui_minimap", UI.get("steam_minimap"));
	}
	
}
