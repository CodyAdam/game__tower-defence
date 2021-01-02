package warcraftTD.Tiles;

import java.awt.Color;

public class Road extends Tile {

    public Road(int x, int y) {
        super(x, y);
        isAvaliable = true;
        gridColor = new Color(219, 88, 0, 140);
    }
}
