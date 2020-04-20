package main.game.ui.elements.core;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.ui.UI;
import main.game.ui.UIElement;
import main.game.ui.elements.core.commandHUD.CommandButton;

public class CommandHUD extends UIElement {

	private Rectangle border;

	private ArrayList<CommandButton> buttons;

	private static final float WIDTH = Engine.getWIDTH() / 3, HEIGHT = 240 - 64;

	public CommandHUD(UI ui, Point pos) {
		super(ui, pos);

		buttons = new ArrayList<CommandButton>();
;

		float marginX = 5;
		float marginY = 5;

		int xCount = (int) Math.floor(WIDTH / (CommandButton.WIDTH + marginX));
		int yCount = (int) Math.floor(HEIGHT / (CommandButton.HEIGHT + marginY));

		float frameMarginX = (WIDTH - xCount * (CommandButton.WIDTH + marginX)) / 2;
		float frameMarginY = (HEIGHT - yCount * (CommandButton.HEIGHT + marginY)) / 2;
				
		for (int i = 0; i < xCount; i++) {

			for (int j = 0; j < yCount; j += 1) {
				float x = pos.getX() + frameMarginX + i * (CommandButton.WIDTH + marginX);
				float y = pos.getY() + frameMarginY + j * (CommandButton.HEIGHT + marginY);
				System.out.println("Added a new button" + i + " " + j);

				buttons.add(new CommandButton(ui, this, new Point(x, y)));
			}
		}

		// Overall rectangle
		border = new Rectangle(pos.getX(), pos.getY(), WIDTH, HEIGHT);
	}

	@Override
	public void draw(Graphics g) {
		g.draw(border);
	}

	@Override
	public void step() {
	}

}
