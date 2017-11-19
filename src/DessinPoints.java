import geomD2.DroiteD2;
import geomD2.PointD2;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class DessinPoints extends JPanel {
	DataPoints listePts;
    int bord;          // taille des bords
    int enveloppe;     // dimension des rectangles
	int initiale_size[];
	boolean enter_once = true;
	
    DessinPoints(DataPoints pts, int largeur, int hauteur, int bord, int enveloppe) {
		this.listePts = pts;
		this.bord = bord;
		this.enveloppe = enveloppe;
		this.setSize(largeur, hauteur);
		initiale_size = new int[2];
	}

	private double[] dessine_point(PointD2 point, Graphics g, double FactEchX, double FactEchY)
	{
		double ret[] = new double[2];
		double x = point.getX() / FactEchX;
		double y = point.getY() / FactEchY;
		((Graphics2D)g).draw(new Rectangle2D.Double(x-(double)this.enveloppe/2 ,y-(double)this.enveloppe/2 , this.enveloppe, this.enveloppe));

		ret[0] = x;
		ret[1] = y;

		return ret;
	}
	
	public void paintComponent(Graphics g)
	{
		if(enter_once)
		{
			enter_once = false;

			initiale_size[0] = this.getWidth();
			initiale_size[1] = this.getHeight();
		}

		double FactEchX = (double)initiale_size[0] / (double)this.getSize().width;
		double FactEchY = (double)initiale_size[1] / (double)this.getSize().height;

		super.paintComponent(g);
		g.setXORMode(Color.red);

		for(int i = 0; i < this.listePts.size(); i++) {
			if(this.listePts.get(i).size() > 1) 
			{ // Si c'est une droite
				double ret_1[] = new double[2];
				double ret_2[] = new double[2];

				ret_1 = dessine_point(this.listePts.get(i).get(0), g, FactEchX, FactEchY);
				ret_2 = dessine_point(this.listePts.get(i).get(1), g, FactEchX, FactEchY);

				DroiteD2 droite = new DroiteD2(new PointD2((int) ret_1[0], (int) ret_1[1]), new PointD2((int) ret_2[0], (int) ret_2[1]));

				((Graphics2D)g).draw(new Line2D.Double(0 , droite.getOrdonnee(), this.getWidth(), droite.get_y_from_x(this.getWidth())));
			}
			else //si c'est un point
				dessine_point(this.listePts.get(i).get(0), g, FactEchX, FactEchY);
		}
	}  
}
