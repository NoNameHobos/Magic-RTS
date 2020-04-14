package main.game;

import static main.util.ResourceLoader.MAPS;
import static main.GameConstants.TW_RENDER;
import static main.GameConstants.TH_RENDER;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.entities.Entity;
import main.entities.ai.pathfinding.NodeMap;
import main.game.map.Map;
import main.input.Mouse;
import main.input.Selector;
import main.player.Camera;

public class Game {

	/*
	 * A game class to handle the rendering and updating of entities and such within a given game
	 */
	
	private boolean renderPathing = true;
	
	private Map map;
	private NodeMap nm;
	
	private String mapName;
	private boolean started = false;
	
	public Game(String mapName) {
		this.mapName = mapName;
	}
	
	public void init() {
		map = MAPS.get(mapName);
		map.init(this);
		nm = NodeMap.createNodeMap(map.getMapData());
		System.out.println("Loaded map: Grass Map");
		started = true;
	}
	
	public void render(Graphics g) {

		Camera curCamera = map.getControlledPlayer().getCamera();
		Point offset = curCamera.getPos();
		float xOffset = offset.getX();
		float yOffset = offset.getY();

		//Apply Camera transformations
		g.translate(-xOffset, -yOffset);
		g.scale((float)curCamera.getZoom(), (float)curCamera.getZoom());
		
		//Render all the tiles
		map.renderTiles(g);
		if(renderPathing) {
			nm.render(g);
		}
		
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
		if(map.getControlledPlayer() != null)
			map.getControlledPlayer().render(g);
		
		//Revert Camera transformations
		g.scale((float)(1/curCamera.getZoom()), (float)(1/curCamera.getZoom()));
		g.translate(xOffset, yOffset);
		
		if(map.getControlledPlayer() != null)
			map.getControlledPlayer().renderUI(g);

		Mouse m = Engine.getMouse();
		g.drawString(Float.toString(m.getPos().getX()), 30, 60);
		g.drawString(Float.toString(m.getPos().getY()), 30, 75);
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

	public boolean isRenderPathing() {
		return renderPathing;
	}

	public void setRenderPathing(boolean renderPathing) {
		this.renderPathing = renderPathing;
	}
	
}
