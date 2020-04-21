package main.game.ui.elements.core;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.ui.UI;
import main.game.ui.UIElement;
import main.game.ui.elements.UIButton;

public class CommandHUD extends UIElement {

	private Rectangle border;

	private ArrayList<UIButton> buttons;

	public static final float WIDTH = Engine.getWIDTH() / 3, HEIGHT = 240 - 64;

	public CommandHUD(UI ui, Point pos) {
		super(ui, pos, WIDTH, HEIGHT);

		buttons = new ArrayList<UIButton>();
;

		float marginX = 5;
		float marginY = 5;

		int xCount = (int) Math.floor(WIDTH / (UIButton.WIDTH + marginX));
		int yCount = (int) Math.floor(HEIGHT / (UIButton.HEIGHT + marginY));

		float frameMarginX = (WIDTH - xCount * (UIButton.WIDTH + marginX)) / 2;
		float frameMarginY = (HEIGHT - yCount * (UIButton.HEIGHT + marginY)) / 2;
				
		for (int i = 0; i < xCount; i++) {

			for (int j = 0; j < yCount; j += 1) {
				float x = pos.getX() + frameMarginX + i * (UIButton.WIDTH + marginX);
				float y = pos.getY() + frameMarginY + j * (UIButton.HEIGHT + marginY);
				System.out.println("Added a new button" + i + " " + j);

				buttons.add(new UIButton(ui, this, new Point(x, y)));
			}
		}

		// Overall rectangle
		border = new Rectangle(pos.getX(), pos.getY(), WIDTH, HEIGHT);
	}

	@Override
	public void draw(Graphics g) {
		g.fill(border);
	}

	@Override
	public void step() {
	}

}
