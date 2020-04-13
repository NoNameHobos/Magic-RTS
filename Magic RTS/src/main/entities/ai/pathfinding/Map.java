package main.entities.ai.pathfinding;

public class Map {

	private final Node[][] map;
	
	public Map(int mapWidth, int mapHeight) {
		map = new Node[mapHeight][mapWidth];
		
		for(int i = 0; i < mapHeight; i++) {
			for(int j = 0; j < mapWidth; j++) {
				map[i][j] = new Node(0);
			}
		}
	}
	
	public Node[][] getMap() {
		return map;
	}
}
