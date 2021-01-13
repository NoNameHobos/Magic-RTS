package main.graphics;

import org.newdawn.slick.Animation;

public class AnimSet {

	// TODO: Implement anim sets

	private Anim anims[];
	
	public static final int DEFAULT_SPRITE = 0;
	
	private Animation currentAnim;
	
	public AnimSet(int anim_count) {
		anims = new Anim[anim_count];
	}
	
	// Getters and Setters
	
	public boolean set(Anim anim, int index) {
		try {
			anims[index] = anim;
			return true;
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public Anim get(int index) {
		// TODO: Do some meaningful error handling here
		try {
			return anims[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			return anims[DEFAULT_SPRITE];
		}
	}
	
	/**
	 * Gets the default sprite (slick animation) of the set
	 * @return AnimObj at id 0 (DEFAULT_SPRITE)
	 */
	public Anim getSpriteObj() {
		return getSpriteObj(DEFAULT_SPRITE);
	}

	/**
	 * Gets the default sprite (slick animation) of the set
	 * @param id an AnimObj id
	 * @return AnimObj at given id (DEFAULT_SPRITE)
	 */
	public Anim getSpriteObj(int id) {
		return get(id);
	}
	
	public Animation getDefaultSprite() {
		return get(DEFAULT_SPRITE).getAnim();
	}
}
