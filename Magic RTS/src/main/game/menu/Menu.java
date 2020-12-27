 package main.game.menu;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.states.MenuState;
import main.input.Clickable;

public abstract class Menu implements Clickable {

	protected ArrayList<MenuButton> buttons;

	protected int[] alarm = new int[10];
	
	protected MenuState menuState;
	
	protected boolean initialized = false;

	//Some abstract events
	public abstract void init();
	public abstract void draw(Graphics g);
	public abstract void step();

	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int MIDDLE = 2;
	
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
	
	public boolean addButton(Point pos, String text, ButtonAction action) {
		MenuButton mb = new MenuButton(this, pos, text, (action == null));
		mb.setAction(action);
		return addButton(mb, action);
	}
	
	public boolean addButton(MenuButton button, ButtonAction action) {
		// TODO: Check success
		buttons.add(button);
		
		return (action != null);
	}
	
	/*----------------------INTERFACE: MouseListener -------------*/

	@Override
	public void mousePressed(int b, int x, int y) {
		
		switch(b) {
			case LEFT:
				for (MenuButton button : buttons) {
					if(!button.isDefunct()) {
						if(button.mouseOver()) {
							button.getAction().execute();
						}
					} else alarm[0] = 120;
				}
			break;
		}
	}
	
	@Override
	public boolean isAcceptingInput() {
		return menuState.getCurrentMenu() == this;
	}
	
	// Getters and Setters
	public ArrayList<MenuButton> getButtons() {
		return buttons;
	}
}
