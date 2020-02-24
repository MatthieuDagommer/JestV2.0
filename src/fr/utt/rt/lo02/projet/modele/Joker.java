package fr.utt.rt.lo02.projet.modele;


/**
 * Cette classe definie une unique instance pour la carte de Joker elle 
 * herite de la classe Carte.
 */
public class Joker extends Carte {
	
	/** Creation d'une instance unique de JOKER avec comme couleur le carreau.
	 * Les parametres sont definis comme tels JOKER comme valeur, CARREAU comme Couleur et Meilleur Jest comme trophee. 
	 * La couleur de Carreau a ete choisie de maniere arbitraire.
	 * Les valeur et le trophees sont correspond directement a la carte JOKER du jeu */
	
	private static Joker INSTANCE = new Joker(Valeur.JOKER, Couleur.CARREAU, Trophee.bestJest);

	/**
	 * Constructeur du joker.
	 * Cette methode est un constructeur qui instancie une unique valeur
	 * qui correspond a une unique carte de JOKER dans le jeu
	 * Cette carte ne peut être changee que via l'attribut privee INSTANCE de cette même classe 
	 * 
	 *
	 * @param valeur de type de l'enumeration Valeur avec comme valeur unique JOKER 
	 * @param couleur de l'enumeration Couleur avec comme valeur choisie arbitrairement CARREAU
	 * @param trophee de l'enumeration Trophee avec comme valeir Meilleur Jest.
	 */
	private Joker(Valeur valeur, Couleur couleur, Trophee trophee) {
		super(valeur, couleur, trophee);
	}

	/**
	 * Cette methode permet d'obtenir l'unique instance de la classe JOKER
	 * L'instance de Joker a ete creee directement dans la classe JOKER via l'attribut INSTANCE.
	 * @return Cette methode retourne l'unique instance de la classe JOKER
	 */
	public static Joker getInstance() {
		return INSTANCE;
	}
}
