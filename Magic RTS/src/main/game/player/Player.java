package main.game.player;

import static main.GameConstants.TH_RENDER;
import static main.GameConstants.TW_RENDER;
import static main.game.player.Faction.FACTIONS;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.Game;
import main.game.entities.Building;
import main.game.entities.Entity;
import main.game.entities.buildings.House;
import main.game.entities.buildings.Townhall;
import main.game.entities.selectables.Unit;
import main.game.map.Map;
import main.game.player.factions.FType;
import main.game.ui.UI;

public class Player {
	
	private Color playerColor;
	private String name;
	private Point spawn;
	
	private ArrayList<Entity> selected;
	
	private ArrayList<Building> buildings;
	private ArrayList<Unit> units;
	
	private boolean isAI;
	
	//private Point spawn;
	private Camera playerCamera;
	private UI ui;
	private Game game;
	
	private int playerID;
	private Map map;
	
	private Faction faction;
	
	private Selector selector;
	
	private Player(String name, int playerID, Map map, boolean AI, Color playerColor, Faction faction, Point s) {
		System.out.println(faction);
		this.game = map.getGame();
		this.playerColor = playerColor;
		this.isAI = AI;
		this.faction = faction;
		this.playerID = playerID;

		this.map = map;
		this.name = name;
		this.spawn = s;
		
		buildings = new ArrayList<Building>();
		units = new ArrayList<Unit>();
		selected = new ArrayList<Entity>();

		buildings.add(new House(this, new Point(spawn.getX() + TW_RENDER * 5, spawn.getY() + TH_RENDER * 5)));
		buildings.add(new Townhall(this, new Point(spawn.getX(), spawn.getY())));
		
		if(map.getFocusedPlayer() == this) selector = new Selector(this, Engine.getInput());
	}

	public void tick() {
		if(map.getFocusedPlayer() == this) {
			if(playerCamera != null) playerCamera.update();
			if(selector == null)
				selector = new Selector(this, Engine.getInput());
			selector.update();
		}
		if(Engine.getInput().isKeyPressed(Input.KEY_P)) {
			if(game.isRenderPathing())
				game.setRenderPathing(false);
			else game.setRenderPathing(true);
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		if(selector != null) {
			if(selector.getStartDrag() != null && selector.getEndDrag() != null) {
				Rectangle selectBox = selector.getSelectBox();
				g.draw(selectBox);
			}
		}
	}
	
	public void renderUI(Graphics g) {
		if(ui != null) ui.render(g);
		else System.out.println("UI is null: " + name);
	}

	/**
	 * Used to create a new player
	 * @param spawn Point on the map to spawn on (in terms of tiles)
	 * @param name  Player name
	 * @param map Map to spawn on
	 * @param color Player color
	 * @param faction Player faction (from FType enum)
	 * @param id Player ID
	 * @param ai isAI?
	 * @return returns player object
	 */
	public static Player createPlayer(Point spawn, String name, Map map, Color color, FType faction, int id, boolean ai) {
		System.out.println("Spawned player at: (" + spawn.getX() + ", " + spawn.getY() + ")");
		Point tSpawn = new Point(spawn.getX() * TW_RENDER, spawn.getY() * TH_RENDER);
		return new Player(name, id, map, ai, color, FACTIONS.get(faction), tSpawn);
	}
	/**
	 * Used to create a new non-ai player
	 * @param spawn Point on the map to spawn on (in terms of tiles)
	 * @param name Player name
	 * @param map Map to spawn on
	 * @param color Player color
	 * @param faction Player faction (string)
	 * @param id Player ID
	 * @return returns player object
	 */
	public static Player createPlayer(Point spawn, String name, Map map, Color color, FType faction, int id) {
		return createPlayer(spawn, name, map, color, faction, id, false);
	}
		
	// Getters and Setters
	public Color getPlayerColor() {
		return playerColor;
	}

	public ArrayList<Entity> getSelected() {
		return selected;
	}
	
	public void setSelected(ArrayList<Entity> sel) {
		selected = sel;
	}
	
	public boolean isAI() {
		return isAI;
	}

	public int getPlayerID() {
		return playerID;
	}

	public Faction getFaction() {
		return faction;
	}

	public ArrayList<Building> getBuildings() {
		return buildings;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public Camera getCamera() {
		return playerCamera;
	}

	public void setCamera(Camera playerCamera) {
		this.playerCamera = playerCamera;
	}

	public Map getMap() {
		return map;
	}

	public UI getUI() {
		return ui;
	}
	
	public void setUI(UI ui) {
		this.ui = ui;
	}
	
	public String getName() {
		return name;
	}

	public Point getSpawn() {
		return spawn;
	}

	public Game getGame() {
		return game;
	}
}
