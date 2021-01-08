package warcraftTD.Tiles;

import java.awt.Color;

/**
 * Tile utiliser en tant que bouton pour l'interface. Le bouton change de cible
 * au mode précédent
 */
public class ChangeTargetLeft extends Tile {
    public ChangeTargetLeft(int x, int y) {
        super(x, y);
        isAvaliable = false;
        gridColor = new Color(181, 128, 13, 170);
    }
}
