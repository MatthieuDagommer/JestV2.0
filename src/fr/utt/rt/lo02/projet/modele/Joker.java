package fr.utt.rt.lo02.projet.modele;

// TODO: Auto-generated Javadoc
/**
 * The Class Joker.
 */
public class Joker extends Carte {
	
	/** The instance. */
	private static Joker INSTANCE = new Joker(Valeur.JOKER, Couleur.CARREAU, Trophee.bestJest);

	/**
	 * Instantiates a new joker.
	 *
	 * @param valeur the valeur
	 * @param couleur the couleur
	 * @param trophee the trophee
	 */
	private Joker(Valeur valeur, Couleur couleur, Trophee trophee) {
		super(valeur, couleur, trophee);
	}

	/**
	 * Gets the single instance of Joker.
	 *
	 * @return single instance of Joker
	 */
	public static Joker getInstance() {
		return INSTANCE;
	}
}
