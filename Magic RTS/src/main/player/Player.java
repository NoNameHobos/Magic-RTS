package main.player;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.entities.Building;
import main.entities.Entity;
import main.entities.Unit;
import main.entities.buildings.House;
import main.util.ResourceLoader;

public class Player {

	public static final ArrayList<Player> PLAYERS = new ArrayList<Player>();
	
	private Color playerColor;
	private ArrayList<Entity> selected;
	
	private ArrayList<Building> buildings;
	private ArrayList<Unit> units;
	
	private boolean isAI;
	
	private Point spawn;
	
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
		
		PLAYERS.add(this);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
	
	public Color getPlayerColor() {
		return playerColor;
	}

	public ArrayList<Entity> getSelected() {
		return selected;
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

	public static ArrayList<Player> getPlayers() {
		return PLAYERS;
	}
}
