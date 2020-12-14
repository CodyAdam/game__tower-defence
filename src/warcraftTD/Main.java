package warcraftTD;

import warcraftTD.Levels.Level;
import warcraftTD.Levels.Level1;

public class Main {

	public static void main(String[] args) {
		// final int WIDTH = 1240; // ne pas modifier
		final int WIDTH = 1840; // ne pas modifier
		final int HEIGHT = 520; // ne pas modifier

		Level startingLevel = new Level1();

		World w = new World(startingLevel, WIDTH, HEIGHT);

		// Lancement de la boucle principale du jeu
		w.run();
	}
}
