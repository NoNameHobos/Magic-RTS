package main.engine;

import java.io.File;

public class Main {

	private static Thread engineThread;
	private static Engine engine;
	
	public static void main(String[] args) {
		
		System.setProperty("org.lwjgl.librarypath", new File("").getAbsolutePath());
		
		engine = new Engine(1280, 720, "RTS Game");
		engineThread = new Thread(engine);
		engineThread.start();
	}
	
}
