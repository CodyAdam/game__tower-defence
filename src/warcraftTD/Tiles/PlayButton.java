package warcraftTD.Tiles;

import java.awt.Color;

/**
 * Tile utiliser en tant que bouton pour l'interface. Le bouton lance la
 * prochaine vague
 */
public class PlayButton extends Tile {
    public PlayButton(int x, int y) {
        super(x, y);
        isAvaliable = true;
        gridColor = new Color(56, 150, 168, 170);
    }
}
