package main.engine;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public abstract class State {

	protected String name;
	public static ArrayList<State> STATES = new ArrayList<State>();
	
	protected boolean init = true;
	
	public State(String name) {
		this.name = name;
		STATES.add(this);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public abstract void initState();
	
}
