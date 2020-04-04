package main.game.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.State;
import main.entities.Entity;
import main.entities.Unit;
import main.player.Faction;
import main.player.Player;
import main.player.factions.Viking;

public class GameState extends State {

	private ArrayList<Unit> mobs = new ArrayList<Unit>();
	
	private Player player1;
	
	private static final Faction viking = new Viking();
	
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
			entities.get(j).tick();
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
		ArrayList<Player> players = Player.getPlayers();
		for(int i = 0; i < players.size(); i++) {
			players.get(i).render(g);
		}
	}

	@Override
	public void initState() {
		init = false;
		
		player1 = new Player(0, false, new Color(255, 0, 0), viking, new Point(300, 300));
	}

}
