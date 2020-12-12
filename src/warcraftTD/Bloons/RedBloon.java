package warcraftTD.Bloons;

import warcraftTD.Position;
import warcraftTD.StdDraw;

public class RedBloon extends Bloon {

	public RedBloon(Position p) {
		super(p);
	}

	/**
	 * Affiche un monstre qui change de couleur au cours du temps Le monstre est
	 * représenté par un cercle de couleur bleue ou grise
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(p.x, p.y, 0.01);
	}
}
