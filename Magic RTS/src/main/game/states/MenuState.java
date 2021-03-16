package main.game.states;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.engine.Engine;
import main.game.State;
import main.game.menu.Menu;
import main.game.menu.MenuElement;
import main.game.menu.menus.EditorMenu;
import main.game.menu.menus.MainMenu;
import main.game.menu.menus.MapSelectMenu;

public class MenuState extends State {

	public final ArrayList<MenuElement> elements = new ArrayList<MenuElement>();

	public static HashMap<String, Menu> MENUS = new HashMap<String, Menu>();

	private Menu lastMenu, currentMenu;

	public MenuState() {
		super("Menu");
		MENUS.put("main", new MainMenu(this));
		MENUS.put("map_select", new MapSelectMenu(this));
		MENUS.put("editor", new EditorMenu(this));
		MENUS.forEach((str, menu) -> Engine.getInput().addMouseListener(menu));
	}

	@Override
	public void init() {
		currentMenu = MENUS.get("main");
		currentMenu.init();
	}

	@Override
	public void step() {
		currentMenu.update();
	}

	@Override
	public void draw(Graphics g) {
		g.setBackground(new Color(0, 75, 0));
		currentMenu.render(g);
	}

	public ArrayList<MenuElement> getElements() {
		return elements;
	}

	public void setCurrentMenu(Menu menu) {
		lastMenu = currentMenu;
		menu.init();
		currentMenu = menu;
	}
	
	public void setCurrentMenu(String name) {
		Menu menu = MENUS.get(name);
		try {
			setCurrentMenu(menu);
		} catch(NullPointerException e) {
			System.err.println("Menu: " + name + " is null");
			e.printStackTrace();
		}
	}
	
	public Menu getCurrentMenu() {
		return currentMenu;
	}
	
	public Menu getLastMenu() {
		return lastMenu;
	}
	
	public void goBack() {
		setCurrentMenu(lastMenu);
	}

}
