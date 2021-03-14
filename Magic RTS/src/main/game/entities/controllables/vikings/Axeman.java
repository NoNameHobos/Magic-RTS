package main.game.entities.controllables.vikings;

import main.game.entities.controllables.Unit;
import main.game.entities.controllables.UnitStat;
import main.game.player.Player;

public class Axeman extends Unit {

	//TODO: Implement State machine
		
	/*
	private static final SpriteSheet[] ANIM_SS = { 
			SPRITE_SHEETS.get("axeman_down"), //0
			SPRITE_SHEETS.get("axeman_up"), //1
			SPRITE_SHEETS.get("axeman_right"), //2
			SPRITE_SHEETS.get("axeman_left") //3
	};*/
	
	//private static Animation[] animations;
	
	public Axeman(Player player, float x, float y) {
		// TODO: Add Axeman Sprite to this constructor
		super(player, x, y, RES.getSprite("axeman_down"));
		
		//animations = new Animation[4];
		
		// Init Stats
		setStat(UnitStat.HP, 1f);
		setStat(UnitStat.HP_MAX, 1f);
		setStat(UnitStat.ACCELERATION, 0.0001f);
		setStat(UnitStat.PHYS_DEF, 0f);
		setStat(UnitStat.MAG_DEF, 0f);
		
		setStat(UnitStat.ATTACK, 1f);
		setStat(UnitStat.ATTACK_RANGE, 1f);
		/*
		animations[0] = new Animation(ANIM_SS[0], (int)(67 * (max_speed + 0.5f)));
		animations[0].setLooping(true);
		
		animations[1] = new Animation(ANIM_SS[1], (int)(67 * (max_speed + 0.5f)));
		animations[1].setLooping(true);
		
		animations[2] = new Animation(ANIM_SS[2], (int)(67 * (max_speed + 0.5f)));
		animations[2].setLooping(true);
		
		animations[3] = new Animation(ANIM_SS[3], (int)(67 * (max_speed + 0.5f)));
		animations[3].setLooping(true);*/
	}

	@Override
	public void step() {
		if(speed != 0)
			walking = true;
		else walking = false;
		
	}
}
