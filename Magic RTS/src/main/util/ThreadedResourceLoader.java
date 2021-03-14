package main.util;

import java.util.ArrayList;
import java.util.HashMap;

public class ThreadedResourceLoader implements Runnable {

	private ArrayList<String> queue;
	public final static HashMap<String, Resource> RESOURCES = new HashMap<String, Resource>();

	private boolean running = false;

	public ThreadedResourceLoader() {
	}

	private void init() {
		queue = new ArrayList<String>();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;

		init();
	}

	@Override
	public void run() {
		start();

		// Do something
		while (running) {
			// If something is on the queue, load it
			if (queue.size() > 0) {

			}
		}

		stop();
	}

	public synchronized void stop() {

	}

}
