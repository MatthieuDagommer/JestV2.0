package fr.utt.rt.lo02.projet.modele;

import java.util.LinkedList;

import fr.utt.rt.lo02.projet.controleur.PartieControleur;

/**
 * The Class StratPhysique.
 */
public class StratPhysique extends StrategieJoueur {

	/** The victime. */
	private Joueur victime;

	/** The carte visible victime. */
	private boolean carteVisibleVictime;
	
	/** The cache. */
	private Carte cache;

	/**
	 * Methode qui permet de definir l'offre cachee d'un joueur physique
	 * 
	 *
	 * @param ceJoueur the ce joueur
	 */
	@Override
	public void faireOffre(Joueur ceJoueur) {
		LinkedList<Carte> main = ceJoueur.getMain();
		PartieControleur.fenetreChoixOffre(ceJoueur);
		while(cache == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ceJoueur.setOffreCache(cache);
		main.remove(cache);
		ceJoueur.setOffreVisible(main.pop());
		cache = null;
		setChanged();
		notifyObservers(ceJoueur);
	}
	
	/**
	 * Cacher.
	 *
	 * @param carte the carte
	 */
	@Override
	public void cacher(Carte carte) {
		this.cache = carte;
	}

	/**
	 * Choisir carte.
	 *
	 * @param ceJoueur the ce joueur
	 * @return the joueur
	 */
	@Override
	public Joueur choisirCarte(Joueur ceJoueur) {
		String message = "";
		while (victime == null) {//modifier avec de l'attente passive
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		if(this.carteVisibleVictime == true) {
			ceJoueur.addJest(victime.getOffreVisible());
			message = ceJoueur.getNom() + " prend la carte "+victime.getOffreVisible()+" de "+victime.getNom();
			
			victime.setOffreVisible(null);
			
		} else if (this.carteVisibleVictime == false) {
			ceJoueur.addJest(victime.getOffreCache());
			message = ceJoueur.getNom() + " prend la carte "+victime.getOffreCache()+" de "+victime.getNom();

			victime.setOffreCache(null);
		}
		Joueur j = victime;
		victime = null;
		System.out.println(message);
		setChanged();
		notifyObservers(message);
		setChanged();
		notifyObservers(j);
		return j;

	}

	/**
	 * Sets the victime.
	 *
	 * @param joueur the new victime
	 */
	public void setVictime(Joueur joueur) {
		victime = joueur;
	}

	/**
	 * Gets the victime.
	 *
	 * @return the victime
	 */
	public Joueur getVictime() {
		return victime;
	}

	/**
	 * Choix.
	 *
	 * @param visible the visible
	 * @param victime the victime
	 */
	public void choix(boolean visible, Joueur victime) {

		this.carteVisibleVictime = visible;

		this.victime = victime;
	}



}
