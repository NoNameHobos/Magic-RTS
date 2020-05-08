package main.input;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.Game;
import main.game.entities.Entity;
import main.game.player.Camera;
import main.game.states.GameState;

public class Mouse implements MouseListener {

	private Point pos, screenPos;
	private Input input;

	private final boolean[] button = new boolean[30];

	public Mouse(Input input) {
		this.input = input;
		input.addMouseListener(this);
		pos = new Point(input.getMouseX(), input.getMouseY());
		screenPos = new Point(input.getMouseX(), input.getMouseY());
	}

	public void update() {

		Point raw = new Point(input.getMouseX(), input.getMouseY());

		if (Engine.getCurrentState() == Engine.gameState) {
			Camera c = ((GameState) Engine.gameState).getGame().getMap().getMainCamera();
			if (c != null) {
				Point targetPoint = Game.UIToObject(raw, c);

				pos.setX(targetPoint.getX());
				pos.setY(targetPoint.getY());
			}
		} else {
			pos.setX(raw.getX());
			pos.setY(raw.getY());
		}

		screenPos.setX(raw.getX());
		screenPos.setY(raw.getY());
	}

	public boolean overEntity() {
		ArrayList<Entity> entities = Entity.ENTITIES;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getCollider().contains(pos))
				return true;
		}
		return false;
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
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {

	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		this.button[button] = true;
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.button[button] = false;
	}

	@Override
	public void mouseWheelMoved(int change) {

	}

	public boolean[] getButton() {
		return button;
	}

	public Point getPos() {
		return pos;
	}

	public Point getScreenPos() {
		return screenPos;
	}

}
