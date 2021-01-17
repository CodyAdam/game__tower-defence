package warcraftTD;

import warcraftTD.Levels.Level;
import warcraftTD.Levels.LevelProcedural;
import warcraftTD.Tiles.ChangeTargetLeft;
import warcraftTD.Tiles.ChangeTargetRight;
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
    private int generationMode = 0;

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

        level = new LevelProcedural(generationMode);
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
        drawInfos();

        // Affiche le tout
        StdDraw.show();
    }

    private void drawInfos() {
        // Petit rectangle en bas de l'ecran :
        StdDraw.setFont(); // set default font
        StdDraw.setPenColor(new Color(0, 0, 0, 70));
        StdDraw.filledRectangle(0, 0, 0.25, 0.05);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(0.01, 0.023, "Press 'Q' to go back to the MAIN MENU");

        // Switch de mode :
        double alignX = 0.904;
        double alignY = 0.79;
        StdDraw.text(alignX, alignY + 0.025, "Mode");
        switch (generationMode) {
            case 0:
                StdDraw.text(alignX, alignY + 0.0, "Smooth 1");
                break;
            case 1:
                StdDraw.text(alignX, alignY + 0.0, "Smooth 2");
                break;
            case 2:
                StdDraw.text(alignX, alignY + 0.0, "Raw");
                break;
        }
        StdDraw.picture(alignX - 0.052, alignY + 0.012, Assets.leftArrow);
        StdDraw.picture(alignX + 0.052, alignY + 0.012, Assets.rightArrow);
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
            state = (tpsCounter / (generationMode == 2 ? 15 : 1));
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
            // StdDraw.circle(current.x, current.y, 0.001);
            StdDraw.line(current.x, current.y, next.x, next.y);
        }
        for (int i = 0; i < Math.min(pathSize - 1, state); i++) {
            Position current = pathing.get(i);
            StdDraw.setPenColor(PATH_COLOR_UNDER);
            Position p = new Position(1, 1).multi(0.04).inFrameSpace();
            StdDraw.filledEllipse(current.x, current.y, p.x, p.y);
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

    /**
     * Affiche les tiles de routes avec une petite animation après avoir afficher le
     * chemin
     */
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

    /**
     * Affiche les tiles de décor après avoir afficher les tiles de route
     */
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

    /**
     * Affiche les tiles de vide après avoir afficher les tiles de décor
     */
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

    /**
     * Affiche la tile de coordonnées (x,y)
     * 
     * @param LINE_COLOR couleur des bords de la tile
     * @param x          coordonnée X de la tile
     * @param y          coordonnée Y de la tile
     */
    private void drawOneTile(final Color LINE_COLOR, int y, int x) {
        double squareWidth = (double) 1 / level.nbSquareX;
        double squareHeight = (double) 1 / level.nbSquareY;
        Position pos = new Position(x, y).inFrameSpace().plus(new Position(squareWidth / 2, squareHeight / 2));
        // Position pos = new Position(x, y).inFrameSpace();
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
            } else if (mouseTile instanceof ChangeTargetLeft) {
                generationMode = (((generationMode - 1) % 3) + 3) % 3;
            } else if (mouseTile instanceof ChangeTargetRight) {
                generationMode = (((generationMode + 1) % 3) + 3) % 3;
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
