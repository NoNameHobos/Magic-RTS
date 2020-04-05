package main.player;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.entities.Building;
import main.entities.Entity;
import main.entities.Unit;
import main.entities.buildings.House;
import main.entities.unit.Axeman;
import main.game.map.Map;

public class Player {
	
	private Color playerColor;
	
	private ArrayList<Entity> selectedGroup;
	private Entity selected;
	
	private ArrayList<Building> buildings;
	private ArrayList<Unit> units;
	
	private boolean isAI;
	
	private Point spawn;
	private Camera playerCamera;
	
	private int playerID;
	private Map map;
	
	private Faction faction;
	
	public Player(int playerID, Map map, boolean AI, Color playerColor, Faction faction, Point spawn) {
		this.playerColor = playerColor;
		isAI = AI;
		this.faction = faction;
		this.playerID = playerID;
		this.spawn = spawn;
		this.map = map;
		
		buildings = new ArrayList<Building>();
		units = new ArrayList<Unit>();
		
		buildings.add(new House(this, spawn));
		units.add(new Axeman(this, spawn.getX() + 30, spawn.getY() + 30));
		
		playerCamera = new Camera(map, spawn.getX() - Engine.getWIDTH()/2, spawn.getY() - Engine.getHEIGHT()/2, Engine.getWIDTH(), Engine.getHEIGHT());
	}
	
	public void tick() {
		playerCamera.update();
	}
	
	public void render(Graphics g) {
		Point p = playerCamera.getPos();
		g.drawString("X: " + Float.toString(p.getX()), 20, 20);
		g.drawString("Y: " + Float.toString(p.getY()), 20, 40);
	}
	
	public Color getPlayerColor() {
		return playerColor;
	}

	public Entity getSelected() {
		return selected;
	}

	public ArrayList<Entity> getSelectedGroup() {
		return selectedGroup;
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
}
