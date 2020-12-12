package warcraftTD;

import warcraftTD.Bloons.Bloon;
import warcraftTD.Bloons.RedBloon;
import warcraftTD.Levels.Level;
import warcraftTD.Levels.Level1;

public class Main {

	public static void main(String[] args) {
		final int width = 1240; // VALEUR BRUT à NE PAS MODIFIER
		final int height = 720; // VALEUR BRUT à NE PAS MODIFIER

		Level startingLevel = new Level1();

		World w = new World(startingLevel, width, height);

		// Lancement de la boucle principale du jeu
		w.run();
	}
}
