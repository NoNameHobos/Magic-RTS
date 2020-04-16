package main.player;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.entities.Entity;
import main.entities.SelectableEntity;
import main.game.Game;
import main.input.Mouse;

public class Selector implements MouseListener {

	private Point startDrag = null, endDrag = null;

	private Player player;
	private Camera camera;

	private Rectangle selectBox;

	public Selector(Player p, Input input) {
		input.addMouseListener(this);
		player = p;

		camera = player.getCamera();
		selectBox = new Rectangle(0, 0, 0, 0);
	}

	public void update() {
		if (startDrag != null) {
			if (endDrag != null) {
				Point p = Game.UIToObject(startDrag, camera);
				selectBox.setX(p.getX());
				selectBox.setY(p.getY());
				float width = (endDrag.getX() - startDrag.getX()) / camera.getZoom();
				float height = (endDrag.getY() - startDrag.getY()) / camera.getZoom();
				selectBox.setWidth(width);
				selectBox.setHeight(height);
			}
		}
	}

	public Entity getNearestEntity(boolean onlySelectable) {
		ArrayList<Entity> entities = Entity.ENTITIES;
		Entity curEnt = entities.get(0);

		Mouse m = Engine.getMouse();
		
		float disCur = getDist(m.getPos(), curEnt.getPos());

		for (int i = 0; i < entities.size(); i++) {
			
			float disI = getDist(m.getPos(), entities.get(i).getPos());
			
			if (disI < disCur) {
				if (onlySelectable) {
					if (curEnt.isSelectable()) {
						curEnt = entities.get(i);
						disCur = disI;
					}
				} else {
					curEnt = entities.get(i);
					disCur = disI;
				}
			}
		}

		return curEnt;
	}

	public float getDist(Point p1, Point p2) {
		float distX = (float) Math.pow(p1.getX() - p2.getX(), 2);
		float distY = (float) Math.pow(p1.getY() - p2.getY(), 2);
		return (float) Math.pow(distX + distY, 0.5);

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
		if (startDrag != null) {
			if (camera != null) {
				endDrag = new Point(newx, newy);
			}
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (button == 0) {
			if (camera != null) {
				startDrag = new Point(x, y);

				Entity e = getNearestEntity(true);
				if (((SelectableEntity) e).mouseOver() && ((SelectableEntity) e).getPlayer() == player) {
					player.setSelected(e);
				} else
					player.setSelected(null);
			}
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		if (button == 0) {
			ArrayList<Entity> entities = Entity.ENTITIES;
			ArrayList<Entity> selected = new ArrayList<Entity>();
			for(Entity entity : entities) {
				if(entity.isSelectable()) {
					if(((SelectableEntity) entity).getPlayer() == player) {
						if(selectBox.contains(entity.getPos())) {
							System.out.println("Found entity");
							if(!selected.contains(entity)) {
								selected.add(entity);
							}
						}
					}
				}
			}
			
			player.setSelectedGroup(selected);
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

	public Rectangle getSelectBox() {
		return selectBox;
	}

}
