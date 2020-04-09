package main.game.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.State;
import main.game.menu.MenuButton;
import main.game.menu.MenuElement;

public class MenuState extends State {

	public final ArrayList<MenuElement> elements = new ArrayList<MenuElement>();

	public final ArrayList<MenuButton> buttons = new ArrayList<MenuButton>();

	public MenuState() {
		super("Menu");
	}

	@Override
	public void init() {
		buttons.add(new MenuButton(this, new Point(0, 400), "Play"));
		buttons.add(new MenuButton(this, new Point(0, 500), "Quit"));
	}

	@Override
	public void tick() {
		for (int i = 0; i < buttons.size(); i++) {
			MenuElement curButton = buttons.get(i);
			curButton.tick();
			if(curButton.mouseOver()) {
				if (Engine.getMouse().getButton()[0]) {
					switch (((MenuButton) curButton).getText()) {
					case "Play":
						Engine.setCurrentState(Engine.gameState);
						break;
					case "Quit":
						System.exit(0); //TODO:Probably need to change this
						break;
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setBackground(new Color(0, 75, 0));
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).render(g);
		}
	}

	public ArrayList<MenuElement> getElements() {
		return elements;
	}

}
