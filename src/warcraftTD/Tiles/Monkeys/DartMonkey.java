package warcraftTD.Tiles.Monkeys;

import warcraftTD.Assets;
import warcraftTD.Bloons.Bloon;

public class DartMonkey extends Monkey {
    public DartMonkey(int x, int y) {
        super(x, y);
        cooldown = 60;
        range = 0.2;
        sprite = Assets.dartMonkey;
    }

    @Override
    protected void shootAt(Bloon target) {
        turnToward(target);
    }
}
