package warcraftTD;

import warcraftTD.Bloons.Bloon;
import warcraftTD.Levels.Level;
import warcraftTD.Tiles.Empty;
import warcraftTD.Tiles.Obstructed;
import warcraftTD.Tiles.Tile;
import warcraftTD.Tiles.BuyTiles.BuyTile;
import warcraftTD.Tiles.Monkeys.Monkey;
import warcraftTD.Waves.Waves;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Iterator;

public class World {

	// l'ensemble des monstres
	List<Bloon> bloons = new ArrayList<Bloon>();

	// l'ensemble des tours
	List<Monkey> monkeys = new ArrayList<Monkey>();

	// l'ensemble des alert (pop-up textuel) utiliser pour les gains d'argent ou
	// améliorations de tours
	List<Alert> alerts = new ArrayList<Alert>();
	// l'objet d'alert général pour toutes les info importantes qui serons afficher
	// au milieu de l'écran
	Alert mainAlert;

	// Nombre de points de vie du joueur
	int life = 200;

	// Quantité d'argent
	int money = 600;

	// Le niveau actuel
	Level level;

	// La police du texte
	Font font;

	// Le gestionnaire de vagues
	Waves waves;

	// grille qui determine qu'est-ce que qu'il y a sur la carte
	// (ex : SingeXXX, SingeYYY, Rien, Route ou Arbre ou rochers, UI, etc...)
	Tile[][] map;

	// La tuile qui est actuelement selectionné peut être sois une BuyTile ou Monkey
	Tile selectedTile;

	boolean placing;

	// Information sur la taille du plateau de jeu
	int width;
	int height;
	int nbSquareX;
	int nbSquareY;
	double squareWidth;
	double squareHeight;

	// La vitesse à laquelle s'écoule le jeu
	double gameSpeed = 1.0;

	// Commande sur laquelle le joueur appuie (sur le clavier)
	char key;

	// Condition pour activer le mode DEBUG --> activation avec "D"
	boolean debug = true;

	// Condition pour terminer la partie
	boolean end = false;

	// compte le nombre de tps (tick per second) et fps (frames per second)
	int fps;
	int fpsCount = 0;
	long tpsTimerStart = System.nanoTime();
	long fpsTimerStart = System.nanoTime();
	final long TARGET_TPS = 1000000000 / 60; // on veut avoir 60 tps constant

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
		loadFont();

		this.mainAlert = new Alert(new Position(0.5, 0.6), 120, new Color(250, 250, 250, 255), this.font, 60, 0.1, 40);
		this.level = level;
		this.waves = new Waves(level.pathing, this.bloons);
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

