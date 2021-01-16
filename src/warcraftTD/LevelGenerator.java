package warcraftTD;

import warcraftTD.Levels.Level;
import warcraftTD.Levels.Level2;
import warcraftTD.Levels.LevelProcedural;
import warcraftTD.Tiles.PlayButton;
import warcraftTD.Tiles.SpeedupButton;
import warcraftTD.Tiles.Tile;
import warcraftTD.Tiles.UpgradeLeft;
import warcraftTD.Tiles.UpgradeRight;

import java.awt.Color;
import java.util.List;

public class LevelGenerator {
    private boolean clicking = false; // est-ce que le joueur est actuelement en train de clicker

    // compte le nombre de tps (tick per second)
    private long tpsTimerStart = System.nanoTime();
    private final long TARGET_TPS = 1000000000 / 60; // on veut avoir 30 tps constant

    private double gameSpeed = 1;// La vitesse à laquelle s'écoule le jeu
    private Level level;

    private boolean pathIsDone;
    private boolean decorIsDone;
    private boolean roadIsDone;
    private boolean emptyIsDone;
    private int pathSize;
    private int decorSize;
    private int roadSize;
    private int emptySize;
    private boolean end = false;
    private int tpsCounter = 0; // compteur de tick utilisé pour les animations

    public LevelGenerator() {
        generate();
        loop();
    }

    /**
     * Génere une nouvelle map
     */
    private void generate() {
        pathIsDone = false;
        decorIsDone = false;
        roadIsDone = false;
        emptyIsDone = false;
        pathSize = 0;
        decorSize = 0;
        roadSize = 0;
        emptySize = 0;
        tpsCounter = 0;

        level = new LevelProcedural();
        pathSize = level.pathing.size();

        for (int y = 0; y < level.nbSquareY; y++) {
            for (int x = 0; x < level.nbSquareX; x++) {
                if (level.road[x][y])
                    roadSize++;
                else if (level.decor[x][y])
                    decorSize++;
                else
                    emptySize++;
            }
        }
    }

    /**
     * Update tout les éléments de la fenêtre de menu
     */
    private void tick() {
        tpsCounter++;
    }

    /**
     * Affiche les éléments de la fenêtre de menu
     */
    private void draw() {
        StdDraw.clear();

        drawBackground();
        drawPath();
        drawGrid();
        drawButtons();

        // Affiche le tout
        StdDraw.show();
    }

    /**
     * Affiche les buttons
     */
    private void drawButtons() {
        // ############ Draw play button ############

        StdDraw.picture(0.764, 0.071d, Assets.buttonPlay);

        // ############ Draw speedup button ############

        StdDraw.picture(0.764, 0.929, gameSpeed == 1 ? Assets.buttonSpeedup0 : Assets.buttonSpeedup1);

    }

    /**
     * Affiche le chemin avec une petite animation
     */
    private void drawPath() {
        final Color PATH_COLOR = new Color(139, 77, 219, 255);
        final Color PATH_COLOR_UNDER = new Color(245, 241, 34, 255);
        final double PATH_RADIUS = 0.01;
        List<Position> pathing = level.pathing;

        int state;
        if (pathIsDone) {
            state = pathSize - 1;
        } else {
            state = (tpsCounter / 6);
            if (state > pathSize - 1) {
                pathIsDone = true;
                tpsCounter = 0;
            }
        }
        StdDraw.setPenRadius(PATH_RADIUS);
        for (int i = 0; i < Math.min(pathSize - 1, state); i++) {
            Position current = pathing.get(i);
            Position next = pathing.get(i + 1);
            if (next.bool)
                StdDraw.setPenColor(PATH_COLOR_UNDER);
            else
                StdDraw.setPenColor(PATH_COLOR);
            StdDraw.line(current.x, current.y, next.x, next.y);
        }
    }

    /**
     * Affiche la grille avec une petite animation
     */
    private void drawGrid() {
        if (pathIsDone) {
            drawRoad();
            if (roadIsDone) {
                drawDecor();
                if (decorIsDone)
                    drawEmpty();
            }
        }
    }

    private void drawRoad() {
        int state;
        if (roadIsDone) {
            state = level.nbSquareY * level.nbSquareX - 6;
        } else {
            state = (tpsCounter / 3);
            if (state > roadSize) {
                roadIsDone = true;
                tpsCounter = 0;
            }
        }
        int drawed = 0;
        final Color LINE_COLOR = new Color(0, 0, 0, 80);
        StdDraw.setPenRadius(0.001);

        for (int y = 0; y < level.nbSquareY; y++) {
            for (int x = 0; x < level.nbSquareX - 6; x++) {
                if (level.road[x][y] && drawed++ < state) {
                    drawOneTile(LINE_COLOR, y, x);
                }
            }
        }
    }

