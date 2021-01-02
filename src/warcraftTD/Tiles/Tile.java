package warcraftTD.Tiles;

import java.awt.Color;

public abstract class Tile {
    public boolean isAvaliable;
    public Color gridColor;
    public int x;
    public int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
