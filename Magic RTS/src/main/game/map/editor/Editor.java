package main.game.map.editor;

import main.game.map.Map;

public class Editor {

	private Map currentMap;
	
	public Editor() {
		
	}
	
	// Initialize a new editor
	public Editor(Map map) {
		// Generate a new map
		if(map == null) {
			map = new Map("");
		}
		this.currentMap = map;
	}
	
	
	// Getters and Setters
	public Map getMap() {
		return this.currentMap;
	}
	
	/*
	 * 	Map: 104864 Bytes
	 * 		Header: 70 Bytes
	 * 			Map ID: 1 Byte
	 *			Title: 16 Bytes
	 *			Player Count: 1 Byte (8 Player game)
	 *			Size: 4 Bytes (1024 km)
	 *			Generic Flags: 1 Byte
	 *			Spawn Location: 64 Bytes
	 *				Location 1: 4
	 *				Location 2: 4
	 *				etc. : 56
	 *					
	 */
}
