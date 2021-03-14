package main.game.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.graphics.res.Sprite;
import main.util.ResourceLoader;

public class MenuButton extends MenuElement  {

	private Sprite sprite;
	private String text;

	private ButtonAction buttonAction;
	
	public MenuButton(Menu menu, Point pos, String text, boolean defunct) {
		super(menu, text,  pos, new Point(190, 48));
		this.text = text;
		sprite = RES.getSprite("menu_button").copy();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		
		//float x;
		
		g.setFont(ResourceLoader.FONTS.get("Menu"));
		if (mouseOver()) {
			sprite.draw(mapPos.getX(), mapPos.getY());
			// TODO: In Sprite.java implement boolean isStopped()
			//if(animation.isStopped())
			//	animation.start();
			
			//animation.draw(pos.getX(), pos.getY());
			
			//if(backAnim.getCurrentFrame())
			//backAnim.restart();
			//backAnim.stop();
			//x = pos.getX() + animation.getFrame() * 0.5f;
		} else {
			//animation.restart();
			//animation.stop();
			//backAnim.start();
			//x = pos.getX() + (backAnim.getFrameCount() - backAnim.getFrame()) * 0.5f;
			//backAnim.draw(pos.getX(), pos.getY());
		}
		g.drawString(text, mapPos.getX(), mapPos.getY() - g.getFont().getHeight(text)/8);
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
