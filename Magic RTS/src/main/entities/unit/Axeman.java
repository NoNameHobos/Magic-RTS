package main.entities.unit;

import static main.util.ResourceLoader.SPRITES;
import static main.util.ResourceLoader.SPRITE_SHEETS;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

import main.entities.Unit;
import main.game.states.GameState;
import main.player.Player;

public class Axeman extends Unit {

	private static final Image SPRITE = SPRITES.get("axeman");
	private static final SpriteSheet[] ANIM_SS = { 
			SPRITE_SHEETS.get("axeman_down") //0
	};
	
	private static Animation walk;
	
	public Axeman(Player player, float x, float y) {
		super(null, x, y, SPRITE);
		
		//Init Stats
		health = 80;
		move_speed = 3f;
		direction = 120;
		acc = 0.5f;
		phys_def = 12;
		mag_def = 9;
		health_max = health;
		walk = new Animation(ANIM_SS[0], 67);
		walk.setLooping(true);
	}

	@Override
	public void render(Graphics g, float xOffset, float yOffset) {
		super.render(g, xOffset, yOffset);
		if(speed != 0 && getFacing() == 0)
			walk.draw(pos.getX() - origin.getX() - xOffset, pos.getY() - origin.getY() - yOffset);
		else
			sprite.draw(pos.getX() - origin.getX() - xOffset, pos.getY() - origin.getY() - yOffset);
	}

	@Override
	public void tick() {
		super.tick();
		Point camPos = ((GameState) (ENGINE.getCurrentState())).getGame().getMap().getPlayers()[0].getCamera().getPos();
		float desX = ENGINE.getMouse().getPos().getX() + camPos.getX();
		float desY = ENGINE.getMouse().getPos().getY() + camPos.getY();
		moveTo(new Point(desX, desY));
		move(speed, direction);
	}
}
