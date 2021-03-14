package main.game.entities;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.Entity;
import main.game.player.Camera;
import main.graphics.res.Sprite;
import main.util.ResourceLoader;

/**
 * A Renderable class to designate in-game objects as renderable.
 * Contains an active sprite 
 * @author bmeachem
 *
 */
public abstract class Renderable extends Entity {
	private static final ArrayList<Renderable> RENDERABLES = new ArrayList<Renderable>();

	protected static final ResourceLoader RES = Engine.RESOURCES;
	protected Sprite activeSprite;
	
	// Rendering depth
	// TODO: Implement this
	protected int depth;
	
	/**
	 * Create a Renderable with an assigned active sprite.
	 * @param pos - Position to create the renderable at
	 * @param sprite - Default sprite to render
	 */
	public Renderable(Point pos, Sprite sprite) {
		super(pos);
		activeSprite = sprite;
		RENDERABLES.add(this);
	}
	
	/**
	 * Create a new renderable with no assigned active sprite.
	 * @param pos - Position to create the renderable at
	 */
	public Renderable(Point pos) {
		this(pos, null);
	}
	
	public static void renderAll(Graphics g, Camera cam) {
		for(Renderable ren : getRenderables()) {
			if(cam.contains(ren)) {
				ren.render(g);
			}
		}
	}
	
	/**
	 * Render the active Sprite
	 */
	private void render(Graphics g) {
		if(activeSprite != null) {
			activeSprite.draw(mapPos.getX(), mapPos.getY());
		}
	}
	
	// Getters and Setters
	/**
	 * Get the active sprite currently being rendered
	 * @return - Sprite (main.graphics.res.Sprite)
	 */
	public Sprite getSprite() {
		return activeSprite;
	}

	/**
	 * Get the depth the Renderable is rendered at
	 * @return
	 */
	public int getDepth() {
		return depth;
	}
	
	public static ArrayList<Renderable> getRenderables() {
		return RENDERABLES;
	}
}
