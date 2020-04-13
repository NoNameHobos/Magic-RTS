package main.entities.unit;

import static main.util.ResourceLoader.SPRITES;
import static main.util.ResourceLoader.SPRITE_SHEETS;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import main.engine.Engine;
import main.entities.Unit;
import main.player.Player;

public class Golem extends Unit {
	
	private static Image SPRITE = SPRITES.get("spr_golem");
	private static SpriteSheet WALK_SS = SPRITE_SHEETS.get("golem_walk");
	
	private static Animation walk;
	
	public Golem(Player player, float x, float y) {
		super(player, x, y, SPRITE);
		
		//Init Stats
		health = 80;
		move_speed = 1.5f;
		direction = 120;
		acc = 0.1f;
		phys_def = 12;
		mag_def = 9;
		health_max = health;
		walk = new Animation(WALK_SS, 500);
		walk.setLooping(true);
		
	}
	//Render/Draw event for the Golem
	@Override
	public void draw(Graphics g) {
		if(speed != 0) {
			walk.draw(pos.getX(), pos.getY());
			walk.getCurrentFrame().setRotation(direction);
		}
	}
	
	//Tick/Step event for the Golem
	public void tick() {
		super.tick();
		moveTo(Engine.getMouse().getPos());
		move(speed, direction);
	}
}
