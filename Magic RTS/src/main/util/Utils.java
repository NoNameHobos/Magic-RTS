package main.util;

public class Utils {

	public static float lerp(float a, float b, float f) {
		
		float result = ((a * (1.0f - f)) + (b * f));

		if (Math.abs(a-b) < 0.001f) return b; else return result;

	}

}
