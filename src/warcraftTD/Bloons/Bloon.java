package warcraftTD.Bloons;

import java.util.ArrayDeque;
import java.util.List;

import warcraftTD.Position;
import warcraftTD.StdDraw;

public abstract class Bloon {

	public Position pos; // Position du bloon à l'instant t
	public int hp; // Points de vie du ballon (<1 == mort)
	public int power; // Nombre de points de vie que le joueur perd quand le Bloon arrive à la fin du
						// chemin et aussi le nombre d'argent gagner quand il éclate
	public String sprite; // Chemin vers l'image du ballons
	public boolean reached = false;// Boolean pour savoir si le bloon à atteint le "chateau" du joueur
	public boolean targetable = true;// Boolean pour savoir si le bloon à atteint le "chateau" du joueur
	public double traveledDistance = 0;// Compteur de distance déplacé pour savoir quelle Bloons est en tête
	public List<Bloon> spawnOnDeath;// liste des Bloons à faire apparaitre quand le bloon actuel meurt
	public ArrayDeque<Position> pathing;// Queue de Position qui sont les point par lequel le Bloon doit passer
	// public double speed = 0.00225; // Vitesse du bloon
	public double speed = 0.00225; // Vitesse du bloon
	private final double SPEED_RATIO = ((double) 720 / 1240); // la fenêtre n'étant pas carré la vitesse X n'est pas la
																// même que Y donc nous égalisont avevc cette constante
																// pour que le ballon ce déplace à la même vitesse
																// horizontalement et verticalement
	private final double ON_DEATH_RADIUS = 0.04;// Rayons dans lequel les bloons apparaissent

	/**
	 * Fait apparaitre un Bloon au tout premier point de "pathing" (le spawn du
	 * level)
	 */
	public Bloon(List<Position> pathing) {
		this.pathing = new ArrayDeque<Position>(pathing);
		this.pos = new Position(this.pathing.removeFirst());
	}

	public List<Bloon> onDeath(List<Bloon> bloons) {
		for (Bloon b : spawnOnDeath) {
			b.hp += this.hp;// en cas d'overkill

			// Donne la position du Bloon éclaté
			// offset la position de façon aléatoire avec un rayon défini
			if (spawnOnDeath.size() != 1)
				b.pos = new Position(this.pos.x + Math.random() * ON_DEATH_RADIUS - ON_DEATH_RADIUS / 2,
						this.pos.y + Math.random() * ON_DEATH_RADIUS - ON_DEATH_RADIUS / 2);
			else
				b.pos = new Position(this.pos.x, this.pos.y);

			b.pathing = this.pathing.clone();// Reprend le chemin du Bloon parent
		}
		return this.spawnOnDeath;
	}

	/**
	 * Déplace le bloon en fonction de sa vitesse sur l'axe des x et des y et de sa
	 * prochaine position.
	 */
	public void move() {
		if (this.pathing.isEmpty()) {
			reached = true;
			return;
		}
		targetable = !pathing.getFirst().bool; // Under a bridge ?

		// Mesure le vecteur direction
		Position dir = pathing.getFirst().minus(pos);
		double dirNorm = dir.norm();
		dir = dir.normalized();

		// Mesure le vecteur vitesse
		Position speedVec = dir.multi(Math.sqrt(Math.pow(dir.x * SPEED_RATIO, 2) + Math.pow(dir.y, 2))).multi(speed);
		double speedNorm = speedVec.norm();

		if (dirNorm < speedNorm) { // Le Bloon à atteint le waypoint alors on passe au waypoint suivant
			traveledDistance += speedNorm - dirNorm;
			pos.x = pathing.getFirst().x;
			pos.y = pathing.getFirst().y;
			pathing.removeFirst();
		} else {
			traveledDistance += speedNorm;
			pos.x += speedVec.x;
			pos.y += speedVec.y;
		}
	}

	/**
	 * Affiche un monstre qui change de couleur au cours du temps Le monstre est
	 * représenté par un cercle de couleur bleue ou grise
	 */
	public void draw() {
		if (targetable)
			StdDraw.picture(this.pos.x, this.pos.y, this.sprite);
	}

	/**
	 * À chaque tick déplace puis affiche le bloons
	 */
	public void tick() {
		move();
	}
}
