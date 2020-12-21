package warcraftTD.Bloons;

import java.util.ArrayDeque;
import java.util.List;

import warcraftTD.Position;
import warcraftTD.StdDraw;

public abstract class Bloon {
	// Position du bloon à l'instant t
	public Position pos;

	// Points de vie du ballon (<1 == mort)
	public int hp;

	// Nombre de points de vie que le joueur perd quand le Bloon arrive à la fin du
	// chemin
	public int power;

	// Chemin vers l'image du ballons
	public String imgPath;
	// centre de l'objet, utilisé pour l'affichage de l'image
	public Position center;

	// Vitesse du bloon
	public double speed = 0.00225;
	// la fenêtre n'étant pas carré la vitesse X n'est pas la
	// même que Y donc nous égalisont avevc cette constante
	// pour que le ballon ce déplace à la même vitesse horizontalement et
	// verticalement
	private final double SPEED_RATIO = ((double) 1240 / 720);

	// Boolean pour savoir si le bloon à atteint le "chateau" du joueur
	public boolean reached = false;

	// liste des Bloons à faire apparaitre quand le bloon actuel meurt
	public List<Bloon> spawnOnDeath;

	// Rayons dans lequel les bloons apparaissent
	private final double ON_DEATH_RADIUS = 0.04;

	// Compteur de distance déplacé pour savoir quelle Bloons est en tête
	public double traveledDistance = 0;

	// Queue de Position qui sont les point par lequel le Bloon doit passer
	public ArrayDeque<Position> pathing;

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
		if (this.pathing.isEmpty()) {
			reached = true;
			return;
		}

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

	/**
	 * Affiche un monstre qui change de couleur au cours du temps Le monstre est
	 * représenté par un cercle de couleur bleue ou grise
	 */
	public void draw() {
		StdDraw.picture(this.pos.x, this.pos.y, this.imgPath);
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
	 * À chaque tick déplace puis affiche le bloons
	 */
	public void tick() {
		move();
	}
}
