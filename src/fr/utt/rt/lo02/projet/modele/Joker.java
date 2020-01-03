package fr.utt.rt.lo02.projet.modele;

// TODO: Auto-generated Javadoc
/**
 * Cette classe définie une unique instance pour la carte de Joker elle 
 * hérite de la classe Carte.
 */
public class Joker extends Carte {
	
	/** Création d'une instance unique de JOKER avec comme couleur le carreau.
	 * Les paramètres sont définis comme tels JOKER comme valeur, CARREAU comme Couleur et Meilleur Jest comme trophée. 
	 * La couleur de Carreau à été choisie de manière arbitraire.
	 * Les valeur et le trophées sont correspond directement à la carte JOKER du jeu */
	
	private static Joker INSTANCE = new Joker(Valeur.JOKER, Couleur.CARREAU, Trophee.bestJest);

	/**
	 * Constructeur du joker.
	 * Cette méthode est un constructeur qui instancie une unique valeur
	 * qui correspond à une unique carte de JOKER dans le jeu
	 * Cette carte ne peut être changée que via l'attribut privée INSTANCE de cette même classe 
	 * 
	 *
	 * @param valeur de type de l'énumération Valeur avec comme valeur unique JOKER 
	 * @param couleur de l'énumération Couleur avec comme valeur choisie arbitrairement CARREAU
	 * @param trophee de l'énumération Trophée avec comme valeir Meilleur Jest.
	 */
	private Joker(Valeur valeur, Couleur couleur, Trophee trophee) {
		super(valeur, couleur, trophee);
	}

	/**
	 * Cette méthode permet d'obtenir l'unique instance de la classe JOKER
	 * L'instance de Joker à été créee directement dans la classe JOKER via l'attribut INSTANCE.
	 * @return Cette méthode retourne l'unique instance de la classe JOKER
	 */
	public static Joker getInstance() {
		return INSTANCE;
	}
}
