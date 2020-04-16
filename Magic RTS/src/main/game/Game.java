package main.game;

import static main.util.ResourceLoader.MAPS;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.entities.Entity;
import main.entities.ai.pathfinding.NodeMap;
import main.game.map.Map;
import main.game.player.Camera;
import main.game.player.Player;
import main.input.Mouse;

public class Game {

	/*
	 * A game class to handle the rendering and updating of entities and such within a given game
	 */
	
	private boolean renderPathing = false;
	
	public static String MAP_TO_LOAD = "";
	
	private Map map;
	private NodeMap nm;
	
	private boolean started = false;
	
	private Player controllingPlayer;
	
	private static Camera currentView;
	
	public void init() {
		System.err.println(MAP_TO_LOAD);
		System.err.println(MAPS.keySet().toString());
		map = MAPS.get(MAP_TO_LOAD);
		map.init(this);
		
		controllingPlayer = map.getControlledPlayer();
		
		currentView = controllingPlayer.getCamera();
		
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
		
		//Render player stuff
		map.getControlledPlayer().render(g);
		
		
		//Revert Camera transformations to draw the UI
		g.scale((float)(1/curCamera.getZoom()), (float)(1/curCamera.getZoom()));
		g.translate(xOffset, yOffset);
		
		renderUI(g);
		
	}
	
	public static Point UIToObject(Point point, Camera c) {
		//(ui + offset)/zoom
		Point offset = c.getPos();

		float newx = (point.getX() + offset.getX()) / c.getZoom();
		float newy = (point.getY() + offset.getY()) / c.getZoom();
		
		return new Point(newx, newy);
	}
	
	public static Point objectToUI(Point point, Camera c) {
		//UI to Game -> ui * zoom - offset
		float newx = point.getX() * c.getZoom() - c.getPos().getX();
		float newy = point.getY() * c.getZoom() - c.getPos().getY();
		
		return new Point(newx, newy);
	}
	
	public void renderUI(Graphics ui) {
		
		if(map.getControlledPlayer() != null)
			map.getControlledPlayer().renderUI(ui);

		Mouse m = Engine.getMouse();
		
		ui.drawString("Game", 30, 45);
		ui.drawString(Float.toString(m.getPos().getX()), 30, 60);
		ui.drawString(Float.toString(m.getPos().getY()), 30, 75);
		
		ui.drawString("UI", 120, 45);
		ui.drawString(Float.toString(m.getScreenPos().getX()), 120, 60);
		ui.drawString(Float.toString(m.getScreenPos().getY()), 120, 75);
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

	
	// Getters and Setters
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

	public static Camera getCurrentView() {
		return currentView;
	}

	public static void setCurrentView(Camera c) {
		currentView = c;
	}

	public Player getControllingPlayer() {
		return controllingPlayer;
	}
	
}
