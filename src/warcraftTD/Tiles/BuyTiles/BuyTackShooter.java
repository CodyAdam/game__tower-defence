package warcraftTD.Tiles.BuyTiles;

import warcraftTD.Tiles.Monkeys.TackShooter;

public class BuyTackShooter extends BuyTile {

    public BuyTackShooter(int x, int y) {
        super(x, y);
        toPlace = new TackShooter(x, y);
        cost = toPlace.cost;
    }

    public void reset() {
        toPlace = new TackShooter(x, y);
    }
}
