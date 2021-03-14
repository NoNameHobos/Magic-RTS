package main.game.ui.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.engine.Engine;
import main.game.ui.UI;
import main.game.ui.UIElement;
import main.graphics.res.Sprite;
import main.input.Clickable;

public class UIButton extends UIElement implements Clickable, KeyListener {

	private Rectangle collider;

	private Point mouse;

	private boolean pressed = false;
	private boolean overButton = false;

	public static final int WIDTH = 48, HEIGHT = 48;

	private String name;
	
	private char hotKey;
	
	public UIButton(UI ui, UIElement container, Point pos, String name, Sprite s) {
		super(ui, pos, WIDTH, HEIGHT);

		Engine.getInput().addMouseListener(this);
		Engine.getInput().addKeyListener(this);
		
		collider = bounding;

		sprite = s;

		this.name = name;
		System.out.println(name);
		hotKey = this.name.toLowerCase().charAt(0);
		
		mouse = new Point(0, 0);
	}
	
	public UIButton(UI ui, UIElement container, Point pos, String name) {
		super(ui, pos, WIDTH, HEIGHT);

		Engine.getInput().addMouseListener(this);
		collider = bounding;
		
		sprite = null;
		
		this.name = name;
		System.out.println(name);
		try {
			hotKey = this.name.toLowerCase().charAt(0);
		} catch(StringIndexOutOfBoundsException e) {
			System.err.println("failed to get hotkey: " + name);
		}

		mouse = new Point(0, 0);
	}

	@Override // UIElement draw event
	public void draw(Graphics g) {
		if (sprite != null) {
			float x1 = bounding.getX();
			float x2 = bounding.getX() + bounding.getWidth();
			
			float y1 = bounding.getY();
			float y2 = bounding.getY() + bounding.getHeight();
			
			sprite.draw(x1, y1, x2, y2);
		}
		g.setColor(Color.red);
		if (pressed)
			g.setColor(Color.green);
		g.draw(collider);
		
		if(overButton) {
			String tooltip = name + "(" + Character.toUpperCase(hotKey) + ")";
			int alpha = 100;
			int tooltipLen = g.getFont().getWidth(tooltip);
			int tooltipHeight = g.getFont().getHeight(tooltip);
			
			int buffer = 4;
			
			
			g.setColor(new Color(0,0,0,alpha));
			g.fillRect(mouse.getX() - buffer, mouse.getY() - buffer, tooltipLen + buffer * 2, tooltipHeight + buffer * 2);
			g.setColor(Color.white);
			g.drawString(tooltip, mouse.getX(), mouse.getY());
		}
	}

	@Override // UIElement step event
	public void step() {
		if (!collider.contains(mouse))
			pressed = false;
	}

	// Button input
	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	// Button input
	@Override
	public void mousePressed(int button, int x, int y) {
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
		
		overButton = bounding.contains(new Point(newx, newy));
	}

	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		mouse.setX(newx);
		mouse.setY(newy);

		overButton = bounding.contains(new Point(newx, newy));
	}

	@Override
	public void keyPressed(int key, char c) {
		if(c == hotKey) {
			System.out.println(name + " hotkey pressed");
		}
	}

	@Override
	public void keyReleased(int key, char c) {
	}
}
