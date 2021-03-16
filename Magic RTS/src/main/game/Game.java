package main.game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.entities.Renderable;
import main.game.map.Map;
import main.game.player.Camera;
import main.game.player.Faction;
import main.game.player.Player;
import main.input.Mouse;


public class Game {

	/*
	 * A game class to handle the rendering and updating of entities and such within
	 * a given game
	 */

	private boolean renderPathing = false;

	public static String MAP_TO_LOAD = "";

	private Map map;

	private boolean started = false;
	private boolean dispView = false;

	private Player controllingPlayer;

	private static Camera currentView;

	public void init() {
		Faction.initFactions();
		System.out.println("Initializing game on " + MAP_TO_LOAD);
		map = Engine.RES.getMap(MAP_TO_LOAD);
		map.init(this);

		controllingPlayer = map.getFocusedPlayer();

		currentView = controllingPlayer.getCamera();

		System.out.println("Loaded map: " + MAP_TO_LOAD);

		Engine.getInput().addMouseListener(Engine.getMouse());

		started = true;
	}

	// Update
	public void tick() {
		// Update Entities
		ArrayList<GameObject> gameObjects = GameObject.getObjects();
		
		gameObjects.removeIf(obj -> (obj.toDispose()));
		GameObject.updateAll();
		
		for (Player player : map.getPlayers()) {
			if (player != null) {
				player.tick();
			}
		}
		
		if(Engine.getInput().isKeyPressed(Input.KEY_V)) {
			if(!dispView) dispView = true;
			else dispView = false;
		}
	}

	// Render
	public void render(Graphics g) {
		Camera curCamera = map.getMainCamera();
		Rectangle offset = curCamera.getViewRect();
		float xOffset = offset.getX();
		float yOffset = offset.getY();

		// Apply Camera transformations
		g.scale((float) curCamera.getZoom(), (float) curCamera.getZoom());
		g.translate(-xOffset, -yOffset);

		// Render all the tiles
		map.renderTiles(g);

		// Render the Renderables
		Camera cam = map.getMainCamera();
		Renderable.renderAll(g, cam);

		//UI ui = map.getControlledPlayer().getUI();
		/*
		 * for (Entity entity : entities) { if
		 * (cam.getRenderRect().contains(entity.getPos()) //&&
		 * !ui.contains(objectToUI(entity.getPos(), c)) ) entity.draw(g); }
		 */
		// Render player stuff
		map.getFocusedPlayer().render(g);

		// Render the view port if necessary
		if (dispView) {
			g.setColor(Color.blue);
			g.draw(cam.getRenderRect());

			g.setColor(Color.pink);
			g.drawOval(cam.getPos(true).getX() - 16, cam.getPos(true).getY() - 16, 32, 32);

			g.setColor(Color.red);
			g.draw(cam.getViewRect());
		}

		// Revert Camera transformations to draw the UI
		g.translate(xOffset, yOffset);
		g.scale((float) (1 / curCamera.getZoom()), (float) (1 / curCamera.getZoom()));

		renderUI(g);

	}

	public void renderUI(Graphics ui) {

		if (map.getFocusedPlayer() != null)
			map.getFocusedPlayer().renderUI(ui);

		Mouse m = Engine.getMouse();

		if(dispView) {
			ui.drawString("Game", 30, 45);
			ui.drawString(Float.toString(m.getPos().getX()), 30, 60);
			ui.drawString(Float.toString(m.getPos().getY()), 30, 75);
	
			ui.drawString("UI", 120, 45);
			ui.drawString(Float.toString(m.getScreenPos().getX()), 120, 60);
			ui.drawString(Float.toString(m.getScreenPos().getY()), 120, 75);
		}
	}

	// Conversions
	public static Point UIToObject(Point point, Camera c) {
		// ui/zoom + offset
		Point offset = c.getPos(false);

		float newx = (offset.getX() + point.getX() / c.getZoom());
		float newy = (offset.getY() + point.getY() / c.getZoom());

		return new Point(newx, newy);
	}

	public static Point objectToUI(Point point, Camera c) {
		// UI to Game -> (ui - offset) * zoom 
		float newx = (point.getX() - c.getPos(false).getX()) * c.getZoom();
		float newy = (point.getY() - c.getPos(false).getY()) * c.getZoom();

		return new Point(newx, newy);
	}

	// Getters and Setters
	public boolean isStarted() {
		return started;
	}

	public Map getMap() {
		return map;
	}

	public boolean isRenderPathing() {
		return renderPathing;
	}

	public void setRenderPathing(boolean renderPathing) {
		this.renderPathing = renderPathing;
	}

	public static Camera getCurrentView() {
		return currentView;
	}

	public static void setCurrentView(Camera c) {
		currentView = c;
	}

	public Player getControllingPlayer() {
		return controllingPlayer;
	}
}
