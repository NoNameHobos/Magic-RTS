package main.game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.game.Game;
import main.game.State;

public class GameState extends State {

	private Game game;
	
	public GameState() {
		super("Game");
	}
	
	@Override
	protected void init() {
		game = new Game();
		game.init();
	}
	
	@Override
	public void draw(Graphics g) {
		g.setBackground(Color.black);
		if(game.isStarted()) {
			game.render(g);
		}
		
	}

	@Override
	public void step() {
		if(game.isStarted()) {
			game.tick();
		}
		
	}
	
	public Game getGame() {
		return game;
	}

}
