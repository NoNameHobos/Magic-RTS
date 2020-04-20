package main.game.ui.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.GameConstants;
import main.entities.Entity;
import main.game.map.Map;
import main.game.player.Player;
import main.game.ui.UI;
import main.game.ui.UIElement;
import main.util.ResourceLoader;

public class Minimap extends UIElement {

	private Map map;

	private static int ALPHA = 100;

	private Player player;

	private Rectangle border;

	private float scaleX, scaleY;

	private static Image sprite;

	private float width = 13;

	public Minimap(UI ui, Point pos) {
		super(ui, pos);
		this.player = ui.getPlayer();
		this.map = player.getMap();

		float width = 200;
		float height = 200;
		scaleX = width / (map.getMapWidth() * GameConstants.TW_RENDER);
		scaleY = height / (map.getMapHeight() * GameConstants.TH_RENDER);

		sprite = ResourceLoader.UI.get("viking_minimap");

		border = new Rectangle(pos.getX(), pos.getY(), width, height);
	}

	@Override
	public void draw(Graphics g) {
		
		//Draw minimap sprite
		g.drawImage(sprite, pos.getX() - width, pos.getY() - width, new Color(255, 255, 255, ALPHA));
		g.setColor(Color.white);
		g.drawString(player.getFaction().getName(), border.getX(), border.getY() - 15);

		for (Entity entity : Entity.getEntities()) {
			Color c = entity.getPlayer().getPlayerColor();
			Color n = new Color(c.getRed(), c.getGreen(), c.getBlue());
			g.setColor(n);

			float width = entity.getSprite().getWidth() * scaleX;
			float height = entity.getSprite().getHeight() * scaleY;

			Point miniPos = mapToMinimap(entity.getPos());

			g.fillOval(miniPos.getX() - width / 2, miniPos.getY() - height / 2, width, height);
		}

		g.setColor(new Color(255, 255, 255, ALPHA));

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

		g.setColor(new Color(255, 255, 255, ALPHA));
		g.drawRect(miniPos.getX(), miniPos.getY(), width, height);

		// Draw border outline
		g.setColor(Color.black);
		g.draw(border);

	}

	public Point mapToMinimap(Point mapPoint) {
		return new Point(pos.getX() + mapPoint.getX() * scaleX, pos.getY() + mapPoint.getY() * scaleY);
	}

	// Getters and Setters
	public Map getMap() {
		return map;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

}
