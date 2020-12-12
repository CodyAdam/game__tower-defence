package warcraftTD.Bloons;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.StdDraw;

public class RedBloon extends Bloon {

	public RedBloon(Position p, List<Position> pathing) {
		super(p, pathing);
		this.speed = 0.001;
	}

	public RedBloon(List<Position> pathing) {
		super(pathing);
		this.speed = 0.001;
	}

	/**
	 * Affiche un monstre qui change de couleur au cours du temps Le monstre est
	 * représenté par un cercle de couleur bleue ou grise
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(pos.x, pos.y, 0.01);
	}
}
