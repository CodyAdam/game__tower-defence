package warcraftTD.Tiles;

import java.awt.Color;

public class Empty extends Tile {
    public Empty(int x, int y) {
        super(x, y);
        isAvaliable = true;
        gridColor = new Color(0, 100, 0, 110);
    }
}
