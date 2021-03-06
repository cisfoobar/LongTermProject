package utility;

import java.lang.Math;
import utility.Geometry;

public class Vector {
	private float x, y;
	
	public Vector() {
		x = 0;
		y = 0;
	}
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(float m, float a, int x) {
		this.x = Geometry.getXComponent(m, a);
		this.y = Geometry.getYComponent(m, a);
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void changeX(float dx) {
		x += dx;
	}
	
	public void changeY(float dy) {
		y += dy;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getMagnitude() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public Vector add(Vector v) {
		return new Vector(getX() + v.getX(), getY() + v.getY());
	}
	
	public String toString() {
		return x + ", " + y; 
	}
}
