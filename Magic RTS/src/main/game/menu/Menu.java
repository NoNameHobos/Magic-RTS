package main.game.menu;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.engine.Engine;
import main.game.states.MenuState;

public abstract class Menu {

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
	
	
	//Some abstract events
	public abstract void draw(Graphics g);
	public abstract void step();
	
	// Getters and Setters
	
	public ArrayList<MenuButton> getButtons() {
		return buttons;
	}
}
