package main.game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.engine.State;
import main.game.Game;

public class GameState extends State {

	private Game game;
	
	public GameState() {
		super("Game");
	}
	
	//Main Step Event for Game State
	public void tick() {
		if(game.isStarted()) {
			game.tick();
		}
	}

	//Render the Game State
	public void render(Graphics g) {
		g.setBackground(Color.black);
		if(game.isStarted()) {
			game.render(g);
		}
	}
	
	@Override
	public void init() {
		game = new Game("map1");
		System.out.println("Starting new game on map1"); //Place holder
		game.init();
	}
	
	public Game getGame() {
		return game;
	}
}
