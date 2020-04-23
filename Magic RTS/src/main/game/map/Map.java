package main.game.map;

import static main.GameConstants.TH_RENDER;
import static main.GameConstants.TW_RENDER;
import static main.util.ResourceLoader.TILE_SETS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.Game;
import main.game.player.Camera;
import main.game.player.Faction;
import main.game.player.Player;
import main.game.player.factions.Steampunk;
import main.game.player.factions.Viking;
import main.game.ui.UI;

public class Map {

	public final static HashMap<String, Faction> FACTIONS = new HashMap<String, Faction>();

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
	private Camera mainCamera;
	private UI ui;

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
		if (tiles == null) {
			String[][] tileData = new String[mapHeight][mapWidth];

			for (int i = 0; i < mapHeight; i++) {
				tileData[i] = mapData.get(i).split(" ");
			}

			this.game = game;

			FACTIONS.put("vikings", new Viking());
			FACTIONS.put("steampunk", new Steampunk());

			loadPlayers();
			loadTiles(tileData);
		}
	}

	public void loadPlayers() {
		players = new Player[spawns.length];
		focusedPlayer = Player.createPlayer("BRYN", 0, this, new Color(0, 0, 255), FACTIONS.get("vikings"),
				spawns[0]);
		System.out.println("Spawned player at: (" + spawns[0].getX() + ", " + spawns[0].getY() + ")");
		players[0] = focusedPlayer;

		mainCamera = new Camera(this, focusedPlayer.getSpawn(), Engine.getWIDTH(), Engine.getHEIGHT());

		//Create the UI
		ui = new UI(focusedPlayer);
		mainCamera.setUI(ui);
		
		focusedPlayer.setCamera(mainCamera);

		for (int i = 1; i < players.length; i++) {
			Random r = new Random();
			String n = PLAYER_NAMES[Math.round(r.nextInt(PLAYER_NAMES.length))];
			System.out.println("Spawned player at: (" + spawns[i].getX() + ", " + spawns[i].getY() + ")");
			players[i] = Player.createPlayer(n, i, this, new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)),
					FACTIONS.get("steampunk"), spawns[i]);
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
				tiles[j][i] = new Tile(tileset.getTile(tileType), j * TW_RENDER, i * TH_RENDER);
			}
		}
	}

	public TileSet getTileSet() {
		return tileset;
	}

	public void renderTiles(Graphics g) {

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				Point tilePos = tiles[i][j].getPos();

				float rendX = tilePos.getX();
				float rendY = tilePos.getY();

				boolean inCam = mainCamera.getRenderRect().contains(rendX, rendY);
				if (inCam
				// && !controlledPlayer.getUI().contains(Game.UIToObject(new Point(rendX,
				// rendY), currentCamera))
				) {
					Image image = tiles[i][j].getImage();

					float width = TW_RENDER;
					float height = TH_RENDER;

					image.draw((float) Math.floor(rendX), (float) Math.floor(rendY), width, height);
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

}
