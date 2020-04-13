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
		map.init();
		System.out.println("Loaded map: Grass Map");
		started = true;
	}
	
	public void render(Graphics g) {

		Camera curCamera = map.getControlledPlayer().getCamera();
		Point offset = curCamera.getPos();
		float xOffset = offset.getX();
		float yOffset = offset.getY();

		g.translate(-xOffset, -yOffset);
		map.renderTiles(g);
		
		//Render Entities
		ArrayList<Entity> entities = Entity.getEntities();
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
		
		g.setColor(Color.white);
		for(int i = 0; i < map.getPlayers().length; i++) {
			if(map.getPlayers()[i] != null) {
				map.getPlayers()[i].render(g);
				
			}
		}
		g.translate(xOffset, yOffset);
		if(map.getControlledPlayer() != null)
			map.getControlledPlayer().getUI().render(g);
	}
	
	public void tick() {
		//Update Entities
		ArrayList<Entity> entities = Entity.getEntities();
		for(int j = 0; j < entities.size(); j++) {
			entities.get(j).tick();
		}
		
		for(int i = 0; i < map.getPlayers().length; i++) {
			if(map.getPlayers()[i] != null) {
				map.getPlayers()[i].tick();
			}
		}
	}

	public boolean isStarted() {
		return started;
	}
	
	public Map getMap() {
		return map;
	}
	
}
