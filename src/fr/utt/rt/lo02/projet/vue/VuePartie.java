package fr.utt.rt.lo02.projet.vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.utt.rt.lo02.projet.controleur.PartieControleur;
import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;

// TODO: Auto-generated Javadoc
/**
 * The Class VuePartie.
 */
public class VuePartie implements Observer {

	/** The modele. */
	private Partie modele;

	/** The controleur. */
	private PartieControleur controleur;

	/** The joueurs. */
	private ArrayList<Joueur> joueurs;
	
	/** The vue joueurs. */
	private ArrayList<VueJoueur> vueJoueurs;

	/** The panel joueur. */
	private JPanel panelJoueur;
	
	/** The panel jest. */
	private JPanel panelJest;
	
	/** The fenetre. */
	private JFrame fenetre;

	/** The trophe 1. */
	private JLabel trophe1;
	
	/** The trophe 2. */
	private JLabel trophe2;
	
	/** The trophe 3. */
	private JLabel trophe3;

	/** The pioche. */
	private JLabel pioche;

	/** The log. */
	private JTextArea log;

	/** The scroll pane. */
	private JScrollPane scrollPane;

	/**
	 * Instantiates a new vue partie.
	 *
	 * @param modele the modele
	 */
	public VuePartie(final Partie modele) {

		this.setModele(modele);
		modele.addObserver(this);

		joueurs = modele.getJoueurs();

		fenetre = new JFrame("JEST");
		fenetre.setLayout(new BorderLayout());
		fenetre.setResizable(true);
		
		panelJoueur = new JPanel();
		panelJoueur.setLayout(new BoxLayout(panelJoueur, BoxLayout.X_AXIS));
		
		panelJest = new JPanel();
		panelJest.setLayout(new BoxLayout(panelJest, BoxLayout.Y_AXIS));

		Iterator<Joueur> it = joueurs.iterator();
		vueJoueurs = new ArrayList<VueJoueur>();
		while(it.hasNext()) {
			VueJoueur vueJoueur = new VueJoueur(it.next(), this);
			vueJoueurs.add(vueJoueur);
			panelJoueur.add(vueJoueur.getOffre());

		}
		
		VueJest vueJest = new VueJest();
		panelJest.add(vueJest.getJest());
		
		JPanel panelTapis = new JPanel();
		JLabel imgTapis = new JLabel(new ImageIcon("image/tapis.jpg"));

		pioche = new JLabel(new ImageIcon("image/dosCarte.jpg"));

		trophe1 = new JLabel(new ImageIcon("image/tasVide.jpg"));
		trophe2 = new JLabel(new ImageIcon("image/tasVide.jpg"));
		trophe3 = new JLabel(new ImageIcon("image/tasVide.jpg"));

		imgTapis.setLayout(new GridLayout());
		imgTapis.add(pioche);
		imgTapis.add(trophe1);
		imgTapis.add(trophe2);
		imgTapis.add(trophe3);
		
		panelTapis.add(imgTapis);

		setLog(new JTextArea());
		getLog().setEditable(false);
		getLog().setRows(5);
		scrollPane = new JScrollPane(getLog());

		fenetre.add(scrollPane, BorderLayout.NORTH);
		//fenetre.add(scroll, BorderLayout.WEST);
		// fenetre.add(continuer, BorderLayout.SOUTH);
		fenetre.add(panelJoueur, BorderLayout.SOUTH);
		fenetre.add(panelJest, BorderLayout.WEST);
		fenetre.add(panelTapis, BorderLayout.EAST);
		afficherTrophes();

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
		

		

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
	 * Effacer pioche.
	 */
	public void effacerPioche() {
		this.pioche.setIcon(new ImageIcon("image/tasVide.png"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Afficher trophes.
	 */
	public void afficherTrophes() {
		LinkedList<Carte> listetrophes = Partie.getInstance().getTrophes();
		Iterator<Carte> it = listetrophes.iterator();
		int i = 0;
		while (it.hasNext()) {
			VueCarte trophe = new VueCarte(it.next());
			if (i == 0) {
				this.trophe1.setIcon(new ImageIcon(trophe.getChemin()));
			} else if (i == 1) {
				this.trophe2.setIcon(new ImageIcon(trophe.getChemin()));
			} else if(i==2) {
				this.trophe3.setIcon(new ImageIcon(trophe.getChemin()));
			}
			i++;
		}

	}

	/**
	 * Effacer trophes.
	 */
	public void effacerTrophes() {
		this.trophe1.setIcon(new ImageIcon("image/tasVide.png"));
		this.trophe2.setIcon(new ImageIcon("image/tasVide.png"));
		this.trophe3.setIcon(new ImageIcon("image/tasVide.png"));
	}

	/**
	 * Gets the pioche.
	 *
	 * @return the pioche
	 */
	public JLabel getPioche() {
		return pioche;
	}

	/**
	 * Sets the pioche.
	 *
	 * @param pioche the new pioche
	 */
	public void setPioche(JLabel pioche) {
		this.pioche = pioche;
	}

	/**
	 * Sets the log.
	 *
	 * @param log the new log
	 */
	public void setLog(JTextArea log) {
		this.log = log;
	}

	/**
	 * Gets the log.
	 *
	 * @return the log
	 */
	public JTextArea getLog() {
		return log;
	}

	/**
	 * Gets the modele.
	 *
	 * @return the modele
	 */
	public Partie getModele() {
		return modele;
	}

	/**
	 * Sets the modele.
	 *
	 * @param modele the new modele
	 */
	public void setModele(Partie modele) {
		this.modele = modele;
	}

	/**
	 * Update.
	 *
	 * @param o the o
	 * @param arg the arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			String message = arg.toString();
			if(message.equals("Les troph�s sont distribu�s")) {
				effacerTrophes();
			}
			else if(message.equals("La pioche est vide")) {
				effacerPioche();
			} else if(message.contains("Les troph�s sont ")) {
				afficherTrophes();
			}
			else {
				controleur.updateJTextArea(message);
			}
		}
	}

	/**
	 * Gets the controleur.
	 *
	 * @return the controleur
	 */
	public PartieControleur getControleur() {
		return controleur;
	}


	/**
	 * Gets the vue joueurs.
	 *
	 * @return the vue joueurs
	 */
	public ArrayList<VueJoueur> getVueJoueurs() {
		return vueJoueurs;
	}


	/**
	 * Sets the vue joueurs.
	 *
	 * @param vueJoueurs the new vue joueurs
	 */
	public void setVueJoueurs(ArrayList<VueJoueur> vueJoueurs) {
		this.vueJoueurs = vueJoueurs;
	}


}
