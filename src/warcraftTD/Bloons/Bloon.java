package warcraftTD.Bloons;

import java.util.ArrayDeque;
import java.util.List;

import warcraftTD.Position;

public abstract class Bloon {
	// Position du bloon à l'instant t
	public Position pos;

	// Vitesse du bloon
	public double speed;
	private final double SPEED_RATIO = ((double) 1240 / 720); // la fenêtre n'étant pas carré la vitesse X n'est pas la
																// même que Y donc nous égalisont

	// Boolean pour savoir si le bloon à atteint le "chateau" du joueur
	public boolean reached;

	// Compteur de distance déplacé pour savoir quelle Bloons est en tête
	public double traveledDistance = 0;

	// Queue de Position qui sont les point par lequel le Bloon doit passer
	public ArrayDeque<Position> pathing;

	/**
	 * Fait apparaitre un Bloon sur une position donnée (utilisé pour faire
	 * apparaitre les Bloons contenu dans un autre qui viens de mourir)
	 */
	public Bloon(Position p, List<Position> pathing) {
		this.pathing = new ArrayDeque<Position>(pathing);
		this.pos = p;
	}

	/**
	 * Fait apparaitre un Bloon au tout premier point de "pathing" (le spawn du
	 * level)
	 */
	public Bloon(List<Position> pathing) {
		this.pathing = new ArrayDeque<Position>(pathing);
		this.pos = new Position(this.pathing.removeFirst());
	}

	/**
	 * Déplace le bloon en fonction de sa vitesse sur l'axe des x et des y et de sa
	 * prochaine position.
	 */
	public void move() {
		// Mesure le vecteur direction
		Position dir = this.pathing.getFirst().minus(pos);

		// Mesure le vecteur vitesse
		Position speedVec = new Position(dir.normalized().x * speed / SPEED_RATIO, dir.normalized().y * speed);

		if (dir.norm() < speedVec.norm()) { // Le Bloon à atteint le waypoint alors on passe au suivant
			pos.x = this.pathing.getFirst().x;
			pos.y = this.pathing.getFirst().y;
			this.pathing.removeFirst();
		} else {
			pos.x += speedVec.x;
			pos.y += speedVec.y;
		}
	}

	public void onDeath() {
		// TODO remove from board
	}

	/**
	 * À chaque tick déplace puis affiche le bloons
	 */
	public void update() {
		if (reached)
			// TODO remove health to player
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
