package main.input;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Point;

import main.entities.Entity;
import main.entities.SelectableEntity;

public class Mouse implements MouseListener {
	
	private Point pos;
	private Input input;

	private final boolean[] button = new boolean[30];
	
	public Mouse(Input input) {
		this.input = input;
		input.addMouseListener(this);
		pos = new Point(input.getMouseX(), input.getMouseY());
	}
	
	public void update() {
		pos.setX(input.getMouseX());
		pos.setY(input.getMouseY());
	}
	
	public boolean overEntity() {
		ArrayList<Entity> entities = Entity.ENTITIES;
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i).getCollider().contains(pos))
				return true;
		}
		return false;
	}
	
	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setInput(Input input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	public boolean[] getButton() {
		return button;
	}
	
	public Point getPos() {
		return pos;
	}

}
