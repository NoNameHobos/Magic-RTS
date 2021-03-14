package main;

import org.newdawn.slick.Input;

public class GameConstants {

	//~Errors
	public static final int ERROR_MISSING_TEXTURE = 1;
	
	public static final int TILE_WIDTH = 64;
	public static final int TILE_HEIGHT = 64;
	
	public static final int TW_RENDER = 64;
	public static final int TH_RENDER = 64;

	
	private static boolean arrowKeys = false;
	
	// Camera controls
	public static final int CAMERA_DOWN  = (!arrowKeys) ? Input.KEY_S : Input.KEY_DOWN;
	public static final int CAMERA_UP    = (!arrowKeys) ? Input.KEY_W : Input.KEY_UP;
	public static final int CAMERA_LEFT  = (!arrowKeys) ? Input.KEY_A : Input.KEY_LEFT;
	public static final int CAMERA_RIGHT = (!arrowKeys) ? Input.KEY_D : Input.KEY_RIGHT;
	
	// Unit states
	public static final int STATE_MOVING = 0;
	public static final int STATE_IDLE   = 1;
	public static final int STATE_ATTACK = 2;
	public static final int STATE_PATROL = 3;
}
