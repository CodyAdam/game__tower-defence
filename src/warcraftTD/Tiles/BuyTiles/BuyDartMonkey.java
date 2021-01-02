package warcraftTD.Tiles.BuyTiles;

import java.awt.Color;

import warcraftTD.Tiles.Monkeys.DartMonkey;

public class BuyDartMonkey extends BuyTile {

    public BuyDartMonkey(int x, int y) {
        super(x, y);
        isAvaliable = false;
        toPlace = new DartMonkey(x, y);
        gridColor = new Color(163, 247, 245, 30);
    }
}
