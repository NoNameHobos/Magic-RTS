package main.game.entities.ai.pathfinding;

public class PathObject {

	private boolean completed;
	private Path path;
	
	public PathObject(boolean completed, Path path) {
		this.completed = completed;
		this.path = path;
	}

	public boolean isCompleted() {
		return completed;
	}

	public Path getPath() {
		return path;
	}
	
	
}
