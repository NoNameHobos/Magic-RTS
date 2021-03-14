package main.game.entities.controllables;

import java.util.ArrayList;
import java.util.Comparator;

import org.newdawn.slick.geom.Point;

import main.GameConstants;
import main.game.entities.ai.pathfinding.Node;
import main.game.entities.ai.pathfinding.NodeMap;
import main.game.entities.ai.pathfinding.Path;
import main.game.entities.ai.pathfinding.PathFinder;
import main.game.entities.ai.pathfinding.PathObject;
import main.game.entities.controllables.abilities.BasicCommandable;
import main.game.player.Player;
import main.graphics.res.Sprite;
import main.util.Utils;

public abstract class Unit extends Controllable implements BasicCommandable {

	protected boolean walking = false;

	// Pathing
	protected Point des;
	protected Path path;
	protected boolean pathing;
	protected float direction;
	protected float speed, max_speed;
	protected Controllable target;

	// Patrol stuff
	protected Point[] patrolPoints;
	protected int cur_point;

	// State stuff
	protected int state; 
	

	public static final int TOLERANCE = 20; // Pathfinding Tolerance

	public Unit(Player player, float x, float y, Sprite sprite) {
		super(new Point(x, y));
		
		des = new Point(x, y);
		pathing = false;
		direction = 120;
		max_speed = 0.1f;

		// Pathing setup
		cur_point = 0;
		patrolPoints = null;
	}

	public void move(float spd, float angle) {
		float sin = (float) Math.sin(Math.toRadians(angle));
		float cos = (float) Math.cos(Math.toRadians(angle));
		mapPos.setX(mapPos.getX() + spd * cos);
		mapPos.setY(mapPos.getY() + spd * sin);
	}

	public void moveTo(Point target) {
		if (getDistanceTo(target) >= (TOLERANCE)) {
			direction = getPointDirection(target);
			if (speed < max_speed)
				speed += stats[UnitStat.ACCELERATION.val()];
		} else
			speed = 0;
	}

	public Path findPath(Point target) {
		if (Utils.distance(mapPos, target) > (NodeMap.NODE_WIDTH / (NodeMap.RES))) {
			Path p;

			speed = 0;
			ArrayList<Node> nearestStartNodes = getNearestNodes(mapPos);
			ArrayList<Node> nearestEndNodes = getNearestNodes(target);

			nearestStartNodes.sort(new SortByDist(target));
			nearestEndNodes.sort(new SortByDist(target));

			if (nearestStartNodes.size() == 0) {
				System.out.println("Start Nodes Size: 0");
				return null;
			}
			if (nearestEndNodes.size() == 0) {
				System.out.println("End Nodes Size: 0");
				return null;
			}

			PathObject po = PathFinder.findPath(map.getNodeMap(), nearestStartNodes.get(0), nearestEndNodes.get(0));

			p = po.getPath();
			return p;
		} else
			return null;
	}

	public void moveAlongPath(Path p) {
		// Use path
		if (path.getNodes().size() > 0) {
			pathing = false;
			Point endPoint = path.getNodes().get(path.getNodes().size() - 1).getPos();
			Point nodePos = path.getNodes().get(0).getPos();

			float dist = Utils.distance(endPoint, mapPos);

			if (Utils.distance(mapPos, nodePos) < TOLERANCE) {
				path.getNodes().remove(0);
			} else
				moveTo(nodePos);

			if (dist <= (1.5 * NodeMap.NODE_WIDTH)) {
				des.setX(mapPos.getX());
				des.setY(mapPos.getY());

				speed = 0;
				path = null;
				return;
			}
		} else {
			speed = 0;
			path = null;
			return;
		}
	}

	public ArrayList<Node> getNearestNodes(Point pos) {
		ArrayList<Node> nodes = new ArrayList<Node>();

		Node[][] nMap = map.getNodeMap().getNodes();

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
		// State machine
		
		switch (state) {
		case GameConstants.STATE_IDLE:
			break;
			
		case GameConstants.STATE_MOVING:
			move(speed, direction);
			if(path == null)
				path = findPath(des);
			else if (path != null)
				moveAlongPath(path);
			break;
			
		case GameConstants.STATE_ATTACK:
			if (target != null)

				attack(this, target);
			break;
			
		case GameConstants.STATE_PATROL:
			// Set some patrol points
			if (patrolPoints == null)
				state = GameConstants.STATE_IDLE;
			
			patrol(this, patrolPoints);
			break;
			
		default:
			System.err.println(this + " invalid state: " + state + " setting state to idle");
			state = GameConstants.STATE_IDLE;
		}
		step();
	}
	
	// ============================================== Getters and Setters

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

	public float getMaxSpeed() {
		return max_speed;
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

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public int getCurPoint() {
		return cur_point;
	}

	public void setCurPoint(int index) {
		cur_point = index;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
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
