package fr.utt.rt.lo02.projet.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

import fr.utt.rt.lo02.projet.controleur.PartieControleur;

// TODO: Auto-generated Javadoc
/**
 * The Class Partie.
 */
public class Partie extends Observable {

	/** The instance. */
	private static Partie instance = null;
	
	/** The joueurs. */
	private ArrayList<Joueur> joueurs;
	
	/** The trophes. */
	private LinkedList<Carte> trophes;
	
	/** The jeu de cartes. */
	private Deck jeuDeCartes;
	
	/** The regle. */
	private Regle regle;
	
	/** The joueur actuel. */
	private Joueur joueurActuel;
	

	/**
	 * Gets the joueur actuel.
	 *
	 * @return the joueur actuel
	 */
	public Joueur getJoueurActuel() {
		return joueurActuel;
	}

	/**
	 * Sets the joueur actuel.
	 *
	 * @param joueurActuel the new joueur actuel
	 */
	public void setJoueurActuel(Joueur joueurActuel) {
		this.joueurActuel = joueurActuel;
	}

	/**
	 * Gets the regle.
	 *
	 * @return the regle
	 */
	public Regle getRegle() {
		return regle;
	}

	/**
	 * Sets the regle.
	 *
	 * @param regle the new regle
	 */
	public void setRegle(Regle regle) {
		this.regle = regle;
	}

	/**
	 * Instantiates a new partie.
	 */
	private Partie() {
		joueurActuel = null;
		joueurs = new ArrayList<Joueur>();
		trophes = new LinkedList<Carte>();
	}

	/**
	 * Gets the single instance of Partie.
	 *
	 * @return single instance of Partie
	 */
	public static Partie getInstance() {
		if (instance == null) {
			instance = new Partie();
		}
		return instance;
	}

	/**
	 * Gets the trophes.
	 *
	 * @return the trophes
	 */
	public LinkedList<Carte> getTrophes() {
		return trophes;
	}

	/**
	 * Sets the trophes.
	 *
	 * @param trophes the new trophes
	 */
	public void setTrophes(LinkedList<Carte> trophes) {
		this.trophes = trophes;
	}

	/**
	 * Adds the joueur.
	 *
	 * @param j the j
	 */
	public void addJoueur(Joueur j) {
		if (this.joueurs.contains(j) == false) {
			this.joueurs.add(j);

		}
	}

	/**
	 * Removes the joueur.
	 *
	 * @param j the j
	 */
	public void removeJoueur(Joueur j) {
		if (this.joueurs.contains(j) == true) {
			this.joueurs.remove(j);
		}
	}

	/**
	 * Gets the joueurs.
	 *
	 * @return the joueurs
	 */
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	/**
	 * Sets the joueurs.
	 *
	 * @param joueurs the new joueurs
	 */
	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	/**
	 * Builds the jeu de carte.
	 *
	 * @param extension the extension
	 * @return the deck
	 */
	public Deck buildJeuDeCarte(int extension) {
		Deck jeu = new Deck(extension);
		this.jeuDeCartes = jeu;
		return jeu;
	}

