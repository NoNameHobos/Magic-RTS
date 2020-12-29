package main.game.menu;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.game.states.MenuState;
import main.input.Clickable;

public abstract class Menu implements Clickable {
	
	protected ArrayList<MenuButton> buttons;

	protected int[] alarm = new int[10];

	protected MenuState menuState;

	protected boolean initialized = false;

	// Some abstract events
	protected abstract void init_buttons();

	private static final int M_LEFT = 0;
	//private static final int M_RIGHT = 1;
	//private static final int M_MIDDLE = 2;
	
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
	
	public MenuButton addButton(int x, int y, String text, ButtonAction action) {
		Point pos = new Point(x, y);
		return addButton(pos, text, action);
	}
	
	public MenuButton addBackButton(Point pos) {
		return addButton(pos, "Back", () -> {
			menuState.setCurrentMenu(menuState.getLastMenu());
		});
	}

	/*----------------------INTERFACE: MouseListener -------------*/

	@Override
	public void mousePressed(int b, int x, int y) {

		switch (b) {
			case M_LEFT:
				for (MenuButton button : buttons) {		
						if (button.mouseOver()) {			
							if (!button.isDefunct()) {
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
							} else System.out.println("Button defunct");
						}
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
