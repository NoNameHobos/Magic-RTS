package main.entities.ai.pathfinding;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public class Path {

	private ArrayList<Node> nodes;
	private ArrayList<Node> visited;
	private int currentIndex;
	
	private float cost;

	public Path(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public Path(Node start) {
		nodes = new ArrayList<Node>();
		nodes.add(start);
	}

	public void render(Graphics g) {
		float width = 6;
		float height = 6;
		
		for(Node v : visited) {
			g.setColor(Color.red);
			g.drawOval(v.getPos().getX() - width / 2, v.getPos().getY() - height / 2, width, height);
		}
		
		for (Node n : nodes) {
			g.setColor(Color.yellow);
			g.drawOval(n.getPos().getX() - width / 2, n.getPos().getY() - height / 2, width, height);
		}
		
		
		if (nodes.size() > 0) {
			g.setColor(Color.blue);
			g.drawOval(nodes.get(0).getPos().getX() - width / 2, nodes.get(0).getPos().getY() - height / 2, width,
					height);

			g.setColor(Color.green);
			g.drawOval(nodes.get(nodes.size() - 1).getPos().getX() - width / 2,
					nodes.get(nodes.size() - 1).getPos().getY() - height / 2, width, height);
		}
	}

	public boolean addNode(Node n) {
		boolean isPresent = false;
		for (int i = 0; i < nodes.size(); i++) {
			Point current = nodes.get(i).getPos();
			if (current.getX() == n.getPos().getX() && current.getY() == n.getPos().getY())
				isPresent = true; // Node already exists in path chain
		}
		if (!isPresent) {
			nodes.add(n);
			return true;
		} else
			return false;
	}

	// Getters and Setters
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setVisited(ArrayList<Node> v) {
		visited = v;
	}
	
	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public Node getCurrentNode() {
		return nodes.get(currentIndex);
	}

	public Node getNode(int index) {
		return nodes.get(index);
	}

	public Node getFinal() {
		return nodes.get(nodes.size() - 1);
	}

}
