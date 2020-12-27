package main.game.menu.menus;

import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.Game;
import main.game.map.Map;
import main.game.menu.Menu;
import main.game.menu.MenuButton;
import main.game.states.MenuState;
import main.util.ResourceLoader;

public class MapSelectMenu extends Menu {

	private HashMap<String, Map> mapList;

	public MapSelectMenu(MenuState state) {
		super(state);
	}

	@Override
	public void init() {
		if (initialized)
			return;
		initialized = true;

		mapList = ResourceLoader.MAPS;
		String str = mapList.keySet().toString().replaceAll("\\[", "");
		str = str.replaceAll("\\]", "");
		String[] vals = str.split(", ");

		float y = 200;
		float marginY = 55;

		for (int i = 0; i < vals.length; i++) {
			buttons.add(new MenuButton(this, new Point(0, y + marginY * i), vals[i], false));
		}

		buttons.add(new MenuButton(this, new Point(0, Engine.getHEIGHT() - 65), "Back", false));
	}

	@Override
	public void draw(Graphics g) {

	}

	@Override
	public void step() {
		for (MenuButton button : buttons)
			button.tick();
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void mousePressed(int b, int x, int y) {
		if (b == 0) {
			for (MenuButton button : buttons) {
				if (button.mouseOver()) {
					if (button.getText() != "Back") {
						Game.MAP_TO_LOAD = button.getText();
						Engine.getInput().removeAllListeners();
						Engine.setCurrentState(Engine.gameState);
					} else {
						menuState.goBack();
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(int b, int x, int y) {
	}

}
