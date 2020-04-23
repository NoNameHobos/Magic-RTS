package main.game.player;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.entities.Entity;
import main.entities.SelectableEntity;
import main.entities.Unit;
import main.game.Game;
import main.input.Mouse;

public class Selector implements MouseListener {

	private Point startPoint = null, endPoint = null;

	private Player player;
	private Camera camera;

	private boolean selectedSomething = false;

	private Rectangle selectBox;
	
	private boolean selecting = false;

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
		if (player.getUI() != null) {
			if (!player.getUI().contains(new Point(Engine.getInput().getMouseX(), Engine.getInput().getMouseY()))
					|| selecting) {
				return true;
			} else return false;
		} else return false;
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
		if (camera != null) {
			switch (button) {
			// Left click
			case 0:
				startPoint = new Point(x, y);

				selecting = true;
				
				// Clear the selected units
				if (!selectedSomething)
					player.getSelected().clear();

				// Handle single selection
				SelectableEntity nearest = (SelectableEntity) getNearestEntity(true);

				if (nearest.mouseOver()) {
					player.getSelected().add(nearest);
					selectedSomething = true;
				}
				break;
			// Right click
			case 1:
				if (player.getSelected().size() > 0) {
					for (Entity entity : player.getSelected()) {
						if (entity.getType() == "Unit") {

							Unit unit = (Unit) entity;
							Point p = Game.UIToObject(new Point(x, y), camera);
							unit.setPath(null);
							unit.getDes().setX(p.getX());
							unit.getDes().setY(p.getY());
							unit.setPathing(true);
						}
					}
				}
				break;
			}
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		if (button == 0) {
			ArrayList<Entity> entities = Entity.ENTITIES;
			ArrayList<Entity> selected = new ArrayList<Entity>();

			selecting = false;
			
			if (startPoint != null && endPoint != null) {

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

								if (!selected.contains(entity)) {
									selected.add(entity);
								}
							}
						}
					}
				}
			} else if (startPoint != null) {
				if (camera != null) {
					SelectableEntity e = (SelectableEntity) getNearestEntity(true);
					float centX = e.getCollider().getWidth() / 2 + e.getCollider().getX();
					float centY = e.getCollider().getHeight() / 2 + e.getCollider().getY();

					Point toGame = Game.UIToObject(new Point(x, y), camera);

					boolean safeX = Math.abs(toGame.getX() - centX) < e.getCollider().getWidth() / 2;
					boolean safeY = Math.abs(toGame.getY() - centY) < e.getCollider().getHeight() / 2;
					if (safeX && safeY)
						selected.add(e);
				}
			}

			player.setSelected(selected);

			endPoint = null;
			startPoint = null;
			selectedSomething = false;
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
