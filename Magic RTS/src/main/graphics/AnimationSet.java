package main.graphics;

import java.util.HashMap;

import org.newdawn.slick.Animation;

public class AnimationSet {

	// TODO: Implement anim sets
	
	public final HashMap<String, AnimObj> animations = new HashMap<String, AnimObj>();

	private boolean addAnimObj(String name, AnimObj anim, boolean isFlipped) {
		animations.put(name, anim);
		return false;
	}
}
