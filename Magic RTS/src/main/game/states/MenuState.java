package main.game.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.game.State;
import main.game.menu.Menu;
import main.game.menu.MenuElement;
import main.game.menu.menus.MainMenu;

public class MenuState extends State {

	public final ArrayList<MenuElement> elements = new ArrayList<MenuElement>();

	protected Menu menu;
	
	public MenuState() {
		super("Menu");
		menu = new MainMenu();
	}

	@Override
	public void init() {
		menu.init();
	}

	@Override
	public void tick() {
		menu.update();
	}

	@Override
	public void render(Graphics g) {
		g.setBackground(new Color(0, 75, 0));
		menu.render(g);
	}

	public ArrayList<MenuElement> getElements() {
		return elements;
	}

}
