package main.game.map;

import static main.util.ResourceLoader.TILE_HEIGHT;
import static main.util.ResourceLoader.TILE_SETS;
import static main.util.ResourceLoader.TILE_WIDTH;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.player.Player;
import main.player.factions.Viking;

public class Map {

	private Tile[][] tiles;
	
	private Player[] players;
	private int maxPlayers = 8;
	private int mapWidth, mapHeight;
	
	private String mapName = "Grass Map";
	
	private TileSet tileset;
	
	public Map(String tileSet, int mapWidth, int mapHeight) {

		tileset = TILE_SETS.get("grass");
		players = new Player[maxPlayers];
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		loadTiles();
	}
	
	public Map(TileSet tileSet, int mapWidth, int mapHeight) {
		
		this.tileset = tileSet;
		players = new Player[maxPlayers];
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		loadTiles();
	}
	
	public Map(String tileSet, ArrayList<String> mapData) {
		
		tileset = TILE_SETS.get("grass");
		players = new Player[maxPlayers];

		mapWidth = mapData.get(0).split(" ").length - 1;
		mapHeight = mapData.size();
		String[][] tileData = new String[mapWidth][mapHeight];
		
		for(int i = 0; i < tileData.length; i++) {
			tileData[i] = mapData.get(i).split(" ");
		}
		
		loadTiles(tileData);
	}

	public void loadPlayers() {
		System.out.println("Loading player 0 with default attribs");
		players[0] = new Player(0, false, new Color(255, 0, 0), new Viking(), new Point(300, 300));
	}
	
	public void loadTiles() {
		tiles = new Tile[mapWidth][mapHeight];
		System.out.println(mapWidth);
		System.out.println(mapHeight);
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				System.out.println("Creating tile @" + Integer.toString(i*TILE_WIDTH));
				tiles[i][j] = new Tile(this, tileset.getTile(0, 0), i*TILE_WIDTH,j*TILE_HEIGHT);
			}
		}
	}
	
	public void loadTiles(String[][] tileData) {
		tiles = new Tile[tileData.length][tileData[0].length];
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				String tileType = "";
				switch(tileData[i][j]) {
					case "0":
						tileType = "darkGrass";
						break;
					case "1":
						tileType = "darkWater";
						break;
				}
				tiles[i][j] = new Tile(this, tileset.getTile(tileType), i*TILE_WIDTH,j*TILE_HEIGHT);
			}
		}
	}
	
	
	public TileSet getTileSet() {
		return tileset;
	}
	
	public void renderTiles(Graphics g) {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				Point tilePos = tiles[i][j].getPos();
				
				tiles[i][j].getImage().draw(tilePos.getX(), tilePos.getY());
			}
		}
	}
	
	public String getName() {
		return mapName;
	}
	
}
