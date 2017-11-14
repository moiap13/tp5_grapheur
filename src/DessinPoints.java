import geomD2.DroiteD2;
import geomD2.PointD2;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.*;

public class DessinPoints extends JPanel {
	DataPoints listePts;
    int bord;          // taille des bords
    int enveloppe;     // dimension des rectangles
	
    DessinPoints(DataPoints pts,
   		 int largeur, int hauteur,
   		 int bord, int enveloppe) {
		
		this.listePts = pts;
		this.bord = bord;
		this.enveloppe = enveloppe;
		this.setSize(largeur, hauteur);	
		
	}

	private int[] dessine_point(PointD2 point, Graphics g, double FactEchX, double FactEchY)
	{
		int ret[] = new int[2];
		Integer x = (int)(Math.round((point.getX() -listePts.min_x)*FactEchX-this.enveloppe/2+this.bord));
		Integer y = (int)(Math.round((point.getY() -listePts.min_y)*FactEchY-this.enveloppe/2+this.bord));
		g.drawRect(x , y , this.enveloppe, this.enveloppe);

		ret[0] = x;
		ret[1] = y;

		return ret;
	}
	
	public void paintComponent(Graphics g){
//		int x1 = this.getWidth()/4;
//		int y1 = this.getHeight()/4;
//		g.drawRect(x1, y1, this.getWidth()/2, getHeight()/2);
		//g.drawLine(0, 0, this.getWidth(), getHeight());
		//g.drawLine(10, 10, 65, 65); // xdepart, ydepart, xarrivee, yarrivee

		double FactEchX = (this.getSize().width-this.bord*2) / this.listePts.etendue_x;
		double FactEchY = (this.getSize().height-this.bord*2) / this.listePts.etendue_y;
		
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

				Integer x = (int)(Math.round((pt1.getX() -listePts.min_x)*FactEchX-this.enveloppe/2+this.bord));
				Integer y = (int)(Math.round((pt1.getY() -listePts.min_y)*FactEchY-this.enveloppe/2+this.bord));
				g.drawRect(x , y , this.enveloppe, this.enveloppe);

				test = new PointD2(x, y);

				x = (int)(Math.round((pt2.getX() -listePts.min_x)*FactEchX-this.enveloppe/2+this.bord));
				y = (int)(Math.round((pt2.getY() -listePts.min_y)*FactEchY-this.enveloppe/2+this.bord));
				g.drawRect(x , y , this.enveloppe, this.enveloppe);

				test_2 = new PointD2(x, y);

				//Affiche les 2 points

				System.out.println(DroiteD2.calc_pente(test, test_2));

//				ret_1 = dessine_point(pt1,g, FactEchX, FactEchY);
//				ret_2 = dessine_point(pt2,g, FactEchX, FactEchY);
				//Affiche la droite qui passe par ses 2 points (drawline)

//				PointD2 test = new PointD2(ret_1[0], ret_1[1]);
//				PointD2 test_2 = new PointD2(ret_2[0], ret_2[1]);
				droite = new DroiteD2(test, test_2);

				g.drawLine(0,droite.getOrdonnee(),test_2.getX(),test_2.getY());

				System.out.println(droite.getOrdonnee());
				
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
