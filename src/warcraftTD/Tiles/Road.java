package warcraftTD.Tiles;

import java.awt.Color;

/**
 * Tile utiliser dans la grille pour savoir ou ce trouve les emplacements de
 * route
 */
public class Road extends Tile {
    public Road(int x, int y) {
        super(x, y);
        isAvaliable = true;
        gridColor = new Color(252, 119, 3, 70);
    }
}
