package main.game.map;

import static main.util.ResourceLoader.TILE_SETS;

import static main.util.ResourceLoader.TILE_WIDTH;
import static main.util.ResourceLoader.TILE_HEIGHT;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.player.Camera;
import main.player.Player;
import main.player.factions.Viking;

public class Map {

	private Tile[][] tiles;

	private Player[] players;
	private int maxPlayers = 8;
	private int mapWidth, mapHeight;

	private String mapName = "Grass Map";

	private TileSet tileset;

	public Map(String tileSet, ArrayList<String> mapData) {

		tileset = TILE_SETS.get("grass");
		players = new Player[maxPlayers];

		mapWidth = mapData.get(0).split(" ").length;
		mapHeight = mapData.size();
		
		System.out.print("Loaded map with size: ");
		System.out.print(mapWidth);
		System.out.print(" x ");
		System.out.print(mapHeight);
		
		String[][] tileData = new String[mapHeight][mapWidth];

		for(int i = 0; i < mapHeight; i++) {
			tileData[i] = mapData.get(i).split(" ");
		}
		loadTiles(tileData);
	}

	public void loadPlayers() {
		System.out.println("Loading player 0 with default attribs");
		players[0] = new Player(0, this, false, new Color(255, 0, 0), new Viking(), new Point(300, 300));
	}
	
	public void loadTiles(String[][] tileData) {
		
		tiles = new Tile[mapWidth][mapHeight];
		
		for(int i = 0; i < tileData.length; i++) {
			for(int j = 0; j < tileData[i].length; j++) {
				String tileType = "";
                switch (tileData[i][j]) {
                case "0":
                    tileType = "darkGrass";
                    break;
                case "1":
                    tileType = "darkWater";
                    break;
                }
                tiles[j][i] = new Tile(this, tileset.getTile(tileType), j * TILE_WIDTH, i * TILE_HEIGHT);
			}
		}
	}

	public TileSet getTileSet() {
		return tileset;
	}

	public void renderTiles(Graphics g) {
		Camera currentCamera = players[0].getCamera();
		
		float xOffset = currentCamera.getPos().getX();
		float yOffset = currentCamera.getPos().getY();
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				Point tilePos = tiles[i][j].getPos();
				boolean inCam = currentCamera.getView().contains(tilePos.getX(), tilePos.getY());
				if (inCam)
					tiles[i][j].getImage().draw(tilePos.getX() - xOffset, tilePos.getY() - yOffset);
			}
		}
	}

	public String getName() {
		return mapName;
	}

	public Player[] getPlayers() {
		return players;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

}
