package main.entities;

import java.util.ArrayList;
import java.util.Comparator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.entities.ai.pathfinding.Node;
import main.entities.ai.pathfinding.NodeMap;
import main.entities.ai.pathfinding.Path;
import main.entities.ai.pathfinding.PathFinder;
import main.entities.ai.pathfinding.PathObject;
import main.entities.unit.Worker;
import main.game.player.Player;
import main.util.Utils;

public abstract class Unit extends SelectableEntity {

	protected float health, health_max;
	protected float move_speed;
	protected float speed;
	protected float direction;
	protected float acc;
	protected float phys_def;
	protected float mag_def;

	protected boolean walking = false;
	//TODO: Add state machine
	
	protected Point des;

	protected Path path;
	protected boolean pathing;

	public static final int TOLERANCE = 20; // Pathfinding Tolerance

	public Unit(Player player, float x, float y, Image sprite) {
		super(player, new Point(x, y), sprite);
		type = "Unit";
		des = new Point(x, y);
		origin.setY(sprite.getHeight() - 5);

		pathing = false;
		
		//Init Stats
		health = 1;
		move_speed = 0.1f;
		direction = 120;
		acc = 0.0001f;
		phys_def = 0;
		mag_def = 0;
		health_max = health;
	}

	public void move(float spd, float angle) {
		float sin = (float) Math.sin(Math.toRadians(angle));
		float cos = (float) Math.cos(Math.toRadians(angle));
		pos.setX(pos.getX() + spd * cos);
		pos.setY(pos.getY() + spd * sin);
		
		if(this instanceof Worker) {
			System.out.println("Moving to: " + spd + " " + angle);
		}
	}

	public void moveTo(Point target) {
		if (getDistanceTo(target) >= (TOLERANCE)) {
			direction = getPointDirection(target);
			if (speed < move_speed)
				speed += acc;
		} else
			speed = 0;
	}

	public void moveAlongPath(Point target) {
		if (Utils.distance(pos, target) > (NodeMap.NODE_WIDTH)) {
			if (path == null) {
				if (pathing) {
					speed = 0;
					ArrayList<Node> nearestStartNodes = getNearestNodes(pos);
					ArrayList<Node> nearestEndNodes = getNearestNodes(target);

					nearestStartNodes.sort(new SortByDist(target));
					nearestEndNodes.sort(new SortByDist(target));

					if (nearestStartNodes.size() == 0) {
						System.out.println("Start Nodes Size: 0");
						return;
					}
					if (nearestEndNodes.size() == 0) {
						System.out.println("End Nodes Size: 0");
						return;
					}

					PathObject po = PathFinder.findPath(map.getGame().getNodeMap(), nearestStartNodes.get(0),
							nearestEndNodes.get(0));

					path = po.getPath();
				}
			} else {
				// Use path
				if (path.getNodes().size() > 0) {
					pathing = false;
					Point endPoint = path.getNodes().get(path.getNodes().size() - 1).getPos();
					Point nodePos = path.getNodes().get(0).getPos();

					float dist = Utils.distance(endPoint, target);

					if (Utils.distance(pos, nodePos) < TOLERANCE) {
						System.out.println("Next node");
						path.getNodes().remove(0);
					} else
						moveTo(nodePos);
					
					if (dist >= (1.5 * NodeMap.NODE_WIDTH)) {
						System.out.println("Distance: " + dist);

						des.setX(pos.getX());
						des.setY(pos.getY());

						speed = 0;
						path = null;
						return;
					}
				} else {
					speed = 0;
					path = null;
				}
			}
		} else {
			speed = 0;
			path = null;
		}
	}

	public ArrayList<Node> getNearestNodes(Point pos) {
		ArrayList<Node> nodes = new ArrayList<Node>();

		Node[][] nMap = map.getGame().getNodeMap().getNodes();

		for (int i = 0; i < nMap.length; i++) {
			for (int j = 0; j < nMap[i].length; j++) {
				if (Utils.distance(pos, nMap[i][j].getPos()) < (1.5 * NodeMap.NODE_WIDTH)) {
					if (nMap[i][j].getCost() < 100)
						nodes.add(nMap[i][j]);
				}
			}
		}

		return nodes;
	}

	public void tick() {
		super.tick();
		move(speed, direction);
		moveAlongPath(des);
		step();
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		draw(g);
	}

	public abstract void draw(Graphics g);

	public abstract void step();

	// Getters and Setters

	public int getFacing() {
		float dir = Math.abs(360 - direction);
		if (dir > 45 && dir < 135) {
			return 1; // Up (For Now)
		} else if (dir <= 45 || (dir >= 305 && dir <= 360)) {
			return 2; // Right (For Now)
		} else if (dir >= 135 && dir <= 205) {
			return 3; // Left (For Now)
		} else {
			return 0; // Down (For Now)
		}
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health, boolean rel) {
		if (!rel)
			this.health = health;
		else
			this.health += health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed, boolean rel) {
		if (!rel)
			this.speed = speed;
		else
			this.speed += speed;
	}

	public float getAcc() {
		return acc;
	}

	public void setAcc(float acc, boolean rel) {
		if (!rel)
			this.acc = acc;
		else
			this.acc += acc;
	}

	public float getHealthMax() {
		return health_max;
	}

	public float getMoveSpeed() {
		return move_speed;
	}

	public Player getPlayer() {
		return player;
	}

	public Point getDes() {
		return des;
	}

	public boolean isPathing() {
		return pathing;
	}

	public void setPathing(boolean pathing) {
		this.pathing = pathing;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

}

// Comparators

class SortByDist implements Comparator<Node> {

	Point goal;

	public SortByDist(Point g) {
		goal = g;
	}

	public int compare(Node n1, Node n2) {
		float dis1 = Utils.distance(n1.getPos(), goal);
		float dis2 = Utils.distance(n1.getPos(), goal);
		return (int) Math.signum(dis1 - dis2);
	}
}
