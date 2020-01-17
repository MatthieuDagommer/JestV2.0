import fr.utt.rt.lo02.projet.controleur.PartieControleur;
import fr.utt.rt.lo02.projet.modele.Joueur;
import fr.utt.rt.lo02.projet.modele.Partie;
import fr.utt.rt.lo02.projet.modele.StratFacile;
import fr.utt.rt.lo02.projet.vue.VueConsole;
import fr.utt.rt.lo02.projet.vue.VuePartie;

/**
 * Classe permettant de lancer tout les element d'une partie en graphique
 */
public class MainGraphique implements Runnable {

	/** la partie. */
	private Partie partie;

	/** le controleur de la partie. */
	private PartieControleur controleur;

	/** la vue de la partie. */
	private VuePartie vuePartie;

	/**
	 * Constructeur de la classe MainGraphique. il construit la partie et
	 * l'initialise, lie la vue a la partie, et le controleur aux 2 elements
	 * precedent
	 */
	public MainGraphique() {
		partie = Partie.getInstance();
		partie.initialisation();
		vuePartie = new VuePartie(partie);
		VueConsole vc = new VueConsole(partie);

		PartieControleur partieControleur = new PartieControleur(partie, vuePartie);

		partie.addObserver(vuePartie);
	}

	/**
	 * Methode Main qui lance et créer les thread du MainGraphique et de swing
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Thread partie = new Thread(new MainGraphique());
		partie.start();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Thread();
			}
		});
	}

	/**
	 * Methode qui lance la partie
	 */
	@Override
	public void run() {
		partie.lancerPartie();
	}

}
