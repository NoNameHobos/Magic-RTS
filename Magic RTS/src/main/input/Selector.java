package main.input;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.entities.Entity;
import main.entities.SelectableEntity;
import main.player.Camera;
import main.player.Player;

public class Selector implements MouseListener {

	private Point startDrag = null, endDrag = null;
	
	private Player player;
	private Camera camera;
	
	private boolean dragging = false;
	
	public Selector(Player p, Input input) {
		input.addMouseListener(this);
		player = p;
		
		camera = player.getCamera();
	}
	
	public Entity getNearestEntity(boolean selectable) {
		ArrayList<Entity> entities = Entity.ENTITIES;
		Entity curEnt = entities.get(0);

		Mouse m = Engine.getMouse();
		float disCur = getDist(m.getPos(), curEnt.getPos());
		
		for(int i = 0; i < entities.size(); i++) {
			float disI = getDist(m.getPos(), entities.get(i).getPos());
			if(disI < disCur) {
				if(selectable) {
					if(curEnt.isSelectable()) {}
				} else {
					curEnt = entities.get(i);
					disCur = disI;
				}
			}
		}
		
		return curEnt;
	}
	
	public float getDist(Point p1, Point p2) {
		float distX = (float)Math.pow(p1.getX() - p2.getX(), 2);
		float distY = (float)Math.pow(p1.getY() - p2.getY(), 2);
		return (float)Math.pow(distX + distY, 0.5);
		
	}
	
	@Override
	public void inputEnded() {
	}

	@Override
	public void inputStarted() {		
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input input) {
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		if(startDrag != null) {
			Mouse m = Engine.getMouse();
			endDrag = new Point(m.getPos().getX(), m.getPos().getY());
		}
		dragging = true;
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if(button == 0) {
			Mouse m = Engine.getMouse();
			startDrag = new Point(m.getPos().getX(), m.getPos().getY());
			Entity e = getNearestEntity(true);
			if(((SelectableEntity) e).mouseOver()) {
				player.setSelected(e);
			}	
			System.out.println("pressed");
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		if(button == 0) {
			dragging = false;
			endDrag = null;
			startDrag = null;
		}
	}

	@Override
	public void mouseWheelMoved(int change) {
	}

	public Point getStartDrag() {
		return startDrag;
	}

	public Point getEndDrag() {
		return endDrag;
	}

}
