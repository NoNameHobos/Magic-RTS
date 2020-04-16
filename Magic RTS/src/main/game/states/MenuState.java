package main.game.states;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.game.State;
import main.game.menu.Menu;
import main.game.menu.MenuElement;
import main.game.menu.menus.MainMenu;

public class MenuState extends State {

	public final ArrayList<MenuElement> elements = new ArrayList<MenuElement>();

	protected Map<String, Menu> menus = new HashMap<String, Menu>();
	
	protected Menu currentMenu;
	
	public MenuState() {
		super("Menu");
		menus.put("Main Menu", new MainMenu());
	}

	@Override
	public void init() {
		currentMenu.init();
	}

	@Override
	public void tick() {
		currentMenu.update();
	}

	@Override
	public void render(Graphics g) {
		g.setBackground(new Color(0, 75, 0));
		currentMenu.render(g);
	}

	public ArrayList<MenuElement> getElements() {
		return elements;
	}

}
