package warcraftTD;

import warcraftTD.Bloons.Bloon;
import warcraftTD.Levels.Level;
import warcraftTD.Tiles.Tile;
import warcraftTD.Waves.Waves;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class World {
	// l'ensemble des monstres, pour gerer (notamment) l'affichage
	List<Bloon> bloons = new ArrayList<Bloon>();

	// Répertorie les chemins de tout les sprites du jeu
	Map<String, String> sprites;

	// Le niveau actuel
	Level level;

	// Les vagues de Bloons
	Waves waves;

	// grille qui determine qu'est-ce que qu'il y a sur la carte à sa création
	// (ex : SingeX, SingeY, Rien, Route, Arbre et rochers)
	Tile[][] map;

	// Information sur la taille du plateau de jeu
	int width;
	int height;
	int nbSquareX;
	int nbSquareY;
	double squareWidth;
	double squareHeight;

	// Nombre de points de vie du joueur
	int life = 200;

	// Commande sur laquelle le joueur appuie (sur le clavier)
	char key;

	// Condition pour activer le mode DEBUG --> activation avec "D"
	boolean debug = true;

	// Condition pour terminer la partie
	boolean end = false;

	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de
	 * cases données
	 * 
	 * @param width
	 * @param height
	 * @param nbSquareX
	 * @param nbSquareY
	 * @param startSquareX
	 * @param startSquareY
	 */
	public World(Level level, int width, int height) {
		sprites = new HashMap<String, String>();
		sprites.put("test", "/images/hud.png");
		sprites.put("hud", "/images/hud.png");

		this.level = level;
		this.waves = new Waves(level.spawn, this.bloons);
		this.map = level.map;

		this.width = width;
		this.height = height;
		this.nbSquareX = level.nbSquareX;
		this.nbSquareY = level.nbSquareY;
		this.squareWidth = (double) 1 / nbSquareX;
		this.squareHeight = (double) 1 / nbSquareY;
		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
	}

	/**
	 * @param x coordonnée x dans la grille
	 * @param y coordonnée y dans la grille
	 * @return Position, les coordonée dans l'espace de la fenêtre
	 * @note coordonnées compris entre 0 et 1
	 */
	public Position inFrameSpace(double x, double y) {
		squareWidth = (double) 1 / nbSquareX;
		squareHeight = (double) 1 / nbSquareY;
		return new Position(x * squareWidth + squareWidth / 2, y * squareHeight + squareHeight / 2);
	}

	/**
	 * @param x coordonnée x dans la fenêtre
	 * @param y coordonnée y dans la fenêtre
	 * @return Position, les coordonée dans l'espace de la grille
	 * @note coordonnées compris entre 0 et taille max de la grille horizontalement
	 *       puis verticalement
	 */
	public Position inGridSpace(double x, double y) {
		squareWidth = (double) 1 / nbSquareX;
		squareHeight = (double) 1 / nbSquareY;

		x = Math.round((x - x % squareWidth) / squareWidth);
		y = Math.round((y - y % squareHeight) / squareHeight);

		return new Position(x, y);
	}

	/**
	 * @return la Tile de la grille qui se trouve à la position de la souris
	 */
	public Tile getMouseTile() {
		Position pos = inGridSpace(StdDraw.mouseX(), StdDraw.mouseY());
		pos.x = Math.max(0, Math.min(nbSquareX - 1, pos.x)); // Clamp value pour évité IndexOutOfBounds
		pos.y = Math.max(0, Math.min(nbSquareY - 1, pos.y)); // Clamp value pour évité IndexOutOfBounds
		return map[(int) pos.x][(int) pos.y];
	}

	/**
	 * Définit le décors du plateau de jeu.
	 */
	public void drawBackground() {
		int widthBackground = 1000;
		StdDraw.picture((double) widthBackground / (2 * width), 0.5, level.spritePath, (double) widthBackground / width,
				1);
		int xPosHud = 960;
		int widthHud = 280;
		StdDraw.picture((double) xPosHud / width + (double) widthHud / (2 * width), 0.5, sprites.get("hud"),
				(double) widthHud / width, 1);
	}

	/**
	 * Affiche le chemin que vas suivre les Bloons
	 */
	public void drawPath() {
		StdDraw.setPenColor(new Color(206, 255, 10, 128));
		StdDraw.setPenRadius(0.01);
		if (debug) {
			Position[] pathing = level.pathing;
			for (int i = 0; i < pathing.length; i++) {
				if (i + 1 < pathing.length) {
					StdDraw.line(pathing[i].x, pathing[i].y, pathing[i + 1].x, pathing[i + 1].y);
				}
			}
		}
	}

	/**
	 * Affiche la grille de jeu quand on place un singe ou en mode DEBUG
	 */
	public void drawGrid() {
		if (debug) {
			squareWidth = (double) 1 / nbSquareX;
			squareHeight = (double) 1 / nbSquareY;

			StdDraw.setPenRadius(0.001);
			Color line = new Color(0, 0, 0, 80);

			for (int y = 0; y < nbSquareY; y++) {
				for (int x = 0; x < nbSquareX; x++) {
					Position pos = inFrameSpace(x, y);

					// Draw : les lignes de la grille
					StdDraw.setPenColor(line);
					StdDraw.rectangle(pos.x, pos.y, squareWidth / 2, squareHeight / 2);

					// Draw : les cases de la grille avec la couleur qui dépend de la case
					StdDraw.setPenColor(map[x][y].gridColor);
					StdDraw.filledRectangle(pos.x, pos.y, squareWidth / 2, squareHeight / 2);
				}
			}
		}
	}

	/**
	 * Affiche certaines informations sur l'écran telles que les points de vie du
	 * joueur ou son or
	 * 
	 * Affiche également les information de débugage en mode DEBUG
	 */
	public void drawInfos() {
		if (debug) {
			squareWidth = (double) 1 / nbSquareX;
			squareHeight = (double) 1 / nbSquareY;
			double alignLeft = 0.025;
			double mouseX = Math.round(StdDraw.mouseX() * 1000) / (double) 1000;
			double mouseY = Math.round(StdDraw.mouseY() * 1000) / (double) 1000;
			Position mouseGrid = inGridSpace(mouseX, mouseY);

			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.textLeft(alignLeft, 0.13, "Debug info : ");
			StdDraw.textLeft(alignLeft, 0.10, "Mouse Pos (In frame) : " + mouseX + ",d " + mouseY);
			StdDraw.textLeft(alignLeft, 0.08, "Mouse Pos (In grid) : " + (int) mouseGrid.x + ", " + (int) mouseGrid.y);
			StdDraw.textLeft(alignLeft, 0.06, "Mouse Tile : " + getMouseTile().getClass().getName());
		}
	}

	/**
	 * Fonction qui récupère le positionnement de la souris et permet d'afficher une
	 * image de tour en temps réél lorsque le joueur appuie sur une des touches
	 * permettant la construction d'une tour.
	 */
	public void drawMouse() {
		double normalizedX = (int) (StdDraw.mouseX() / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int) (StdDraw.mouseY() / squareHeight) * squareHeight + squareHeight / 2;
		String image = null;
		switch (key) {
			case 'a':
				image = sprites.get("test");
				break;
			case 'b':
				image = sprites.get("test");
				break;
		}
		if (image != null)
			StdDraw.picture(normalizedX, normalizedY, image, squareWidth, squareHeight);
	}

	/**
	 * Pour chaque monstre de la liste de monstres de la vague, utilise la fonction
	 * update() qui appelle les fonctions run() et draw() de Monster. Modifie la
	 * position du monstre au cours du temps à l'aide du paramètre nextP.
	 */
	public void updateBloons() {
		this.waves.update();

		Iterator<Bloon> i = bloons.iterator();
		Bloon m;
		while (i.hasNext()) {
			m = i.next();
			m.update();
			if (m.p.y < 0) {
				m.p.y = 1;
			}
		}
	}

	/**
	 * Met à jour toutes les informations du plateau de jeu ainsi que les
	 * déplacements des monstres et les attaques des tours.
	 * 
	 * @return les points de vie restants du joueur
	 */
	public int update() {
		drawBackground();
		drawGrid();
		drawPath();
		drawInfos();
		updateBloons();
		drawMouse();
		return life;
	}

	/**
	 * Récupère la touche appuyée par l'utilisateur et affiche les informations pour
	 * la touche séléctionnée
	 * 
	 * @param key la touche utilisée par le joueur
	 */
	public void keyPress(char key) {
		double normalizedX = (int) (StdDraw.mouseX() / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int) (StdDraw.mouseY() / squareHeight) * squareHeight + squareHeight / 2;

		key = Character.toLowerCase(key);
		this.key = key;
		switch (key) {
			case 'a':
				System.out.println("Arrow Tower selected (50g).");
				break;
			case 'h':
				System.out.println(
						String.format("obstructed[%s][%s] = true;", (int) (inGridSpace(normalizedX, normalizedY).x),
								(int) (inGridSpace(normalizedX, normalizedY).y)));
				break;
			case 'b':
				System.out.println("Bomb Tower selected (60g).");
				break;
			case 'e':
				System.out.println("Evolution selected (40g).");
				break;
			case 's':
				System.out.println("Starting game!");
				this.waves.startNextWave();
				break;
			case 'd':
				System.out.println("Debug mode toggled!");
				debug = !debug;
				break;
			case 'q':
				System.out.println("Exiting.");
				exit();
				break;
		}
	}

	/**
	 * Ferme le jeu
	 */
	public void exit() {
		end = true;
	}

	/**
	 * Vérifie lorsque l'utilisateur clique sur sa souris qu'il peut: - Ajouter une
	 * tour à la position indiquée par la souris. - Améliorer une tour existante.
	 * Puis l'ajouter à la liste des tours
	 * 
	 * @param x
	 * @param y
	 */
	public void mouseClick(double x, double y) {
		// double normalizedX = (int) (x / squareWidth) * squareWidth + squareWidth / 2;
		// double normalizedY = (int) (y / squareHeight) * squareHeight + squareHeight /
		// 2;
		// Position p = new Position(normalizedX, normalizedY);
		switch (key) {
			case 'a':
				System.out.println("il faut ajouter une tour d'archers si l'utilisateur à de l'or !!");
				break;
			case 'b':
				System.out.println("Ici il faut ajouter une tour de bombes");
				break;
			case 'e':
				System.out.println("Ici il est possible de faire évolué une des tours");
				break;
		}
	}

	/**
	 * Comme son nom l'indique, cette fonction permet d'afficher dans le terminal
	 * les différentes possibilités offertes au joueur pour intéragir avec le
	 * clavier
	 */
	public void printCommands() {
		System.out.println("Press A to select Arrow Tower (cost 50g).");
		System.out.println("Press B to select Cannon Tower (cost 60g).");
		System.out.println("Press E to update a tower (cost 40g).");
		System.out.println("Click on the grass to build it.");
		System.out.println("Press S to start.");
	}

	/**
	 * Récupère la touche entrée au clavier ainsi que la position de la souris et
	 * met à jour le plateau en fonction de ces interractions
	 */
	public void run() {
		printCommands();
		while (!end) {

			StdDraw.clear();
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}

			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(50);
			}

			update();
			StdDraw.show();
			StdDraw.pause(20);
		}
		System.exit(0);
	}
}
