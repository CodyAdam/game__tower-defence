package warcraftTD;

import warcraftTD.Bloons.Bloon;
import warcraftTD.Levels.Level;
import warcraftTD.Projectiles.Projectile;
import warcraftTD.Tiles.ChangeTargetLeft;
import warcraftTD.Tiles.ChangeTargetRight;
import warcraftTD.Tiles.Empty;
import warcraftTD.Tiles.Obstructed;
import warcraftTD.Tiles.PlayButton;
import warcraftTD.Tiles.RemoveTower;
import warcraftTD.Tiles.SpeedupButton;
import warcraftTD.Tiles.Tile;
import warcraftTD.Tiles.UpgradeLeft;
import warcraftTD.Tiles.UpgradeRight;
import warcraftTD.Tiles.BuyTiles.BuyTile;
import warcraftTD.Tiles.Monkeys.Monkey;
import warcraftTD.Tiles.Monkeys.Monkey.Upgrade;
import warcraftTD.Waves.WaveManager;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Iterator;

public class World {

	List<Bloon> bloons = new ArrayList<Bloon>(); // l'ensemble des monstres
	List<Monkey> monkeys = new ArrayList<Monkey>(); // l'ensemble des tours
	List<Projectile> projectiles = new ArrayList<Projectile>(); // l'ensemble des tours
	List<Alert> alerts = new ArrayList<Alert>(); // l'ensemble des alert (pop-up textuel) utiliser pour les gains
													// d'argent ou
													// améliorations de tours
	Alert mainAlert; // l'objet d'alert général pour toutes les info importantes qui serons afficher
						// au milieu de l'écran
	WaveManager waves;// Le gestionnaire de vagues
	Level level;// Le niveau actuel

	int life = 150;// Nombre de points de vie du joueur
	int money = 650;// Quantité d'argent
	Font font;// La police du texte

	// grille qui determine qu'est-ce que qu'il y a sur la carte
	// (ex : SingeXXX, SingeYYY, Rien, Route ou Arbre ou rochers, UI, etc...)
	Tile[][] map;
	Tile selectedTile;// La tuile qui est actuelement selectionné peut être sois une BuyTile ou Monkey

	// Information sur la taille du plateau de jeu
	int width;
	int height;
	int nbSquareX;
	int nbSquareY;
	double squareWidth;
	double squareHeight;

	double gameSpeed = 1;// La vitesse à laquelle s'écoule le jeu
	char key;// Commande sur laquelle le joueur appuie (sur le clavier)
	boolean placing; // Est-ce que le joueur est en train de placer une tour ?
	boolean debug = false;// Condition pour activer le mode DEBUG --> activation avec "D"
	boolean end = false;// Condition pour terminer la partie
	boolean clicking = false; // Evite le click à répétition quand on reste appuyer

