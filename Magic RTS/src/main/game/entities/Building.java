package main.game.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.GameConstants;
import main.game.entities.ai.pathfinding.Node;
import main.game.entities.ai.pathfinding.NodeMap;
import main.game.player.Player;
import main.graphics.AnimSet;

public abstract class Building extends SelectableEntity {

	protected Rectangle collider;

	protected Node[][] nodes;

	public Building(Player player, Point _pos, AnimSet anims) {
		// TODO: Rearrange this
		super(player, 
				new Point(_pos.getX() + anims.getDefaultSprite().getWidth() / 2, _pos.getY() + anims.getDefaultSprite().getHeight() / 2)
				, anims);
		
		nodes = null;
		while(nodes == null) nodes = findNodes();

		float colWidth = nodes[0].length * GameConstants.TW_RENDER;
		float colHeight = nodes.length * GameConstants.TH_RENDER;

		collider = new Rectangle(pos.getX() - colWidth / 2 * GameConstants.TW_RENDER,
				pos.getY() - colHeight / 2 * GameConstants.TH_RENDER, colWidth, colHeight);
	}

	public Node[][] findNodes() {
		int w = (int) Math.ceil(currentSprite.getWidth() / GameConstants.TW_RENDER);
		int h = (int) Math.ceil((currentSprite.getHeight() / 2) / GameConstants.TH_RENDER);

		Node[][] n = new Node[w * NodeMap.RES][h * NodeMap.RES];
		NodeMap nm = player.getMap().getNodeMap();
		Node[][] nodeMap = nm.getNodes();

		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < n[i].length; j++) {

				Point topLeft = new Point(pos.getX() - origin.getX(),
						pos.getY() - origin.getY() + (float) Math.ceil(currentSprite.getHeight() / 3 * 2));

				int x = (int) ((topLeft.getX() - NodeMap.XOFFSET) / NodeMap.NODE_WIDTH) + i;
				int y = (int) ((topLeft.getY() - NodeMap.YOFFSET) / NodeMap.NODE_HEIGHT) + j;
				try {
					n[i][j] = nodeMap[y + 1][x + 1];
					n[i][j].setCost(100);
				} catch (ArrayIndexOutOfBoundsException e) {
					if(System.currentTimeMillis() % 1000 == 0) 
						System.err.println(pos.getX() + " " + pos.getY());
					pos.setX(map.getMapWidth() / 2);
					pos.setY(map.getMapHeight() / 2);
					return null;					
				}
			}
		}
		
		return n;
	}
	
	public void draw(Graphics g) {
		float width = currentSprite.getWidth();
		float height = currentSprite.getHeight();
		currentSprite.draw(pos.getX() - origin.getX(), pos.getY() - origin.getY(), width, height);

		for (Node[] nodeSet : nodes) {
			for (Node node : nodeSet) {
				g.setColor(Color.white);
				Point p = node.getPos();
				g.drawOval(p.getX() - 8, p.getY() - 8, 16, 16);
			}
		}
	}
}
