package warcraftTD.Bloons;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.StdDraw;

public class RedBloon extends Bloon {

	public RedBloon(Position p, List<Position> pathing, int hurt) {
		super(p, pathing, hurt);
		init();
	}

	public RedBloon(List<Position> pathing) {
		super(pathing);
		init();
	}

	public void init() {
		this.speed = 0.005;
		this.hp = 1;
	}

	/**
	 * Affiche un monstre qui change de couleur au cours du temps Le monstre est
	 * représenté par un cercle de couleur bleue ou grise
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledRectangle(pos.x, pos.y, 0.01 / ((double) 1240 / 720), 0.01);
	}
}
