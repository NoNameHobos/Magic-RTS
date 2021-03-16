package main.game.entities.controllables.buildings;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.GameConstants;
import main.game.entities.ai.pathfinding.Node;
import main.game.entities.ai.pathfinding.NodeMap;
import main.game.entities.controllables.Controllable;
import main.game.player.Player;
import main.graphics.res.Sprite;

public abstract class Building extends Controllable {

	protected Rectangle collider;

	protected Node[][] nodes;

	public Building(Player player, Point _pos, Sprite sprite) {
		// TODO: Rearrange this
		super(new Point(_pos.getX() + sprite.getWidth() / 2, _pos.getY() + sprite.getHeight() / 2), sprite, player);
		
		/*nodes = null;
		//while (nodes == null)
		//	nodes = findNodes();
		if (nodes.length > 0) {
			float colWidth = nodes[0].length * GameConstants.TW_RENDER;
			float colHeight = nodes.length * GameConstants.TH_RENDER;

			collider = new Rectangle(mapPos.getX() - colWidth / 2 * GameConstants.TW_RENDER,
					mapPos.getY() - colHeight / 2 * GameConstants.TH_RENDER, colWidth, colHeight);
		}*/
	}

	public Node[][] findNodes() {
		int w = (int) Math.ceil(activeSprite.getWidth() / GameConstants.TW_RENDER);
		int h = (int) Math.ceil((activeSprite.getHeight() / 2) / GameConstants.TH_RENDER);

		Node[][] n = new Node[w * NodeMap.RES][h * NodeMap.RES];
		NodeMap nm = player.getMap().getNodeMap();
		Node[][] nodeMap = nm.getNodes();

		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < n[i].length; j++) {

				Point topLeft = new Point(mapPos.getX() - activeSprite.getOrigin().getX(), mapPos.getY()
						- activeSprite.getOrigin().getY() + (float) Math.ceil(activeSprite.getHeight() / 3 * 2));

				int x = (int) ((topLeft.getX() - NodeMap.XOFFSET) / NodeMap.NODE_WIDTH) + i;
				int y = (int) ((topLeft.getY() - NodeMap.YOFFSET) / NodeMap.NODE_HEIGHT) + j;
				try {
					n[i][j] = nodeMap[y + 1][x + 1];
					n[i][j].setCost(100);
				} catch (ArrayIndexOutOfBoundsException e) {
					if (System.currentTimeMillis() % 1000 == 0)
						System.err.println(mapPos.getX() + " " + mapPos.getY());
					mapPos.setX(map.getMapWidth() / 2);
					mapPos.setY(map.getMapHeight() / 2);
					return null;
				}
			}
		}
		return n;
	}

	public void draw(Graphics g) {
		// float width = activeSprite.getWidth();
		// float height = activeSprite.getHeight();
		// activeSprite.draw(pos.getX() - origin.getX(), pos.getY() - origin.getY(),
		// width, height);

		for (Node[] nodeSet : nodes) {
			for (Node node : nodeSet) {
				g.setColor(Color.white);
				Point p = node.getPos();
				g.drawOval(p.getX() - 8, p.getY() - 8, 16, 16);
			}
		}
	}
}
