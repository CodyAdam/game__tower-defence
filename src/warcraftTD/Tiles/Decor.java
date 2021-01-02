package warcraftTD.Tiles;

import java.awt.Color;

public class Decor extends Tile {

    public Decor(int x, int y) {
        super(x, y);
        isAvaliable = false;
        gridColor = new Color(100, 0, 0, 140);
    }
}
