package main.input;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Point;

public class Mouse implements MouseListener {

	private boolean leftPressed;
	private boolean rightPressed;
	private boolean leftButton;
	private boolean rightButton;
	private boolean middlePressed;
	private boolean middleButton;
	
	private Point pos;
	private Input input;
	
	public Mouse(Input input) {
		this.input = input;
		input.addMouseListener(this);
		pos = new Point(input.getMouseX(), input.getMouseY());
	}
	
	public void update() {
		leftPressed = input.isMousePressed(0);
		rightPressed = input.isMousePressed(1);
		middlePressed = input.isMousePressed(2);
		
		leftButton = input.isMouseButtonDown(0);
		rightButton = input.isMouseButtonDown(1);
		middleButton = input.isMouseButtonDown(2);
		pos.setX(input.getMouseX());
		pos.setY(input.getMouseY());
	}
	
	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public boolean isLeftButton() {
		return leftButton;
	}

	public boolean isRightButton() {
		return rightButton;
	}

	public boolean isMiddlePressed() {
		return middlePressed;
	}

	public boolean isMiddleButton() {
		return middleButton;
	}
	
	public Point getPos() {
		return pos;
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
		// TODO Auto-generated method stub
		System.out.println(button);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int change) {
		// TODO Auto-generated method stub
		
	}
}
