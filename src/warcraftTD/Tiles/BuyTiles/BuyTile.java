package warcraftTD.Tiles.BuyTiles;

import warcraftTD.Tiles.Tile;
import warcraftTD.Tiles.Monkeys.Monkey;

public abstract class BuyTile extends Tile {
    public Monkey toPlace;

    public BuyTile(int x, int y) {
        super(x, y);
    }
}