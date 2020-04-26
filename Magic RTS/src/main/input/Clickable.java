package main.input;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public interface Clickable extends MouseListener {
	
	@Override
	default void mouseClicked(int button, int x, int y, int ClickCount) {
		
	}

	@Override
	default void mouseDragged(int oldx, int oldy, int newx, int newy) {
		
	}
	
	default void mouseMoved(int oldx, int oldy, int newx, int newy) {
		
	}
	
	@Override
	default void mouseWheelMoved(int change){
		
	}
	
	@Override
	default void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	default void inputStarted() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	default void setInput(Input input) {
		// TODO Auto-generated method stub
		
	}
	
}
