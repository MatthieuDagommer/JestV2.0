package fr.utt.rt.lo02.projet.vue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.rt.lo02.projet.modele.Carte;
import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;

public class VueJest implements Observer{
	
	private JPanel jest;
	
	private JLabel nom;
	
	public VueJest() {
		Partie.getInstance().addObserver(this);
		jest = new JPanel();
		jest.setLayout(new BoxLayout(jest, BoxLayout.Y_AXIS));
	}
	
	public JPanel getJest() {
		return jest;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Joueur) {
			Joueur joueur = (Joueur) arg;
			majJest(joueur);
		}
	}

	private void majJest(Joueur joueur) {
		jest.removeAll();
		nom = new JLabel(joueur.getNom());
		this.jest.add(nom);
		LinkedList<Carte> jest = joueur.getJest();
		Iterator<Carte> it = jest.iterator();
		while(it.hasNext()) {
			VueCarte vc = new VueCarte(it.next());
			JLabel carte = vc.getImage();
			this.jest.add(carte);
		}
		
	}
}
