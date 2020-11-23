package main;

import org.newdawn.slick.Input;

public class GameConstants {

	public static final int TILE_WIDTH = 128;
	public static final int TILE_HEIGHT = 128;
	
	public static final int TW_RENDER = 64;
	public static final int TH_RENDER = 64;

	
	// Camera controls
	public static final int CAMERA_DOWN = Input.KEY_DOWN;
	public static final int CAMERA_UP = Input.KEY_UP;
	public static final int CAMERA_LEFT = Input.KEY_LEFT;
	public static final int CAMERA_RIGHT = Input.KEY_RIGHT;
	
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
