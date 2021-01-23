package warcraftTD.Tiles.BuyTiles;

import warcraftTD.Tiles.Monkeys.BombShooter;

public class BuyBombShooter extends BuyTile {

    public BuyBombShooter(int x, int y) {
        super(x, y);
        toPlace = new BombShooter(0, 0);
        cost = toPlace.cost;
    }

    public void reset() {
        toPlace = new BombShooter(0, 0);
    }
}
