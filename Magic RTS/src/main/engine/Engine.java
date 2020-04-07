package main.engine;

import java.io.File;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import main.game.states.GameState;
import main.game.states.LoadState;
import main.graphics.Display;
import main.input.Mouse;

public class Engine implements Game {

	public static Engine ENGINE;
	
	private static State currentState;
	public static String ABS_PATH = (new File("").getAbsolutePath() + "\\");
	
	private static int WIDTH, HEIGHT;
	
	private static Input input;
	private Mouse mouse;
	
	
	//Initialize States
	public static final State loadState = new LoadState();
	public static final State gameState = new GameState();
	
	
	public Engine(int WIDTH, int HEIGHT, String TITLE) {
		System.out.println("Initializing Engine..");
		ENGINE = this;
		Engine.WIDTH = WIDTH;
		Engine.HEIGHT = HEIGHT;
		new Display(this, WIDTH, HEIGHT);
		
	}
	
	public void init(GameContainer gc) throws SlickException {
		input = gc.getInput();
		mouse = new Mouse(input);
		
		//Start game here once everything is loaded
		Engine.currentState = loadState;
		Engine.currentState.init();
	}

	public void update(GameContainer gc, int i) throws SlickException {
		mouse.update();
		if(Engine.currentState == loadState) {
			
		}
		if(Engine.currentState != null)
			Engine.currentState.tick();
	}

	public void render(GameContainer gc, Graphics g) throws SlickException{
		if(Engine.currentState != null) {
			Engine.currentState.render(g);
		}
	}

	public boolean closeRequested() {
		System.exit(0);
		return false;
	}

	public String getTitle() {
		return null;
	}
	
	public Mouse getMouse() {
		return mouse;
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State currentState) {
		Engine.currentState = currentState;
		Engine.currentState.init();
	}
	
	public static Input getInput() {
		return input;
	}
}
