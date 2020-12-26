package main;

import org.newdawn.slick.Input;

public class GameConstants {

	public static final int TILE_WIDTH = 128;
	public static final int TILE_HEIGHT = 128;
	
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
	
	// Unit stats
	public static final int STAT_HEALTH       = 0;
	public static final int STAT_HEALTH_MAX   = 1;
	public static final int STAT_ACC          = 2;
	public static final int STAT_PHYS_DEF     = 3;
	public static final int STAT_MAG_DEF      = 4;
	public static final int STAT_ATTACK       = 5;
	public static final int STAT_ATTACK_RANGE = 6;
	
	public static final int STAT_COUNT = 7;
}
