package main.entities.ai.pathfinding;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import static main.util.ResourceLoader.TILE_WIDTH;
import static main.util.ResourceLoader.TILE_HEIGHT;

public class NodeMap {

	public static NodeMap createNodeMap(ArrayList<String> mapData) {
		//TODO: Make a decent Node Map based off map data
		int mapHeight = mapData.size();
		int mapWidth = mapData.get(0).split(" ").length;

		NodeMap nm = new NodeMap();
		
		String[][] mD = new String[mapHeight][mapWidth];

		Node[][] nodes = new Node[mapWidth][mapHeight];

		for (int i = 0; i < mapHeight; i++) {

			mD[i] = mapData.get(i).split(" ");
			for (int j = 0; j < mapWidth; j++) {
				int cost = 0;
				if(Integer.parseInt(mD[i][j]) == 0)
					cost = 0;
				else if(Integer.parseInt(mD[i][j]) == 1) cost = 100;
				nodes[j][i] = new Node(nm, j, i, cost);
			}
		}
		nm.setNodes(nodes);
		return nm;
	}
	
	public void render(Graphics g) {
		
		for(int i = 0; i < nodes.length; i++) {
			for(int j = 0; j < nodes[i].length; j++) {
				Node n = nodes[i][j];
				
				float red = (n.getCost()/100);
				float green = (1f - (n.getCost()/100));
				float blue = 0;
				
				g.setColor(new Color(red, green, blue));
				g.fillRect(n.getPos().getX() * TILE_WIDTH, n.getPos().getY() * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
				g.setColor(Color.black);
				g.drawRect(n.getPos().getX() * TILE_WIDTH, n.getPos().getY() * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
				g.drawString(Integer.toString(n.getCost()), n.getPos().getX() * TILE_WIDTH, n.getPos().getY() * TILE_HEIGHT);
			}
		}
	}
	
	private Node[][] nodes;

	private int mapHeight, mapWidth;
	
	public NodeMap() {
	}
	
	// Getters and Setters
	public int getHeight() {
		return mapHeight;
	}

	public int getWidth() {
		return mapWidth;
	}

	public Node[][] getNodes() {
		return nodes;
	}
	
	public void setNodes(Node[][] nodes) {
		this.nodes = nodes;
	}

}
