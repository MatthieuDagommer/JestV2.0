package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import player.Joueur;

public class Partie {

	private static Partie instance = null;
	private ArrayList<Joueur> joueurs;
	private LinkedList<Carte> trophes;
	private Deck jeuDeCartes;
	private Regle regle;

	public Regle getRegle() {
		return regle;
	}

	public void setRegle(Regle regle) {
		this.regle = regle;
	}

	private Partie() {
		joueurs = new ArrayList<Joueur>();
		trophes = new LinkedList<Carte>();
	}

	public static Partie getInstance() {
		if (instance == null) {
			instance = new Partie();
		}
		return instance;
	}

	public LinkedList<Carte> getTrophes() {
		return trophes;
	}

	public void setTrophes(LinkedList<Carte> trophes) {
		this.trophes = trophes;
	}

	public void addJoueur(Joueur j) {
		if (this.joueurs.contains(j) == false) {
			this.joueurs.add(j);
			
		}
	}

	public void removeJoueur(Joueur j) {
		if (this.joueurs.contains(j) == true) {
			this.joueurs.remove(j);
		}
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public Deck buildJeuDeCarte(int extension) {
		Deck jeu = new Deck(extension);
		this.jeuDeCartes = jeu;
		return jeu;
	}

	public void lancerPartie() {
		Joueur suivant ;
		choixTrophee();
		do {
			jeuDeCartes.melanger();
			razAJoue();
			distribuerJeu();
			offreJoueur();
			suivant = meilleureOffre();
			for (int i = 0; i < Joueur.NB_JOUEURS; i++) {
				suivant = suivant.jouer();
				if (suivant.isaJouer()) {
					suivant = meilleureOffre();
				}
			}
			if (!jeuDeCartes.estVide()) {
				rammaserCartesRestante();
			}
		} while (!jeuDeCartes.estVide());
		rammaserCartesRestanteJest();
		updateScore();
		distribuerTrophees();
		fusionJest();
		updateScore();

	}
	
	public void razAJoue() {
		Iterator<Joueur> it = joueurs.iterator();
		while(it.hasNext()) {
			Joueur j = it.next();
			j.setaJouer(false);
		}
	}
	
	public void offreJoueur() {

		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = (Joueur) it.next();
			j.faireOffre();
		}
	}

