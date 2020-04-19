package main.game.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.engine.Engine;
import main.game.State;
import main.game.menu.Menu;
import main.game.menu.MenuElement;
import main.game.menu.menus.MainMenu;
import main.game.menu.menus.MapSelectMenu;

public class MenuState extends State {

	public final ArrayList<MenuElement> elements = new ArrayList<MenuElement>();

	public static ArrayList<Menu> MENUS = new ArrayList<Menu>();

	private Menu currentMenu;

	public MenuState() {
		super("Menu");
		MENUS.add(new MainMenu(this));
		MENUS.add(new MapSelectMenu(this));
		currentMenu = MENUS.get(0);
	}

	@Override
	public void init() {
		currentMenu.init();
		
		for(Menu menu : MENUS) {
			Engine.getInput().addMouseListener(menu);
		}
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

	public void setCurrentMenu(Menu menu) {
		menu.init();
		currentMenu = menu;
	}
	
	public Menu getCurrentMenu() {
		return currentMenu;
	}

}
