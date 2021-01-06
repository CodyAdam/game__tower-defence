package warcraftTD;

import warcraftTD.Levels.Level;
import warcraftTD.Levels.Level1;

public class Main {

	public static void main(String[] args) {
		final int WIDTH = 1240; // modifier cette valeur va étirer la fenêtre
		final int HEIGHT = 720; // modifier cette valeur va étirer la fenêtre

		Level startingLevel = new Level1();

		World w = new World(startingLevel, WIDTH, HEIGHT);

		// Lancement de la boucle principale du jeu
		w.run();
	}
}
