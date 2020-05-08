package main.game.ui;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.map.Map;
import main.game.player.Player;
import main.game.ui.elements.core.CommandHUD;
import main.game.ui.elements.core.Frame;
import main.game.ui.elements.core.Minimap;
import main.game.ui.elements.core.ResourceDisplay;
import main.game.ui.elements.core.UnitAbilities;
import main.game.ui.elements.core.UnitInfo;

public class UI {

	private final ArrayList<UIElement> elements = new ArrayList<UIElement>();

	private Player player;
	
	private int alpha = 255;
	
	public UI(Player player) {
		this.player = player;
		player.setUI(this);

		new Minimap(this, new Point(Engine.getWIDTH() - player.getFaction().getSprite("ui_minimap").getWidth(), 0));

		Image frameSprite = player.getFaction().getSprite("ui_bottombar");
		new Frame(this, new Point(0, Engine.getHEIGHT() - frameSprite.getHeight()));

		new CommandHUD(this, new Point(0, Engine.getHEIGHT() - CommandHUD.HEIGHT));
		
		UnitInfo.WIDTH = (int) (Engine.getWIDTH() - CommandHUD.WIDTH - UnitAbilities.WIDTH);
		
		new UnitAbilities(this, new Point(Engine.getWIDTH() - UnitAbilities.WIDTH, Engine.getHEIGHT() - UnitAbilities.HEIGHT));
		new UnitInfo(this, new Point(CommandHUD.WIDTH, Engine.getHEIGHT() - UnitInfo.HEIGHT));

		new ResourceDisplay(this, new Point(5, 5));
		
		System.out.println("Created a ui");
	}
	
	public void tick() {
		int size = elements.size();
		for (int i = 0; i < size; i++) {
			elements.get(i).tick();
		}
	}

	public void render(Graphics g) {
		int size = elements.size();
		for (int i = 0; i < size; i++) {
			elements.get(i).render(g);
		}

		renderPlayerList(g);

	}

	public void renderPlayerList(Graphics g) {
		Map m = player.getMap();
		Player[] players = m.getPlayers();
		for (int i = 0; i < players.length; i++) {
			Player currentPlayer = players[i];
			if (currentPlayer != null) {
				float width = 8;
				float height = 8;

				float marginX = 12;
				float marginY = 12;

				float x1 = Engine.getWIDTH() - Minimap.WIDTH;
				float y1 = elements.get(0).getBounding().getY() + marginY + i * g.getFont().getHeight(currentPlayer.getName());

				g.setColor(Color.darkGray);
				g.drawRect(x1, y1, width, height);
				g.setColor(currentPlayer.getPlayerColor());
				g.fillRect(x1 + 1, y1 + 1, width - 1, height - 1);
				g.setColor(Color.white);
				float buffer = g.getFont().getWidth(currentPlayer.getName()) + marginX;
				g.drawString(currentPlayer.getName(), x1 + marginX - buffer, y1 - height / 2);
			}
		}

	}
	
	public boolean contains(Point p) {		
		for(UIElement element : elements) {
			if(element.getBounding().contains(p))
				return true;
		}
		return false;
	}
	
	// Getters and Setters
	
	public int getAlpha() {
		return alpha;
	}
	
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public ArrayList<UIElement> getElements() {
		return elements;
	}

	public Player getPlayer() {
		return player;
	}

}
