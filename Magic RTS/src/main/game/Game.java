package main.game;

import static main.util.ResourceLoader.MAPS;

import java.util.ArrayList;
import java.util.Comparator;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.entities.Entity;
import main.game.map.Map;
import main.game.player.Camera;
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
		map = MAPS.get(MAP_TO_LOAD);
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
		ArrayList<Entity> entities = Entity.getEntities();
		entities.sort(new SortByDepth());
		for (Entity entity : entities) {
			entity.tick();
		}

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

	// Tick
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

		// Render Entities
		ArrayList<Entity> entities = Entity.getEntities();

		Camera c = map.getMainCamera();

		//UI ui = map.getControlledPlayer().getUI();
		
		for (Entity entity : entities) {
			if (c.getRenderRect().contains(entity.getPos()) 
					//&& !ui.contains(objectToUI(entity.getPos(), c))
					)
				entity.render(g);
		}

		// Render player stuff
		map.getFocusedPlayer().render(g);

		// Render the view port if necessary
		if (dispView) {
			g.setColor(Color.blue);
			g.draw(c.getRenderRect());

			g.setColor(Color.pink);
			g.drawOval(c.getPos(true).getX() - 16, c.getPos(true).getY() - 16, 32, 32);

			g.setColor(Color.red);
			g.draw(c.getViewRect());
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

// Comparators

class SortByDepth implements Comparator<Entity> {

	@Override
	public int compare(Entity e1, Entity e2) {
		return (e2.getDepth() - e1.getDepth());
	}
	
}
