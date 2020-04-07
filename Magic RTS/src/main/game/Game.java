package main.game;

import static main.util.ResourceLoader.MAPS;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.entities.Entity;
import main.game.map.Map;
import main.player.Camera;

public class Game {

	/*
	 * A game class to handle the rendering and updating of entities and such within a given game
	 */
	
	private Map map;
	private String mapName;
	
	private boolean started = false;
	
	public Game(String mapName) {
		this.mapName = mapName;
	}
	
	public void init() {
		map = MAPS.get(mapName);
		System.out.println("Loaded map: Grass Map");
		map.loadPlayers();
		started = true;
	}
	
	public void render(Graphics g) {

		map.renderTiles(g);
		
		Camera curCamera = map.getPlayers()[0].getCamera();
		Point offset = curCamera.getPos();
		float xOffset = offset.getX();
		float yOffset = offset.getY();
		
		//Render Entities
		ArrayList<Entity> entities = Entity.getEntities();
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g, xOffset, yOffset);
		}
		
		g.setColor(Color.white);
		for(int i = 0; i < map.getPlayers().length; i++) {
			map.getPlayers()[0].render(g);
			map.getPlayers()[0].getUI().render(g);
		}
	}
	
	public void tick() {
		//Update Entities
		ArrayList<Entity> entities = Entity.getEntities();
		for(int j = 0; j < entities.size(); j++) {
			entities.get(j).tick();
		}
		
		for(int i = 0; i < map.getPlayers().length; i++) {
			map.getPlayers()[0].tick();
		}
	}

	public boolean isStarted() {
		return started;
	}
	
	public Map getMap() {
		return map;
	}
	
}
