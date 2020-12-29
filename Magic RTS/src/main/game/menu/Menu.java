package main.game.menu;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.states.MenuState;
import main.input.Clickable;

public abstract class Menu implements Clickable {

	protected ArrayList<MenuButton> buttons;

	protected int[] alarm = new int[10];

	protected MenuState menuState;

	protected boolean initialized = false;

	// Some abstract events
	protected abstract void init_buttons();
	public abstract void draw(Graphics g);

	private static final int M_LEFT = 0;
	private static final int M_RIGHT = 1;
	private static final int M_MIDDLE = 2;

	
	
	public Menu(MenuState menuState) {
		this.menuState = menuState;
		buttons = new ArrayList<MenuButton>();

		for (@SuppressWarnings("unused")
		int a : alarm) {
			a = -1;
		}
	}
	
	public void init() {
		if (initialized)
			return;
		initialized = true;
		
		init_buttons();
	}
	
	public void update() {
		for (int i = 0; i < alarm.length; i++) {
			if (alarm[i] != -1)
				alarm[i] -= 1;
		}
		if (menuState.getCurrentMenu() == this) {
			for (MenuButton button : buttons) {
				button.tick();
			}
		}
	}

	public void render(Graphics g) {
		for (MenuButton button : buttons) {
			button.render(g);
		}
		if (alarm[0] != -1) {
			g.setColor(new Color(1, 1, 1, (float) (alarm[0]) / 60));
			g.drawString("Button  Defunct", Engine.getWIDTH() / 3, Engine.getHEIGHT() / 2);
		}

		draw(g);
	}
	
	public MenuButton addButton(Point pos, String text, ButtonAction action) {
		MenuButton mb = new MenuButton(this, pos, text, (action == null));
		mb.setAction(action);
		return addButton(mb, action);
	}
	
	public MenuButton addButton(MenuButton button, ButtonAction action) {
		// TODO: Check success
		buttons.add(button);
		return button;
	}

	/*----------------------INTERFACE: MouseListener -------------*/

	@Override
	public void mousePressed(int b, int x, int y) {

		switch (b) {
			case M_LEFT:
				for (MenuButton button : buttons) {
					if (button.mouseOver())
						System.out.println(button.isDefunct());
					
					if (!button.isDefunct()) {
						if (button.mouseOver()) {
							// TODO: Fix NullPointer
							try {
								button.getAction().execute();
							} catch (NullPointerException e) {
								if (button.getAction() == null)
									System.err.println("Button: " + button.getText() + " has no action.");
								else
									System.err.println("An unknown error occurred with button: " + button.getText());
								e.printStackTrace();
							}
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

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void mouseReleased(int b, int x, int y) {
	}

	// Getters and Setters
	public ArrayList<MenuButton> getButtons() {
		return buttons;
	}
}