	public void loadFont() {
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(Assets.font)))
					.deriveFont(Font.PLAIN, 24);
			this.font = font;
		} catch (Exception e) {
			System.err.println(e);
		}
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
	 * update tout les bloons de la liste sans les draw
	 */
	public void tickBloons() {
		ListIterator<Bloon> i = bloons.listIterator();
		Bloon b;
		while (i.hasNext()) {
			b = i.next();
			if (b.reached == true) { // kill on reach
				this.life -= b.power;
				i.remove();
			} else if (b.hp <= 0) { // remove on bloon death

				// Affiche une petite alert textuel pour montré le nombre d'argent gagné en
				// tuant le bloon
				Alert gainAlert = new Alert(b.pos, 100, new Color(225, 232, 21, 255), this.font, 60, 0.05, 20);
				gainAlert.add("+" + b.power + "$");
				alerts.add(gainAlert);
				money += b.power;

				// Supprime le bloon
				i.remove();
				for (Bloon toAdd : b.onDeath(bloons)) {
					i.add(toAdd);
				}
			}
			b.tick();
		}
	}

	/**
	 * draw tout les bloons
	 */
	public void tickAlerts() {
		mainAlert.tick();
		Iterator<Alert> i = alerts.iterator();
		Alert a;
		while (i.hasNext()) {
			a = i.next();
			if (a.isEmpty()) {
				i.remove();
			} else
				a.tick();
		}
	}

	/**
	 * Définit le décors du plateau de jeu.
	 */
	public void drawBackground() {
		// Draw level background
		double bgSize = (double) 1000 / ((double) 1240 * 2);
		StdDraw.picture(bgSize, 0.5, level.spritePath, (2 * bgSize), 1);

		// Draw right side panel
		double panelSize = (double) 240 / ((double) 1240 * 2);
		StdDraw.picture(1 - panelSize, 0.5, Assets.panel, panelSize * 2, 1);

	}

	/**
	 * Affiche le chemin que vas suivre les Bloons
	 */
	public void drawPath() {
		StdDraw.setPenColor(new Color(206, 255, 10, 128));
		StdDraw.setPenRadius(0.01);
		if (debug) {
			List<Position> pathing = level.pathing;
			for (int i = 0; i < pathing.size(); i++) {
				if (i + 1 < pathing.size()) {
					StdDraw.line(pathing.get(i).x, pathing.get(i).y, pathing.get(i + 1).x, pathing.get(i + 1).y);
				}
			}
		}
	}

	/**
	 * Affiche la grille de jeu quand on place un singe ou en mode DEBUG
	 */
	public void drawGrid() {
		if (debug || placing) {
			squareWidth = (double) 1 / nbSquareX;
			squareHeight = (double) 1 / nbSquareY;

			StdDraw.setPenRadius(0.001);
			Color line = new Color(0, 0, 0, 80);
			Color isPlacable = StdDraw.GREEN;
			Color isNotPlacable = StdDraw.RED;

			for (int y = 0; y < nbSquareY; y++) {
				for (int x = 0; x < nbSquareX; x++) {
					Position pos = inFrameSpace(x, y);
					Color squarreColor = map[x][y].gridColor;

					if (placing) {
						Position mouseGrid = inGridSpace(StdDraw.mouseX(), StdDraw.mouseY());
						if ((x == mouseGrid.x && y == mouseGrid.y) || (x + 1 == mouseGrid.x && y == mouseGrid.y)
								|| (x == mouseGrid.x && y + 1 == mouseGrid.y)
								|| (x - 1 == mouseGrid.x && y == mouseGrid.y)
								|| (x == mouseGrid.x && y - 1 == mouseGrid.y))
							squarreColor = ((BuyTile) selectedTile).toPlace.isPlacableAt(x, y, map) ? isPlacable
									: isNotPlacable;
					}
					// Draw : les lignes de la grille
					StdDraw.setPenColor(line);
					StdDraw.rectangle(pos.x, pos.y, squareWidth / 2, squareHeight / 2);

					// Draw : les cases de la grille avec la couleur qui dépend de la case
					StdDraw.setPenColor(squarreColor);
					StdDraw.filledRectangle(pos.x, pos.y, squareWidth / 2, squareHeight / 2);

				}
			}
		}
	}

	/**
	 * Affiche certaines informations sur l'écran telles que les points de vie du
	 * joueur ou son or. Tout ce qui est en relation avec l'interface
	 * 
	 * Affiche également les information de débugage en mode DEBUG
	 */
	public void drawInfos() {
		if (debug) { // ############ DEBUG DRAWING ############
			squareWidth = (double) 1 / nbSquareX;
			squareHeight = (double) 1 / nbSquareY;
			double alignLeft = 0.025;
			double mouseX = Math.round(StdDraw.mouseX() * 1000) / (double) 1000;
			double mouseY = Math.round(StdDraw.mouseY() * 1000) / (double) 1000;
			Position mouseGrid = inGridSpace(mouseX, mouseY);

			StdDraw.setFont(); // set default font
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.textLeft(alignLeft, 0.19, "Debug info : ");
			StdDraw.textLeft(alignLeft, 0.16, "FPS : " + fps);
			StdDraw.textLeft(alignLeft, 0.14, "Life : " + this.life);
			StdDraw.textLeft(alignLeft, 0.12, "Mouse Pos (In frame space) : " + mouseX + ", " + mouseY);
			StdDraw.textLeft(alignLeft, 0.10,
					"Mouse Pos (In grid space) : " + (int) mouseGrid.x + ", " + (int) mouseGrid.y);
			StdDraw.textLeft(alignLeft, 0.08, "On Mouse Tile : " + getMouseTile().getClass().getName());
			StdDraw.textLeft(alignLeft, 0.06, "Number of Bloons : " + bloons.size());
			StdDraw.textLeft(alignLeft, 0.04, "Number of Tower : " + monkeys.size());
		}

		// Constants
		final Color CAN_BUY = new Color(126, 217, 42, 255);
		final Color CANT_BUY = new Color(248, 46, 46, 255);
		final Color SHADOW = new Color(0, 0, 0, 150);
		final Color MAIN_TEXT = new Color(250, 250, 250, 255);
		final double SHADOW_OFFSET = 0.008;

		// ############ DRAWING TOWER PRICES ############

		font.deriveFont(20f); // font size
		StdDraw.setFont(font);
		int cost;

		// for the Dart monkey tower
		cost = ((BuyTile) map[26][12]).cost;
		StdDraw.setPenColor(SHADOW);
		StdDraw.text(0.868, 0.631 - SHADOW_OFFSET, cost + "$");
		StdDraw.setPenColor(cost <= money ? CAN_BUY : CANT_BUY);
		StdDraw.text(0.868, 0.631, cost + "$");

		// ############ Drawing life and money counter with shadow ############

		font = font.deriveFont(32f); // font size

		StdDraw.setFont(font);
		StdDraw.picture(0.03, 0.876, Assets.moneyLife);
		StdDraw.setPenColor(SHADOW);
		StdDraw.textLeft(0.054, 0.906 - SHADOW_OFFSET, money + "");
		StdDraw.textLeft(0.054, 0.846 - SHADOW_OFFSET, life + "");
		StdDraw.setPenColor(MAIN_TEXT);
		StdDraw.textLeft(0.054, 0.906, money + "");
		StdDraw.textLeft(0.054, 0.846, life + "");

		// ############ Draw play button ############
		if (!waves.isRunning()) {
			StdDraw.picture(0.76, 0.08, Assets.buttonPlay);
		}

		// ############ Draw speedup button ############
		StdDraw.picture(0.76, 0.92, gameSpeed == 1 ? Assets.buttonSpeedup0 : Assets.buttonSpeedup1);

	}

	/**
	 * Fonction qui récupère le positionnement de la souris et permet d'afficher une
	 * image de tour en temps réél lorsque le joueur appuie sur une des touches
	 * permettant la construction d'une tour.
	 */
	public void drawMouse() {
		if (selectedTile != null && selectedTile instanceof BuyTile) {
			Position mousePos = new Position(StdDraw.mouseX(), StdDraw.mouseY());
			String image = ((BuyTile) selectedTile).toPlace.sprite;
			if (image != null)
				StdDraw.picture(mousePos.x, mousePos.y, image, squareWidth, squareHeight);
		}
	}

	/**
	 * draw tout les bloons
	 */
	public void drawBloons() {
		Iterator<Bloon> i = bloons.iterator();
		Bloon m;
		while (i.hasNext()) {
			m = i.next();
			m.draw();
		}
	}

	/**
	 * draw toutes les tours
	 */
	public void drawMonkeys() {
		Iterator<Monkey> i = monkeys.iterator();
		Monkey m;
		while (i.hasNext()) {
			m = i.next();
			m.draw();
		}
	}

	/**
	 * draw toutes les alerts
	 */
	public void drawAlerts() {
		mainAlert.draw();
		Iterator<Alert> i = alerts.iterator();
		Alert a;
		while (i.hasNext()) {
			a = i.next();
			a.draw();
		}
	}

	/**
	 * Place la tour selectionnée
	 * 
	 * @param target est la tile sur laquelle on va placer la tour selectionnée
	 */
	private void placeMonkey(Tile target) {
		Monkey placed = ((BuyTile) selectedTile).toPlace;
		if (placed.isPlacableAt(target.x, target.y, map)) {
			placed.x = target.x;
			placed.y = target.y;
			placed.framePos = inFrameSpace(placed.x, placed.y);
			map[target.x][target.y] = placed;
			ArrayList<Integer[]> toBlock = placed.getOccupiedTiles();
			for (Integer[] coordinate : toBlock) {
				int cx = coordinate[0];
				int cy = coordinate[1];
				if (map[cx][cy] instanceof Empty)
					map[cx][cy] = new Obstructed(cx, cy);
			}
			money -= ((BuyTile) selectedTile).cost;
			((BuyTile) selectedTile).reset();
			this.monkeys.add(placed);
		}
		selectedTile = null;
		placing = false;
	}

	/**
	 * Récupère la touche appuyée par l'utilisateur et affiche les informations pour
	 * la touche séléctionnée
	 * 
	 * @param key la touche utilisée par le joueur
	 */
	public void keyPress(char key) {
		key = Character.toLowerCase(key);
		this.key = key;
		switch (key) {
			case 's':
				if (waves.isRunning())
					System.out.println("You can't start another one now, a wave is currently running!");
				else {
					waves.startNextWave();
				}
				break;
			case 'd':
				mainAlert.add("DEBUG MODE : " + (!debug ? "Activated" : "Desactivated"));
				debug = !debug;
				break;
			case 'k':
				for (Bloon b : bloons) {
					b.hp -= 1;
				}
				break;
			case 'l':
				for (Bloon b : bloons) {
					b.hp = 0;
				}
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
		Tile mouseTile = getMouseTile();
		if (placing) {
			placeMonkey(mouseTile);
		}
		if (mouseTile == selectedTile)
			return;
		selectedTile = mouseTile;
		if (selectedTile instanceof BuyTile)
			if (money < ((BuyTile) selectedTile).cost) {
				mainAlert.add("You don't have enough money to purchase this!");
				selectedTile = null;
			} else
				placing = true;
	}

	/**
	 * Met à jour toutes les informations du plateau de jeu ainsi que les
	 * déplacements des monstres et les attaques des tours. (Ne draw rien)
	 */
	public void tick() {
		this.waves.update();
		tickBloons();
		tickAlerts();
	}

	/**
	 * Affiche tout le contenu du jeu
	 */
	public void draw() {
		drawBackground();
		drawGrid();
		drawPath();
		drawInfos();
		drawBloons();
		drawMonkeys();
		drawMouse();
		drawAlerts();
		StdDraw.show();
	}

	/**
	 * Récupère la touche entrée au clavier ainsi que la position de la souris et
	 * met à jour le plateau en fonction de ces interractions
	 */
	public void run() {
		while (!end) {

			StdDraw.clear();

			while (tpsTimerStart - System.nanoTime() < TARGET_TPS / gameSpeed) {
				tpsTimerStart += TARGET_TPS / gameSpeed;
				tick();
			}
			draw();

			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}
			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(50);
			}

			// FPS Counter
			if (debug) {
				if (fpsTimerStart == 0 || System.nanoTime() - fpsTimerStart >= 1000000000) {
					fpsTimerStart = System.nanoTime();
					fps = fpsCount;
					fpsCount = 0;
				}
				fpsCount++;
			}
		}
		System.exit(0);
	}
}
