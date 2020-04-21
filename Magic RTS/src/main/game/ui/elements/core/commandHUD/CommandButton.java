package main.game.ui.elements.core.commandHUD;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.ui.UI;
import main.game.ui.UIElement;
import main.game.ui.elements.core.CommandHUD;
import main.input.Button;

public class CommandButton extends UIElement implements Button {

	private CommandHUD cmd;
	private Rectangle collider;

	private Point mouse;
	
	private boolean pressed = false;

	public static final int WIDTH = 64, HEIGHT = 64;

	public CommandButton(UI ui, CommandHUD cmd, Point pos) {
		super(ui, pos);

		this.cmd = cmd;

		Engine.getInput().addMouseListener(this);
		collider = new Rectangle(pos.getX(), pos.getY(), WIDTH, HEIGHT);
		
		mouse = new Point(0, 0);
	}

	@Override // UIElement draw event
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		if (pressed)
			g.setColor(Color.green);
		g.draw(collider);
	}

	@Override // UIElement step event
	public void step() {
		if (!collider.contains(mouse))
			pressed = false;
	}

	// Button input
	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

	// Button input
	@Override
	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub
		if (collider.contains(new Point(x, y)))
			switch (button) {
			case 0:
				pressed = true;
				break;
			default:
				break;
			}
	}

	// Butotn input
	@Override
	public void mouseReleased(int button, int x, int y) {
		// TODO Auto-generated method stub
		if (collider.contains(new Point(x, y)))
			switch (button) {
			case 0:
				pressed = false;
				break;
			default:
				break;
			}

	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		mouse.setX(newx);
		mouse.setY(newy);
	}
	
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		mouse.setX(newx);
		mouse.setY(newy);
	}
}
