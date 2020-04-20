package main.game.ui;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.map.Map;
import main.game.player.Player;
import main.game.ui.elements.Bar;
import main.game.ui.elements.core.CommandHUD;
import main.game.ui.elements.core.Frame;
import main.game.ui.elements.core.Minimap;
import main.game.ui.elements.core.UnitInfo;
import main.util.ResourceLoader;

public class UI {

	private final ArrayList<UIElement> elements = new ArrayList<UIElement>();

	private Player player;

	public UI(Player player) {
		this.player = player;

		new Minimap(this, new Point(Engine.getWIDTH() - player.getFaction().getSprite("ui_minimap").getWidth(), 0));

		Image frameSprite = player.getFaction().getSprite("ui_bottombar");
		new Frame(this, new Point(0, Engine.getHEIGHT() - frameSprite.getHeight()));

		new CommandHUD(this, new Point(0, Engine.getHEIGHT() * 2 / 3 + frameSprite.getHeight()));
		
		float marginX = 16;
		float marginY = 32;
		
		new UnitInfo(this, new Point(Engine.getWIDTH() / 3 + marginX, Engine.getHEIGHT() * 2 / 3 + marginY));

		Image barSprite = ResourceLoader.UI.get("UIManaBar");
		new Bar(this, new Point(10, 10), barSprite, 100);
		barSprite = ResourceLoader.UI.get("UIStoneBar");
		new Bar(this, new Point(10, 20), barSprite, 100);
		barSprite = ResourceLoader.UI.get("UIMithrilBar");
		new Bar(this, new Point(10, 30), barSprite, 100);
	}

	public ArrayList<UIElement> getElements() {
		return elements;
	}

	public Player getPlayer() {
		return player;
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

				float x1 = Engine.getWIDTH() - 120;
				float y1 = Engine.getHEIGHT() - 200 + (height + marginY) * i;

				g.setColor(Color.darkGray);
				g.drawRect(x1, y1, width, height);
				g.setColor(currentPlayer.getPlayerColor());
				g.fillRect(x1 + 1, y1 + 1, width - 2, height - 2);
				g.setColor(Color.white);
				g.drawString(currentPlayer.getName(), x1 + marginX, y1 - height / 2);
			}
		}

	}
}
