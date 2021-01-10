package warcraftTD;

/**
 * Classe utilitaire utilisée pour stoquer et faire des opération sur des
 * vecteur 2D (soit des positions)
 */
public class Position {
	public double x;
	public double y;
	public boolean bool;

	private final int GRID_WIDTH = 31;
	private final int GRID_MAX_Y = 18;

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
		this.bool = false;
	}

	/**
	 * Classe qui permet d'avoir la position sur l'axe des x et des y des monstres
	 * et des tours
	 * 
	 * @param x
	 * @param y
	 */
	public Position(double x, double y, boolean bool) {
		this.x = x;
		this.y = y;
		this.bool = bool;
	}

	public Position(Position p) {
		x = p.x;
		y = p.y;
	}

	public boolean equals(Position p) {
		return x == p.x && y == p.y;
	}

	/**
	 * Mesure la distance euclidienne entre 2 positions dans le référenciel de la
	 * fenêtre (entre 0 et 1).
	 * 
	 * @param p
	 * @return
	 */
	public double distInFrameSpace(Position p) {
		return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
	}

	/**
	 * Mesure la distance euclidienne entre 2 positions dans le référenciel de la
	 * grille.
	 * 
	 * @param p
	 * @return
	 */
	public double distInGridSpace(Position p) {
		return Math.sqrt(Math.pow((x - p.x) * GRID_WIDTH, 2) + Math.pow((y - p.y) * GRID_MAX_Y, 2));
	}

	/**
	 * @param x coordonnée x dans la grille
	 * @param y coordonnée y dans la grille
	 * @return Position, les coordonée dans l'espace de la fenêtre
	 * @note coordonnées compris entre 0 et 1
	 */
	public Position inFrameSpace() {
		double squareWidth = (double) 1 / GRID_WIDTH;
		double squareHeight = (double) 1 / GRID_MAX_Y;
		return new Position(x * squareWidth + squareWidth / 2, y * squareHeight + squareHeight / 2);
	}

	/**
	 * @param x coordonnée x dans la fenêtre
	 * @param y coordonnée y dans la fenêtre
	 * @return Position, les coordonée dans l'espace de la grille
	 * @note coordonnées compris entre 0 et taille max de la grille horizontalement
	 *       puis verticalement
	 */
	public Position inGridSpace() {
		return inGridSpace(true);
	}

	/**
	 * @param x coordonnée x dans la fenêtre
	 * @param y coordonnée y dans la fenêtre
	 * @return Position, les coordonée dans l'espace de la grille
	 * @note coordonnées compris entre 0 et taille max de la grille horizontalement
	 *       puis verticalement
	 */
	public Position inGridSpace(boolean round) {
		double squareWidth = (double) 1 / GRID_WIDTH;
		double squareHeight = (double) 1 / GRID_MAX_Y;

		if (round) {
			x = Math.round((x - x % squareWidth) / squareWidth);
			y = Math.round((y - y % squareHeight) / squareHeight);
		} else {
			x = x / squareWidth;
			y = y / squareHeight;
		}

		return new Position(x, y);
	}

	/**
	 * @return Rend l'angle du vecteur position en radian
	 * @note Soit : (1, 0) -> 0 degree, (0, 1) -> 90 degree, (-1, 0) -> 180 degree,
	 *       (0, -1) -> 270 degree
	 */
	public double angle() {
		return Math.atan2(this.y, this.x) * 180 / Math.PI;
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
	 * additionne deux vecteur position
	 * 
	 * @param p la position à additionner à "this"
	 * @return la position qui correspon à l'addition de (this + p)
	 */
	public Position plus(Position p) {
		return new Position(this.x + p.x, this.y + p.y);
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
	public double normInFrameSpace() {
		Position zero = new Position(0, 0);
		return distInFrameSpace(zero);
	}

	/**
	 * @return Donne la norme du vecteur position
	 */
	public double normInGridSpace() {
		Position zero = new Position(0, 0);
		return distInGridSpace(zero);
	}

	/**
	 * @return rend le vecteur position mais normalisé (norme = 1)
	 */
	public Position normalized() {
		double norm = normInFrameSpace();
		return new Position(x / norm, y / norm);
	}

	/**
	 * Retourne la position du point sur l'axe des x et des y
	 */
	public String toString() {
		return "(" + Double.toString(x) + ", " + Double.toString(y) + ")";
	}
}
