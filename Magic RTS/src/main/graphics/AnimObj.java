package main.graphics;

import org.newdawn.slick.Animation;

public class AnimObj {
	
	// TODO: Implement AnimObj
	
	private Animation anim;
	
	public boolean isFlipped = false;
	public int duration;
	
	public AnimObj(Animation anim) {
		this.anim = anim;
		this.duration = 0;
	}

}
