package warcraftTD.Bloons;

import java.util.ArrayDeque;
import java.util.List;

import warcraftTD.Position;

public abstract class Bloon {
	// Position du bloon à l'instant t
	public Position pos;

	// Vitesse du bloon
	public double speed;

	// Boolean pour savoir si le bloon à atteint le "chateau" du joueur
	public boolean reached;

	// Compteur de distance déplacé pour savoir quelle Bloons est en tête
	public double traveledDistance = 0;

	// Queue de Position qui sont les point par lequel le Bloon doit passer
	public ArrayDeque<Position> pathing;

	public Bloon(Position p, List<Position> pathing) {
		this.pathing = new ArrayDeque<Position>(pathing);
		this.pos = p;
	}

	public Bloon(List<Position> pathing) {
		this.pathing = new ArrayDeque<Position>(pathing);
		this.pos = new Position(this.pathing.removeFirst());
	}

	/**
	 * Déplace le bloon en fonction de sa vitesse sur l'axe des x et des y et de sa
	 * prochaine position.
	 */
	public void move() {
		// Mesure le vecteur direction (Position normalisé)
		Position dir = pos.minus(this.pathing.getFirst()).normalized();
		pos.x -= dir.x * speed;
		pos.y -= dir.y * speed;
	}

	public void onDeath() {

	}

	public void update() {
		if (reached)
			onDeath();
		else {
			move();
			draw();
		}
	}

	/**
	 * Fonction abstraite qui sera instanciée dans les classes filles pour afficher
	 * le bloon sur le plateau de jeu.
	 */
	public abstract void draw();
}
