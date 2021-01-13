package main.graphics;

import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Anim {

	// TODO: Implement Anim

	private Animation anim;
	private String name;

	public static final int NUM_PROPS = 1;
	public boolean properties[] = new boolean[NUM_PROPS];

	public static final int FLIPPED = 0;

	public int duration;

	public Anim(Animation anim, String name) {
		this.anim = anim;
		this.duration = Arrays.stream(anim.getDurations()).sum();
		this.name = name;
	}

	// Static create function
	private static Anim createAnim(SpriteSheet sprite, int duration) {
		Animation anim = new Animation(sprite, duration);
		return new Anim(anim, sprite.getName());
	}

	public static Anim createSet(Image ref, int tw, int th, int duration) {
		SpriteSheet ss = new SpriteSheet(ref, tw, th);
		return createAnim(ss, duration);
	}

	// Getters and Setters

	public String getName() {
		return name;
	}

	public Animation getAnim() {
		return anim;
	}

	public boolean getProperty(int index) {
		try {
			return properties[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Failed to get property of " + name + " at index " + index);
			return false;
		}
	}

	public void setProperty(int index, boolean prop) {
		try {
			properties[index] = prop;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Could not retrieve property of " + name + " at index " + index);
		}
	}

	public Image getFrame(int index) {
		return anim.getImage(index);
	}
}
