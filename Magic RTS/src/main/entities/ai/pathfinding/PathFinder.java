package main.entities.ai.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.newdawn.slick.geom.Point;

public class PathFinder {

	
	// Number of ops per tile in distance
	public static final int MAXOPS = 5;
	
	public static PathObject findPath(NodeMap map, Node startNode, Node endNode) {

		System.err.println("Finding path between: " + startNode.getPos().getX() + " " + startNode.getPos().getY());
		System.err.println("	And: " + endNode.getPos().getX() + " " + endNode.getPos().getY());

		boolean concluded = false;

		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();

		startNode.setGCost(0);
		open.add(startNode);
		Node current = null;

		int count = 0;

		int max_ops = (int) (MAXOPS * NodeMap.RES * getDist(startNode.getPos(), endNode.getPos()));
		
		while (!concluded && count < max_ops) {
			open.sort(new SortAStar());
			count++;
			
			try {
				current = open.get(0);
				open.remove(0);
			} catch (IndexOutOfBoundsException e) {
				System.err.println(count);
				concluded = true;
				return new PathObject(false, null);
			}
			
			closed.add(current);
			if (current == endNode)
				concluded = true;

			ArrayList<Node> neighbours = getNeighbours(map, current); // Get neighbours
			for (Node neighbour : neighbours) {

				// Check its not in closed
				if (closed.contains(neighbour))
					continue;

				float distBetween = getDist(current.getPos(), neighbour.getPos());

				if (!open.contains(neighbour)) {
					neighbour.setGCost(current.getGCost() + distBetween + neighbour.getCost());
					neighbour.setHCost(getDist(neighbour.getPos(), endNode.getPos()));
					neighbour.setFCost(neighbour.getGCost() + neighbour.getHCost());
					neighbour.setParent(current);
					if (!open.contains(neighbour))
						open.add(neighbour);
				} else {
					float pathToNeighbour = current.getGCost() + (neighbour.getCost() + 1) * distBetween;
					if (pathToNeighbour < neighbour.getGCost()) {
						neighbour.setGCost(current.getGCost() + distBetween + neighbour.getCost());
						neighbour.setHCost(getDist(neighbour.getPos(), endNode.getPos()));
						neighbour.setFCost(neighbour.getGCost() + neighbour.getHCost());
						neighbour.setParent(current);
						if (!open.contains(neighbour))
							open.add(neighbour);
					}
				}
			}
		}

		ArrayList<Node> path = new ArrayList<Node>();
		do {
			path.add(current);
			current = current.getParent();
		} while (current != startNode);
		Collections.reverse(path);
		Path p = new Path(path);
		p.setVisited(closed);
		return new PathObject(true, p);
	}

	public static Point mapToGrid(Point nodePos) {
		float x = (nodePos.getX() - (int) NodeMap.XOFFSET) / NodeMap.NODE_WIDTH;
		float y = (nodePos.getY() - (int) NodeMap.YOFFSET) / NodeMap.NODE_HEIGHT;
		return new Point(x, y);
	}

	public static ArrayList<Node> getNeighbours(NodeMap m, Node n) {
		ArrayList<Node> neighbours = new ArrayList<Node>();

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {

				Point gridPos = mapToGrid(n.getPos());

				int x = (int) gridPos.getX() + i;
				int y = (int) gridPos.getY() + j;

				if (x < 0) {
					x = 0;
				} else if (x > m.getWidth() - 1) {
					x = m.getWidth() - 1;
				}
				if (y < 0)
					y = 0;
				else if (y > m.getHeight() - 1)
					y = m.getHeight() - 1;
/*
				if (i != 0 || j != 0) {
					boolean cornerObst = false;
					if (i != 0 && j != 0) {

						int xx = (int) gridPos.getX(), yy = (int) gridPos.getY();

						cornerObst = (m.getNodes()[yy][x].getCost() >= 100) || (m.getNodes()[y][xx].getCost() >= 100);
					}
					if (!cornerObst)*/
				if(m.getNodes()[y][x].getCost() < 100) 
						neighbours.add(m.getNodes()[y][x]);
				//}
			}
		}
		return neighbours;
	}

	public static float getDist(Point p1, Point p2) {
		float distX = (float) (Math.abs(p1.getX() - p2.getX()));
		float distY = (float) (Math.abs(p1.getY() - p2.getY()));
		float dist2 = (float) (Math.pow(distX, 2) + Math.pow(distY, 2));
		return (float) (Math.pow(dist2, 0.5));
	}
}

class SortAStar implements Comparator<Node> {

	public int compare(Node n1, Node n2) {
		float h1 = n1.getHCost();
		float h2 = n2.getHCost();

		float g1 = n1.getGCost();
		float g2 = n2.getGCost();

		float f1 = h1 + g1;
		float f2 = h2 + g2;

		if (f1 != f2) {
			return (int) Math.signum(f1 - f2);
		} else {
			return (int) Math.signum(h1 - h2);
		}
	}

}