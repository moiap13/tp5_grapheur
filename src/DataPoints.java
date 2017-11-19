import java.io.*;
import java.util.*;

import geomD2.*;

public class DataPoints extends ArrayList<ArrayList<PointD2>> {
	int max_x, max_y, min_x, min_y, etendue_x, etendue_y;


	public DataPoints (String fichier) {
		LineNumberReader lecteurDeLignes = null;
		try {
			lecteurDeLignes = new LineNumberReader(new FileReader(fichier));
			
			String texteDeLigne = null;
			// lecture ligne après ligne
			while ((texteDeLigne = lecteurDeLignes.readLine()) != null) {
				ArrayList<PointD2> tab_point = new ArrayList<PointD2>();
				
				// Tokenisation de la ligne
				StringTokenizer st = new StringTokenizer(texteDeLigne);
				
				try {
					if(st.countTokens() == 2 || st.countTokens() == 4) {
						int x1 = Integer.parseInt(st.nextToken());
						int y1 = Integer.parseInt(st.nextToken());
						PointD2 pt1 = new PointD2(x1, y1);
						tab_point.add(pt1);
						
					//Si il reste encore 2 tokens (donc c'est une droite)
					if (st.countTokens() == 2) {
						int x2 = Integer.parseInt(st.nextToken());
						int y2 = Integer.parseInt(st.nextToken());
						PointD2 pt2 = new PointD2(x2, y2);
						tab_point.add(pt2);
						//this.droites.add(new DroiteD2(pt1,pt2));
					}
					
					this.add(tab_point);
					}	
					else {
						throw new MauvaisNombre();
					}
				} catch (EntreeInvalide EI) {
					EI.printStackTrace();
				}
				catch (MauvaisNombre MN) {
					MN.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (lecteurDeLignes != null) {
				try {
					lecteurDeLignes.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			min_max();
		}
	}
	
	public void min_max() {
		max_x = this.get(0).get(0).getX();
		max_y = this.get(0).get(0).getY();
		min_x = this.get(0).get(0).getX();
		min_y = this.get(0).get(0).getY();
		
		for(int i = 0; i < this.size(); i++)
			for(int j = 0; j < this.get(i).size(); j++) {
				PointD2 pt = this.get(i).get(j);
				
				if (max_x < pt.getX())
					max_x = pt.getX();

				if (max_y < pt.getY())
					max_y = pt.getY();

				if (min_x > pt.getX())
					min_x = pt.getX();

				if (min_y > pt.getY())
					min_y = pt.getY();
			}

		this.etendue_x = max_x - min_x;
		this.etendue_y = max_y - min_y;
		
	}
	
	
}