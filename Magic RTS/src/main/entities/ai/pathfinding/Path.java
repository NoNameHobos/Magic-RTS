package main.entities.ai.pathfinding;

import java.util.ArrayList;
import java.util.Set;

import org.newdawn.slick.geom.Point;

public class Path {

	private ArrayList<Node> nodes;
	private int currentIndex;
	private Set<Node> visited;
	
	private float cost;
	
	public Path(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}
	
	public Path(Node start) {
		nodes = new ArrayList<Node>();
		nodes.add(start);
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
	
	public boolean addNode(Node n) {
		boolean isPresent = false;
		for(int i = 0; i < nodes.size(); i++) {
			Point current = nodes.get(i).getPos();
			if(current.getX() == n.getPos().getX() && current.getY() == n.getPos().getY())
				isPresent = true; //Node already exists in path chain
		}
		if(!isPresent) {
			nodes.add(n);
			return true;
		} else return false;
	}

	
	//Getters and Setters
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public Set<Node> getVisited() {
		return visited;
	}

	public void setVisited(Set<Node> visited) {
		this.visited = visited;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
}
