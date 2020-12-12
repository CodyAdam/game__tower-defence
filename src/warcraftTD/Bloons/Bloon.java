package warcraftTD.Bloons;

import java.util.ArrayDeque;
import java.util.List;

import warcraftTD.Position;

public abstract class Bloon {
	// Position du bloon à l'instant t
	public Position p;

	// Vitesse du bloon
	public double speed;

	// Position du bloon à l'instant t+1
	public Position nextP;

	// Boolean pour savoir si le bloon à atteint le chateau du joueur
	public boolean reached;

	// Compteur de déplacement pour savoir si le bloon à atteint le chateau du
	// joueur
	public int checkpoint = 0;

	public ArrayDeque<Position> pathing;

	public Bloon(Position p, List<Position> pathing) {
		this.pathing = new ArrayDeque<Position>(pathing);
		this.p = p;
		this.nextP = new Position(p);
	}

	public Bloon(List<Position> pathing) {
		this.pathing = new ArrayDeque<Position>(pathing);
		this.p = new Position(this.pathing.removeFirst());
		this.nextP = new Position(p);
	}

	/**
	 * Déplace le bloon en fonction de sa vitesse sur l'axe des x et des y et de sa
	 * prochaine position.
	 */
	public void move() {
		// Mesure sur quel axe le bloon se dirige.
		double dx = this.pathing.getFirst().x - p.x;
		double dy = this.pathing.getFirst().y - p.y;
		if (dy + dx != 0) {
			// Mesure la distance à laquelle le bloon à pu se déplacer.
			double ratioX = dx / (Math.abs(dx) + Math.abs(dy));
			double ratioY = dy / (Math.abs(dx) + Math.abs(dy));
			p.x += ratioX * speed;
			p.y += ratioY * speed;
		}
	}

	public void update() {
		move();
		draw();
		checkpoint++;
	}

	/**
	 * Fonction abstraite qui sera instanciée dans les classes filles pour afficher
	 * le bloon sur le plateau de jeu.
	 */
	public abstract void draw();
}
