package main.game;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import main.engine.Engine;
import main.util.ResourceLoader;

public abstract class State {
	protected static final ResourceLoader RES = Engine.RESOURCES;
	
	protected String name;
	public static ArrayList<State> STATES = new ArrayList<State>();
	
	protected boolean initialized = false;
	
	protected abstract void init();
	public abstract void step();
	public abstract void draw(Graphics g);
	
	public State(String name) {
		this.name = name;
		STATES.add(this);
	}

	public void start() {
		if(initialized)
			return;
		initialized = true;
		System.out.println("Initializing " + name);
		init();
	}
	
	public String getName() {
		return name;
	}
}
