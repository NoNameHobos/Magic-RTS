package main.game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.engine.Engine;
import main.game.State;
import main.util.ResourceLoader;

public class LoadState extends State {
	
	public LoadState() {
		super("Loading");
	}

	public void init() {
		ResourceLoader.initResources();	
	}
	
	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.setBackground(Color.white);
		g.setColor(Color.black);
		g.drawString("Loading...",Engine.getWIDTH()/4, Engine.getHEIGHT()/4);
		g.drawRect(300, 300, 100, 25);
		float percentage = ResourceLoader.getLoaded();
		g.drawRect(301, 301, 99*percentage, 24);
	}

}
