package main.engine.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.engine.State;
import main.entities.Entity;
import main.entities.Unit;
import main.entities.unit.Axeman;
import main.entities.unit.Golem;

public class GameState extends State {

	private ArrayList<Unit> mobs = new ArrayList<Unit>();
	
	private Unit axeman;
	
	public GameState(String name) {
		super(name);
	}

	//Main Step Event for Game State
	public void tick() {
		if(init)
			initState();
		//Update Entities
		ArrayList<Entity> entities = Entity.getEntities();
		for(int j = 0; j < entities.size(); j++) {
			entities.get(j).pollEntity();
		}
	}

	//Render the Game State
	public void render(Graphics g) {
		g.setBackground(new Color(0, 40, 0));
		g.setColor(Color.white);
		//Render Entities
		ArrayList<Entity> entities = Entity.getEntities();
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
	}

	@Override
	public void initState() {
		axeman = new Axeman(null, 500, 400);
		init = false;
	}

}
