package main.engine;

import java.io.File;

public class Main {
	
	public static void main(String[] args) {
		
		System.setProperty("org.lwjgl.librarypath", new File("").getAbsolutePath());
		new Engine(1280, 720, "RTS Game");
	}
	
}
