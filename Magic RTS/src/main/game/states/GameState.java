package main.game.states;

import static main.util.ResourceLoader.MAPS;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.State;
import main.entities.Entity;
import main.entities.Unit;
import main.game.map.Map;
import main.player.Camera;

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
		
		for(int i = 0; i < map.getPlayers().length; i++) {
			map.getPlayers()[0].tick();
		}
	}

	//Render the Game State
	public void render(Graphics g) {
		g.setBackground(Color.black);
		
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
		}
	}

	public Map getMap() {
		// TODO Auto-generated method stub
		return map;
	}
}
