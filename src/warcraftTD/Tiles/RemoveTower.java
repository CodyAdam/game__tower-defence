package warcraftTD.Tiles;

import java.awt.Color;

/**
 * Tile utiliser en tant que bouton pour l'interface. Le bouton supprime la tour
 * selectionner
 */
public class RemoveTower extends Tile {
    public RemoveTower(int x, int y) {
        super(x, y);
        isAvaliable = false;
        gridColor = new Color(90, 0, 0, 170);
    }
}
