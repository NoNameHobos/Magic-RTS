package main.game.menu;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.input.Clickable;
import main.util.ResourceLoader;

public class MenuButton extends MenuElement implements Clickable {

	private Animation animation;
	private Animation backAnim;
	private String text;

	private ButtonAction buttonAction;
	
	private boolean defunct;
	
	public MenuButton(Menu menu, Point pos, String text, boolean defunct) {
		super(menu, text,  pos, new Point(190, 48));
		this.text = text;
		animation = new Animation(ResourceLoader.SPRITE_SHEETS.get("menu_button"), 5);
		animation.setLooping(false);
		backAnim = new Animation(ResourceLoader.SPRITE_SHEETS.get("menu_buttonR"), 5);
		backAnim.setLooping(false);
		
		this.defunct = defunct;
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
	
	// Button Input
	
	@Override
	public void mousePressed(int button, int x, int y) {
		
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		
	}

	@Override
	public boolean isAcceptingInput() {
		return false;
	}
	
	// Getters and Setters
	public String getText() {
		return text;
	}
	
	public boolean isDefunct() {
		return defunct;
	}
	
	public void setAction(ButtonAction action) {
		this.buttonAction = action;
	}
	
	public ButtonAction getAction() {
		return buttonAction;
	}
}
