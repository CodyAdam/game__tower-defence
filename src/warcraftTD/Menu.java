package warcraftTD;

import warcraftTD.StdDraw;

/**
 * Menu d'accueil qui permet la sélection de niveaux
 */
public class Menu {
    private boolean end = false; // condition d'arret de la fenêtre
    private boolean clicking = false; // est-ce que le joueur est actuelement en train de clicker

    // compte le nombre de tps (tick per second) et fps (frames per second)
    private long tpsTimerStart = System.nanoTime();
    private long tpsCounter = 0;
    private final long TARGET_TPS = 1000000000 / 30; // on veut avoir 30 tps constant

    public Menu(int width, int height) {
        StdDraw.setCanvasSize(width, height);
        StdDraw.enableDoubleBuffering();
    }

    private void onMouseClick(double x, double y) {

    }

    /**
     * Update tout les éléments de la fenêtre de menu
     */
    private void tick() {

    }

    /**
     * Affiche les éléments de la fenêtre de menu
     */
    private void draw() {

    }

    public void loop() {
        while (!end) {
            while (tpsTimerStart - System.nanoTime() < TARGET_TPS / 1) {
                tpsTimerStart += TARGET_TPS / 1;
                tpsCounter++;
                tick();
            }
            draw();

            if (StdDraw.isMousePressed())
                onMouseClick(StdDraw.mouseX(), StdDraw.mouseY());
            else
                clicking = false;
        }

        System.exit(0);
    }
}
