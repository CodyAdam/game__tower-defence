package warcraftTD.Tiles;

import java.awt.Color;

public class Empty extends Tile {
    public Empty() {
        isAvaliable = true;
        isSelected = false;
        gridColor = new Color(0, 100, 0, 150);
    }

    @Override
    void onClick() {
        return;
    }

    @Override
    void draw() {
        return;
    }
}
