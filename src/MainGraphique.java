import fr.utt.rt.lo02.projet.controleur.PartieControleur;
import fr.utt.rt.lo02.projet.modele.Partie;
import fr.utt.rt.lo02.projet.vue.VuePartie;

public class MainGraphique implements Runnable {

	public MainGraphique() {
		Partie partie = Partie.getInstance();

		VuePartie vuePartie = new VuePartie(partie);

		PartieControleur partieControleur = new PartieControleur(partie, vuePartie);

		partie.addObserver(vuePartie);

		partie.lancerPartie();
	}

	public static void main(String[] args) {
		Thread partie = new Thread(new MainGraphique());
		partie.start();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainGraphique();
			}
		});
	}

	@Override
	public void run() {
		new MainGraphique();
	}

}
