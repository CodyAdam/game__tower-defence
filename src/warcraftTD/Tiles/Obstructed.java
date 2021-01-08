package warcraftTD.Tiles;

import java.awt.Color;

/**
 * Tile utiliser dans la grille pour savoir ou ce trouve les emplacements libres
 */
public class Obstructed extends Tile {

    public Obstructed(int x, int y) {
        super(x, y);
        isAvaliable = false;
        gridColor = new Color(90, 0, 0, 170);
    }
}
