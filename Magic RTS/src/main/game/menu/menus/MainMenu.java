package main.game.menu.menus;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.menu.Menu;
import main.game.menu.MenuButton;
import main.game.states.MenuState;

public class MainMenu extends Menu {

	public MainMenu(MenuState state) {
		super(state);
	}
	
	@Override
	public void init() {
		if(initialized)
			return;
		initialized = true;
		
		buttons.add(new MenuButton(this, new Point(0, 400), "Campaign"));
		buttons.add(new MenuButton(this, new Point(0, 455), "Skirmish"));
		buttons.add(new MenuButton(this, new Point(0, 510), "Options"));
		buttons.add(new MenuButton(this, new Point(0, 565), "Quit"));
	}

	@Override
	public void draw(Graphics g) {
	}
	
	@Override
	public void step() {
		for (MenuButton button : buttons) {
			button.tick();
			if (button.mouseOver()) {
				if (Engine.getMouse().getButton()[0]) {

					switch (((MenuButton) button).getText()) {

					case "Skirmish":
						menuState.setCurrentMenu(MenuState.MENUS.get(1));
						//Engine.setCurrentState(Engine.gameState);
						break;

					case "Quit":
						System.exit(0); // TODO:Probably need to change this
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
