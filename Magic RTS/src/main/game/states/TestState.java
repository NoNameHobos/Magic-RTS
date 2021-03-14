package main.game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.engine.Engine;
import main.game.State;
import main.graphics.res.Sprite;

public class TestState extends State {

	private Sprite test_sprite;
	
	public TestState() {
		super("Test State");
	}

	@Override
	protected void init() {
		test_sprite = RES.getSprite("menu_button");
		System.out.println("Loaded sprite: " + test_sprite);
	}

	@Override
	public void step() {
		
	}

	@Override
	public void draw(Graphics g) {
		g.setBackground(Color.black);
		test_sprite.draw(Engine.getWIDTH() / 2, Engine.getHEIGHT() / 2);
	}

}
