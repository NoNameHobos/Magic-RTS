package main.game.entities;

import java.util.ArrayList;

import org.newdawn.slick.geom.Point;

import main.game.Renderable;
import main.game.entities.selectables.unit.UnitStat;
import main.game.map.Map;
import main.game.player.Player;

public abstract class Controllable extends Renderable {

	public static final ArrayList<Controllable> OBJECTS = new ArrayList<Controllable>();
	
	protected Player player;
	protected Map map;
	
	protected float[] stats;
	
	public Controllable(Point pos) {
		super(pos);
		stats = new float[UnitStat.values().length];
		OBJECTS.add(this);
	}

	/**
	 * Get a stat from the unit
	 * @param stat - UnitStat (One of the UnitStat constants)
	 */
	public float getStat(UnitStat stat) {
		int val = stat.val();
		return stats[val];
	}

	/**
	 * Add to a stat from the unit
	 * @param stat - UnitStat (One of the UnitStat constants)
	 * @param num - float value to assign to the stat
	 */
	public void addToStat(UnitStat stat, float num) {
		int val = stat.val();
		stats[val] += num;
	}
	
	/**
	 * Set a stat from the unit
	 * @param stat - UnitStat (One of the UnitStat constants)
	 * @param num - float value to assign to the stat
	 */
	public void setStat(UnitStat stat, float num) {
		int val = stat.val();
		stats[val] = num;
	}
	
	
	// Getters and Setters
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player p) {
		player = p;
	}
	
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	

}
