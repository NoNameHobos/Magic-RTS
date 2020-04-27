package main.entities.buildings;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.GameConstants;
import main.entities.SelectableEntity;
import main.entities.ai.pathfinding.Node;
import main.entities.ai.pathfinding.NodeMap;
import main.game.player.Player;

public abstract class Building extends SelectableEntity {

	protected Player player;

	protected Rectangle collider;

	protected Node[][] nodes;

	public Building(Player player, Point _pos, Image sprite) {
		super(player, new Point(_pos.getX() + sprite.getWidth() / 2, _pos.getY() + sprite.getHeight() / 2), sprite);
		type = "Building";

		int w = (int) Math.ceil(sprite.getWidth() / GameConstants.TW_RENDER);
		int h = (int) Math.ceil((sprite.getHeight() / 2) / GameConstants.TH_RENDER);

		nodes = new Node[w * NodeMap.RES][h * NodeMap.RES];

		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {
				NodeMap nm = player.getGame().getNodeMap();
				Node[][] nodeMap = nm.getNodes();

				Point topLeft = new Point(pos.getX() - origin.getX(),
						pos.getY() - origin.getY() + (float) Math.ceil(sprite.getHeight() / 3 * 2));

				int x = (int) ((topLeft.getX() - NodeMap.XOFFSET) / NodeMap.NODE_WIDTH) + i;
				int y = (int) ((topLeft.getY() - NodeMap.YOFFSET) / NodeMap.NODE_HEIGHT) + j;

				nodes[i][j] = nodeMap[y + 1][x + 1];
				nodes[i][j].setCost(100);
			}
		}

		float colWidth = nodes[0].length * GameConstants.TW_RENDER;
		float colHeight = nodes.length * GameConstants.TH_RENDER;

		collider = new Rectangle(pos.getX() - colWidth / 2 * GameConstants.TW_RENDER,
				pos.getY() - colHeight / 2 * GameConstants.TH_RENDER, colWidth, colHeight);
	}

	public void draw(Graphics g) {
		float width = sprite.getWidth();
		float height = sprite.getHeight();
		sprite.draw(pos.getX() - origin.getX(), pos.getY() - origin.getY(), width, height);
	}
}
