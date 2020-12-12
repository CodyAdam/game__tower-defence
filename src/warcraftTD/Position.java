package warcraftTD;

public class Position {
	public double x;
	public double y;

	/**
	 * Classe qui permet d'avoir la position sur l'axe des x et des y des monstres
	 * et des tours
	 * 
	 * @param x
	 * @param y
	 */
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position p) {
		x = p.x;
		y = p.y;
	}

	public boolean equals(Position p) {
		return x == p.x && y == p.y;
	}

	/**
	 * Mesure la distance euclidienne entre 2 positions.
	 * 
	 * @param p
	 * @return
	 */
	public double dist(Position p) {
		return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
	}

	/**
	 * soustrait deux vecteur position
	 * 
	 * @param p la position à soustraire à "this"
	 * @return la position qui correspon à la soustraction de (this - p)
	 */
	public Position minus(Position p) {
		return new Position(this.x - p.x, this.y - p.y);
	}

	/**
	 * multiplie deux vecteur position
	 * 
	 * @param i la valeur par laquelle multiplier "this"
	 * @return la position qui correspond à la multiplication de (this * i)
	 */
	public Position multi(double i) {
		return new Position(this.x * i, this.y * i);
	}

	/**
	 * @return Donne la norme du vecteur position
	 */
	public double norm() {
		Position zero = new Position(0, 0);
		return dist(zero);
	}

	/**
	 * @return rend le vecteur position mais normalisé (norme = 1)
	 */
	public Position normalized() {
		return new Position(x / norm(), y / norm());
	}

	/**
	 * Retourne la position du point sur l'axe des x et des y
	 */
	public String toString() {
		return "(" + Double.toString(x) + ", " + Double.toString(y) + ")";
	}
}
