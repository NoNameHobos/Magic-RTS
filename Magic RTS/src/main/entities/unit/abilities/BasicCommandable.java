package main.entities.unit.abilities;

import org.newdawn.slick.geom.Point;

import main.GameConstants;
import main.entities.SelectableEntity;
import main.entities.Unit;
import main.entities.ai.pathfinding.Path;

public interface BasicCommandable {

	/*
	 * An interface for a basic list of commands
	 */
	
	// Patrol a set of points
	public default void patrol(Unit unit, Point[] points) {
		// Find a path to the next point
		
		if(unit.getPath() == null) {
			int len = points.length;
			if(unit.getCurPoint() + 1 < len)
				unit.setCurPoint(unit.getCurPoint() + 1);
			else {
				unit.setCurPoint(0);
			}
			Path p = unit.findPath(points[unit.getCurPoint()]);
			unit.setPath(p);
		} else {
			unit.moveAlongPath(unit.getPath());
		}
	}
	
	public default void attack(Unit unit, SelectableEntity target) {
		if(unit.getDistanceTo(target.getPos()) > unit.getStat(GameConstants.STAT_ATTACK_RANGE) * GameConstants.TW_RENDER) {
			unit.setState(GameConstants.STATE_MOVING);
		} else {
			float attack_dmg = unit.getStat(GameConstants.STAT_ATTACK);
			target.setStat(GameConstants.STAT_HEALTH, -attack_dmg, true);
			// TODO: Make an attack anim
		}
	}
}
