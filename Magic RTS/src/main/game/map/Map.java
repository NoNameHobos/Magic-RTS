package main.game.map;

import static main.GameConstants.TH_RENDER;
import static main.GameConstants.TW_RENDER;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.Game;
import main.game.entities.ai.pathfinding.NodeMap;
import main.game.entities.resources.ManaNode;
import main.game.player.Camera;
import main.game.player.Player;
import main.game.player.factions.FType;
import main.game.ui.UI;

public class Map {

	public final static String[] PLAYER_NAMES = { "GARY", "BEN", "TOM", "TODD", "DAN", "DANIELLE" };

	private Tile[][] tiles;

	private Player[] players;
	private Point[] spawns;
	private int maxPlayers;

	private int mapWidth, mapHeight;

	private String mapName = "Grass Map";

	private TileSet tileset;

	private ArrayList<String> mapData;

	private Game game;

	private Player focusedPlayer;
	private Player neutralPlayer;

	private Camera mainCamera;
	private UI ui;

	private NodeMap nodeMap;
	
	// TODO: Merge constructor and create static load function
	// For a new map
	public Map(String title) {
		
	}
	
	// For a loaded map
	public Map(String title, String tileSet, ArrayList<String> mapData) {
		mapName = title;
		tileset = Engine.RES.getTileSet(tileSet);
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
		if (tiles == null) {
			this.game = game;
			
			String[][] tileData = new String[mapHeight][mapWidth];

			for (int i = 0; i < mapHeight; i++) {
				tileData[i] = mapData.get(i).split(" ");
			}
			
			nodeMap = NodeMap.createNodeMap(this);
			loadTiles(tileData);
			loadPlayers();
		}
	}

	public void loadPlayers() {
		System.out.println("Loading players");
		int NODES_PER_SITE = 1;

		players = new Player[spawns.length];
		neutralPlayer = Player.createPlayer(new Point(0, 0), "NEUTRAL", this, Color.green, FType.Wild, 0);
		focusedPlayer = Player.createPlayer(spawns[0], "BRYN", this, new Color(0, 0, 255), FType.Viking, 0);
		
		players[0] = focusedPlayer;
		spawnNodes(spawns[0], NODES_PER_SITE, 180 / 8, "MANA");

		mainCamera = new Camera(this, focusedPlayer.getSpawn(), Engine.getWIDTH(), Engine.getHEIGHT());

		// Create the UI
		ui = new UI(focusedPlayer);
		mainCamera.setUI(ui);

		for (int i = 1; i < players.length; i++) {
			Random r = new Random();
			String str = PLAYER_NAMES[r.nextInt(PLAYER_NAMES.length - 1)];
			Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
			
			players[i] = Player.createPlayer(spawns[i], str, this, c, FType.Steampunk,  i);
			
			spawnNodes(spawns[i], NODES_PER_SITE, 180 / 8, "MANA");
		}

		focusedPlayer.setCamera(mainCamera);

	}

	public void spawnNodes(Point centrePoint, int density, float sep, String nodeType) {
		for (int i = 0; i < density; i++) {
			float x = (float) Math.cos(Math.toRadians(sep * i)) * Engine.RES.getSprite("node_mana").getWidth() * 2
					+ (centrePoint.getX() + 2) * TW_RENDER;
			float y = (float) Math.sin(Math.toRadians(sep * i)) * (Engine.RES.getSprite("node_mana").getHeight())
					+ (centrePoint.getY() + 4) * TH_RENDER;
			switch (nodeType.toUpperCase()) {
			case "MANA":
				new ManaNode(this, new Point(x, y));
				break;
			}
		}
	}

	public void loadTiles(String[][] tileData) {

		tiles = new Tile[mapWidth][mapHeight];

		for (int i = 0; i < tileData.length; i++) {
			for (int j = 0; j < tileData[i].length; j++) {
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
				case "5":
					tileType = "darkDirt";
					break;
				}
				tiles[j][i] = new Tile(tileset.getTile(tileType), j * TW_RENDER, i * TH_RENDER, this);
			}
		}
	}

	public TileSet getTileSet() {
		return tileset;
	}

	public void renderTiles(Graphics g) {

		ArrayList<Tile> tile_array = new ArrayList<Tile>();

		for (Tile[] tileList : tiles) {
			for (Tile tile : tileList) {
				tile_array.add(tile);
			}
		}

		tile_array.sort(new SortByDepth());

		for (Tile tile : tile_array) {
			Point tilePos = tile.getPos();

			float rendX = tilePos.getX();
			float rendY = tilePos.getY();

			boolean inCam = mainCamera.getRenderRect().contains(rendX, rendY);
			if (inCam) {
				Image image = tile.getImage();

				float width = TW_RENDER + 1;
				float height = TH_RENDER + 1;

				image.draw((float) Math.floor(rendX), (float) Math.floor(rendY), width, height);
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

	public Player getFocusedPlayer() {
		return focusedPlayer;
	}

	public void setFocusedPlayer(Player controlledPlayer) {
		this.focusedPlayer = controlledPlayer;
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

	public Camera getMainCamera() {
		return mainCamera;
	}

	public Player getNeutralPlayer() {
		return neutralPlayer;
	}
	
	public NodeMap getNodeMap() {
		return nodeMap;
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}

	private class SortByDepth implements Comparator<Tile> {

		@Override
		public int compare(Tile t1, Tile t2) {
			return (int) (t2.getDepth() - t1.getDepth());
		}
	}	
}


