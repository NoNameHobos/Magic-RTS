package main.game.ui.elements.core;

import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.game.ui.UI;
import main.game.ui.UIElement;
import main.game.ui.elements.UIButton;

import static main.util.ResourceLoader.UI;

public class CommandHUD extends UIElement {

	private HashMap<String, UIButton> buttons;

	private static final int buttonWidth = 3;
	private static final int buttonHeight = 3;

	public static final float WIDTH = UIButton.WIDTH * buttonWidth + 20, HEIGHT = UIButton.HEIGHT * buttonHeight + 20;

	private static String[] buttonTags = { 
			"MOVE", "BLANK1", "BLANK2", 
			"BLANK3", "BLANK4", "BLANK5", 
			"BLANK6", "BLANK7","BLANK8" 
	};

	private static Image[] buttonSprites = { 
			UI.get("move_button"), null, null, 
			null, null, null, 
			null, null, null 
	};

	public CommandHUD(UI ui, Point pos) {
		super(ui, pos, WIDTH, HEIGHT);

		buttons = new HashMap<String, UIButton>();

		float marginX = 2;
		float marginY = 2;
		/*
		 * int xCount = (int) Math.floor(WIDTH / (UIButton.WIDTH + marginX)); int yCount
		 * = (int) Math.floor(HEIGHT / (UIButton.HEIGHT + marginY));
		 */

		float frameMarginX = (WIDTH - buttonWidth * (UIButton.WIDTH + marginX)) / 2;
		float frameMarginY = (HEIGHT - buttonHeight * (UIButton.HEIGHT + marginY)) / 2;

		for (int i = 0; i < buttonWidth; i++) {

			for (int j = 0; j < buttonHeight; j++) {
				float x = pos.getX() + frameMarginX + i * (UIButton.WIDTH + marginX);
				float y = pos.getY() + frameMarginY + j * (UIButton.HEIGHT + marginY);

				String name = buttonTags[i + j * buttonWidth];
				Image image = buttonSprites[i + j * buttonWidth];
				
				buttons.put(name, new UIButton(ui, this, new Point(x, y), name, image));
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
