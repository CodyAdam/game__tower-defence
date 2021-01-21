package warcraftTD.Tiles.BuyTiles;

import warcraftTD.Tiles.Monkeys.DartlingGun;

public class BuyDartlingGun extends BuyTile {

    public BuyDartlingGun(int x, int y) {
        super(x, y);
        toPlace = new DartlingGun(0, 0);
        cost = toPlace.cost;
    }

    public void reset() {
        toPlace = new DartlingGun(0, 0);
    }
}
