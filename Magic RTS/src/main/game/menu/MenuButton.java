package main.game.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.graphics.res.Sprite;

public class MenuButton extends MenuElement  {

	private Sprite forwardSprite, backwardSprite;
	private String text;

	private ButtonAction buttonAction;
	
	public MenuButton(Menu menu, Point pos, String text, boolean defunct) {
		super(menu, text,  pos, new Point(190, 48));
		this.text = text;
		forwardSprite = RES.getSprite("menu_button").copy();
		forwardSprite.getAnim().setLooping(false);
		backwardSprite = RES.getSprite("menu_buttonR");
		backwardSprite.getAnim().setLooping(false);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		
		float x = 0;
		
		g.setFont(RES.getFont("Menu"));
		if (mouseOver()) {
			forwardSprite.getAnim().start();
			forwardSprite.draw(mapPos.getX(), mapPos.getY());
			
			//animation.draw(pos.getX(), pos.getY());
			if(backwardSprite.getAnim().getFrame() != 0) {
				backwardSprite.getAnim().restart();
				backwardSprite.getAnim().stop();
			}
			//backAnim.restart();
			//backAnim.stop();
			x = mapPos.getX() + forwardSprite.getAnim().getFrame() * 0.5f;
		} else {
			if(forwardSprite.getAnim().getFrame() > 0) {
				backwardSprite.draw(mapPos.getX(), mapPos.getY());
				backwardSprite.getAnim().start();
			} else {
				forwardSprite.draw(x, mapPos.getY());
			}
			if(!forwardSprite.getAnim().isStopped()) {
				forwardSprite.getAnim().stop();
				forwardSprite.getAnim().restart();
			}
			if(backwardSprite.getAnim().getFrame() == backwardSprite.lastFrame())
				backwardSprite.getAnim().stop();
			//animation.restart();
			//animation.stop();
			//backAnim.start();
			x = mapPos.getX() + (backwardSprite.getAnim().getFrameCount() - backwardSprite.getAnim().getFrame()) * 0.5f;
			//backAnim.draw(pos.getX(), pos.getY());
		}
		g.drawString(text, x, mapPos.getY() - g.getFont().getHeight(text)/8);
	}

	// Getters and Setters
	public String getText() {
		return text;
	}
	
	public boolean isDefunct() {
		return (this.buttonAction == null);
	}
	
	public void setAction(ButtonAction action) {
		this.buttonAction = action;
	}
	
	public ButtonAction getAction() {
		return buttonAction;
	}

	@Override
	protected void step() {
		// TODO Auto-generated method stub
		
	}
}
