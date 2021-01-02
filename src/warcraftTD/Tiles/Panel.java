package warcraftTD.Tiles;

import java.awt.Color;

public class Panel extends Tile {

    public Panel(int x, int y) {
        super(x, y);
        isAvaliable = false;
        gridColor = new Color(240, 232, 5, 60);
    }
}
