package main.game.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.State;
import main.entities.Entity;
import main.entities.Unit;
import main.game.map.Map;
import main.player.Faction;
import main.player.Player;
import main.player.factions.Viking;

import static main.util.ResourceLoader.*;

public class GameState extends State {

	private ArrayList<Unit> mobs = new ArrayList<Unit>();
	
	private Map map;
	
	public GameState() {
		super("Game");
	}
	
	public void init() {
		map = MAPS.get("map1");
		System.out.println("Loaded map: Grass Map");
		map.loadPlayers();
	}
	
	//Main Step Event for Game State
	public void tick() {
		//Update Entities
		ArrayList<Entity> entities = Entity.getEntities();
		for(int j = 0; j < entities.size(); j++) {
			entities.get(j).tick();
		}
	}

	//Render the Game State
	public void render(Graphics g) {
		g.setBackground(Color.black);
		//System.out.println(map.getName());
		if(map != null) map.renderTiles(g);
		//Render Entities
		ArrayList<Entity> entities = Entity.getEntities();
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
		/*
		for(int i = 0; i < players.size(); i++) {
			players.get(i).render(g);
		}*/
	}
}
