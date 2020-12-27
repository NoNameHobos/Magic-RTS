package main.game.menu.menus;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.game.menu.Menu;
import main.game.menu.MenuButton;
import main.game.states.MenuState;

public class MainMenu extends Menu {

	public MainMenu(MenuState state) {
		super(state);
	}

	@Override
	public void init() {
		if (initialized)
			return;
		initialized = true;

		final int MENU_Y = 400;
		final int BUTTON_HEIGHT = 55;

		boolean all_defunct = false;

		final String[] button_text = { "Campaign", "Skirmish", "Map Editor", "Options", "Quit" };

		for(int i = 0; i < button_text.length; i++) {
			buttons.add(new MenuButton(this, new Point(0, MENU_Y + BUTTON_HEIGHT * i), button_text[i], all_defunct));
		}
	}

	@Override
	public void draw(Graphics g) {
	}

	@Override
	public void step() {
		for (MenuButton button : buttons) {
			button.tick();

		}

	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {

	}
	
	@Override
	public void mouseReleased(int b, int x, int y) {
	}

}