	// compte le nombre de tps (tick per second) et fps (frames per second)
	int fps;
	int fpsCount = 0;
	long tpsTimerStart = System.nanoTime();
	long fpsTimerStart = System.nanoTime();
	long tpsCounter = 0;
	final long TARGET_TPS = 1000000000 / 60; // on veut avoir 60 tps constant (sauf quand le jeu est accéléré)

	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de
	 * cases données
	 * 
	 * @param level  le niveau à chargé
	 * @param width  largeur de la taille de la fenêtre
	 * @param height hauteur de la taille de la fenêtre
	 */
	public World(Level level, int width, int height) {
		loadFont();

		this.mainAlert = new Alert(new Position(0.5, 0.6), 120, new Color(250, 250, 250, 255), this.font, 60, 0.1, 40);
		this.level = level;
		this.waves = new WaveManager(level.pathing, this.bloons);
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
	 * Charge la police du jeu
	 */
	public void loadFont() {
		try {
			URL url = StdDraw.class.getResource(Assets.font);
			Font font = Font.createFont(Font.TRUETYPE_FONT, url.openStream()).deriveFont(Font.PLAIN, 24);
			this.font = font;
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * @return la Tile de la grille qui se trouve à la position de la souris
	 */
	public Tile getMouseTile() {

		Position pos = new Position(StdDraw.mouseX(), StdDraw.mouseY()).inGridSpace();
		pos.x = Math.max(0, Math.min(nbSquareX - 1, pos.x)); // Clamp value pour évité IndexOutOfBounds
		pos.y = Math.max(0, Math.min(nbSquareY - 1, pos.y)); // Clamp value pour évité IndexOutOfBounds
		return map[(int) pos.x][(int) pos.y];
	}

	/**
	 * Update tous les bloons de la liste sans les afficher
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
				gainAlert.add("+" + b.money + "$");
				alerts.add(gainAlert);
				money += b.money;

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
	 * Update toutes les tours de la liste sans les afficher
	 */
	public void tickMonkeys() {
		ListIterator<Monkey> i = monkeys.listIterator();
		Monkey m;
		while (i.hasNext()) {
			m = i.next();
			m.tick(bloons, projectiles);
		}
	}

	/**
	 * Update tous les projectiles sans les afficher
	 */
	public void tickProjectiles() {
		ListIterator<Projectile> i = projectiles.listIterator();
		Projectile p;
		while (i.hasNext()) {
			p = i.next();
			if (p.remove)
				i.remove();
			p.tick(bloons);
		}
	}

	/**
	 * Update toutes les alertes sans les afficher
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
	 * Affiche le décors du plateau de jeu. Soit : le background et le panel de
	 * droite
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
	 * Affiche le chemin que vas suivre les Bloons avec une petite animation.
	 * Affiche uniquement en mode Debug
	 */
	public void drawPath() {
		if (debug || !waves.hasStarted()) {
			final double PATH_RADIUS = 0.01;
			final int ANIMATION_DELAY = 10;

			List<Position> pathing = level.pathing;
			StdDraw.setPenRadius(PATH_RADIUS);
			for (int i = 0; i < pathing.size() - 1; i++) {
				Position current = pathing.get(i);
				Position next = pathing.get(i + 1);

				double x = Math.sin((i - (double) tpsCounter) / ANIMATION_DELAY) / 2 + 0.5;
				if (next.bool)
					StdDraw.setPenColor(new Color(245, 241, 34, 50 + (int) (x * 205)));
				else
					StdDraw.setPenColor(new Color(95, 50, 50, 50 + (int) (x * 205)));
				StdDraw.line(current.x, current.y, next.x, next.y);
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
			Color isPlacable = new Color(0, 255, 0, 150);
			Color isNotPlacable = new Color(255, 0, 0, 150);

			for (int y = 0; y < nbSquareY; y++) {
				for (int x = 0; x < nbSquareX; x++) {
					Position pos = new Position(x, y).inFrameSpace()
							.plus(new Position(squareWidth / 2, squareHeight / 2));
					Color squarreColor = map[x][y].gridColor;

					if (placing) {
						Position mouseGrid = new Position(StdDraw.mouseX(), StdDraw.mouseY()).inGridSpace();
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
	 * Affiche également les information de débuguage en mode DEBUG
	 */
	public void drawInfos() {
		// Constants
		final Color CAN_BUY = new Color(92, 255, 84, 255);
		final Color CANT_BUY = new Color(248, 46, 46, 255);
		final Color SHADOW = new Color(0, 0, 0, 150);
		final Color BORDER = new Color(51, 46, 43, 255);
		final Color ULTIMATE_UPGRADE = new Color(122, 17, 114, 100);
		final Color MAIN_TEXT = new Color(245, 245, 235, 255);
		final double SHADOW_OFFSET = 0.008;

		// ############ Draw play button ############

		if (!waves.isRunning() || debug) {
			StdDraw.picture(0.764, 0.071d, Assets.buttonPlay);
		}

		// ############ Draw speedup button ############

		StdDraw.picture(0.764, 0.929, gameSpeed == 1 ? Assets.buttonSpeedup0 : Assets.buttonSpeedup1);

		// ############ Draw tower prices ############

		font = font.deriveFont(20f); // font size
		StdDraw.setFont(font);
		int cost;

		// ############ Drawing wave, life and money counter with shadow ############

		font = font.deriveFont(32f); // font size
		StdDraw.setFont(font);

		StdDraw.picture(0.03, 0.876, Assets.moneyLife);
		StdDraw.setPenColor(SHADOW);
		StdDraw.textLeft(0.017, 0.966 - SHADOW_OFFSET, waves.getName());
		StdDraw.textLeft(0.054, 0.906 - SHADOW_OFFSET, money + "");
		StdDraw.textLeft(0.054, 0.846 - SHADOW_OFFSET, life + "");
		StdDraw.setPenColor(MAIN_TEXT);
		StdDraw.textLeft(0.017, 0.966, waves.getName());
		StdDraw.textLeft(0.054, 0.906, money + "");
		StdDraw.textLeft(0.054, 0.846, life + "");

		// for the Dart monkey tower
		cost = ((BuyTile) map[26][12]).cost;
		StdDraw.setPenColor(SHADOW);
		StdDraw.text(0.868, 0.631 - SHADOW_OFFSET, cost + "$");
		StdDraw.setPenColor(cost <= money ? CAN_BUY : CANT_BUY);
		StdDraw.text(0.868, 0.631, cost + "$");

		// ############ Draw tower upgrades in the top right corner ############

		if (selectedTile instanceof Monkey) {
			font = font.deriveFont(26f); // font size
			StdDraw.setFont(font);
			Monkey m = (Monkey) selectedTile;
			StdDraw.setPenColor(SHADOW);
			StdDraw.text(0.905, 0.957 - SHADOW_OFFSET / 3, "UPGRADES");
			StdDraw.setPenColor(MAIN_TEXT);
			StdDraw.text(0.905, 0.957, "UPGRADES");

			double maxW = 0.05;
			double h = 0.006;
			double w = (double) maxW / m.leftUpgrades.size();
			double alignY = 0.8725;

			Tile tileOnMouse = getMouseTile();
			Upgrade upgrade = m.getNextUpgrade(true);

			font = font.deriveFont(15f); // font size
			StdDraw.setFont(font);
			if (tileOnMouse instanceof UpgradeLeft && upgrade != null) {
				StdDraw.setPenColor(SHADOW);
				StdDraw.textRight(0.795, StdDraw.mouseY() - SHADOW_OFFSET / 3, upgrade.description);
				StdDraw.textRight(0.795, StdDraw.mouseY() - SHADOW_OFFSET / 3 - 0.025, upgrade.description2);
				StdDraw.setPenColor(MAIN_TEXT);
				StdDraw.textRight(0.795, StdDraw.mouseY(), upgrade.description);
				StdDraw.textRight(0.795, StdDraw.mouseY() - 0.025, upgrade.description2);
			} else if (tileOnMouse instanceof UpgradeRight && m.getNextUpgrade(false) != null) {
				StdDraw.setPenColor(BORDER);
				StdDraw.textRight(0.795, StdDraw.mouseY() - SHADOW_OFFSET / 3, m.getNextUpgrade(false).description);
				StdDraw.textRight(0.795, StdDraw.mouseY() - SHADOW_OFFSET / 3 - 0.025,
						m.getNextUpgrade(false).description2);
				StdDraw.setPenColor(MAIN_TEXT);
				StdDraw.textRight(0.795, StdDraw.mouseY(), m.getNextUpgrade(false).description);
				StdDraw.textRight(0.795, StdDraw.mouseY() - 0.025, m.getNextUpgrade(false).description2);
			}
			font = font.deriveFont(16f); // font size
			StdDraw.setFont(font);
			// LEFT UPGRADE
			double alignX = 0.865;
			StdDraw.picture(alignX, alignY + 0.016, Assets.upgradeButton);
			for (int i = 0; i < m.leftUpgrades.size(); i++) {
				if (i == m.leftUpgrades.size() - 1)
					StdDraw.setPenColor(i < m.leftUpgrade ? CAN_BUY : ULTIMATE_UPGRADE);
				else
					StdDraw.setPenColor(i < m.leftUpgrade ? CAN_BUY : SHADOW);
				StdDraw.filledRectangle(alignX - (maxW / m.leftUpgrades.size()) + i * w, alignY - 0.02,
						(w / 2) - 0.0005, h);
				StdDraw.setPenColor(BORDER);
				StdDraw.setPenRadius(0.004);
				StdDraw.rectangle(alignX - (maxW / m.leftUpgrades.size()) + i * w, alignY - 0.02, (w / 2) - 0.0005, h);
			}

			if (upgrade != null) {
				StdDraw.setPenColor(SHADOW);
				StdDraw.text(alignX, alignY + 0.05 - SHADOW_OFFSET / 3, upgrade.name);
				StdDraw.text(alignX, alignY + 0.027 - SHADOW_OFFSET / 3, upgrade.name2);
				StdDraw.text(alignX, alignY - SHADOW_OFFSET / 3, upgrade.price + "$");
				StdDraw.setPenColor(upgrade.price <= money ? CAN_BUY : CANT_BUY);
				StdDraw.text(alignX, alignY + 0.05, upgrade.name);
				StdDraw.text(alignX, alignY + 0.027, upgrade.name2);
				StdDraw.text(alignX, alignY, upgrade.price + "$");
			} else {
				StdDraw.setPenColor(SHADOW);
				StdDraw.text(alignX, 0.914 - SHADOW_OFFSET / 3, "Already");
				StdDraw.text(alignX, 0.89 - SHADOW_OFFSET / 3, "max!");
				StdDraw.setPenColor(CANT_BUY);
				StdDraw.text(alignX, 0.914, "Already");
				StdDraw.text(alignX, 0.89, "max!");
			}

			// RIGHT UPGRADE
			alignX = 0.940;
			upgrade = m.getNextUpgrade(false);
			StdDraw.picture(alignX, alignY + 0.016, Assets.upgradeButton);
			for (int i = 0; i < m.rightUpgrades.size(); i++) {
				if (i == m.rightUpgrades.size() - 1)
					StdDraw.setPenColor(i < m.rightUpgrade ? CAN_BUY : ULTIMATE_UPGRADE);
				else
					StdDraw.setPenColor(i < m.rightUpgrade ? CAN_BUY : SHADOW);
				StdDraw.filledRectangle(alignX - (maxW / m.rightUpgrades.size()) + i * w, alignY - 0.02,
						(w / 2) - 0.0005, h);
				StdDraw.setPenColor(BORDER);
				StdDraw.setPenRadius(0.004);
				StdDraw.rectangle(alignX - (maxW / m.rightUpgrades.size()) + i * w, alignY - 0.02, (w / 2) - 0.0005, h);
			}
			if (upgrade != null) {
				StdDraw.setPenColor(SHADOW);
				StdDraw.text(alignX, alignY + 0.05 - SHADOW_OFFSET / 3, upgrade.name);
				StdDraw.text(alignX, alignY + 0.027 - SHADOW_OFFSET / 3, upgrade.name2);
				StdDraw.text(alignX, alignY - SHADOW_OFFSET / 3, upgrade.price + "$");
				StdDraw.setPenColor(upgrade.price <= money ? CAN_BUY : CANT_BUY);
				StdDraw.text(alignX, alignY + 0.05, upgrade.name);
				StdDraw.text(alignX, alignY + 0.027, upgrade.name2);
				StdDraw.text(alignX, alignY, upgrade.price + "$");
			} else {
				StdDraw.setPenColor(SHADOW);
				StdDraw.text(alignX, 0.914 - SHADOW_OFFSET / 3, "Already");
				StdDraw.text(alignX, 0.89 - SHADOW_OFFSET / 3, "max!");
				StdDraw.setPenColor(CANT_BUY);
				StdDraw.text(alignX, 0.914, "Already");
				StdDraw.text(alignX, 0.89, "max!");
			}

			// DRAW TARGETING INDICATOR
			alignX = 0.904;
			alignY = 0.79;
			StdDraw.picture(alignX - 0.052, alignY + 0.012, Assets.leftArrow);
			StdDraw.picture(alignX + 0.052, alignY + 0.012, Assets.rightArrow);
			font = font.deriveFont(19f); // font size
			StdDraw.setFont(font);
			StdDraw.setPenColor(SHADOW);
			StdDraw.text(alignX, alignY + 0.025 - SHADOW_OFFSET / 4, "TARGET");
			StdDraw.text(alignX, alignY - SHADOW_OFFSET / 4, m.getTargetingMode());
			StdDraw.setPenColor(MAIN_TEXT);
			StdDraw.text(alignX, alignY + 0.025, "TARGET");
			StdDraw.setPenColor(MAIN_TEXT);
			StdDraw.text(alignX, alignY, m.getTargetingMode());

			// DRAW REMOVE TOWER BUTTON
			StdDraw.picture(0.985, 0.972, Assets.removeButton);
		}

		// ############ Draw DEBUG ############
		if (debug) {
			squareWidth = (double) 1 / nbSquareX;
			squareHeight = (double) 1 / nbSquareY;
			final double ALIGN_LEFT = 0.02;
			double mouseX = Math.round(StdDraw.mouseX() * 1000) / (double) 1000;
			double mouseY = Math.round(StdDraw.mouseY() * 1000) / (double) 1000;
			Position mouseGrid = new Position(mouseX, mouseY).inGridSpace();

			StdDraw.setFont(); // set default font
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.textLeft(ALIGN_LEFT, 0.21, "Debug info : ");
			StdDraw.textLeft(ALIGN_LEFT, 0.18, "FPS : " + fps);
			StdDraw.textLeft(ALIGN_LEFT, 0.16, "On mouse Tile : " + getMouseTile().getClass().getName());
			StdDraw.textLeft(ALIGN_LEFT, 0.14, "Game speed : " + this.gameSpeed);
			StdDraw.textLeft(ALIGN_LEFT, 0.12, "Mouse pos (in frame space) : " + mouseX + ", " + mouseY);
			StdDraw.textLeft(ALIGN_LEFT, 0.10,
					"Mouse Pos (in grid space) : " + (int) mouseGrid.x + ", " + (int) mouseGrid.y);
			StdDraw.textLeft(ALIGN_LEFT, 0.08, "Number of bloons : " + bloons.size());
			StdDraw.textLeft(ALIGN_LEFT, 0.06, "Number of towers : " + monkeys.size());
			StdDraw.textLeft(ALIGN_LEFT, 0.04, "Number of projetiles : " + projectiles.size());
		} else {
			StdDraw.setPenColor(SHADOW);
			StdDraw.setFont(); // set default font
			StdDraw.textLeft(0.02, 0.04, "Press 'D' to enter DEBUG MODE");
		}
	}

	/**
	 * Fonction qui récupère le positionnement de la souris et permet d'afficher une
	 * image de tour en temps réél lorsque le joueur appuie sur une des touches
	 * permettant la construction d'une tour.
	 */
	public void drawMouse() {

		if (selectedTile != null && selectedTile instanceof BuyTile) {
			// Affiche la tour sur la souris quand on veux la placer ainsi que sa portée
			Position mousePos = new Position(StdDraw.mouseX(), StdDraw.mouseY());
			Monkey m = ((BuyTile) selectedTile).toPlace;
			String image = m.sprite;

			// Affiche la tour
			if (image != null)
				StdDraw.picture(mousePos.x, mousePos.y, image);

			// Affiche la portée
			Position range = new Position(m.range, m.range).inFrameSpace();
			StdDraw.setPenRadius(0.01);
			StdDraw.setPenColor(new Color(252, 3, 65, 110));
			StdDraw.ellipse(mousePos.x, mousePos.y, range.x, range.y);
			StdDraw.setPenColor(new Color(252, 3, 65, 60));
			StdDraw.filledEllipse(mousePos.x, mousePos.y, range.x, range.y);
		}
	}

	/**
	 * draw tous les bloons
	 */
	public void drawBloons() {
		Iterator<Bloon> i = bloons.iterator();
		Bloon m;
		while (i.hasNext()) {
			m = i.next();
			m.draw(debug);
		}
	}

	/**
	 * draw tous les projectiles
	 */
	public void drawProjectiles() {
		Iterator<Projectile> i = projectiles.iterator();
		Projectile p;
		while (i.hasNext()) {
			p = i.next();
			p.draw(debug);
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
			if (m == selectedTile)
				continue;
			m.draw(selectedTile);
		}
		if (selectedTile instanceof Monkey)
			((Monkey) selectedTile).draw(selectedTile);
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
			placed.pos = new Position(placed.x, placed.y).inFrameSpace()
					.plus(new Position(squareWidth / 2, squareHeight / 2));
			map[target.x][target.y] = placed;
			ArrayList<Integer[]> toBlock = placed.getAdjacent(); // liste des cases qui deviendront occupé
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
	 * Supprime du jeu la tour entrée en paramètre
	 * 
	 * @param target la tour à supprimer
	 */
	private void removeMonkey(Monkey target) {
		// On clear les cases sur la grille qui était occupée par cette tour
		ArrayList<Integer[]> toClear = target.getAdjacent(); // liste des cases qui deviendront vide
		map[target.x][target.y] = new Empty(target.x, target.y);
		for (Integer[] coordinate : toClear) {
			int cx = coordinate[0];
			int cy = coordinate[1];
			if (map[cx][cy] instanceof Obstructed)
				map[cx][cy] = new Empty(cx, cy);
		}

		// on re-donne au joueur 2/3 du prix d'origine de placement
		money += (int) (2 * target.cost / 3);
		Alert gainMoney = new Alert(target.pos, 100, new Color(225, 232, 21, 255), this.font, 60, 0.05, 20);
		gainMoney.add("+" + (int) (2 * target.cost / 3) + "$");
		alerts.add(gainMoney);

		// on suprime la tour du jeu
		monkeys.remove(selectedTile);
		selectedTile = null;
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
			case 'd':
				mainAlert.add("DEBUG MODE : " + (!debug ? "Activated" : "Desactivated"));
				debug = !debug;
				break;
			case 'k':
				if (debug) {
					for (Bloon b : bloons) {
						b.hp = 0;
					}
				}
				break;
			case 'm':
				if (debug) {
					Alert gainAlert = new Alert(new Position(StdDraw.mouseX(), StdDraw.mouseY()), 100,
							new Color(225, 232, 21, 255), this.font, 60, 0.05, 20);
					gainAlert.add("+1000$");
					alerts.add(gainAlert);
					money += 1000;
				}
				break;
			case 'b':
				double mouseX = Math.round(StdDraw.mouseX() * 1000) / (double) 1000;
				double mouseY = Math.round(StdDraw.mouseY() * 1000) / (double) 1000;
				System.out.println("pathing.add(new Position(" + mouseX + ", " + mouseY + "));");
				break;
			case 'q':
				exit();
				break;
		}
	}

	/**
	 * Fini la boucle de jeu de cette instance
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
		if (clicking == false) {
			clicking = true;
			Tile mouseTile = getMouseTile();
			if (placing) {
				placeMonkey(mouseTile);
			}
			if (selectedTile instanceof Monkey) { // Interaction avec l'interface d'amélioration
				Monkey m = (Monkey) selectedTile;
				if (mouseTile instanceof ChangeTargetLeft) { // clicked on change targeting to next
					m.prevTargetingMode();
					return;
				} else if (mouseTile instanceof ChangeTargetRight) { // clicked on change targeting to previous
					m.nextTargetingMode();
					return;
				} else if (mouseTile instanceof RemoveTower) { // clicked on remove tower
					removeMonkey(m);
					return;
				} else if (mouseTile instanceof UpgradeRight) { // clicked on upgrade left side tower
					Upgrade up = m.getNextUpgrade(false);
					if (up != null)
						if (up.price <= money) {
							money -= up.price;
							mainAlert.add("Upgraded : " + up.name + " " + up.name2);
							m.upgrade(false);
						} else {
							mainAlert.add("You don't have enough money to purchase this!");
						}
					return;
				} else if (mouseTile instanceof UpgradeLeft) { // clicked on upgrade right side tower
					Upgrade up = m.getNextUpgrade(true);
					if (up != null)
						if (up.price <= money) {
							money -= up.price;
							mainAlert.add("Upgraded : " + up.name + " " + up.name2);
							m.upgrade(true);
						} else {
							mainAlert.add("You don't have enough money to purchase this!");
						}
					return;
				}
			}
			selectedTile = mouseTile;
			if (selectedTile instanceof BuyTile)
				if (money < ((BuyTile) selectedTile).cost) {
					mainAlert.add("You don't have enough money to purchase this!");
					selectedTile = null;
				} else
					placing = true;
			else if (selectedTile instanceof PlayButton) {
				if (!debug && waves.isRunning())
					mainAlert.add("You can't start another wave now!");
				else {
					if (debug && waves.isRunning())
						mainAlert.add("DEBUG : Current wave skipped!");
					waves.startNextWave();
					mainAlert.add(waves.getName() + " has started!");
				}
			} else if (selectedTile instanceof SpeedupButton) {
				gameSpeed = gameSpeed == 1 ? 2.5 : 1;
			}
		}
	}

	/**
	 * Met à jour toutes les informations du plateau de jeu ainsi que les
	 * déplacements des monstres et les attaques des tours. (Ne draw rien)
	 */
	public void tick() {
		waves.update();
		tickBloons();
		tickProjectiles();
		tickMonkeys();
		tickAlerts();
	}

	/**
	 * Affiche tout le contenu du jeu
	 */
	public void draw() {
		// arriere plan
		drawBackground();
		drawGrid();
		drawPath();

		// second plan
		drawProjectiles();
		drawMonkeys();
		drawBloons();

		// premier plan
		drawInfos();
		drawMouse();
		drawAlerts();

		// Affiche le tout
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
				tpsCounter++;
				tick();
			}
			draw();

			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}
			if (StdDraw.isMousePressed())
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
			else
				clicking = false;

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
