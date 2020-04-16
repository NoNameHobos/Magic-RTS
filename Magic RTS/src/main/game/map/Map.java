package main.game.map;

import static main.GameConstants.TH_RENDER;
import static main.GameConstants.TW_RENDER;
import static main.util.ResourceLoader.TILE_SETS;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.game.Game;
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
	private Point[] spawns;
	private int maxPlayers;
	
	private int mapWidth, mapHeight;

	private String mapName = "Grass Map";

	private TileSet tileset;

	private ArrayList<String> mapData;
	
	private Game game;
	
	public Map(String title, String tileSet, ArrayList<String> mapData) {
		mapName = title;
		tileset = TILE_SETS.get(tileSet);
		players = new Player[maxPlayers];

		mapWidth = mapData.get(0).split(" ").length;
		mapHeight = mapData.size();
		
		System.out.print("Loaded map with size: ");
		System.out.print(mapWidth);
		System.out.print(" x ");
		System.out.println(mapHeight);
		this.mapData = mapData;
	}
	
	public void init(Game game) {
		if(tiles == null) {
			String[][] tileData = new String[mapHeight][mapWidth];
	
			for(int i = 0; i < mapHeight; i++) {
				tileData[i] = mapData.get(i).split(" ");
			}
			
			this.game = game;
			
			loadPlayers();
			loadTiles(tileData);
		}
	}
	
	public void loadPlayers() {
		players = new Player[spawns.length];
		
		controlledPlayer = Player.createPlayer("Bryn", 0, this, new Color(0, 255, 0), new Viking(), spawns[0]);
		System.out.println("Spawned player at: (" + spawns[0].getX() + ", " + spawns[0].getY() + ")");
		players[0] = controlledPlayer;
		
		for(int i = 1; i < players.length; i++) {
			Random r = new Random();
			String n = PLAYER_NAMES[Math.round(r.nextInt(PLAYER_NAMES.length))];
			System.out.println("Spawned player at: (" + spawns[i].getX() + ", " + spawns[i].getY() + ")");
			players[i] = Player.createPlayer(n, i, this, new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)), new Viking(), spawns[i]);
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
                case "2":
                	tileType = "lightWater";
                    break;
                case "3":
                	tileType = "lightGrass";
                    break;
                case "4":
                	tileType = "stone";
                    break;
                }
                tiles[j][i] = new Tile(tileset.getTile(tileType), j * TW_RENDER, i * TH_RENDER);
			}
		}
	}

	public TileSet getTileSet() {
		return tileset;
	}

	public void renderTiles(Graphics g) {
		
		Camera currentCamera = controlledPlayer.getCamera();
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				Point tilePos = tiles[i][j].getPos();
				
				float rendX = tilePos.getX();
				float rendY = tilePos.getY();

				boolean inCam = currentCamera.getRenderRect().contains(rendX * currentCamera.getZoom(), rendY * currentCamera.getZoom());
				if (inCam) {
					Image image = tiles[i][j].getImage();
					
					float width = TW_RENDER;
					float height = TH_RENDER;
					
					image.draw(rendX, rendY, width, height);
				}
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

	public ArrayList<String> getMapData() {
		return mapData;
	}

	public Game getGame() {
		return game;
	}

	public void setSpawns(Point[] spawns) {
		this.spawns = spawns;
	}

}
