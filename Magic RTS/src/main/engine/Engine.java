package main.engine;

import java.io.File;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import main.engine.states.GameState;
import main.graphics.Display;
import main.input.Mouse;
import main.util.ResourceLoader;

public class Engine implements Runnable, Game {

	public static Engine ENGINE;
	
	private Thread displayThread;
	private Display display;
	
	private boolean running;
	
	private static State currentState = new GameState("Game");
	public static String ABS_PATH = (new File("").getAbsolutePath() + "\\");
	
	private int WIDTH, HEIGHT;
	private String TITLE;
	
	private Input input;
	private Mouse mouse;
	
	public Engine(int WIDTH, int HEIGHT, String TITLE) {
		System.out.println("Initializing Engine..");
		ENGINE = this;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.TITLE = TITLE;
		display = new Display(this, WIDTH, HEIGHT);
		displayThread = new Thread(display, "DisplayThread");
		
	}
	
	public void init(GameContainer gc) throws SlickException {
		input = gc.getInput();
		mouse = new Mouse(input);
		System.out.println("Loading Resources..");
		ResourceLoader.initResources();
	}

	public void update(GameContainer gc, int i) throws SlickException {
		mouse.update();
		if(currentState != null)
			currentState.tick();
	}

	public void render(GameContainer gc, Graphics g) throws SlickException{
		if(currentState != null)
			currentState.render(g);
		g.drawOval(mouse.getPos().getX(), mouse.getPos().getY(), 32, 32);
	}

	public boolean closeRequested() {
		System.exit(0);
		return false;
	}

	public String getTitle() {
		return null;
	}
	
	public void run() {
		running = true;
		//Start threads
		System.out.println("Started Engine Thread");
		displayThread.start();
		
	}
	
	public Mouse getMouse() {
		return mouse;
	}
}
