package main.game.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.player.Player;

public abstract class UIElement {

	protected UI ui;

	protected Point uiPos;

	protected Player player;

	protected Rectangle bounding;
	
	public UIElement(UI ui, Point pos, float width, float height) {
		this.ui = ui;

		this.player = ui.getPlayer();

		bounding = new Rectangle(pos.getX(), pos.getY(), width, height);
		
		this.uiPos = new Point(bounding.getX() / Engine.getWIDTH(), bounding.getY() / Engine.getHEIGHT());
		ui.getElements().add(this);
	}

	public void tick() {
		if(uiPos != null) {
			//TODO: Give ui visibility to the camera and make only one ui instance instead
			//of one per player
			bounding.setX(uiPos.getX() * Engine.getWIDTH());
			bounding.setY(uiPos.getY() * Engine.getHEIGHT());
		}
		step();
	}

	public void render(Graphics g) {
		g.setColor(new Color(255, 255, 255, ui.getAlpha()));
		draw(g);
	}

	public abstract void draw(Graphics g);

	public abstract void step();

	public UI getUI() {
		return ui;
	}

	public Rectangle getBounding() {
		return bounding;
	}
}
