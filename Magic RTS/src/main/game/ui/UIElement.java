package main.game.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

public abstract class UIElement {

	protected UI ui;
	protected Point pos;
	protected Image sprite;
	protected int tiledX;
	protected int tiledY;
	
	public UIElement(UI ui, Point pos, Image spr, int tx, int ty) {
		this.ui = ui;
		this.pos = pos;
		this.sprite = spr;
		this.tiledX = tx;
		this.tiledY = ty;
		
		ui.getElements().add(this);
	}
	
	public UIElement(UI ui, Point pos, Image spr) {
		this.ui = ui;
		this.pos = pos;
		this.sprite = spr;
		this.tiledX = 1;
		this.tiledY = 1;
		
		ui.getElements().add(this);
	}
	
	public void render(Graphics g) {
		
		int height = sprite.getHeight();
		int width = sprite.getHeight();
		
		for (int x = 0 ; x < tiledX; x++) {
			for (int y = 0; y < tiledY; y++) {
				g.drawImage(sprite, pos.getX() + (x*width), pos.getY() + (y*height));
			}
		}
		
	}
	public abstract void tick();
	
	public UI getUI() {
		return ui;
	}
	
	public Point getPos() {
		return pos;
	}
	
	public void setPos(Point pos) {
		this.pos = pos;
	}
}
