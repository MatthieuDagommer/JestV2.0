package fr.utt.rt.lo02.projet.modele;

import java.util.Iterator;
import java.util.LinkedList;

public class Joueur {

	private String nom;
	private LinkedList<Carte> main;
	private Carte offreVisible;
	private Carte offreCache;
	private LinkedList<Carte> jest;
	private LinkedList<Carte> jestAvecTrophes;
	private StrategieJoueur strategie;
	private int score;
	private boolean aJouer;

	public static int NB_JOUEURS = 0;

	public void accept(Partie p) {
		this.score = p.visitJest(jest);
	}

	public Joueur(String nom) {
		this.nom = nom;
		this.strategie = new StratPhysique();
		this.jest = new LinkedList<Carte>();
		this.jestAvecTrophes = new LinkedList<Carte>();
		this.main = new LinkedList<Carte>();
		Joueur.NB_JOUEURS++;
	}

	public Joueur(String nom, StrategieJoueur strategie) {
		this.nom = nom;
		this.strategie = strategie;
		this.jest = new LinkedList<Carte>();
		this.jestAvecTrophes = new LinkedList<Carte>();
		this.main = new LinkedList<Carte>();
		Joueur.NB_JOUEURS++;
	}
	
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

	public LinkedList<Carte> getJestAvecTrophes() {
		return jestAvecTrophes;
	}

	public void setJestAvecTrophes(LinkedList<Carte> jestAvecTrophes) {
		this.jestAvecTrophes = jestAvecTrophes;
	}

	public Joueur jouer() {
		this.aJouer = true;
		return strategie.choisirCarte();
	}

	public void addJest(Carte c) {
		this.jest.add(c);
	}

	public void addMain(Carte c) {
		this.main.add(c);
	}

	public void addJestAvecTrophes(Carte c) {
		this.jestAvecTrophes.add(c);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public LinkedList<Carte> getMain() {
		return main;
	}

	public void setMain(LinkedList<Carte> main) {
		this.main = main;
	}

	public Carte getOffreVisible() {
		return offreVisible;
	}

	public void setOffreVisible(Carte offreVisible) {
		this.offreVisible = offreVisible;
	}

	public Carte getOffreCache() {
		return offreCache;
	}

	public void setOffreCache(Carte offreCache) {
		this.offreCache = offreCache;
	}

	public LinkedList<Carte> getJest() {
		return jest;
	}

	public void setJest(LinkedList<Carte> jest) {
		this.jest = jest;
	}

	public StrategieJoueur getStrategie() {
		return strategie;
	}

	public void setStrategie(StrategieJoueur strategie) {
		this.strategie = strategie;
	}

	public boolean isaJouer() {
		return aJouer;
	}

	public void setaJouer(boolean aJouer) {
		this.aJouer = aJouer;
	}


}
