package main.game.ui;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import main.player.Player;

public class UI {

	private final ArrayList<UIElement> elements = new ArrayList<UIElement>();
	
	private Player player;
	
	public UI(Player player) {
		this.player = player;
		
		UIElement minimap = new Minimap(this);
	}

	public ArrayList<UIElement> getElements() {
		return elements;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void tick() {
		int size = elements.size();
		for(int i = 0; i < size; i++) {
			elements.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		int size = elements.size();
		for(int i = 0; i < size; i++) {
			elements.get(i).render(g);
		}
	}
}