    private void drawDecor() {

        int state;
        if (decorIsDone) {
            state = level.nbSquareY * level.nbSquareX - 6;
        } else {
            state = (tpsCounter / 4);
            if (state > decorSize) {
                decorIsDone = true;
                tpsCounter = 0;
            }
        }

        int drawed = 0;

        final Color LINE_COLOR = new Color(0, 0, 0, 80);
        StdDraw.setPenRadius(0.001);

        for (int y = 0; y < level.nbSquareY; y++) {
            for (int x = 0; x < level.nbSquareX - 6; x++) {
                if (level.decor[x][y] && drawed++ < state) {
                    drawOneTile(LINE_COLOR, y, x);
                }
            }
        }
    }

    private void drawEmpty() {
        int state;
        if (emptyIsDone) {
            state = level.nbSquareY * level.nbSquareX - 6;
        } else {
            state = (tpsCounter / 1);
            if (state > emptySize) {
                emptyIsDone = true;
                tpsCounter = 0;
            }
        }
        int drawed = 0;
        final Color LINE_COLOR = new Color(0, 0, 0, 80);
        StdDraw.setPenRadius(0.001);

        for (int y = 0; y < level.nbSquareY; y++) {
            for (int x = 0; x < level.nbSquareX - 6; x++) {
                if (!level.road[x][y] && !level.decor[x][y] && drawed++ < state) {
                    drawOneTile(LINE_COLOR, y, x);
                }
            }
        }
    }

    private void drawOneTile(final Color LINE_COLOR, int y, int x) {
        double squareWidth = (double) 1 / level.nbSquareX;
        double squareHeight = (double) 1 / level.nbSquareY;
        Position pos = new Position(x, y).inFrameSpace().plus(new Position(squareWidth / 2, squareHeight / 2));
        Color squarreColor = level.map[x][y].gridColor;

        // Draw : les lignes de la grille
        StdDraw.setPenColor(LINE_COLOR);
        StdDraw.rectangle(pos.x, pos.y, squareWidth / 2, squareHeight / 2);

        // Draw : les cases de la grille avec la couleur qui dépend de la case
        StdDraw.setPenColor(squarreColor);
        StdDraw.filledRectangle(pos.x, pos.y, squareWidth / 2, squareHeight / 2);
    }

    /**
     * Affiche l'arrière plan
     */
    private void drawBackground() {
        // Draw level background
        double bgSize = (double) 1000 / ((double) 1240 * 2);
        StdDraw.picture(bgSize, 0.5, level.spritePath, (2 * bgSize), 1);

        // Draw right side panel
        double panelSize = (double) 240 / ((double) 1240 * 2);
        StdDraw.picture(1 - panelSize, 0.5, Assets.panelProcedural, panelSize * 2, 1);
    }

    /**
     * Affectue des actions quand l'on fait un clique de souris
     * 
     * @param x la coordonnée X de la souris
     * @param y la coordonnée Y de la souris
     */
    private void onMouseClick(double x, double y) {
        if (clicking == false) {
            clicking = true;

            Position pos = new Position(StdDraw.mouseX(), StdDraw.mouseY()).inGridSpace();
            pos.x = Math.max(0, Math.min(level.nbSquareX - 1, pos.x)); // Clamp value pour évité IndexOutOfBounds
            pos.y = Math.max(0, Math.min(level.nbSquareY - 1, pos.y)); // Clamp value pour évité IndexOutOfBounds
            Tile mouseTile = level.map[(int) pos.x][(int) pos.y];

            if (mouseTile instanceof UpgradeLeft || mouseTile instanceof UpgradeRight) {
                generate();
            } else if (mouseTile instanceof PlayButton) {
                end = true;
                new World(level).run();
            } else if (mouseTile instanceof SpeedupButton)
                gameSpeed = gameSpeed == 1 ? 4 : 1;

        }
    }

    /** boucle de jeu */
    private void loop() {
        while (!end) {
            while (tpsTimerStart - System.nanoTime() < TARGET_TPS / gameSpeed) {
                tpsTimerStart += TARGET_TPS / gameSpeed;
                tick();
            }
            draw();
            if (StdDraw.hasNextKeyTyped()) { // Exit game if Q is pressed
                Character key = StdDraw.nextKeyTyped();
                if (Character.toLowerCase(key) == 'q')
                    break;
            }
            if (StdDraw.isMousePressed())
                onMouseClick(StdDraw.mouseX(), StdDraw.mouseY());
            else
                clicking = false;
        }
    }

}
