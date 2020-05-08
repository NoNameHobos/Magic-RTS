package main.game.player.factions;

import static main.util.ResourceLoader.*;

import main.game.player.Faction;

public class Viking extends Faction {
	
	public Viking() {
		super("Vikings");
	}

	@Override
	public void loadUnits() { //Put Unit stuff in here, add to unit hashmap
		sprites.put("axeman", SPRITES.get("axeman"));
		sprites.put("warg", SPRITES.get("warg_right"));
	}

	@Override
	public void loadBuildings() {
		sprites.put("house", SPRITES.get("vike_hut"));
		sprites.put("townhall", SPRITES.get("vike_th"));
	}
	
	public void loadUI() {
		sprites.put("ui_bottombar", UI.get("viking_bottom"));
		sprites.put("ui_minimap", UI.get("viking_minimap"));
	}
	
}
