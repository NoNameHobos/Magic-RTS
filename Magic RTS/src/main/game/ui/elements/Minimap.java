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

	private Player player;

	private Rectangle rectMinimap;

	private float scaleX, scaleY;

	private static Image sprite;

	public Minimap(UI ui, Point pos) {
		super(ui, pos);
		this.player = ui.getPlayer();
		this.map = player.getMap();

		float width = 200;
		float height = 200;
		scaleX = width / (map.getMapWidth() * GameConstants.TW_RENDER);
		scaleY = height / (map.getMapHeight() * GameConstants.TH_RENDER);

		sprite = ResourceLoader.UI.get("viking_minimap");

		rectMinimap = new Rectangle(pos.getX(), pos.getY(), width, height);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(sprite, pos.getX() - 50, pos.getY() - 50, new Color(255, 255, 255, 100));
		g.setColor(Color.white);
		g.drawString(player.getFaction().getName(), rectMinimap.getX(), rectMinimap.getY() + 10);

		for (Entity entity : Entity.getEntities()) {
			Color c = entity.getPlayer().getPlayerColor();
			Color n = new Color(c.getRed(), c.getGreen(), c.getBlue(), 100);
			g.setColor(n);

			float width = entity.getSprite().getWidth() * scaleX;
			float height = entity.getSprite().getHeight() * scaleY;

			Point miniPos = mapToMinimap(entity.getPos());

			g.fillOval(miniPos.getX() - width / 2, miniPos.getY() - height / 2, width, height);
		}
		g.setColor(new Color(255, 255, 255, 100));
		for (Entity entity : player.getSelected()) {

			int buffer = 3;

			float width = entity.getSprite().getWidth() * scaleX;
			float height = entity.getSprite().getHeight() * scaleY;

			Point miniPos = mapToMinimap(entity.getPos());

			float x = miniPos.getX() - width / 2 + buffer;
			float y = miniPos.getY() - height / 2 + buffer;

			g.fillOval(x, y, width - buffer * 2, height - buffer * 2);
		}

		Point miniPos = mapToMinimap(
				new Point(player.getCamera().getViewRect().getX(), player.getCamera().getViewRect().getY()));
		float width = player.getCamera().getViewRect().getWidth() * scaleX;
		float height = player.getCamera().getViewRect().getHeight() * scaleY;

		if (miniPos.getX() + width > rectMinimap.getX() + rectMinimap.getWidth())
			width = rectMinimap.getX() + rectMinimap.getWidth() - miniPos.getX();
		if (miniPos.getY() + height > rectMinimap.getY() + rectMinimap.getHeight())
			height = rectMinimap.getY() + rectMinimap.getHeight() - miniPos.getY();
		if (miniPos.getX() < rectMinimap.getX()) {
			width -= rectMinimap.getX() - miniPos.getX();
			miniPos.setX(rectMinimap.getX());
		}
		if (miniPos.getY() < rectMinimap.getY()) {
			height -= rectMinimap.getY() - miniPos.getY();
			miniPos.setY(rectMinimap.getY());
		}

		g.drawRect(miniPos.getX(), miniPos.getY(), width, height);

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
