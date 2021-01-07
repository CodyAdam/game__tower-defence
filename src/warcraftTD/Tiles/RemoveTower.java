package warcraftTD.Tiles;

import java.awt.Color;

public class RemoveTower extends Tile {
    public RemoveTower(int x, int y) {
        super(x, y);
        isAvaliable = false;
        gridColor = new Color(90, 0, 0, 170);
    }
}
