package main.game.ui.elements;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.game.ui.UI;
import main.game.ui.UIElement;

public class Bar extends UIElement {

	private Image sprite;

	private int width;

	private float progress = 0f;

	public Bar(UI ui, Point pos, Image s, int w) {
		super(ui, pos, w, s.getHeight());
		sprite = s;
		width = w;

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(sprite.getScaledCopy(width, sprite.getHeight()), bounding.getX(), bounding.getY(), new Color(100, 100, 100));
		g.drawImage(sprite.getScaledCopy((int) (width * progress), sprite.getHeight()), bounding.getX(), bounding.getY());
	}

	@Override
	public void step() {
	}

	public void setProgress(float p) {
		progress = p;
	}

}
