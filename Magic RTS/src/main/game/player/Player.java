package main.game.player;

import static main.GameConstants.TH_RENDER;
import static main.GameConstants.TW_RENDER;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.entities.Building;
import main.entities.Entity;
import main.entities.Unit;
import main.entities.buildings.House;
import main.entities.buildings.Townhall;
import main.entities.unit.Axeman;
import main.game.Game;
import main.game.map.Map;
import main.game.player.factions.Viking;
import main.game.ui.UI;

public class Player {
	
	private Color playerColor;
	private String name;
	
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
	
	public Player(String name, int playerID, Map map, boolean AI, Color playerColor, Faction faction, Point spawn) {
		this.playerColor = playerColor;
		this.isAI = AI;
		this.faction = faction;
		this.playerID = playerID;
		//this.spawn = spawn;
		this.map = map;
		this.game = map.getGame();
		this.name = name;

		
		buildings = new ArrayList<Building>();
		units = new ArrayList<Unit>();
		selected = new ArrayList<Entity>();
		
		if(map.getControlledPlayer() != null) {
			if(map.getControlledPlayer().getPlayerID() == playerID) {
				//Create the camera
				Point cP = new Point(spawn.getX(), spawn.getY());
				
				playerCamera = new Camera(map, cP, Engine.getWIDTH(), Engine.getHEIGHT());
				ui = new UI(this);
				playerCamera.setUI(ui);
			}
		}
		else {
			Point cP = new Point(spawn.getX(), spawn.getY());
		
			playerCamera = new Camera(map, cP, Engine.getWIDTH(), Engine.getHEIGHT());
			ui = new UI(this);
			playerCamera.setUI(ui);
		}
		units.add(new Axeman(this, spawn.getX() + TW_RENDER * 2, spawn.getY() + TH_RENDER * 2));
		buildings.add(new House(this, new Point(spawn.getX() + 40, spawn.getY() + 40)));
		if(faction == Map.FACTIONS.get("vikings")) buildings.add(new Townhall(this, spawn));

		selector = new Selector(this, Engine.getInput());
	}
	
	public void tick() {
		if(map.getControlledPlayer().getPlayerID() == playerID) {
			playerCamera.update();
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
		ui.render(g);
	}

	public static Player createPlayer(String name, int id, Map m, boolean ai, Color color, Faction f, Point spawn) {
		Point tSpawn = new Point(spawn.getX() * TW_RENDER, spawn.getY() * TH_RENDER);
		return new Player(name, id, m, ai, color, f, tSpawn);
	}
	
	public static Player createPlayer(String name, int id, Map m, Color color, Faction f, Point spawn) {
		Point tSpawn = new Point(spawn.getX() * TW_RENDER, spawn.getY() * TH_RENDER);
		return new Player(name, id, m, false, color, f, tSpawn);
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

	public Map getMap() {
		return map;
	}

	public UI getUI() {
		return ui;
	}
	
	public String getName() {
		return name;
	}
}
