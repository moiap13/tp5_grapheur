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
	
    DessinPoints(DataPoints pts,
		 int largeur, int hauteur,
		 int bord, int enveloppe) {
		
		this.listePts = pts;
		this.bord = bord;
		this.enveloppe = enveloppe;
		this.setSize(largeur, hauteur);
		initiale_size = new int[2];
	}

	private void dessine_point(PointD2 point, Graphics g, double FactEchX, double FactEchY)
	{
		int ret[] = new int[2];
		double x = point.getX() / FactEchX;
		double y = point.getY() / FactEchY;
		g.drawRect((int)x , (int)y , this.enveloppe, this.enveloppe);

//		ret[0] = x;
//		ret[1] = y;

//		return ret;
	}
	
	public void paintComponent(Graphics g){
//		int x1 = this.getWidth()/4;
//		int y1 = this.getHeight()/4;
//		g.drawRect(x1, y1, this.getWidth()/2, getHeight()/2);
		//g.drawLine(0, 0, this.getWidth(), getHeight());
		//g.drawLine(10, 10, 65, 65); // xdepart, ydepart, xarrivee, yarrivee


		if(enter_once)
		{
			enter_once = false;

			initiale_size[0] = this.getWidth();
			initiale_size[1] = this.getHeight();
		}
		double FactEchX = (double)initiale_size[0] / (double)this.getSize().width;
		double FactEchY = (double)initiale_size[1] / (double)this.getSize().height;
//		double FactEchX = (this.getSize().width-this.bord*2) / this.listePts.etendue_x;
//		double FactEchY = (this.getSize().height-this.bord*2) / this.listePts.etendue_y;
		
		super.paintComponent(g);
		g.setXORMode(Color.red);

		// lire les points de {listePts} et dessiner les rectangles et les droites
		// correspondants

		for(int i = 0; i < this.listePts.size(); i++) {
			if(this.listePts.get(i).size() > 1) { // Si c'est une droite
				PointD2 pt1 = this.listePts.get(i).get(0);
				PointD2 pt2 = this.listePts.get(i).get(1);
				DroiteD2 droite = new DroiteD2(pt1, pt2);

				PointD2 test;
				PointD2 test_2;

				int ret_1[] = new int[2];
				int ret_2[] = new int[2];

				double x = (double)pt1.getX() / FactEchX;
				double y = (double)pt1.getY() / FactEchY;
				//g.drawRect((int)x , (int)y , this.enveloppe, this.enveloppe);
				((Graphics2D)g).draw(new Rectangle2D.Double(x-(double)this.enveloppe/2 ,y-(double)this.enveloppe/2 , this.enveloppe, this.enveloppe));


				System.out.println("x_1 :" + x);
				System.out.println("y_1 :" + y);
				test = new PointD2((int)x, (int)y);

				System.out.println("x_1-2 :" + test.getX());
				System.out.println("y_1-2 :" + test.getY());

				x = (double)pt2.getX() / FactEchX;
				y = (double)pt2.getY() / FactEchY;
				//g.drawRect((int)x-this.enveloppe/2 ,(int)y-this.enveloppe/2 , this.enveloppe, this.enveloppe);

				((Graphics2D)g).draw(new Rectangle2D.Double(x-(double)this.enveloppe/2 ,y-(double)this.enveloppe/2 , this.enveloppe, this.enveloppe));
				test_2 = new PointD2((int)x,(int) y);


				droite.getPoint();
				//Affiche les 2 points
				System.out.println("x_2 :" + x);
				System.out.println("y_2 :" + y);

				System.out.println(DroiteD2.calc_pente(test, test_2));

				System.out.println("x_2-2 :" + test.getX());
				System.out.println("y_2-2 :" + test.getY());

//				ret_1 = dessine_point(pt1,g, FactEchX, FactEchY);
//				ret_2 = dessine_point(pt2,g, FactEchX, FactEchY);
				//Affiche la droite qui passe par ses 2 points (drawline)

//				PointD2 test = new PointD2(ret_1[0], ret_1[1]);
//				PointD2 test_2 = new PointD2(ret_2[0], ret_2[1]);
				droite = new DroiteD2(test, test_2);

				//((Graphics2D) g).draw();
				//g.drawLine(0,(int)droite.getOrdonnee(),this.getWidth(),(int)droite.get_y_from_x(this.getWidth()));
				((Graphics2D)g).draw(new Line2D.Double(0 , droite.getOrdonnee(), this.getWidth(), droite.get_y_from_x(this.getWidth())));

				System.out.println(droite.getPente());
				
//				int x1 = pt1.getX()
//				g.drawRect(x, y, this.enveloppe, this.enveloppe);
//				g.drawRect(x, y, this.enveloppe, this.enveloppe); y = ax+b			
				

				
				
			}
			else { //Si c'est un point
				PointD2 pt = this.listePts.get(i).get(0);
				dessine_point(pt, g, FactEchX, FactEchY);
			}
		}
	}  
}
