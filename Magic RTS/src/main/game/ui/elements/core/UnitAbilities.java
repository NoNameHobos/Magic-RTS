package main.game.ui.elements.core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.game.player.Player;
import main.game.ui.UI;
import main.game.ui.UIElement;
import main.game.ui.elements.UIButton;

public class UnitAbilities extends UIElement {

	private Player player;
	
	private ArrayList<UIButton> buttons = new ArrayList<UIButton>();
	
	public final static int WIDTH = 220;
	public final static int HEIGHT = 220;
	
	public UnitAbilities(UI ui, Point pos) {
		super(ui, pos, WIDTH, HEIGHT);
		player = ui.getPlayer();
		
		float width = UIButton.WIDTH;
		float height = UIButton.HEIGHT;
		float buffer = 5;

		
		float marginX = (WIDTH - (width + buffer) * 3) / 2;
		float marginY = (HEIGHT - (height + buffer) * 3) / 2;
		
		for(int i = 0; i < 3; i++) {
			float y = bounding.getY() + marginY + (width + buffer) *i;
			
			for(int j = 0; j < 3; j++) {
				float x = bounding.getX() + marginX + (width + buffer) * j;
				
				Point buttonPos = new Point(x, y);
				
				buttons.add(new UIButton(ui, this, buttonPos, ""));
			}
		}
		
	}

	@Override
	public void draw(Graphics g) {
		
		// Draw the skills
		g.setColor(Color.red);
		
		if(player.getSelected().size() > 0) {
			for(UIButton button : buttons) {
				button.render(g);
			}
		}
	}

	@Override
	public void step() {
		
	}
	
}
