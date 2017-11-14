// PointD2.java

package geomD2;

import java.awt.Graphics;
import java.util.ArrayList;

public class PointD2 {
	//PointD2 a ses variables membres
	private Integer x = null;
	private Integer y = null;
	
	//PointD2 a ses constructeurs
	public PointD2() { }
	public PointD2(int a, int b) {
		x = new Integer(a); //conversion implicite de int en Integer
		y = new Integer(b);
	}
	public PointD2(PointD2 pt) {
		x = pt.x;
		y = pt.y;
	}
	public PointD2(Integer[] pt) {
		x = pt[0];
		y = pt[1];
	}
	public Integer getX() {
		return x;
	}
	public Integer getY() {
		return y;
	}
	public void setX(Integer X) {
		this.x = X;
	}
	public void setY(Integer Y) {
		this.y = Y;
	}
	
	
	//PointD2 a ses methodes d'instance
	public boolean defini() {
		return (x != null) && (y != null);
	}
	
	public double dist(PointD2 pt) {
		double dx = (double)x, dy = (double)y, dx2 = (double)pt.x, dy2 = (double)pt.y;
		return Math.sqrt(Math.pow((dx2 - dx), 2) + Math.pow(dy2 - dy, 2));
	}
	
	@Override
	public String toString() {
		return("x : " + x + "y : " + y);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof PointD2) { //test si l'objet est de type PointD2
			PointD2 pt = (PointD2)o;
			if (x == pt.x && y == pt.y) {
				return true;
			}
		}
		return false;
	}
	
}