package warcraftTD.Tiles;

import java.awt.Color;

/**
 * Tile utiliser en tant que bouton pour l'interface. Le bouton améliore la
 * parti droite de la tour
 */
public class UpgradeRight extends Tile {
    public UpgradeRight(int x, int y) {
        super(x, y);
        isAvaliable = false;
        gridColor = new Color(209, 56, 186, 170);
    }
}
