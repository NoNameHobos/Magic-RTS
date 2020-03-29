package main.player;

import java.util.HashMap;

import main.entities.Building;
import main.entities.Unit;

public abstract class Civilization {

	protected HashMap<String, Unit> units; //Hashmap for units to make unit type referencing easier
	protected HashMap<String, Building> buildings; //Hashmap for buildings for the same thing
	
	
	public Civilization(HashMap<String, Unit> units, HashMap<String, Building> buildings) {
		this.units = units;
		this.buildings = buildings;
	}
	
	
	public HashMap<String, Unit> getUnits() {
		return units;
	}
	
	public HashMap<String, Building> getBuildings() {
		return buildings;
	}
}
