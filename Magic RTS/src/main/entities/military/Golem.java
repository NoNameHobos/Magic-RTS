package main.entities.military;

import static main.engine.Engine.ENGINE;
import static main.util.ResourceLoader.SPRITES;
import static main.util.ResourceLoader.SPRITE_SHEETS;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import main.engine.Player;
import main.entities.Mob;

public class Golem extends Mob {
	
	private static Image SPRITE = SPRITES.get("spr_golem");
	private static SpriteSheet WALK_SS = SPRITE_SHEETS.get("golem_walk");
	
	private Animation walk;
	
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
	public void render(Graphics g) {
		//walk.getCurrentFrame().setRotation(direction);
		if(speed != 0) {
			walk.draw(pos.getX(), pos.getY());
			walk.getCurrentFrame().setRotation(direction);
		}
		sprite.setRotation(direction);
		sprite.draw(pos.getX(), pos.getY());
		// g.drawString(Float.toString(getPointDirection(ENGINE.getMouse().getPos())), pos.getX(), pos.getY() - 30);
	}
	
	//Tick/Step event for the Golem
	public void tick() {
		moveTo(ENGINE.getMouse().getPos());
		move(speed, direction);
	}
}
