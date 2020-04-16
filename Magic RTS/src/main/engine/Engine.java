package main.engine;

import java.io.File;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import main.game.State;
import main.game.states.GameState;
import main.game.states.LoadState;
import main.game.states.MenuState;
import main.graphics.Display;
import main.input.Mouse;

public class Engine implements Game {

	private static State currentState;
	public static String ABS_PATH = (new File("").getAbsolutePath() + "\\");

	private static int WIDTH, HEIGHT;

	private static Input input;

	private static Mouse mouse;

	private static String TITLE;

	// Initialize States
	public static final State menuState = new MenuState();
	public static final State loadState = new LoadState();
	public static final State gameState = new GameState();

	public Engine(int WIDTH, int HEIGHT, String TITLE) {
		System.out.println("Initializing Engine..");
		Engine.WIDTH = WIDTH;
		Engine.HEIGHT = HEIGHT;
		Engine.TITLE = TITLE;

		new Display(this, WIDTH, HEIGHT);

	}

	public void init(GameContainer gc) throws SlickException {
		input = gc.getInput();
		mouse = new Mouse(input);

		// Start game here once everything is loaded
		Engine.currentState = loadState;
		Engine.currentState.init();

		System.err.println(Engine.WIDTH + " " + Engine.HEIGHT);
	}

	public void update(GameContainer gc, int i) throws SlickException {
		if (Engine.currentState != null) {
			mouse.update();
			Engine.currentState.tick();
		}
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setAntiAlias(false);
		if (Engine.currentState != null) {
			Engine.currentState.render(g);
		}
	}

	public boolean closeRequested() {
		System.exit(0);
		return false;
	}

	public String getTitle() {
		return TITLE;
	}

	public static Mouse getMouse() {
		return mouse;
	}

	public static int getWIDTH() {
		return Engine.WIDTH;
	}

	public static int getHEIGHT() {
		return Engine.HEIGHT;
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
