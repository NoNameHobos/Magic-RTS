package main.input;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;

public class Mouse {

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
}
