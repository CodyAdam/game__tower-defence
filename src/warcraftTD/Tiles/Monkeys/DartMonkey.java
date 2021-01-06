package warcraftTD.Tiles.Monkeys;

import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Projectiles.Dart;
import warcraftTD.Projectiles.Projectile;

public class DartMonkey extends Monkey {
    public DartMonkey(int x, int y) {
        super(x, y);
        cooldown = 60;
        range = 4;
        sprite = Assets.dartMonkey;
    }

    @Override
    protected void shootAt(Bloon target, List<Projectile> projectiles) {
        turnToward(target);
        Dart d = new Dart(pos, target.pos.minus(pos), 0.03, 1);
        d.maxRange = range;
        projectiles.add(d);

    }
}
