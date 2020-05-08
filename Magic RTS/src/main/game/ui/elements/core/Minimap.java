package main.game.ui.elements.core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.GameConstants;
import main.engine.Engine;
import main.game.entities.Entity;
import main.game.map.Map;
import main.game.player.Player;
import main.game.ui.UI;
import main.game.ui.UIElement;
import main.input.Clickable;

public class Minimap extends UIElement implements Clickable {

	private Map map;

	private Player player;

	private Rectangle border;

	private float scaleX, scaleY;

	private static Image sprite;

	private Point mouse;
	
	private float borderWidth;

	private boolean dragging = false;

	public static int WIDTH = 250;
	public static int HEIGHT = 250;

	public Minimap(UI ui, Point pos) {
		super(ui, pos, WIDTH, HEIGHT);
		this.player = ui.getPlayer();
		this.map = player.getMap();

		mouse = new Point(0, 0);
		
		Engine.getInput().addMouseListener(this);

		sprite = player.getFaction().getSprite("ui_minimap");

		float mapWidth = 200;

		borderWidth = (sprite.getWidth() - mapWidth) / 2;

		scaleX = (mapWidth) / (map.getMapWidth() * GameConstants.TW_RENDER);
		scaleY = (mapWidth) / (map.getMapHeight() * GameConstants.TH_RENDER);

		border = new Rectangle(pos.getX() + borderWidth, pos.getY() + borderWidth, mapWidth, mapWidth);
	}

	@Override
	public void draw(Graphics g) {

		// Draw minimap sprite
		g.drawImage(sprite, border.getX() - borderWidth, border.getY() - borderWidth);
		g.setColor(Color.white);
		g.drawString(player.getFaction().getName(), border.getX(), border.getY() - 15);

		for (Entity entity : Entity.getEntities()) {
			Color c;
			if (entity.getPlayer() != null)
				c = entity.getPlayer().getPlayerColor();
			else
				c = new Color(0, 0, 200);
			g.setColor(c);

			float width = entity.getSprite().getWidth() * scaleX;
			float height = entity.getSprite().getHeight() * scaleY;

			Point miniPos = mapToMinimap(entity.getPos());

			g.fillOval(miniPos.getX() - width / 2, miniPos.getY() - height / 2, width, height);
		}

		g.setColor(Color.white);

		for (Entity entity : player.getSelected()) {

			int buffer = 3;

			float width = entity.getSprite().getWidth() * scaleX;
			float height = entity.getSprite().getHeight() * scaleY;

			Point miniPos = mapToMinimap(entity.getPos());

			float x = miniPos.getX() - width / 2 + buffer;
			float y = miniPos.getY() - height / 2 + buffer;

			// Draw entities
			g.fillOval(x, y, width - buffer * 2, height - buffer * 2);
		}

		drawView(g);
	}

	public void drawView(Graphics g) {

		// Draw the camera square
		Rectangle r = player.getCamera().getViewRect();

		Point miniPos = mapToMinimap(new Point(r.getX(), r.getY()));

		float width = r.getWidth() * scaleX;
		float height = r.getHeight() * scaleY;

		if (miniPos.getX() + width > border.getX() + border.getWidth())
			width = border.getX() + border.getWidth() - miniPos.getX();

		if (miniPos.getY() + height > border.getY() + border.getHeight())
			height = border.getY() + border.getHeight() - miniPos.getY();

		if (miniPos.getX() < border.getX()) {
			width -= border.getX() - miniPos.getX();
			miniPos.setX(border.getX());
		}

		if (miniPos.getY() < border.getY()) {
			height -= border.getY() - miniPos.getY();
			miniPos.setY(border.getY());
		}

		g.setColor(Color.white);
		g.drawRect(miniPos.getX(), miniPos.getY(), width - 1, height - 1);
	}

	public Point mapToMinimap(Point mapPoint) {
		return new Point(border.getX() + mapPoint.getX() * scaleX, border.getY() + mapPoint.getY() * scaleY);
	}

	public Point minimapToMap(Point miniPoint) {
		return new Point((miniPoint.getX() - border.getX()) / scaleX, (miniPoint.getY() - border.getY()) / scaleY);
	}

	// Getters and Setters
	public Map getMap() {
		return map;
	}

	@Override
	public void step() {

		if (dragging) {

			Point pos = new Point(mouse.getX(), mouse.getY());

			player.getCamera().setPos(minimapToMap(pos), true);

			// Collision code
			
			if (player.getCamera().getPos(false).getX() < player.getCamera().getBounds()[0]) {
				player.getCamera().getViewRect().setX(player.getCamera().getBounds()[0]);
			}

			if (player.getCamera().getPos(false).getY() < player.getCamera().getBounds()[1]) {
				player.getCamera().getViewRect().setY(player.getCamera().getBounds()[1]);
			}

			if (player.getCamera().getPos(false).getX() > player.getCamera().getBounds()[2]) {
				player.getCamera().getViewRect().setX(player.getCamera().getBounds()[2]);
			}

			if (player.getCamera().getPos(false).getY() > player.getCamera().getBounds()[3]) {
				player.getCamera().getViewRect().setY(player.getCamera().getBounds()[3]);
			}
		}
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int x, int y) {
		mouse.setX(x);
		mouse.setY(y);
	}

	public void mouseMoved(int oldx, int oldy, int x, int y) {
		mouse.setX(x);
		mouse.setY(y);
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		if (border.contains(new Point(x, y))) {
			dragging = true;
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		dragging = false;
	}

	@Override
	public boolean isAcceptingInput() {
		if (Engine.getCurrentState() == Engine.gameState)
			return true;
		else
			return false;
	}
}
