package warcraftTD.Tiles.BuyTiles;

import java.awt.Color;

import warcraftTD.Tiles.Monkeys.DartMonkey;

public class BuyDartMonkey extends BuyTile {

    public BuyDartMonkey(int x, int y) {
        super(x, y);
        toPlace = new DartMonkey(0, 0);
        cost = toPlace.cost;
        gridColor = new Color(203, 247, 245, 110);
    }

    public void reset() {
        toPlace = new DartMonkey(0, 0);
    }
}
