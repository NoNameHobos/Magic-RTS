package main.game;

import static main.util.ResourceLoader.MAPS;

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
	
	private boolean renderPathing = false;
	
	private Map map;
	private NodeMap nm;
	
	private String mapName;
	
	private Selector dragListener;
	
	private boolean started = false;
	
	public Game(String mapName) {
		this.mapName = mapName;
	}
	
	public void init() {
		map = MAPS.get(mapName);
		map.init();
		nm = NodeMap.createNodeMap(map.getMapData());
		System.out.println("Loaded map: Grass Map");
		started = true;
		dragListener = new Selector(Engine.getInput());
		
	}
	
	public void render(Graphics g) {

		Camera curCamera = map.getControlledPlayer().getCamera();
		Point offset = curCamera.getPos();
		float xOffset = offset.getX();
		float yOffset = offset.getY();

		g.translate(-xOffset, -yOffset);
		g.scale((float)curCamera.getZoom(), (float)curCamera.getZoom());
		map.renderTiles(g);
		if(renderPathing)
			nm.render(g);
		
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
		g.scale((float)(1/curCamera.getZoom()), (float)(1/curCamera.getZoom()));
		g.translate(xOffset, yOffset);
		if(map.getControlledPlayer() != null)
			map.getControlledPlayer().getUI().render(g);
		g.setColor(Color.red);
		if(dragListener.getStartDrag() != null && dragListener.getEndDrag() != null) {
			Point p1 = dragListener.getStartDrag();
			Point p2 = dragListener.getEndDrag();
			float width = p2.getX() - p1.getX();
			float height = p2.getY() - p1.getY();
			g.drawRect(p1.getX(), p1.getY(), width, height);
		}
		
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
	
}
