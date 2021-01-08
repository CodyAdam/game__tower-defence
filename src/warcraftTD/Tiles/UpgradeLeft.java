package warcraftTD.Tiles;

import java.awt.Color;

/**
 * Tile utiliser en tant que bouton pour l'interface. Le bouton améliore la
 * parti gauche de la tour
 */
public class UpgradeLeft extends Tile {
    public UpgradeLeft(int x, int y) {
        super(x, y);
        isAvaliable = false;
        gridColor = new Color(56, 107, 209, 170);
    }
}
