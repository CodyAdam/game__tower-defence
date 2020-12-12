package warcraftTD.Tiles;

import java.awt.Color;

public abstract class Tile {

    public boolean isAvaliable;
    public boolean isSelected;
    public Color gridColor;

    abstract void onClick();

    abstract void draw();
}
