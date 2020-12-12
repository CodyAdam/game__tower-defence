package warcraftTD;

import warcraftTD.Levels.Level;
import warcraftTD.Levels.Level1;

public class Main {

	public static void main(String[] args) {
		final int width = 1240; // ne pas modifier
		final int height = 720; // ne pas modifier

		Level startingLevel = new Level1();

		World w = new World(startingLevel, width, height);

		// Lancement de la boucle principale du jeu
		w.run();
	}
}
