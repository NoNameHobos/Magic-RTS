package main.game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.engine.Engine;
import main.game.State;

public class LoadState extends State {
	
	public LoadState() {
		super("Loading");
		//RES = new ResourceLoader();
		//RES.init();
	}
	
	public void init() {
	}
	
	@Override
	public void step() {
	}

	@Override
	public void draw(Graphics g) {
		g.setBackground(Color.white);
		g.setColor(Color.black);
		g.drawString("Loading...",Engine.getWIDTH()/4, Engine.getHEIGHT()/4);
		g.drawRect(300, 300, 100, 25);
	}
}