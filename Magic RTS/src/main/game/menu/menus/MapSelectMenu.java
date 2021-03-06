package main.game.menu.menus;

import java.util.HashMap;

import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.map.Map;
import main.game.menu.Menu;
import main.game.menu.MenuButton;
import main.game.states.MenuState;

import static main.game.Game.MAP_TO_LOAD;;

public class MapSelectMenu extends Menu {

	private HashMap<String, Map> mapList;

	public MapSelectMenu(MenuState state) {
		super(state);
	}

	@Override
	protected void init_buttons() {
		float y = 200;
		float marginY = 55;
		
		mapList = RES.getMaps();
		
		String str = mapList.keySet().toString().replaceAll("\\[", "");
		str = str.replaceAll("\\]", "");
		String[] vals = str.split(", ");


		for (int i = 0; i < vals.length; i++) {
			MenuButton cur_button = addButton(new Point(0, y + i * marginY), vals[i], null);
			cur_button.setAction(() -> {
				MAP_TO_LOAD = cur_button.getText();
				Engine.setCurrentState("game");
			});
		}

		// TODO: Remove "65" as a magic number
		// Back Button
		addBackButton(new Point(0, Engine.getHEIGHT() - 65));
	}
}
