package main.game.map;

import static main.util.ResourceLoader.TILE_HEIGHT;
import static main.util.ResourceLoader.TILE_SETS;
import static main.util.ResourceLoader.TILE_WIDTH;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.player.Camera;
import main.player.Player;
import main.player.factions.Viking;

public class Map {

	public final static String[] PLAYER_NAMES = {
			"GARY",
			"BEN",
			"TOM",
			"TODD",
			"DAN",
			"DANIELLE"
	};
	
	private Tile[][] tiles;

	private Player controlledPlayer;
	private Player[] players;
	private int maxPlayers = 8;
	private int mapWidth, mapHeight;

	private String mapName = "Grass Map";

	private TileSet tileset;

	private ArrayList<String> mapData;
	
	public Map(String tileSet, ArrayList<String> mapData) {

		tileset = TILE_SETS.get(tileSet);
		players = new Player[maxPlayers];

		mapWidth = mapData.get(0).split(" ").length;
		mapHeight = mapData.size();
		
		System.out.print("Loaded map with size: ");
		System.out.print(mapWidth);
		System.out.print(" x ");
		System.out.print(mapHeight);
		this.mapData = mapData;
	}
	
	public void init() {
		String[][] tileData = new String[mapHeight][mapWidth];

		for(int i = 0; i < mapHeight; i++) {
			tileData[i] = mapData.get(i).split(" ");
		}
		loadPlayers();
		loadTiles(tileData);
	}
	
	public void loadPlayers() {
		System.out.println("Loading player 0 with default attribs");
		controlledPlayer = Player.createPlayer("Bryn", 0, this, new Color(0, 255, 0), new Viking(), new Point(100, 300));
		players[0] = controlledPlayer;
		Random r = new Random();
		for(int i = 1; i < players.length; i++) {
			String n = PLAYER_NAMES[Math.round(r.nextInt(PLAYER_NAMES.length))];
			players[i] = Player.createPlayer(n, i, this, new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)), new Viking(), new Point(300+r.nextInt(3000), r.nextInt(1000)));
		}
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
                tiles[j][i] = new Tile(tileset.getTile(tileType), j * TILE_WIDTH, i * TILE_HEIGHT);
			}
		}
	}

	public TileSet getTileSet() {
		return tileset;
	}

	public void renderTiles(Graphics g) {
		Camera currentCamera = controlledPlayer.getCamera();
		float xOffset = currentCamera.getPos().getX();
		float yOffset = currentCamera.getPos().getY();
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				Point tilePos = tiles[i][j].getPos();
				boolean inCam = currentCamera.getRenderRect().contains(tilePos.getX(), tilePos.getY());
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

	public Player getControlledPlayer() {
		return controlledPlayer;
	}

	public void setControlledPlayer(Player controlledPlayer) {
		this.controlledPlayer = controlledPlayer;
	}

}
