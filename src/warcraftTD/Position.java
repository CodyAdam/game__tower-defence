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
	private final int GRID_HEIGHT = 18;

	/**
	 * Constructeur
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
	 * Constructeur
	 * 
	 * @param x
	 * @param y
	 */
	public Position(double x, double y, boolean bool) {
		this.x = x;
		this.y = y;
		this.bool = bool;
	}

	/**
	 * Constructeur
	 * 
	 * @param p la position à importer
	 */
	public Position(Position p) {
		x = p.x;
		y = p.y;
	}

	/**
	 * @param p la position à comparer
	 * @return Est-ce la postion p est égale à cette position?
	 */
	public boolean equals(Position p) {
		return x == p.x && y == p.y;
	}

	/**
	 * @param degree le nombre de degrée à tourner (en degree)
	 * @return la nouvelle position après rotation
	 */
	public Position rotate(double degree) {
		double radiant = Math.toRadians(degree);
		double cos = Math.cos(radiant);
		double sin = Math.sin(radiant);
		return new Position(x * cos + y * -sin, x * sin + y * cos);
	}

	/**
	 * Mesure la distance euclidienne entre 2 positions dans le référenciel de la
	 * fenêtre (entre 0 et 1).
	 * 
	 * @param p
	 * @return
	 */
	public double dist(Position p) {
		double a = x - p.x;
		double b = Math.pow(x - p.x, 2);
		double c = Math.pow(y - p.y, 2);
		double d = Math.sqrt(c + b);

		return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
	}

	/**
	 * @param x coordonnée x dans la grille
	 * @param y coordonnée y dans la grille
	 * @return Position, les coordonée dans l'espace de la fenêtre
	 * @note coordonnées compris entre 0 et 1
	 */
	public Position inFrameSpace() {
		return new Position(x / GRID_WIDTH, y / GRID_HEIGHT);
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
		double squareHeight = (double) 1 / GRID_HEIGHT;
		if (round)
			return new Position(Math.round((x - (x % squareWidth)) * GRID_WIDTH),
					Math.round((y - (y % squareHeight)) * GRID_HEIGHT));
		else
			return new Position(x * GRID_WIDTH, y * GRID_HEIGHT);
	}

	/**
	 * @return Rend l'angle du vecteur en degree
	 * @note Soit : (1, 0) -> 0 degree, (0, 1) -> 90 degree, (-1, 0) -> 180 degree,
	 *       (0, -1) -> 270 degree
	 */
	public double angle() {
		return Math.atan2(this.y, this.x) * 180 / Math.PI;
	}

	/**
	 * @param u vecteur a comparer
	 * @return Rend l'angle en degree entre les deux vecteur this et u
	 */
	public double angle(Position u) {
		return this.angle() - u.angle();
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
	public double norm() {
		Position zero = new Position(0, 0);
		return dist(zero);
	}

	/**
	 * @return rend le vecteur position mais normalisé (norme = 1)
	 */
	public Position normalized() {
		double norm = norm();
		return new Position(x / norm, y / norm);
	}

	/**
	 * Retourne la position du point sur l'axe des x et des y
	 */
	public String toString() {
		return "(" + Double.toString(x) + ", " + Double.toString(y) + ")";
	}
}
