package main.game.player.factions;

import main.game.player.Faction;

public class Viking extends Faction {
	
	public Viking() {
		super("Vikings");
	}

	@Override
	public void loadUnits() { //Put Unit stuff in here, add to unit hashmap
		sprites.put("axeman", RES.getSprite("axeman"));
		sprites.put("warg", RES.getSprite("warg_right"));
	}

	@Override
	public void loadBuildings() {
		sprites.put("house", RES.getSprite("vike_hut"));
		sprites.put("townhall", RES.getSprite("vike_th"));
	}
	
	public void loadUI() {
		sprites.put("ui_bottombar", RES.getSprite("viking_bottom"));
		sprites.put("ui_minimap", RES.getSprite("viking_minimap"));
	}
	
}
