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
        cost = 200;
        leftUpgrades.add(new Upgrade("Long range ", "darts", "Makes the Dart Monkey shoot further than normal", 90));
        leftUpgrades.add(new Upgrade("Enhanced ", "Eyesight",
                "Further increases attack range and allows Dart Monkey to see Camo Bloons", 100));
        leftUpgrades.add(new Upgrade("Spike-O", "Pult",
                "Converts the Dart Monkey into a Spike-O-Pult, a powerful tower that hurls a large spiked ball instead of darts. Increases range even more, albeit slightly, but has slower attack speed. Each ball can pop 18 bloons",
                500));

        rightUpgrades.add(new Upgrade("Sharp", "Shots", "Can pop 1 extra bloon per shot", 140));
        rightUpgrades.add(new Upgrade("Razor Sharp", "Shots", "Can pop 2 extra bloons per shot (4 in total)", 170));
        rightUpgrades.add(new Upgrade("Triple", "Darts", "Throws 3 darts at a time instead of 1.", 330));
    }

    @Override
    protected void shootAt(Bloon target, List<Projectile> projectiles) {
        turnToward(target);
        Dart d = new Dart(pos, target.pos.minus(pos), 0.03, 1);
        d.maxRange = range;
        projectiles.add(d);
    }
}
