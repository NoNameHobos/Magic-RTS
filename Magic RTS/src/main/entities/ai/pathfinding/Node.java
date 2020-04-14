package main.entities.ai.pathfinding;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import static main.GameConstants.TW_RENDER;
import static main.GameConstants.TH_RENDER;

public class Node {

	public static final int MAX_GREEN = 150;
	public static final int MAX_RED = 150;

	private Point pos;
	private int cost;

	private int id;
	
	private Node parent;
	
	private float FCost, GCost, HCost;
	
	public Node(NodeMap map, int x, int y, int cost) {
		pos = new Point(x, y);
		this.cost = cost;
		id = x + map.getHeight() * y;
		parent = null;
	}

	public void render(Graphics g) {
		float red = (float)((cost / 100.0) * MAX_RED);
		float green = (float)((1- cost / 100.0) * MAX_GREEN);
		float blue = 0;
		Rectangle r = new Rectangle(pos.getX() * TW_RENDER, pos.getY() * TH_RENDER, TW_RENDER, TH_RENDER);
		g.setColor(new Color(red/255, green/255, blue/255));
		g.fill(r);
		g.setColor(Color.black);
		g.draw(r);
		g.drawString(Integer.toString(id), pos.getX() * TW_RENDER, pos.getY() * TH_RENDER);
				
	}
	
	public void update() {
	}

	// Getters and Setters
	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}
	
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public float getFCost() {
		return FCost;
	}

	public void setFCost(float fCost) {
		FCost = fCost;
	}
	
	public void setFCost() {
		FCost = GCost + HCost;
	}

	public float getGCost() {
		return GCost;
	}

	public void setGCost(float gCost) {
		GCost = gCost;
	}

	public float getHCost() {
		return HCost;
	}

	public void setHCost(float f) {
		HCost = f;
	}

	public int getId() {
		return id;
	}

	public int getCost() {
		return cost;
	}

}
