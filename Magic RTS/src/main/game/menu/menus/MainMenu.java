package main.game.menu.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.menu.Menu;
import main.game.menu.MenuButton;

public class MainMenu extends Menu {

	public MainMenu() {
		super();
	}

	@Override
	public void init() {
		buttons.add(new MenuButton(this, new Point(0, 400), "Play"));
		buttons.add(new MenuButton(this, new Point(0, 450), "Options"));
		buttons.add(new MenuButton(this, new Point(0, 500), "Quit"));
	}

	@Override
	public void draw(Graphics g) {
		if (alarm[0] != -1) {
			g.setColor(new Color(1, 1, 1, (float) (alarm[0]) / 60));
			g.drawString("Button  Defunct", Engine.getWIDTH() / 3, Engine.getHEIGHT() / 2);
		}
	}

	// The default function for what whappens when a defunct button is pressed
	public void drawDefunct(Graphics g) {
	}

	@Override
	public void step() {
		for (MenuButton button : buttons) {
			button.tick();
			if (button.mouseOver()) {
				if (Engine.getMouse().getButton()[0]) {

					switch (((MenuButton) button).getText()) {

					case "Play":
						Engine.setCurrentState(Engine.gameState);
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
