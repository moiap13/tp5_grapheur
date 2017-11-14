import java.awt.Color;
import java.util.ArrayList;

import geomD2.PointD2;

import javax.swing.*;

public class fenetre {
	public static void main (String args[]) {	
		DataPoints listePts = new DataPoints("./src/fichierPoints.txt");
		DessinPoints monGraphe = new DessinPoints(listePts,600,400,10,10);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(monGraphe,"Center");
		f.setTitle("Grapheur");
		f.setSize(400,400);
		f.setContentPane(monGraphe);
	    f.setVisible(true);
	}
}
