package main.game.ui.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
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

	public Minimap(UI ui, Point pos) {
		super(ui, pos, ResourceLoader.missing);
		this.player = ui.getPlayer();
		this.map = player.getMap();

		float width = 300;
		float height = 300;
		scaleX = width / (map.getMapWidth() * GameConstants.TW_RENDER);
		scaleY = height / (map.getMapHeight() * GameConstants.TH_RENDER);

		rectMinimap = new Rectangle(pos.getX(), pos.getY(), width, height);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.darkGray);
		g.fill(rectMinimap);
		g.setColor(Color.white);
		g.drawString(player.getName(), rectMinimap.getX(), rectMinimap.getY() + 10);

		for (Entity entity : Entity.getEntities()) {
			g.setColor(entity.getPlayer().getPlayerColor());

			float width = entity.getSprite().getWidth() * scaleX;
			float height = entity.getSprite().getHeight() * scaleY;

			Point miniPos = mapToMinimap(entity.getPos());

			g.fillOval(miniPos.getX() - width / 2, miniPos.getY() - height / 2, width, height);
		}
		g.setColor(Color.white);
		for(Entity entity : player.getSelected()) {

			int buffer = 3;
			
			float width = entity.getSprite().getWidth() * scaleX;
			float height = entity.getSprite().getHeight() * scaleY;
			
			Point miniPos = mapToMinimap(entity.getPos());
			
			
			float x = miniPos.getX() - width / 2 + buffer;
			float y = miniPos.getY() - height / 2 + buffer;
			
			g.fillOval(x, y, width - buffer * 2, height - buffer * 2);
		}
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
