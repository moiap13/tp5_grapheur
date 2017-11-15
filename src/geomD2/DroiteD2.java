//DroiteD2.java
package geomD2;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class DroiteD2 {
	private PointD2 p = null;
	private Double m = null;
	private PointD2 p2;
	private double ordonnee;
	//Les constructeurs :
	//Un point et une pente
	public DroiteD2 (PointD2 p, Double m) {
		this.p = p;
		this.m = m;
		this.ordonnee =  -1*((int)Math.floor(m) * p.getX()) + p.getY();
	}
	// Une pente et une ordonnee a l'origine
	public DroiteD2 (Double m, double ord) {
		this.m = m;
		this.p = new PointD2(0, (int)ord);
		this.ordonnee = (int)ord;
	}
	// deux points
	public DroiteD2(PointD2 p1, PointD2 p2) {
		p = p1;
		this.p2 = p2;
		m = calc_pente(p1, p2);//(double)((p2.getY() - p1.getY()) / (p2.getX() - p1.getX()));// m = (p2.y - p1.y) / (p2.x - p1.x)
		this.ordonnee =  getIntersectionY();
	}

	public static double calc_pente(PointD2 p1, PointD2 p2)
	{
		if(p2.getX() - p1.getX() != 0)
			return ((double)(p2.getY() - p1.getY()) / (double)(p2.getX() - p1.getX()));
		else
			return 0;
	}

	//Les modificateurs :
	//changer le point
	public void setPoint(PointD2 p) {
		this.p = p;
	}
	// changer la pente
	public void setPente(Double m) {
		this.m = m;
	}
	// Changer tout
	public void setPointPente(PointD2 p, Double m) {
		setPoint(p);
		setPente(m);
	}
	
	// Les accesseurs purs :
	// obtenir le point
	public PointD2 getPoint(){
		return p;
	}
	//obtenir la pente
	public Double getPente(){
		return m;
	}
	//obtonnir l'ordonnée a l'origine
	public double getOrdonnee() { return  ordonnee; }
	
	// Les accesseurs qui effectuent un calcul
	// abscisse d'intersection avec axe x
	public Double getIntersectionX() {
		return -(getIntersectionY() / m);
	}
	// ordonnée d'intersection avec axe Y
	public Double getIntersectionY() {
		return p.getY() - (m * p.getX());
	}

	public double get_y_from_x(int x) { return x*m+getIntersectionY(); }
	
	public boolean contient(PointD2 p) {
		return this.m * p.getX() - p.getY() + getIntersectionY() == 0;
	}
	public boolean estPareille(DroiteD2 d) {
		return this.m == d.getPente();
	}
	
	//Les utilitaires :
	//Redefinition
	@Override
	public boolean equals(Object o) {
		if(o instanceof DroiteD2) { //test si l'objet est de type DroiteD2
			DroiteD2 d1 = (DroiteD2)o;
			if (p == d1.p && m == d1.m) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		return(p.toString() + "   /   Pente : " + m.toString());
	} 
	public StringBuilder toString2() {
		return null;	
	}
	
}
