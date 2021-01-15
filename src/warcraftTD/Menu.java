package warcraftTD;

import warcraftTD.Levels.*;

/**
 * Menu d'accueil qui permet la sélection de niveaux
 */
public class Menu {
    private boolean clicking = false; // est-ce que le joueur est actuelement en train de clicker

    // compte le nombre de tps (tick per second)
    private long tpsTimerStart = System.nanoTime();
    private final long TARGET_TPS = 1000000000 / 60; // on veut avoir 30 tps constant

    private int cursorAnimationCounter; // Compte les frame pour savoir quand changer l'animation du curseur
    private String cursorImage;
    private Position cursorPos;
    private Position targetPos;
    private final double ANIMATION_SPEED = 3; // vitesse de l'animation du curseur

    private int hoveredButton = -1;

    public Menu(int width, int height) {
        cursorPos = new Position(0.5, 0.5);
        StdDraw.setCanvasSize(width, height);
        StdDraw.enableDoubleBuffering();
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
            double mouseX = Math.round(x * 10000) / (double) 10000;
            double mouseY = Math.round(y * 10000) / (double) 10000;
            System.out.println(new Position(mouseX, mouseY));
        }

        switch (hoveredButton) {
            case 1:
                new World(new Level1()).run(); // instancie le plateau et lancement de la boucle principale du jeu
                break;
            case 2:
                new World(new Level2()).run(); // instancie le plateau et lancement de la boucle principale du jeu
                break;
            case 3:
                new World(new Level3()).run(); // instancie le plateau et lancement de la boucle principale du jeu
                break;
            case 4:
                new World(new Level4()).run(); // instancie le plateau et lancement de la boucle principale du jeu
                break;
        }
    }

    /**
     * Déplace le curseur vers la position désiré mais avec une animation de easing
     */
    private void EaseCursorToTarget() {
        // int EASE_DURATION = 20;
        Position cur = cursorPos;
        Position dir = targetPos.minus(cursorPos);

        cursorPos = cur.plus(dir.multi(0.2));
    }

    /**
     * Update tout les éléments de la fenêtre de menu
     */
    private void tick() {
        tickCursorAnimation();
        EaseCursorToTarget();
    }

    private void tickCursorAnimation() {
        cursorAnimationCounter++;

        switch ((int) (cursorAnimationCounter / ANIMATION_SPEED) % 6) {
            case 1:
            case 5:
                cursorImage = Assets.cursorDart1;
                break;
            case 2:
            case 4:
                cursorImage = Assets.cursorDart2;
                break;
            case 3:
                cursorImage = Assets.cursorDart3;
                break;
            default:
                cursorImage = Assets.cursorDart0;
                break;
        }

        double y = StdDraw.mouseY();
        if (y > 0.2027 && y < 0.52222) {
            if (y > 0.4625) {
                targetPos = new Position(0.3, 0.4944);
                hoveredButton = 1;
            } else if (y > 0.39861) {
                targetPos = new Position(0.325, 0.4319);
                hoveredButton = 2;
            } else if (y > 0.33333) {
                targetPos = new Position(0.2976, 0.3667);
                hoveredButton = 3;
            } else if (y > 0.27222) {
                targetPos = new Position(0.365, 0.3069);
                hoveredButton = 4;
            } else {
                targetPos = new Position(0.3766, 0.2361);
                hoveredButton = 5;
            }
        } else {
            hoveredButton = 0;
            targetPos = new Position(0.696, 0.033);
        }

    }

    /**
     * Affiche les éléments de la fenêtre de menu
     */
    private void draw() {
        StdDraw.clear();

        // Arriere plan
        drawBackground();
        drawCursor();

        // Affiche le tout
        StdDraw.show();
    }

    /**
     * Affiche l'arrière plan
     */
    private void drawBackground() {
        StdDraw.picture(0.5, 0.5, Assets.titleBackground);
    }

    /**
     * Affiche le curseur
     */
    private void drawCursor() {
        StdDraw.picture(cursorPos.x, cursorPos.y, cursorImage);
    }

    /**
     * Boucle de jeu
     */
    public void loop() {
        while (true) {
            while (tpsTimerStart - System.nanoTime() < TARGET_TPS / 1) {
                tpsTimerStart += TARGET_TPS / 1;
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
        System.exit(0);
    }
}
