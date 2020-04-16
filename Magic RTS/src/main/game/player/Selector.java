package main.game.player;

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

	private Point startPoint = null, endPoint = null;

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
		if (startPoint != null) {
			if (endPoint != null) {
				Point p = Game.UIToObject(startPoint, camera);

				selectBox.setX(p.getX());
				selectBox.setY(p.getY());

				float width = (endPoint.getX() - startPoint.getX()) / camera.getZoom();
				float height = (endPoint.getY() - startPoint.getY()) / camera.getZoom();

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
		if (startPoint != null) {
			if (camera != null) {
				endPoint = new Point(newx, newy);
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
				startPoint = new Point(x, y);

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
			if(startPoint != null && endPoint != null) {
				for (Entity entity : entities) {
					if (entity.isSelectable()) {
						if (((SelectableEntity) entity).getPlayer() == player) {
							Point p = Game.UIToObject(startPoint, camera);
							
							
	
							float width = (endPoint.getX() - startPoint.getX()) / camera.getZoom();
							float height = (endPoint.getY() - startPoint.getY()) / camera.getZoom();
	
							float centX = p.getX() + width / 2;
							float centY = p.getY() + height / 2;
	
							boolean safeX = Math.abs(entity.getPos().getX() - centX) < Math.abs(width) / 2;
							boolean safeY = Math.abs(entity.getPos().getY() - centY) < Math.abs(height) / 2;
	
							if (safeX && safeY) {
	
								System.out.println("Found entity");
	
								if (!selected.contains(entity))
									selected.add(entity);
							}
						}
					}
				}
			}

			player.setSelectedGroup(selected);
			endPoint = null;
			startPoint = null;
		}
	}

	@Override
	public void mouseWheelMoved(int change) {
	}

	public Point getStartDrag() {
		return startPoint;
	}

	public Point getEndDrag() {
		return endPoint;
	}

	public Rectangle getSelectBox() {
		return selectBox;
	}

}
