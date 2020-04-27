package main.game.ui.elements.core;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.game.ui.UI;
import main.game.ui.UIElement;
import main.game.ui.elements.UIButton;

public class CommandHUD extends UIElement {

	private ArrayList<UIButton> buttons;

	private static final int buttonWidth = 3;
	private static final int buttonHeight = 3;
	
	public static final float WIDTH = UIButton.WIDTH * buttonWidth + 20, HEIGHT = UIButton.HEIGHT * buttonHeight + 20;

	public CommandHUD(UI ui, Point pos) {
		super(ui, pos, WIDTH, HEIGHT);

		buttons = new ArrayList<UIButton>();

		float marginX = 2;
		float marginY = 2;
		/*
		 * int xCount = (int) Math.floor(WIDTH / (UIButton.WIDTH + marginX)); int yCount
		 * = (int) Math.floor(HEIGHT / (UIButton.HEIGHT + marginY));
		 */

		float frameMarginX = (WIDTH - buttonWidth * (UIButton.WIDTH + marginX)) / 2;
		float frameMarginY = (HEIGHT - buttonHeight * (UIButton.HEIGHT + marginY)) / 2;
				
		for (int i = 0; i < buttonWidth; i++) {

			for (int j = 0; j < buttonHeight; j += 1) {
				float x = pos.getX() + frameMarginX + i * (UIButton.WIDTH + marginX);
				float y = pos.getY() + frameMarginY + j * (UIButton.HEIGHT + marginY);

				buttons.add(new UIButton(ui, this, new Point(x, y)));
			}
		}
	}

	@Override
	public void draw(Graphics g) {
	}

	@Override
	public void step() {
	}

}
