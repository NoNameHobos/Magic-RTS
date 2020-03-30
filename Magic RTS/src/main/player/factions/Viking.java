package main.player.factions;

import main.player.Faction;
import static main.util.ResourceLoader.*;

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
