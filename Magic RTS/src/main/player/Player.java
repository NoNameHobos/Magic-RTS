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
	
	private Faction faction;
	
	public Player(int playerID, boolean AI, Color playerColor, Faction faction, Point spawn) {
		this.playerColor = playerColor;
		isAI = AI;
		this.faction = faction;
		this.playerID = playerID;
		this.spawn = spawn;
		buildings = new ArrayList<Building>();
		units = new ArrayList<Unit>();
		
		buildings.add(new House(this, spawn));
		units.add(new Axeman(this, spawn.getX() + 30, spawn.getY() + 30));
		
		playerCamera = new Camera(spawn.getX(), spawn.getY(), Engine.getWIDTH(), Engine.getHEIGHT());
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
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

	public Camera getPlayerCamera() {
		return playerCamera;
	}
}
