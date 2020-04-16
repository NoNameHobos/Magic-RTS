package main.player.factions;

import static main.util.ResourceLoader.*;

import main.game.player.Faction;

public class Viking extends Faction {
	
	public Viking() {
		super("Vikings");
		loadUnits();
		loadBuildings();
	}

	@Override
	public void loadUnits() { //Put Unit stuff in here, add to unit hashmap
		sprites.put("axeman", SPRITES.get("axeman"));
		units.add("axeman");
	}

	@Override
	public void loadBuildings() {
		sprites.put("house", SPRITES.get("vikehut"));
		buildings.add("house");
	}

	
	
}
