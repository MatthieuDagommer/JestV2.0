package fr.utt.rt.lo02.projet.modele;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * The Class Joueur.
 */
public class Joueur extends Observable{

	/** The nom. */
	private String nom;
	
	/** The main. */
	private LinkedList<Carte> main;
	
	/** The offre visible. */
	private Carte offreVisible;
	
	/** The offre cache. */
	private Carte offreCache;
	
	/** The jest. */
	private LinkedList<Carte> jest;
	
	/** The jest avec trophes. */
	private LinkedList<Carte> jestAvecTrophes;
	
	/** The strategie. */
	private StrategieJoueur strategie;
	
	/** The score. */
	private int score;
	
	/** The a jouer. */
	private boolean aJouer;

	/** The nb joueurs. */
	public static int NB_JOUEURS = 0;

	/**
	 * Accept.
	 *
	 * @param p the p
	 */
	public void accept(Partie p) {
		this.score = p.visitJest(this.jest);
	}

	/**
	 * Instantiates a new joueur.
	 *
	 * @param nom the nom
	 */
	public Joueur(String nom) {
		this.nom = nom;
		this.strategie = new StratPhysique();
		this.jest = new LinkedList<Carte>();
		this.jestAvecTrophes = new LinkedList<Carte>();
		this.main = new LinkedList<Carte>();
		Joueur.NB_JOUEURS++;
	}

	/**
	 * Instantiates a new joueur.
	 *
	 * @param nom the nom
	 * @param strategie the strategie
	 */
	public Joueur(String nom, StrategieJoueur strategie) {
		this.nom = nom;
		this.strategie = strategie;
		this.jest = new LinkedList<Carte>();
		this.jestAvecTrophes = new LinkedList<Carte>();
		this.main = new LinkedList<Carte>();
		Joueur.NB_JOUEURS++;
	}
	
	/**
	 * Plus valeur.
	 *
	 * @param valeur the valeur
	 * @return the int
	 */
	public int plusValeur(Valeur valeur) {
		int nbValeur = 0;
		Iterator<Carte> it = jest.iterator();
		while(it.hasNext()) {
			Carte c = it.next();
			if(c.getValeur() == valeur) {
				nbValeur++;
			}
		}
		return nbValeur;
	}
	
	/**
	 * Best couleur.
	 *
	 * @param valeur the valeur
	 * @return the int
	 */
	public int bestCouleur(Valeur valeur) {
		int valeurCouleur = 0;
		Iterator<Carte> it = jest.iterator();
		while(it.hasNext()) {
			Carte c = it.next();
			if(c.getValeur() == valeur && c.getCouleur().ordinal() > valeurCouleur) {
				valeurCouleur = c.getCouleur().ordinal();
			}
		}
		return valeurCouleur;
	}
	
	/**
	 * Highest valeur in couleur.
	 *
	 * @param couleur the couleur
	 * @return the int
	 */
	public int highestValeurInCouleur(Couleur couleur) {
		int valeur = 0, nbCarte = 0;
		boolean as = false;
		Iterator<Carte> it = jest.iterator();
		while(it.hasNext()) {
			Carte c = it.next();
			if(c.getCouleur() == couleur) {
				nbCarte++;
				if(c.getValeur().ordinal() > valeur) {
					valeur = c.getValeur().ordinal();
				}
				if(c.getValeur() == Valeur.AS) {
					as = true;
				}
			}
		}
		if (as && nbCarte ==1) {
			valeur = 5;
		}
		return valeur;
	}
	
	/**
	 * Lowest valeur in couleur.
	 *
	 * @param couleur the couleur
	 * @return the int
	 */
	public int lowestValeurInCouleur(Couleur couleur) {
		int valeur = 6, nbCarte = 0;
		boolean as = false;
		Iterator<Carte> it = jest.iterator();
		while(it.hasNext()) {
			Carte c = it.next();
			if(c.getCouleur() == couleur) {
				nbCarte++;
				if(c.getValeur().ordinal() < valeur && c.getValeur() != Valeur.JOKER) {
					valeur = c.getValeur().ordinal();
				}
				if(c.getValeur() == Valeur.AS) {
					as = true;
				}
			}
		}
		if (as && nbCarte >1) {
			return 1;
		}else {
			return valeur;
		}
	}

