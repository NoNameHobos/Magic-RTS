package main.game.menu;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.game.states.MenuState;
import main.util.ResourceLoader;

public class MenuButton extends MenuElement {

	private Animation animation;
	private Animation backAnim;
	private String text;

	public MenuButton(MenuState menuState, Point pos, String text) {
		super(menuState, text,  pos, new Point(190, 48));
		this.text = text;
		animation = new Animation(ResourceLoader.SPRITE_SHEETS.get("menu_button"), 5);
		animation.setLooping(false);
		backAnim = new Animation(ResourceLoader.SPRITE_SHEETS.get("menu_buttonR"), 5);
		backAnim.setLooping(false);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		float x;
		g.setFont(ResourceLoader.FONTS.get("Menu"));
		if (mouseOver()) {
			animation.start();
			animation.draw(pos.getX(), pos.getY());
			backAnim.restart();
			x = pos.getX() + animation.getFrame() * 0.5f;
			backAnim.stop();
		} else {
			animation.restart();
			animation.stop();
			backAnim.start();
			x = pos.getX() + (backAnim.getFrameCount() - backAnim.getFrame()) * 0.5f;
			backAnim.draw(pos.getX(), pos.getY());
		}
		g.drawString(text, x, pos.getY() - g.getFont().getHeight(text)/8);
	}

	public String getText() {
		return text;
	}

}
