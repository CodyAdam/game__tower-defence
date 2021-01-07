package warcraftTD.Tiles.BuyTiles;

import java.awt.Color;

import warcraftTD.Tiles.Monkeys.DartMonkey;

public class BuyDartMonkey extends BuyTile {

    public BuyDartMonkey(int x, int y) {
        super(x, y);
        cost = 200;
        isAvaliable = false;
        toPlace = new DartMonkey(x, y);
        gridColor = new Color(203, 247, 245, 110);
    }

    public void reset() {
        toPlace = new DartMonkey(x, y);
    }
}
