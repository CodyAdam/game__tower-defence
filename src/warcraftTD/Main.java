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

		// Ajout d'un monstre "à la main" pour afficher comment un monstre se déplaçe.
		// Vous ne devez pas faire pareil, mais ajouter une vague comportant plusieurs
		// monstres
		Bloon monster = new RedBloon(new Position(startingLevel.spawn.x * w.squareWidth + w.squareWidth / 2,
				startingLevel.spawn.y * w.squareHeight + w.squareHeight / 2));
		monster.nextP = new Position(startingLevel.spawn.x * w.squareWidth + w.squareWidth / 2, 0);
		monster.speed = 0.001;
		w.monsters.add(monster);

		// Lancement de la boucle principale du jeu
		w.run();
	}
}
