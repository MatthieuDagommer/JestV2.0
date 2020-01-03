package fr.utt.rt.lo02.projet.modele;

// TODO: Auto-generated Javadoc
/**
 * Cette classe fait référence à des objet réel de type Carte.
 * Chaque carte est composée d'une valeur (énumération Valeur)
 * d'une couleur  (énumération Couleur) et d'un trophée (énumération trophée)
 * Les objets de type Carte sont crées dès le début de la partie via la classe Deck
 */
public class Carte {
	
	/** Attribut qui défini la valeur d'une carte 
	 * les valeurs valides sont celles de l'énumération Valeur
	 * en cas de valeur null pour la valeur la carte ne pourra pas être utilisé pour la suite */
	private Valeur valeur;
	
	/** Attribut qui défini la couleur d'une carte 
	 * les valeurs valides sont celles de l'énumération Couleur
	 * en cas de valeur null pour la couleur la carte ne pourra pas être utilisé pour la suite */
	private Couleur couleur;
	
	/** Attribut qui défini le trophée d'une carte 
	 * les valeurs valides sont celles de l'énumération Trophée
	 * Une carte peut n'avoir aucun trophée*/
	private Trophee trophee;
	
	/**
	 * Constructeur de la classe carte prenant en compte les 3 paramètres d'une carte 
	 * pour le jeu de Jest (valeur, couleur, trophée).
	 *
	 * @param v définie la Valeur lors de la création d'une carte pour créer une carte avec l'énumération Valeur
	 * @param c définie la Couleur lors de la création d'une carte pour créer une carte avec l'énumération Couleur
	 * @param t définie le Trophée lors de la création d'une carte pour créer une carte avec l'énumération Trophée
	 */
	public Carte(Valeur v, Couleur c, Trophee t) {
		this.valeur = v;
		this.couleur = c;
		this.trophee = t;

	}
	
	/**
	 * Autre constructeur de Carte pour utiliser un entier comme Valeur correspondant à l'indice 
	 * d'une valeur de carte dans l'énumération Valeur
	 * 
	 *
	 * @param v définie la Valeur à l'aide d'un entier correspondant à l'indice de la valeur dans l'énumération Valeur
	 * @param c définie la Couleur d'une carte à l'aide de l'énumération Couleur
	 * @param t définie le Trophée d'une carte à l'aide de l'énumération Trophée
	 */
	public Carte(int v, Couleur c, Trophee t) {
		this.valeur = Valeur.values()[v];
		this.couleur = c;
		this.trophee = t;

	}
	
	/**
	 * Getter permettant de retourner la Valeur d'une carte.
	 *
	 * @return La valeur d'une carte (valeur de l'énumération Valeur).
	 */
	public Valeur getValeur() {
		return valeur;
	}
	
	/**
	 * Setter permettant de donnée à une carte sa valeur.
	 *Cette méthode ne renvoie rien
	 * @param Prends en paramètre d'entrée une Valeur (valeur de l'énumération Valeur)
	 */
	public void setValeur(Valeur valeur) {
		this.valeur = valeur;
	}
	/**
	 * Getter permettant de retourner la Couleur d'une carte.
	 *
	 * @return La couleur d'une carte (valeur de l'énumération Couleur)
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	/**
	 * Setter permettant de donnée à une carte sa Couleur.
	 *Cette méthode ne renvoie rien
	 * @param Prends en paramètre d'entrée une Couleur(valeur de l'énumération Couleur).
	 */
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * Getter permettant de retourner le trophée d'une carte.
	 *
	 * @return Le trophée d'une carte d'une valeur de l'énumération Trophée.
	 */
	public Trophee getTrophee() {
		return trophee;
	}
	
	/**
	 * Setter permettant de donnée à une carte son Trophée.
	 * Cette méthode ne renvoie rien
	 * @param Prends en paramètre d'entrée un Trophée(valeur de l'énumération Trophée).
	 */
	public void setTrophee(Trophee trophee) {
		this.trophee = trophee;
	}

	/**
	 * Méthode d'affichage d'une carte avec ses différents arguments 
	 * sa Valeur, sa Couleur, son trophée.
	 */
	@Override
	public String toString() {
		return "Carte [valeur=" + valeur + ", couleur=" + couleur + ", trophee=" + trophee + "]\n";
	}
	
	
	
}
