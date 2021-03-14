package main.graphics.res;

import java.util.HashMap;

import org.newdawn.slick.Animation;

public class SpriteSet {

	// TODO: Implement anim sets

	private HashMap<String, Sprite> spriteSet;
	
	public static final String DEFAULT_SPRITE = "DEFAULT";
	
	/**
	 * Create a SpriteSet with sprites in it
	 */
	@SafeVarargs
	public SpriteSet(Sprite ... new_sprites) {
		spriteSet = new HashMap<String, Sprite>();
		for(Sprite sprite : new_sprites) {
			spriteSet.put(sprite.getName(), sprite);
		}
	}
	
	/**
	 * Create an empty SpriteSet
	 */
	public SpriteSet() {
		spriteSet = new HashMap<String, Sprite>();
	}
	
	// Getters and Setters
	/**
	 * Add sprite to the SpriteSet
	 * @param sprite - Sprite to add
	 * @return whether or not the operation was successful
	 */
	public boolean add(Sprite sprite) {
		try {
			spriteSet.put(sprite.getName(), sprite);
			return true;
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Gets a Sprite from the SpriteSet
	 * @param sprite_name - Name of sprite
	 * @return Sprite at given id (DEFAULT_SPRITE)
	 */
	public Sprite get(String sprite_name) {
		// TODO: Do some meaningful error handling here
		try {
			return spriteSet.get(sprite_name);
		} catch (ArrayIndexOutOfBoundsException e) {
			return spriteSet.get(DEFAULT_SPRITE);
		}
	}
	
	/**
	 * Gets the default sprite of the set
	 * @return SpriteSet's default sprite
	 */
	public Sprite get() {
		return get(DEFAULT_SPRITE);
	}
	
	public Animation getDefaultAnimation() {
		return get(DEFAULT_SPRITE).getAnim();
	}
}
