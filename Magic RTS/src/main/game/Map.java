package main.game;

public class Map {

	private Tile[][] tiles;
	
	public Map(int mapWidth, int mapHeight) throws Exception {
		if(mapWidth > 1024 || mapHeight > 1024) {
			throw new Exception("Map is size is too big! Max map size is 1024x1024");
		}
		tiles = new Tile[1024][1024];
		
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(this);
			}
		}
	}
	
}
