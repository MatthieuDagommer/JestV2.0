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

public class VuePartie implements Observer {

	private Partie modele;

	private PartieControleur controleur;

	private ArrayList<Joueur> joueurs;
	
	private ArrayList<VueJoueur> vueJoueurs;

	private JPanel panelJoueur;
	
	private JPanel panelJest;
	
	private JFrame fenetre;

	private JLabel trophe1;
	private JLabel trophe2;
	private JLabel trophe3;

	private JLabel pioche;

	private JTextArea log;

	private JScrollPane scrollPane;

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
	

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}


	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}


	public void effacerPioche() {
		this.pioche.setIcon(new ImageIcon("image/tasVide.png"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	public void effacerTrophes() {
		this.trophe1.setIcon(new ImageIcon("image/tasVide.png"));
		this.trophe2.setIcon(new ImageIcon("image/tasVide.png"));
		this.trophe3.setIcon(new ImageIcon("image/tasVide.png"));
	}

	public JLabel getPioche() {
		return pioche;
	}

	public void setPioche(JLabel pioche) {
		this.pioche = pioche;
	}

	public void setLog(JTextArea log) {
		this.log = log;
	}

	public JTextArea getLog() {
		return log;
	}

	public Partie getModele() {
		return modele;
	}

	public void setModele(Partie modele) {
		this.modele = modele;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			String message = arg.toString();
			if(message.equals("Les trophés sont distribués")) {
				effacerTrophes();
			}
			else if(message.equals("La pioche est vide")) {
				effacerPioche();
			} else if(message.contains("Les trophés sont ")) {
				afficherTrophes();
			}
		}
	}

	public PartieControleur getControleur() {
		return controleur;
	}


	public ArrayList<VueJoueur> getVueJoueurs() {
		return vueJoueurs;
	}


	public void setVueJoueurs(ArrayList<VueJoueur> vueJoueurs) {
		this.vueJoueurs = vueJoueurs;
	}


}
