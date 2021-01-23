package warcraftTD.Tiles.BuyTiles;

import warcraftTD.Tiles.Monkeys.SuperMonkey;

public class BuySuperMonkey extends BuyTile {

    public BuySuperMonkey(int x, int y) {
        super(x, y);
        toPlace = new SuperMonkey(0, 0);
        cost = toPlace.cost;
    }

    public void reset() {
        toPlace = new SuperMonkey(0, 0);
    }
}
