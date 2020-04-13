package main.input;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Point;

public class Selector implements MouseListener {

	private Point startDrag = null, endDrag = null;
	
	public Selector(Input input) {
		input.addMouseListener(this);
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
		if(startDrag != null)
			endDrag = new Point(newx, newy);
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if(button == 0)
			startDrag = new Point(x, y);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		if(button == 0) {
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
