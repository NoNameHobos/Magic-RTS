package main.game;

import static main.engine.Engine.ENGINE;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import main.engine.Engine;

public abstract class State {

	protected final Engine engine = ENGINE;
	
	protected String name;
	public static ArrayList<State> STATES = new ArrayList<State>();
	
	public State(String name) {
		this.name = name;
		STATES.add(this);
	}

	public abstract void init();
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public String getName() {
		return name;
	}
	
	public Engine getEngine() {
		return engine;
	}
}
