
public class MauvaisNombre extends RuntimeException {
	public MauvaisNombre()  {
		//Invocation du superconstructeur
		//c'est a dire le constructeur de la classe parente
		super("Le fichier contient un nombre de coordonnées invalide");
	}
}
