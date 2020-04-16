package main.game.ui;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.map.Map;
import main.player.Player;

public class UI {

	private final ArrayList<UIElement> elements = new ArrayList<UIElement>();
	
	private Player player;
	
	public UI(Player player) {
		this.player = player;
		
		new Minimap(this, new Point(0, Engine.getHEIGHT()/3*2));
		new UnitInfo(this, new Point(300, Engine.getHEIGHT()/3 * 2));
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
		
		renderPlayerList(g);
		
	}
	
	public void renderPlayerList(Graphics g) {
		Map m = player.getMap();
		Player[] players = m.getPlayers();
		for(int i = 0; i < players.length; i++) {
			Player currentPlayer = players[i];
			if(currentPlayer != null) {
				float width = 8;
				float height = 8;
				
				float marginX = 12;
				float marginY = 12;
				
				float x1 = Engine.getWIDTH() - 120;
				float y1 = Engine.getHEIGHT() - 200 + (height+marginY)* i;
				
				g.setColor(Color.darkGray);
				g.drawRect(x1, y1, width, height);
				g.setColor(currentPlayer.getPlayerColor());
				g.fillRect(x1+1, y1+1, width-2, height-2);
				g.setColor(Color.white);
				g.drawString(currentPlayer.getName(), x1 + marginX, y1-height/2);
			}
		}
		
	}
}
