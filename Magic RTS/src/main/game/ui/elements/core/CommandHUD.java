package main.game.ui.elements.core;

import static main.util.ResourceLoader.UI;

import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.game.ui.UI;
import main.game.ui.UIElement;
import main.game.ui.elements.UIButton;

public class CommandHUD extends UIElement {

	private HashMap<String, UIButton> buttons;

	private static final int buttonWidth = 3;
	private static final int buttonHeight = 3;

	
	public static final float WIDTH = UIButton.WIDTH * buttonWidth + 20, HEIGHT = UIButton.HEIGHT * buttonHeight + 20;

	private static String[] buttonTags = { 
			"MOVE", "ATTACK", "BUILD", 
			"BLANK3", "BLANK4", "BLANK5", 
			"BLANK6", "BLANK7","BLANK8" 
	};

	private static Image[] buttonSprites = { 
			UI.get("move_button"), UI.get("attack_button"), UI.get("build_button"), 
			null, null, null, 
			null, null, null 
	};
	private static Image sprite = UI.get("commandhud");

	public CommandHUD(UI ui, Point pos) {
		super(ui, pos, WIDTH, HEIGHT);

		buttons = new HashMap<String, UIButton>();

		float marginX = 2;
		float marginY = 2;
		
		float xOffset = 3; //TODO: Adjust image
		float yOffset = 3;
		/*
		 * int xCount = (int) Math.floor(WIDTH / (UIButton.WIDTH + marginX)); int yCount
		 * = (int) Math.floor(HEIGHT / (UIButton.HEIGHT + marginY));
		 */

		float frameMarginX = (WIDTH - buttonWidth * (UIButton.WIDTH + marginX)) / 2;
		float frameMarginY = (HEIGHT - buttonHeight * (UIButton.HEIGHT + marginY)) / 2;

		for (int i = 0; i < buttonWidth; i++) {
			// Buttons are made in reverse order so that the tooltips "appear" on top of them all
			int index = buttonWidth - i - 1;
			float x = xOffset + pos.getX() + frameMarginX + index * (UIButton.WIDTH + marginX);
			
			for (int j = 0; j < buttonHeight; j++) {
				int jindex = buttonHeight - j -1;
				
				float y = yOffset + pos.getY() + frameMarginY + jindex * (UIButton.HEIGHT + marginY);

				String name = buttonTags[index + jindex * buttonWidth];
				Image image = buttonSprites[index + jindex * buttonWidth];
				
				buttons.put(name, new UIButton(ui, this, new Point(x, y), name, image));
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(sprite, bounding.getX(), bounding.getY());
	}

	@Override
	public void step() {
	}

}
