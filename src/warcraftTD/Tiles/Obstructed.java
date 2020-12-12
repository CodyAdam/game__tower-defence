package warcraftTD.Tiles;

import java.awt.Color;

public class Obstructed extends Tile {

    public Obstructed() {
        isAvaliable = false;
        isSelected = false;
        gridColor = new Color(100, 0, 0, 140);
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
