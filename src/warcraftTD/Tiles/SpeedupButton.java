package warcraftTD.Tiles;

import java.awt.Color;

/**
 * Tile utiliser en tant que bouton pour l'interface. Le bouton change la
 * vitesse du jeu
 */
public class SpeedupButton extends Tile {
    public SpeedupButton(int x, int y) {
        super(x, y);
        isAvaliable = true;
        gridColor = new Color(56, 150, 168, 170);
    }
}
