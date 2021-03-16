package main.graphics.res;

import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

import main.util.Resource;

public class Sprite extends Resource {

	public static final int DEFAULT_DURATION = 1000; //ms
	
	private Animation anim;
	private String name;
	
	// Default origin is top-left
	private Point origin = new Point(0, 0);
	
	public boolean isFlipped = false;
	
	private int duration;

	private Sprite(Animation anim, String name) {
		// TODO: Adjust this
		super();
		this.anim = anim;
		this.duration = Arrays.stream(anim.getDurations()).sum();
		this.name = name;
		
	}
	
	/**
	 * Create a single image sprite
	 * @param sprite SpriteSheet to use
	 * @return a new single image 
	 */
	public static Sprite createSprite(String name, Image img) {
		SpriteSheet ss = new SpriteSheet(img, img.getWidth(), img.getHeight());
		return createSprite(name, ss, -1);
	}
	
	/**
	 * Create a sprite from a sprite sheet
	 * @param spriteSheety - Reference sprite sheet
	 * @param duration - Duration of animation (ms)
	 * @return new sprite from the image with size tw x th.
	 */
	public static Sprite createSprite(String name, SpriteSheet spriteSheet, int duration) {
		Animation anim = new Animation(spriteSheet, duration);
		Sprite s = new Sprite(anim, name);
		s.duration = duration;
		System.out.println("Created new sprite: " + name);
		return s;
	}

	/**
	 * Create a sprite from an Image
	 * @param image - Reference sprite sheet (as an image)
	 * @param tw - Tile Width (px)
	 * @param th - Tile Height (px)
	 * @param duration - Duration of animation (ms)
	 * @return new sprite from the image with size tw x th.
	 */
	public static Sprite createSprite(String name, Image image, int tw, int th, int duration) {
		SpriteSheet ss = new SpriteSheet(image, tw, th);
		return createSprite(name, ss, duration);
	}

	// Draw function wrappers
	/**
	 * Draw the sprite
	 * 
	 * @param x The x position to draw the sprite at
	 * @param y The y position to draw the sprite at
	 * @param width The width to draw the sprite at
	 * @param height The height to draw the sprite at
	 * @param col The colour filter to use
	 */
	public void draw(float x, float y, float width, float height, Color col) {
		anim.draw(x - origin.getX(), y - origin.getY(), width, height, col);
	}
	
	public void draw(float x, float y, float width, float height) {
		draw(x, y, width, height, Color.white);
	}
	
	public void draw(float x, float y) {
		draw(x, y, anim.getWidth(), anim.getHeight());
	}
	
	// TODO: Reassess copy function
	public Sprite copy() {
		Sprite spr = new Sprite(anim, name);
		return spr;
	}

	// Getters and Setters
	public boolean isStopped() {
		return anim.isStopped();
	}
	
	public int lastFrame() {
		return anim.getFrameCount() - 1;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Animation getAnim() {
		return anim;
	}

	public Image getFrame(int index) {
		return anim.getImage(index);
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getWidth() {
		return anim.getWidth();
	}
	
	public int getHeight() {
		return anim.getHeight();
	}
	
	public void setOrigin(Point o) {
		origin = o;
	}
	
	public Point getOrigin() {
		return origin;
	}
}
