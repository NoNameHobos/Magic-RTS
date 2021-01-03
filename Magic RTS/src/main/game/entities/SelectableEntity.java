package main.game.entities;

import static main.GameConstants.STAT_ACC;
import static main.GameConstants.STAT_ATTACK;
import static main.GameConstants.STAT_ATTACK_RANGE;
import static main.GameConstants.STAT_COUNT;
import static main.GameConstants.STAT_HEALTH;
import static main.GameConstants.STAT_HEALTH_MAX;
import static main.GameConstants.STAT_MAG_DEF;
import static main.GameConstants.STAT_PHYS_DEF;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.engine.Engine;
import main.game.Game;
import main.game.player.Camera;
import main.game.player.Player;

public abstract class SelectableEntity extends Entity {

	public static final ArrayList<SelectableEntity> SE = new ArrayList<SelectableEntity>();

	protected int width, height;
	protected Boolean selected;
	
	// Stats
	protected float[] stats = new float[STAT_COUNT];

	public SelectableEntity(Player player, Point pos, Image sprite) {
		super(player, player.getMap(), pos, sprite);
		selectable = true;
		width = sprite.getWidth();
		height = sprite.getHeight();
		selected = false;
		
		// Init Stats
		stats[STAT_HEALTH] = 1f;
		stats[STAT_HEALTH_MAX] = 1f;
		stats[STAT_ACC] = 0.0001f;
		stats[STAT_PHYS_DEF] = 0f;
		stats[STAT_MAG_DEF] = 0f;
		
		
		// Attack stuff
		stats[STAT_ATTACK] = 1;
		// Attack range in tiles
		stats[STAT_ATTACK_RANGE] = 1;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void tick() {
		super.tick();
	}
	
	public boolean mouseOver() {
		Camera c = player.getCamera();
		if (c != null) {
			Point mousePos = Game.UIToObject(Engine.getMouse().getScreenPos(), c);

			boolean safe = boundingbox.contains(mousePos.getX(), mousePos.getY());
			return (safe);
		} else {
			return false;
		}
	}

	// Getters and Setters
	
	public float[] getStats() {
		return stats;
	}
	
	public float getStat(int stat) {
		return stats[stat];
	}
	
	public void setStat(int stat, float value, boolean rel) {
		if(!rel)
			this.stats[stat] = value;
		else
			this.stats[stat] += value;
	}
	
	public void setStat(int stat, float value) {
			this.stats[stat] = value;
	}
}
