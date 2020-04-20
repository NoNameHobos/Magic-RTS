package main.game.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.player.Player;

public abstract class UIElement {

	protected UI ui;

	protected Point pos;
	protected Point uiPos;
	
	protected Player player;

	public UIElement(UI ui, Point pos) {
		this.ui = ui;

		this.player = ui.getPlayer();
		
		this.pos = pos;
		this.uiPos = new Point(pos.getX() / Engine.getWIDTH(), pos.getY() / Engine.getHEIGHT());
		ui.getElements().add(this);
	}

	public void tick() {
		if(uiPos != null) {
			//TODO: Give ui visibility to the camera and make only one ui instance instead
			//of one per player
			pos.setX(uiPos.getX() * Engine.getWIDTH());
			pos.setY(uiPos.getY() * Engine.getHEIGHT());
		}
		step();
	}

	public void render(Graphics g) {
		draw(g);
	}

	public abstract void draw(Graphics g);

	public abstract void step();

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
