
class EntreeInvalide extends RuntimeException {
	public EntreeInvalide() {
		//Invocation du superconstructeur
		//c'est a dire le constructeur de la classe parente
		super("Nombre au format invalide");
	}
}