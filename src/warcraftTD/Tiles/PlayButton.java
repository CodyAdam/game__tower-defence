package warcraftTD.Tiles;

import java.awt.Color;

public class PlayButton extends Tile {
    public PlayButton(int x, int y) {
        super(x, y);
        isAvaliable = true;
        gridColor = new Color(56, 150, 168, 140);
    }
}