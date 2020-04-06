package main.graphics;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import main.engine.Engine;

public class Display implements Runnable {

	private AppGameContainer appgc;
	
	public void run() {
		System.out.println("Started Display Thread");
	}
	
	public Display(Engine engine, int WIDTH, int HEIGHT) {
		System.out.println("Initializing Display");
		try {
			appgc = new AppGameContainer(engine);
			appgc.setTargetFrameRate(60);
			appgc.setShowFPS(true);
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
