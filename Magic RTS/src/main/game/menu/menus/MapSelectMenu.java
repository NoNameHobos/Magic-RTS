package main.game.menu.menus;

import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.map.Map;
import main.game.menu.Menu;
import main.game.menu.MenuButton;
import main.game.states.MenuState;
import main.util.ResourceLoader;

public class MapSelectMenu extends Menu {

	public MapSelectMenu(MenuState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	private HashMap<String, Map> maps;

	@Override
	public void init() {
		if(initialized)
			return;
		initialized = true;
		
		maps = ResourceLoader.MAPS;
		String str = maps.keySet().toString().replaceAll("\\[", "");
		str = str.replaceAll("\\]", "");
		String[] vals = str.split(",");

		float y = 200;
		float marginY = 55;

		for (int i = 0; i < vals.length; i++) {
			buttons.add(new MenuButton(this, new Point(0, y + marginY * i), vals[i]));
		}

		buttons.add(new MenuButton(this, new Point(0, Engine.getHEIGHT() - 65), "Back"));
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void step() {
		for (MenuButton button : buttons) {
			button.tick();
			if (button.mouseOver()) {
				if (Engine.getMouse().getButton()[0]) {

					switch (((MenuButton) button).getText()) {
					case "Back":
						menuState.setCurrentMenu(MenuState.MENUS.get(0));
						break;
					default:
						alarm[0] = 120;
						break;

					}
				}
			}
		}

	}

}