	private void fusionJest() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			while (!j.getJestAvecTrophes().isEmpty()) {
				j.addJest(j.getJestAvecTrophes().pop());
			}
		}
	}

	private void rammaserCartesRestante() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.getOffreCache() != null) {
				jeuDeCartes.addCarte(j.getOffreCache());
				j.setOffreCache(null);
			}
			if (j.getOffreVisible() != null) {
				jeuDeCartes.addCarte(j.getOffreVisible());
				j.setOffreVisible(null);
			}
		}
	}

	private void rammaserCartesRestanteJest() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.getOffreCache() != null) {
				j.addJest(j.getOffreCache());
				j.setOffreCache(null);
			}
			if (j.getOffreVisible() != null) {
				j.addJest(j.getOffreVisible());
				j.setOffreVisible(null);
			}
		}
	}

	private Joueur meilleureOffre() {
		Iterator<Joueur> it = joueurs.iterator();
		Joueur bestOffre = joueurs.get(0), j;
		while (it.hasNext() && (bestOffre.getOffreVisible() == null || bestOffre.isaJouer() == true)) {
			bestOffre = it.next();
		}
		it = joueurs.iterator();
		while (it.hasNext()) {
			j = it.next();
			if (j.getOffreVisible() != null) {
				if (j.getOffreVisible().getValeur().ordinal() > bestOffre.getOffreVisible().getValeur().ordinal()
						&& !j.isaJouer()) {
					bestOffre = j;
				}
				if (j.getOffreVisible().getValeur().ordinal() == bestOffre.getOffreVisible().getValeur().ordinal() && j
						.getOffreVisible().getCouleur().ordinal() > bestOffre.getOffreVisible().getCouleur().ordinal()
						&& !j.isaJouer()) {
					bestOffre = j;
				}
			}
		}
		return bestOffre;
	}

	public void choixTrophee() {
		if (Joueur.NB_JOUEURS == 3 && jeuDeCartes.getExtension() == 0) {
			trophes.add(jeuDeCartes.piocherCarte());
			trophes.add(jeuDeCartes.piocherCarte());
		} else if (Joueur.NB_JOUEURS == 4) {
			trophes.add(jeuDeCartes.piocherCarte());
		} else if (Joueur.NB_JOUEURS == 3 && jeuDeCartes.getExtension() == 1) {
			trophes.add(jeuDeCartes.piocherCarte());
			trophes.add(jeuDeCartes.piocherCarte());
		}
	}

	public void distribuerJeu() {
		for (int i = 0; i < 2; i++) {
			Iterator<Joueur> it = joueurs.iterator();
			while (it.hasNext()) {
				Joueur j = (Joueur) it.next();
				j.addMain(jeuDeCartes.piocherCarte());
			}
		}
	}

	public void distribuerTrophees() {
		if (trophes.contains(Joker.getInstance())) {
			Carte joker = trophes.remove(trophes.indexOf(Joker.getInstance()));
			bestJest(joueurs).addJestAvecTrophes(joker);
		}
		Iterator<Carte> it = trophes.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			switch (c.getTrophee()) {
			case aucun:
				break;
			case bascarreau:
				lowestCarteInCouleur(Couleur.CARREAU).addJestAvecTrophes(c);
				break;
			case bascoeur:
				lowestCarteInCouleur(Couleur.COEUR).addJestAvecTrophes(c);
				break;
			case baspic:
				lowestCarteInCouleur(Couleur.PIC).addJestAvecTrophes(c);
				break;
			case bastrefle:
				lowestCarteInCouleur(Couleur.TREFLE).addJestAvecTrophes(c);
				break;
			case bestJest:
				bestJest(joueurs).addJestAvecTrophes(c);
				break;
			case hautcarreau:
				highestCarteInCouleur(Couleur.CARREAU).addJestAvecTrophes(c);
				break;
			case hautcoeur:
				highestCarteInCouleur(Couleur.COEUR).addJestAvecTrophes(c);
				break;
			case hautpic:
				highestCarteInCouleur(Couleur.PIC).addJestAvecTrophes(c);
				break;
			case hauttrefle:
				highestCarteInCouleur(Couleur.TREFLE).addJestAvecTrophes(c);
				break;
			case joker:
				break;
			case nojoker:
				bestJestNoJoker().addJestAvecTrophes(c);
				break;
			case plus2:
				plusValeur(Valeur.DEUX).addJestAvecTrophes(c);
				break;
			case plus3:
				plusValeur(Valeur.TROIS).addJestAvecTrophes(c);
				break;
			case plus4:
				plusValeur(Valeur.QUATRE).addJestAvecTrophes(c);
				break;
			default:
				break;

			}
		}
	}

	public Joueur plusValeur(Valeur valeur) {
		Joueur bestJoueur;
		Iterator<Joueur> it = joueurs.iterator();
		bestJoueur = it.next();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (bestJoueur.plusValeur(valeur) < j.plusValeur(valeur)) {
				bestJoueur = j;
			} else if (bestJoueur.plusValeur(valeur) == j.plusValeur(valeur)
					&& j.bestCouleur(valeur) > bestJoueur.bestCouleur(valeur)) {
				bestJoueur = j;
			}
		}
		return bestJoueur;
	}

	public Joueur highestCarteInCouleur(Couleur couleur) {
		Joueur bestJoueur;
		Iterator<Joueur> it = joueurs.iterator();
		bestJoueur = it.next();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.highestValeurInCouleur(couleur) > bestJoueur.highestValeurInCouleur(couleur)) {
				bestJoueur = j;
			}
		}
		return bestJoueur;
	}

	public Joueur lowestCarteInCouleur(Couleur couleur) {
		Joueur bestJoueur;
		Iterator<Joueur> it = joueurs.iterator();
		bestJoueur = it.next();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.highestValeurInCouleur(couleur) < bestJoueur.highestValeurInCouleur(couleur)) {
				bestJoueur = j;
			}
		}
		return bestJoueur;
	}

	public void updateScore() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			j.accept(this);
		}
	}

	public int visitJest(LinkedList<Carte> jest) {
		int score = 0;
		score += regle.visitCarreau(jest);
		score += regle.visitCoeur(jest);
		score += regle.visitNoir(jest);
		score += regle.visitTreflePic(jest);
		return score;
	}

	private Joueur bestJest(ArrayList<Joueur> joueurs) {
		updateScore();
		Iterator<Joueur> it = joueurs.iterator();
		Joueur bestJ = joueurs.get(0);
		it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j1 = (Joueur) it.next();
			if (j1.getScore() > bestJ.getScore()) {
				bestJ = j1;
			}
		}
		return bestJ;
	}

	private Joueur bestJestNoJoker() {
		updateScore();
		Joueur bestJ = bestJest(joueurs);
		if (bestJ.getJest().contains(Joker.getInstance()) || bestJ.getJestAvecTrophes().contains(Joker.getInstance())) {
			ArrayList<Joueur> noJoke = joueurs;
			noJoke.remove(bestJ);
			return bestJest(noJoke);
		} else {
			return bestJ;
		}
	}
	
	public ArrayList<Joueur> getOffreDispo(Joueur joueur){
		ArrayList<Joueur> copyJoueurs = new ArrayList<Joueur>();
		Iterator<Joueur> it = joueurs.iterator();
		while(it.hasNext()) {
			Joueur j = it.next();
			if(j.getOffreCache()!=null && j.getOffreVisible() != null) {
				copyJoueurs.add(j);
			}
		}
		if (copyJoueurs.size()>1) {
			copyJoueurs.remove(joueur);
		}
		return copyJoueurs;
	}

	public static void main(String[] args) {
		Joueur ordi1 = new Joueur("ordi1", new StratFacile());
		Joueur ordi2 = new Joueur("ordi2", new StratFacile());
		Joueur ordi3 = new Joueur("ordi3", new StratFacile());
		//Joueur ordi4 = new Joueur("ordi4", new StratFacile());

		Partie partie = Partie.getInstance();

		partie.addJoueur(ordi1);
		partie.addJoueur(ordi2);
		partie.addJoueur(ordi3);

		partie.buildJeuDeCarte(0);
		partie.setRegle(new RegleStandard());

		partie.lancerPartie();
		
		System.out.println(partie.bestJest(partie.getJoueurs()));
	}
}
