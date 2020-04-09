package main.game.menu;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.game.states.MenuState;
import main.util.ResourceLoader;

public class Button extends MenuElement {

	private static Animation animation;
	private String text;

	public Button(MenuState menuState, Point pos, String text) {
		super(menuState, pos, new Point(250, 48));
		this.text = text;
		animation = new Animation(ResourceLoader.SPRITE_SHEETS.get("menu_button"), 5);
		animation.setLooping(false);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		if (mouseOver()) {
			animation.start();
			animation.draw(pos.getX(), pos.getY());
		} else {
			Image def = animation.getImage(0);
			def.draw(pos.getX(), pos.getY());
			animation.restart();
			animation.stop();
		}
		g.setColor(Color.white);
		float x = pos.getX() + animation.getFrame() * 0.5f;
		g.setFont(ResourceLoader.FONTS.get("Menu"));
		g.drawString(text, x, pos.getY() - g.getFont().getHeight(text)/8);
		System.out.println();
	}

}
