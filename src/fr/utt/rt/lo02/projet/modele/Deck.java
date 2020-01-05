package fr.utt.rt.lo02.projet.modele;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * Classe permettant la création d'une pioche pour le jeu de JEST
 * Cette classe est composée d'une liste chainée qui correspond aux cartes de la pioche 
 * et d'un entier (extension) qui correspond au choix de l'extension fait par l'utilisateur
 */
public class Deck {

	
	/** Attribut qui définie un tas de carte comme collection chainée. Cet attribut correspond à la pioche dans le jeu de JEST
	 * Cet attribut peut contenir de 0 à 21 cartes dans le cas de l'extension de cartes.
	 * En cas de valeur null, soit la pioche n'a pas encore été crée, soit la partie est terminée */
	private LinkedList<Carte> tasDeCarte;
	
	/** Attribut correspondant au choix de l'extension de carte.
	 * Bornes Valides : 0 pour aucune extension de carte ou 1 pour une extension de carte
	 * Toute autre valeur ne créera pas la pioche voulue composée de 17 cartes sans extension (extension = 0) et de 21 cartes avec extension (extension à 1)
	 *  */
	private int extension;
	
	/**
	 * Constructeur d'un deck qui correspond à la pioche du JEST
	 * Cette méthode permet de crée un deck à 17 cartes (pas d'extension)
	 * ou un deck à 21 cartes avec extension
	 * @param extension à 0 pour aucune extension ou à 1 pour une extension de carte.
	 * Pour construire une pioche, on parcours toutes les couleurs de l'énumération, puis toutes les valeurs de l'énumération
	 * et on ajoute le trophée correspondant. 
	 */
	public Deck(int extension) {
		this.extension = extension;
		tasDeCarte = new LinkedList<Carte>();
		Trophee[] t = Trophee.values();
		int it = 0;

		for (Couleur c : Couleur.values()) { // pour toutes les couleurs
			if (extension == 1) {
				for (Valeur v : Valeur.values()) { // pour toutes les valeurs
					if (v != Valeur.JOKER) {
						if (c != Couleur.COEUR && v != Valeur.SIX) { // les coeurs ont le meme troph�e
							it++;
						}
						Carte carte = new Carte(v, c, t[it]); // création de la carte avec le trophée associé
						tasDeCarte.add(carte);
						// System.out.println(tasDeCarte);
					}
				}
			} else if (extension == 0) {
				for (int v = 0; v < 5; v++) { // pour toutes les valeurs
					if (v != 0) {
						if (c != Couleur.COEUR) { // les coeurs ont le meme troph�e
							it++;
						}
						Carte carte = new Carte(v, c, t[it]); // création de la carte avec le trophée associé
						tasDeCarte.add(carte);
					}
				}
			}
		}
		tasDeCarte.add(Joker.getInstance());
	}
	
	/**
	 * Methode permettant de savoir si la pioche est vide	 *
	 * @return vraie si la pioche est vide et faux sinon
	 */
	public boolean estVide() {
		return tasDeCarte.isEmpty();
	}

	/**
	 * Méthode permettant de piocher une carte en haut du paquet 
	 * grâce à la méthode pop de la collection liste chainée
	 *
	 * @return la carte du haut du paquet
	 */
	public Carte piocherCarte() {
		return tasDeCarte.pop();
	}
	
	/**
	 * Méthode qui permet de mélanger la pioche.
	 * Cette méthode ne renvoi rien et ne prends rien en paramètre.
	 */
	public void melanger() {
		Collections.shuffle(tasDeCarte);
	}
	
	/**
	 * Getter qui permet d'obtenir l'extension d'une pioche
	 *
	 * @return un entier à 0 pour aucune extension et un entier à 1 pour une extension de carte
	 * Pour toute autre valeur, le deck n'est pas bien défini pour notre jeu de JEST
	 */
	public int getExtension() {
		return extension;
	}

	/**
	 * Setter qui permet de définir la valeur de l'extension de jeu
	 * Cette valeur est un entier à 0 si il n'y a pas d'extension et un entier à 1 si il y a une extension
	 * Toute autre valeur n'est pas admise dans notre jeu de JEST
	 * @param extension Entrer 0 pour aucune extension et 1 pour une extension
	 */
	public void setExtension(int extension) {
		this.extension = extension;
	}

	/**
	 * Getter qui permet d'obtenir la pioche sous forme de liste chainée
	 *
	 * @return Cette méthode retourne la picohe sous forme de liste chainée qu'elle soient vide ou non
	 */
	public LinkedList<Carte> getTasDeCarte() {
		return tasDeCarte;
	}

	/**
	 * Setter qui permet de définir un tas de carte sous forme de liste chainée
	 *
	 * @param tasDeCarte Une liste chainée de carte qui correspond à la liste des cartes.
	 */
	public void setTasDeCarte(LinkedList<Carte> tasDeCarte) {
		this.tasDeCarte = tasDeCarte;
	}
	
	/**
	 * méthode d'affichage de la pioche sous forme de liste chainée de carte
	 *
	 * @return Les cartes contenues dans la pioche
	 */
	public String toString() {
		return tasDeCarte.toString();
	}
	
	/**
	 * Méthode permettant d'ajouter une carte à l'attribut tas de carte qui correspond à la pioche
	 *
	 * @param c, Carte (objet de la classe carte) que l'on souhaite ajouter à la liste chainée.
	 */
	public void addCarte(Carte c) {
		this.tasDeCarte.add(c);
	}
	

	
}
