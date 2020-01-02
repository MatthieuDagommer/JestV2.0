package fr.utt.rt.lo02.projet.modele;

// TODO: Auto-generated Javadoc
/**
 * The Class Carte.
 */
public class Carte {
	
	/** The valeur. */
	private Valeur valeur;
	
	/** The couleur. */
	private Couleur couleur;
	
	/** The trophee. */
	private Trophee trophee;
	
	/**
	 * Instantiates a new carte.
	 *
	 * @param v the v
	 * @param c the c
	 * @param t the t
	 */
	public Carte(Valeur v, Couleur c, Trophee t) {
		this.valeur = v;
		this.couleur = c;
		this.trophee = t;

	}
	
	/**
	 * Instantiates a new carte.
	 *
	 * @param v the v
	 * @param c the c
	 * @param t the t
	 */
	public Carte(int v, Couleur c, Trophee t) {
		this.valeur = Valeur.values()[v];
		this.couleur = c;
		this.trophee = t;

	}
	
	/**
	 * Gets the valeur.
	 *
	 * @return the valeur
	 */
	public Valeur getValeur() {
		return valeur;
	}
	
	/**
	 * Sets the valeur.
	 *
	 * @param valeur the new valeur
	 */
	public void setValeur(Valeur valeur) {
		this.valeur = valeur;
	}
	
	/**
	 * Gets the couleur.
	 *
	 * @return the couleur
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	/**
	 * Sets the couleur.
	 *
	 * @param couleur the new couleur
	 */
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * Gets the trophee.
	 *
	 * @return the trophee
	 */
	public Trophee getTrophee() {
		return trophee;
	}
	
	/**
	 * Sets the trophee.
	 *
	 * @param trophee the new trophee
	 */
	public void setTrophee(Trophee trophee) {
		this.trophee = trophee;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Carte [valeur=" + valeur + ", couleur=" + couleur + ", trophee=" + trophee + "]\n";
	}
	
	
	
}
