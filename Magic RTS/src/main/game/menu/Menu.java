package main.game.menu;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public abstract class Menu {

	protected ArrayList<MenuButton> buttons;

	protected int[] alarm = new int[10];
	
	public Menu(ArrayList<MenuButton> buttons) {
		this.buttons = buttons;
		for(@SuppressWarnings("unused") int a : alarm) {
			a = -1;
		}
	}

	public Menu() {
		buttons = new ArrayList<MenuButton>();
	}
	
	public abstract void init();
	
	public void update() {
		for(MenuButton button : buttons) {
			button.tick();
		}
		
		for(int i = 0; i < alarm.length; i++) {
			if(alarm[i] != -1) alarm[i] -= 1;
		}
		
		step();
	}
	
	public void render(Graphics g) {
		for(MenuButton button : buttons) {
			button.render(g);
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
