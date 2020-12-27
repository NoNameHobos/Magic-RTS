package main.game.menu;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.states.MenuState;

public abstract class Menu implements MouseListener {

	protected ArrayList<MenuButton> buttons;

	protected int[] alarm = new int[10];
	
	protected MenuState menuState;
	
	protected boolean initialized = false;
	
	public Menu(MenuState menuState, ArrayList<MenuButton> buttons) {
		this.menuState = menuState;
		
		this.buttons = buttons;
		for(@SuppressWarnings("unused") int a : alarm) {
			a = -1;
		}
	}

	public Menu(MenuState menuState) {
		this.menuState = menuState;
		buttons = new ArrayList<MenuButton>();
		
		for(@SuppressWarnings("unused") int a : alarm) {
			a = -1;
		}
	}
	
	public boolean addButton(Point pos, String text, boolean defunct) {
		MenuButton mb = new MenuButton(this, pos, text, defunct);
		addButton(mb);
		return true;
	}
	
	public boolean addButton(MenuButton button) {
		// TODO: Check success
		
		buttons.add(button);
		
		return true;
	}
	
	public abstract void init();
	
	public void update() {
		for(int i = 0; i < alarm.length; i++) {
			if(alarm[i] != -1) alarm[i] -= 1;
		}
		if(menuState.getCurrentMenu() == this) {
			for(MenuButton button : buttons) {
				button.tick();
			}
			
			
			step();
		}
	}
	
	public void render(Graphics g) {
		for(MenuButton button : buttons) {
			button.render(g);
		}
		if (alarm[0] != -1) {
			g.setColor(new Color(1, 1, 1, (float) (alarm[0]) / 60));
			g.drawString("Button  Defunct", Engine.getWIDTH() / 3, Engine.getHEIGHT() / 2);
		}
		
		draw(g);
	}
	
	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
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
		return menuState.getCurrentMenu() == this;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	
	//Some abstract events
	public abstract void draw(Graphics g);
	public abstract void step();
	
	// Getters and Setters
	
	public ArrayList<MenuButton> getButtons() {
		return buttons;
	}
}