	/**
	 * Lancer partie.
	 */
	public void lancerPartie() {
		jeuDeCartes.melanger();
		choixTrophee();
		do {
			jeuDeCartes.melanger();
			razAJoue();
			distribuerJeu();
			offreJoueur();
			joueurActuel = meilleureOffre();
			for (int i = 0; i < Joueur.NB_JOUEURS; i++) {
				setChanged();
				notifyObservers(joueurActuel);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				joueurActuel = joueurActuel.jouer();
				if (joueurActuel.isaJouer() && i< Joueur.NB_JOUEURS-1) {
					//System.out.println("Dans le tour");
					joueurActuel = meilleureOffre();
				}
			}
			if (!jeuDeCartes.estVide()) {
				rammaserCartesRestante();
			}
			//System.out.println("Fin du tour");
		} while (!jeuDeCartes.estVide());
		setChanged();
		notifyObservers("La pioche est vide");
		rammaserCartesRestanteJest();
		updateScore();
		distribuerTrophees();
		fusionJest();
		updateScore();
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			//System.out.println(j.toString());
		}
	}

	/**
	 * Raz A joue.
	 */
	public void razAJoue() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			j.setaJouer(false);
		}
	}

	/**
	 * Offre joueur.
	 */
	public void offreJoueur() {

		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = (Joueur) it.next();
			j.faireOffre();
		}
	}

	/**
	 * Fusion jest.
	 */
	private void fusionJest() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			while (!j.getJestAvecTrophes().isEmpty()) {
				j.addJest(j.getJestAvecTrophes().pop());
			}
		}
	}

	/**
	 * Rammaser cartes restante.
	 */
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
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Rammaser cartes restante jest.
	 */
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
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Meilleure offre.
	 *
	 * @return the joueur
	 */
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

	/**
	 * Choix trophee.
	 */
	public void choixTrophee() {
		String message = "";
		if (Joueur.NB_JOUEURS == 3 && jeuDeCartes.getExtension() == 0) {
			trophes.add(jeuDeCartes.piocherCarte());
			trophes.add(jeuDeCartes.piocherCarte());
		} else if (Joueur.NB_JOUEURS == 4) {
			trophes.add(jeuDeCartes.piocherCarte());
		} else if (Joueur.NB_JOUEURS == 3 && jeuDeCartes.getExtension() == 1) {
			trophes.add(jeuDeCartes.piocherCarte());
			trophes.add(jeuDeCartes.piocherCarte());
			trophes.add(jeuDeCartes.piocherCarte());
		}
		message = "Les troph�s sont " + trophes.toString();
		//System.out.println(message);
		
		setChanged();
		notifyObservers(message);
	}

	/**
	 * Distribuer jeu.
	 */
	public void distribuerJeu() {
		for (int i = 0; i < 2; i++) {
			Iterator<Joueur> it = joueurs.iterator();
			while (it.hasNext()) {
				Joueur j = (Joueur) it.next();
				j.addMain(jeuDeCartes.piocherCarte());
			}
		}
	}

	/**
	 * Distribuer trophees.
	 */
	public void distribuerTrophees() {
		String message = "";
		if (trophes.contains(Joker.getInstance())) {
			Carte joker = trophes.remove(trophes.indexOf(Joker.getInstance()));
			bestJest(joueurs).addJestAvecTrophes(joker);
			message = "Le Trophe Joker est distribu� � " + bestJest(joueurs);
			//System.out.println(message);
			setChanged();
			notifyObservers(message);
			setChanged();
			notifyObservers();
		}
		Iterator<Carte> it = trophes.iterator();
		while (it.hasNext()) {
			Carte c = it.next();
			switch (c.getTrophee()) {
			case aucun:
				break;
			case bascarreau:
				lowestCarteInCouleur(Couleur.CARREAU).addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+lowestCarteInCouleur(Couleur.CARREAU).getNom();
				break;
			case bascoeur:
				lowestCarteInCouleur(Couleur.COEUR).addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+lowestCarteInCouleur(Couleur.COEUR).getNom();
				break;
			case baspic:
				lowestCarteInCouleur(Couleur.PIC).addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+lowestCarteInCouleur(Couleur.PIC).getNom();
				break;
			case bastrefle:
				lowestCarteInCouleur(Couleur.TREFLE).addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+lowestCarteInCouleur(Couleur.TREFLE).getNom();
				break;
			case bestJest:
				bestJest(joueurs).addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+bestJest(joueurs).getNom();
				break;
			case hautcarreau:
				highestCarteInCouleur(Couleur.CARREAU).addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+highestCarteInCouleur(Couleur.CARREAU).getNom();
				break;
			case hautcoeur:
				highestCarteInCouleur(Couleur.COEUR).addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+highestCarteInCouleur(Couleur.COEUR).getNom();
				break;
			case hautpic:
				highestCarteInCouleur(Couleur.PIC).addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+highestCarteInCouleur(Couleur.PIC).getNom();
				break;
			case hauttrefle:
				highestCarteInCouleur(Couleur.TREFLE).addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+highestCarteInCouleur(Couleur.TREFLE).getNom();
				break;
			case joker:
				hasJoker().addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+hasJoker().getNom();
				break;
			case nojoker:
				bestJestNoJoker().addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+bestJestNoJoker().getNom();
				break;
			case plus2:
				plusValeur(Valeur.DEUX).addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+plusValeur(Valeur.DEUX).getNom();
				break;
			case plus3:
				plusValeur(Valeur.TROIS).addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+plusValeur(Valeur.TROIS).getNom();
				break;
			case plus4:
				plusValeur(Valeur.QUATRE).addJestAvecTrophes(c);
				message = "Le trophe "+c+" est distribu� � "+plusValeur(Valeur.QUATRE).getNom();
				break;
			default:
				break;

			}
			//System.out.println(message);
			setChanged();
			notifyObservers(message);

			message = "Les troph�s sont distribu�s";
			setChanged();
			notifyObservers(message);
		}
	}

	/**
	 * Checks for joker.
	 *
	 * @return the joueur
	 */
	public Joueur hasJoker() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.getJest().contains(Joker.getInstance()) || j.getJestAvecTrophes().contains(Joker.getInstance())) {
				return j;
			}
		}
		return null;
	}

	/**
	 * Plus valeur.
	 *
	 * @param valeur the valeur
	 * @return the joueur
	 */
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

	/**
	 * Highest carte in couleur.
	 *
	 * @param couleur the couleur
	 * @return the joueur
	 */
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

	/**
	 * Lowest carte in couleur.
	 *
	 * @param couleur the couleur
	 * @return the joueur
	 */
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

	/**
	 * Update score.
	 */
	public void updateScore() {
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			j.accept(this);
		}
	}

	/**
	 * Visit jest.
	 *
	 * @param jest the jest
	 * @return the int
	 */
	public int visitJest(LinkedList<Carte> jest) {
		int score = 0;
		score += regle.visitCarreau(jest);
		score += regle.visitCoeur(jest);
		score += regle.visitNoir(jest);
		score += regle.visitTreflePic(jest);
		return score;
	}

	/**
	 * Best jest.
	 *
	 * @param joueurs the joueurs
	 * @return the joueur
	 */
	public Joueur bestJest(ArrayList<Joueur> joueurs) {
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

	/**
	 * Best jest no joker.
	 *
	 * @return the joueur
	 */
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

	/**
	 * Gets the offre dispo.
	 *
	 * @param joueur the joueur
	 * @return the offre dispo
	 */
	public ArrayList<Joueur> getOffreDispo(Joueur joueur) {
		ArrayList<Joueur> copyJoueurs = new ArrayList<Joueur>();
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = it.next();
			if (j.getOffreCache() != null && j.getOffreVisible() != null) {
				copyJoueurs.add(j);
			}
		}
		if (copyJoueurs.size() > 1) {
			copyJoueurs.remove(joueur);
		}
		return copyJoueurs;
	}

	/**
	 * Initialisation.
	 */
	public void initialisation() {
		PartieControleur.initialisationJest();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Joueur ordi1 = new Joueur("ordi1", new StratFacile());
		Joueur ordi2 = new Joueur("ordi2", new StratFacile());
		Joueur ordi3 = new Joueur("ordi3", new StratFacile());
		Joueur ordi4 = new Joueur("ordi4", new StratFacile());

		Partie partie = Partie.getInstance();

		partie.addJoueur(ordi1);
		partie.addJoueur(ordi2);
		partie.addJoueur(ordi3);
		partie.addJoueur(ordi4);

		partie.buildJeuDeCarte(0);
		partie.setRegle(new RegleStandard());

		partie.lancerPartie();

		//System.out.println(partie.bestJest(partie.getJoueurs()));
	}
}
