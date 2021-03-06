package utility;

import java.lang.Math;

public class Geometry {
	public static int getQuadrant(float x1, float y1, float x2, float y2) {
		int quadrant = 1;
		
		if(x1 > x2 && y1 < y2)
			quadrant = 1;
		else if(x1 < x2 && y1 < y2)
			quadrant = 4;
		else if(x1 < x2 && y1 > y2)
			quadrant = 3;
		else
			quadrant = 2;
		
		return quadrant;
	}
	
	public static float getAngle(float x1, float y1, float x2, float y2) {
		int quadrant = getQuadrant(x1, y1, x2, y2);
		float dx = Math.abs(x1 - x2);
		float dy = Math.abs(y1 - y2);
		float angle = 0;
		
		if(quadrant == 1)
			angle = (float) Math.toDegrees(Math.atan(dx / dy));
		else if(quadrant == 2)
			angle = (float) Math.toDegrees(Math.atan(dy / dx)) + 90;
		else if(quadrant == 3)
			angle = (float) Math.toDegrees(Math.atan(dx / dy)) + 180;
		else
			angle = (float) Math.toDegrees(Math.atan(dy / dx)) + 270;
		
		return (angle + 180) % 360;
	}
	
	public static float getXComponent(float m, float a) {
		float x = (float) -(m * Math.sin(Math.toRadians(360 - a)));
		return x;
	}
	
	public static float getYComponent(float m, float a) {
		float y = (float) -(m * Math.cos(Math.toRadians(360 - a)));
		return y;
	}
}