	/**
	 * Gets the jest avec trophes.
	 *
	 * @return the jest avec trophes
	 */
	public LinkedList<Carte> getJestAvecTrophes() {
		return jestAvecTrophes;
	}

	/**
	 * Sets the jest avec trophes.
	 *
	 * @param jestAvecTrophes the new jest avec trophes
	 */
	public void setJestAvecTrophes(LinkedList<Carte> jestAvecTrophes) {
		this.jestAvecTrophes = jestAvecTrophes;
	}

	/**
	 * Jouer.
	 *
	 * @return the joueur
	 */
	public Joueur jouer() {
		this.aJouer = true;
		return strategie.choisirCarte(this);
	}
	
	/**
	 * Faire offre.
	 */
	public void faireOffre() {
		strategie.faireOffre(this);
	}

	/**
	 * Adds the jest.
	 *
	 * @param c the c
	 */
	public void addJest(Carte c) {
		this.jest.add(c);
	}

	/**
	 * Adds the main.
	 *
	 * @param c the c
	 */
	public void addMain(Carte c) {
		this.main.add(c);
	}

	/**
	 * Adds the jest avec trophes.
	 *
	 * @param c the c
	 */
	public void addJestAvecTrophes(Carte c) {
		this.jestAvecTrophes.add(c);
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Sets the nom.
	 *
	 * @param nom the new nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Gets the main.
	 *
	 * @return the main
	 */
	public LinkedList<Carte> getMain() {
		return main;
	}

	/**
	 * Sets the main.
	 *
	 * @param main the new main
	 */
	public void setMain(LinkedList<Carte> main) {
		this.main = main;
	}

	/**
	 * Gets the offre visible.
	 *
	 * @return the offre visible
	 */
	public Carte getOffreVisible() {
		return offreVisible;
	}

	/**
	 * Sets the offre visible.
	 *
	 * @param offreVisible the new offre visible
	 */
	public void setOffreVisible(Carte offreVisible) {
		this.offreVisible = offreVisible;
	}

	/**
	 * Gets the offre cache.
	 *
	 * @return the offre cache
	 */
	public Carte getOffreCache() {
		return offreCache;
	}

	/**
	 * Sets the offre cache.
	 *
	 * @param offreCache the new offre cache
	 */
	public void setOffreCache(Carte offreCache) {
		this.offreCache = offreCache;
	}

	/**
	 * Gets the jest.
	 *
	 * @return the jest
	 */
	public LinkedList<Carte> getJest() {
		return jest;
	}

	/**
	 * Sets the jest.
	 *
	 * @param jest the new jest
	 */
	public void setJest(LinkedList<Carte> jest) {
		this.jest = jest;
	}

	/**
	 * Gets the strategie.
	 *
	 * @return the strategie
	 */
	public StrategieJoueur getStrategie() {
		return strategie;
	}

	/**
	 * Sets the strategie.
	 *
	 * @param strategie the new strategie
	 */
	public void setStrategie(StrategieJoueur strategie) {
		this.strategie = strategie;
	}

	/**
	 * Checks if is a jouer.
	 *
	 * @return true, if is a jouer
	 */
	public boolean isaJouer() {
		return aJouer;
	}

	/**
	 * Sets the a jouer.
	 *
	 * @param aJouer the new a jouer
	 */
	public void setaJouer(boolean aJouer) {
		this.aJouer = aJouer;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", main=" + main + ", offreVisible=" + offreVisible + ", offreCache=" + offreCache
				+ ", jest=" + jest + ", jestAvecTrophes=" + jestAvecTrophes + ", strategie=" + strategie + ", score="
				+ score + ", aJouer=" + aJouer + "]\n";
	}


}
