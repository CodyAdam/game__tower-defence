package warcraftTD;

/**
 * Classe principale qui est lancé à l'éxécution du jeu
 */
public class Main {
	public static void main(String[] args) {
		final int WIDTH = 1240; // modifier cette valeur va étirer la fenêtre
		final int HEIGHT = 720; // modifier cette valeur va étirer la fenêtre

		Menu mainMenu = new Menu(WIDTH, HEIGHT);
		mainMenu.loop();

	}
}
