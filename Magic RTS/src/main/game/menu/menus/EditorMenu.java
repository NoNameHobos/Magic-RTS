package main.game.menu.menus;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.game.menu.Menu;
import main.game.states.MenuState;

public class EditorMenu extends Menu {

	public EditorMenu(MenuState menuState) {
		super(menuState);
	}

	@Override
	protected void init_buttons() {

		final int MENU_Y = 400;
		final int BUTTON_HEIGHT = 55;
		
		addButton(new Point(0, MENU_Y), "New Map", null);
		addButton(new Point(0, MENU_Y + BUTTON_HEIGHT), "Load Map", null);
		addButton(new Point(0, MENU_Y + BUTTON_HEIGHT * 2), "Delete", null);
		addButton(new Point(0, MENU_Y + BUTTON_HEIGHT * 3), "Back", null);
	}
	
	@Override
	public void draw(Graphics g) {
		
	}
}
