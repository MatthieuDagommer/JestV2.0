package fr.utt.rt.lo02.projet.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
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

public class VuePartie implements Observer{

	private Partie modele;
	
	private PartieControleur controleur;
	
	private ArrayList<Joueur> joueurs;
	
	private JFrame fenetre;
	
	private JLabel trophe1;
	private JLabel trophe2;
	
	private JPanel trophes;
	
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
		
		
		JPanel panelTapis = new JPanel();
		JLabel imgTapis = new JLabel(new ImageIcon("image/tapis.jpg"));
		
		pioche = new JLabel(new ImageIcon("image/dosCarte.jpg"));
		
		trophe1 = new JLabel(new ImageIcon("image/dosCarte.jpg"));
		trophe2 = new JLabel(new ImageIcon("image/dosCarte.jpg"));
		
		
		imgTapis.setLayout(new GridLayout());
		imgTapis.add(pioche);
		imgTapis.add(trophe1);
		imgTapis.add(trophe2);

		panelTapis.add(imgTapis);
		
		setLog(new JTextArea());
		getLog().setEditable(false);
		getLog().setRows(5);
		scrollPane = new JScrollPane(getLog());
		
		fenetre.add(scrollPane, BorderLayout.NORTH);
		//fenetre.add(scroll, BorderLayout.WEST);
		//fenetre.add(continuer, BorderLayout.SOUTH);
		fenetre.add(panelTapis, BorderLayout.EAST);
		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);;
		
		afficherTrophes();
		
	}
	
	public void affacerPioche() {
		this.pioche.setIcon(new ImageIcon("image/tasVide.png"));
	}
	
	public void afficherTrophes() {
		this.trophe1.setIcon(new ImageIcon("image/AsTrefle.png"));
		
	}
	
	public void effacerTrophes() {
		trophes.removeAll();
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
		// TODO Auto-generated method stub
		
	}
}
