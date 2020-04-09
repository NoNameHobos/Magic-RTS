package main.game.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.game.State;
import main.game.menu.Button;
import main.game.menu.MenuElement;

public class MenuState extends State {

	public final ArrayList<MenuElement> elements = new ArrayList<MenuElement>();
	
	public MenuState() {
		super("Menu");

	}

	@Override
	public void init() {
		elements.add(new Button(this, new Point(0, 400), "Play"));
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setBackground(new Color(0, 75, 0));
		for(int i = 0; i < elements.size(); i++) {
			elements.get(i).render(g);
		}
		
	}
	
	public ArrayList<MenuElement> getElements() {
		return elements;
	}

}
