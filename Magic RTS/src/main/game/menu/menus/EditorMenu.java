package main.game.menu.menus;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.game.menu.Menu;
import main.game.menu.MenuButton;
import main.game.states.MenuState;

public class EditorMenu extends Menu {

	public EditorMenu(MenuState menuState) {
		super(menuState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int b, int x, int y) {
		if (b == 0) {
			for (MenuButton button : buttons) {
				if (button.mouseOver()) {
					switch (button.getText()) {

					case "New Map":
						alarm[0] = 60;
						break;

					case "Back":
						menuState.goBack();
						break;
					default:
						alarm[0] = 120;
						break;

					}
				}
			}
		}
	}

	@Override
	public void init() {
		if (initialized)
			return;
		initialized = true;

		final int MENU_Y = 400;
		final int BUTTON_HEIGHT = 55;
		
		buttons.add(new MenuButton(this, new Point(0, MENU_Y), "New Map"));
		buttons.add(new MenuButton(this, new Point(0, MENU_Y + BUTTON_HEIGHT), "Load Map"));
		buttons.add(new MenuButton(this, new Point(0, MENU_Y + BUTTON_HEIGHT * 2), "Delete"));
		buttons.add(new MenuButton(this, new Point(0, MENU_Y + BUTTON_HEIGHT * 4), "Back"));
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

}
