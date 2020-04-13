package main.entities.ai.pathfinding;

public class Node {

	private int cost;
	
	public Node(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
