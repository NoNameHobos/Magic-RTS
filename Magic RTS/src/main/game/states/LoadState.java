package main.game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.engine.Engine;
import main.game.State;
import main.game.map.MapLoader;
import main.game.map.TileSet;
import main.util.ResourceLoader;

public class LoadState extends State {
	
	private ResourceLoader resources;
	
	public LoadState() {
		super("Loading");
		
		resources = new ResourceLoader();
		Engine.setCurrentState(Engine.menuState);
	}
	
	public void init() {
		loadSprites();
	}
	
	@Override
	public void step() {
	}

	@Override
	public void draw(Graphics g) {
		g.setBackground(Color.white);
		g.setColor(Color.black);
		g.drawString("Loading...",Engine.getWIDTH()/4, Engine.getHEIGHT()/4);
		g.drawRect(300, 300, 100, 25);
	}

	private void loadSprites() {
		// TODO: Autoload Sprites

		//~UI Sprites====================================
		final String sprite_path = "res\\sprites\\UI\\";
		
		// Resources Icons
		resources.setCurrentDir(sprite_path);
		resources.addSprite("manaIcon", "icons\\manaIcon.png");
		resources.addSprite("mithrilIcon", "icons\\manaIcon.png");
		resources.addSprite("stoneIcon", "icons\\manaIcon.png");
		
		resources.addSprite("UIManaBar", "bars\\manaBar.png");
		resources.addSprite("UIStoneBar", "bars\\stoneBar.png");
		resources.addSprite("UIMithrilBar", "bars\\mithrilBar.png");
		
		resources.addSprite("viking_bottom", "frames\\bottombar_vike.png");
		resources.addSprite("steam_bottom", "frames\\bottombar_steam.png");
		
		resources.addSprite("viking_minimap", "minimap\\minimap_vike.png");
		resources.addSprite("steam_minimap", "minimap\\minimap_steam.png");

		resources.addSprite("move_button", "buttons\\move.png");
		resources.addSprite("attack_button", "buttons\\attack.png");
		resources.addSprite("build_button", "buttons\\build.png");
		
		resources.addSprite("commandhud", "command hud\\commandhud.png");
		
		//~Map Sprites====================================
		// Warg
		//resources.addSprite("warg_right", "mobs\\bigUnit\\warg\\warg");

		// Vikings
		resources.addSprite("vike_hut", "buildings\\viking\\hut.png");
		resources.addSprite("vike_th", "buildings\\viking\\th.png");
		
		// Steampunk
		resources.addSprite("steam_th", "buildings\\steampunk\\th.png");

		// Resource Nodes
		resources.addSprite("node_mana", "objects\\resources\\mana.png");
		resources.addSprite("node_stone", "objects\\resources\\stone.png");
		resources.addSprite("node_mithril", "objects\\resources\\mithril.png");

		// Workers
		resources.addSprite("worker_right", "mobs\\worker\\miner.png");
		resources.addSprite("worker_left", "mobs\\worker\\miner_left.png");
		
		// Axeman
		resources.setCurrentDir(sprite_path + "mobs\\axeman");
		resources.addSpriteSheet("axeman_down", "axeman_down.png", 48, 48);
		resources.addSpriteSheet("axeman_up", "axeman_up.png", 48, 48);
		resources.addSpriteSheet("axeman_left", "axeman_left.png", 48, 48);
		resources.addSpriteSheet("axeman_right", "axeman_right.png", 48, 48);
		
		// Menu button
		resources.setCurrentDir(sprite_path + "res\\sprites\\menu\\");
		resources.addSpriteSheet("menu_button", "button_anim.png", 220, 60);
		resources.addSpriteSheet("menu_buttonR", "button_animR.png", 220, 60);

		resources.addFont("Menu", loadFont"Fantaisieartisique.ttf", 45);
		
		// Load setons tile set
		resources.addTileSet("setons", TileSet.loadTileSet("setons"));
		
		resources.addMap("Seton's Clutch", MapLoader.loadMap("map\\setons"));
		resources.addMap("Mountain Pass", MapLoader.loadMap("maps\\mountainpass"));
		resources.addMap("Grass", MapLoader.loadMap("map\\grass"));
	}

}