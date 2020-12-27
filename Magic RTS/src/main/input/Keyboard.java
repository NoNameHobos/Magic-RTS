package main.input;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class Keyboard implements KeyListener {
	
	
	public Keyboard(Input input) {
		input.addKeyListener(this);
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
	public void keyPressed(int key, char c) {
	}

	@Override
	public void keyReleased(int key, char c) {
	}

}
