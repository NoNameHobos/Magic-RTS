package main.engine;

import java.io.File;
import java.util.HashMap;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import main.game.State;
import main.game.states.*;
import main.graphics.Display;
import main.input.Mouse;
import main.util.ResourceLoader;

public class Engine implements Game {
	public static final ResourceLoader RES = new ResourceLoader();
	
	private static State currentState;
	public static String ABS_PATH = (new File("").getAbsolutePath() + "\\");

	private static int WIDTH, HEIGHT;

	private static Input input;
	private static Mouse mouse;

	private static String TITLE;
	
	public static final HashMap<String, State> STATES = new HashMap<String, State>();

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
		RES.init();
		loadSprites();
		createStates();
		Engine.setCurrentState("menu");
	}
	
	public void createStates() {
		STATES.put("load", new LoadState());
		STATES.put("game", new GameState());
		STATES.put("editor", new EditorState());
		STATES.put("menu", new MenuState());
		STATES.put("test", new TestState());
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		if (Engine.currentState != null) {
			mouse.update();
			Engine.currentState.step();
		}
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setAntiAlias(false);
		if (Engine.currentState != null) {
			Engine.currentState.draw(g);
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

	public static void setCurrentState(String stateName) {
		State new_state = STATES.get(stateName);
		if(new_state != null) {
			Engine.currentState = new_state;
			Engine.currentState.start();
		} else System.err.println("Error changing state to: " + stateName);
	}

	public static Input getInput() {
		return input;
	}

	/**
	 * Load all sprites here!
	 */
	private void loadSprites() {
		System.out.println("Loading resources");
		// TODO: Autoload Sprites

		//~UI Sprites====================================
		final String sprite_path = "res\\sprites\\UI\\";
		
		// Resources Icons
		RES.setCurrentDir(sprite_path);
		RES.addSprite("manaIcon", "icons\\manaIcon.png");
		RES.addSprite("mithrilIcon", "icons\\manaIcon.png");
		RES.addSprite("stoneIcon", "icons\\manaIcon.png");
		
		RES.addSprite("UIManaBar", "bars\\manaBar.png");
		RES.addSprite("UIStoneBar", "bars\\stoneBar.png");
		RES.addSprite("UIMithrilBar", "bars\\mithrilBar.png");
		
		RES.addSprite("viking_bottom", "frames\\bottombar_vike.png");
		RES.addSprite("steam_bottom", "frames\\bottombar_steam.png");
		
		RES.addSprite("viking_minimap", "minimap\\minimap_vike.png");
		RES.addSprite("steam_minimap", "minimap\\minimap_steam.png");

		RES.addSprite("move_button", "buttons\\move.png");
		RES.addSprite("attack_button", "buttons\\attack.png");
		RES.addSprite("build_button", "buttons\\build.png");
		
		RES.addSprite("commandhud", "command hud\\commandhud.png");
		
		//~Map Sprites====================================
		// Warg
		//resources.addSprite("warg_right", "mobs\\bigUnit\\warg\\warg");

		// Vikings
		RES.setCurrentDir("res\\sprites\\");
		RES.addSprite("vike_hut", "buildings\\viking\\hut.png");
		RES.addSprite("vike_th", "buildings\\viking\\th.png");
		
		// Steampunk
		RES.addSprite("steam_th", "buildings\\steampunk\\th.png");

		// Resource Nodes
		RES.addSprite("node_mana", "objects\\resources\\mana.png");
		RES.addSprite("node_stone", "objects\\resources\\stone.png");
		RES.addSprite("node_mithril", "objects\\resources\\mithril.png");

		// Workers
		RES.addSprite("worker_right", "mobs\\worker\\miner.png");
		RES.addSprite("worker_left", "mobs\\worker\\miner_left.png");
		
		// Axeman
		RES.setCurrentDir("res\\sprites\\mobs\\");
		RES.addSpriteSheet("axeman_down", "axeman\\axeman_down.png", 48, 48, 100);
		RES.addSpriteSheet("axeman_up", "axeman\\axeman_up.png", 48, 48, 100);
		RES.addSpriteSheet("axeman_left", "axeman\\axeman_left.png", 48, 48, 100);
		RES.addSpriteSheet("axeman_right", "axeman\\axeman_right.png", 48, 48, 100);
		
		// Menu button
		RES.setCurrentDir("res\\sprites\\menu\\");
		RES.addSpriteSheet("menu_button", "button_anim.png", 220, 60, 3);
		RES.addSpriteSheet("menu_buttonR", "button_animR.png", 220, 60, 3);

		RES.setCurrentDir("res\\font\\");
		RES.addFont("Menu", "FantaisieArtistique.ttf", 45);
		
		// Load setons tile set
		RES.setCurrentDir("res\\sprites\\tilesets");
		RES.addTileSet("setons", "setons");
		
		RES.setCurrentDir("res\\maps\\");
		RES.loadMap("Seton's Clutch", "setons.map");
		RES.loadMap("Mountain Pass", "mountainpass.map");
		RES.loadMap("Grass", "grass.map");
	}
}
