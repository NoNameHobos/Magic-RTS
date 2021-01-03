package main.util;

import org.newdawn.slick.geom.Point;

public class Utils {

	public static final float MIN_LERP_DIST = 0.001f;
	
	public static float lerp(float a, float b, float f) {

		float result = ((a * (1.0f - f)) + (b * f));

		if (Math.abs(a - b) < MIN_LERP_DIST)
			return b;
		else
			return result;

	}

	public static float distance(Point pos1, Point pos2) {
		float disX = (float) Math.pow(pos1.getX() - pos2.getX(), 2);
		float disY = (float) Math.pow(pos1.getY() - pos2.getY(), 2);

		return (float) Math.pow(disX + disY, 0.5);
	}

}
