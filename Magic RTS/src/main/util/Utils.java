package main.util;

public class Utils {

	public static float lerp(float a, float b, float f) {
		
		float result = ((a * (1.0f - f)) + (b * f)) * 1000;
		
		result = Math.round(result);
		
		
		return result/1000;

	}

}
