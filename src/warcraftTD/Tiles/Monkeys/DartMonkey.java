package warcraftTD.Tiles.Monkeys;

import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Projectiles.Dart;
import warcraftTD.Projectiles.Pike;
import warcraftTD.Projectiles.Projectile;

public class DartMonkey extends Monkey {

    private int pierce;

    public DartMonkey(int x, int y) {
        super(x, y);
        cooldown = 50;
        range = 3;
        sprite = Assets.dartMonkey;
        spriteCenter = new Position(0.5, 0.5);
        cost = 200;
        pierce = 1;

        // Setup Upgrades
        leftUpgrades.add(new Upgrade("Long range ", "darts", "Makes the Dart Monkey shoot further than normal", 90));
        leftUpgrades.add(new Upgrade("Enhanced", "Eyesight", "Further increases attack", 100));
        leftUpgrades.add(new Upgrade("Spike-O", "Pult",
                "Converts the Dart Monkey into a Spike-O-Pult, a powerful tower that hurls a large spiked ball instead of darts.",
                "Increases range even more, albeit slightly, but has slower attack speed. Each ball can pop 18 bloons",
                500));

        rightUpgrades.add(new Upgrade("Sharp", "Shots", "Can pop 1 extra bloon per shot", 140));
        rightUpgrades.add(new Upgrade("Razor Sharp", "Shots", "Can pop 2 extra bloons per shot (4 in total)", 170));
        rightUpgrades.add(new Upgrade("Triple", "Darts", "Throws 3 darts at a time instead of 1.", 330));
    }

    @Override
    protected void postUpgradeLeft(int upgradeLevel) {
        switch (upgradeLevel) {
            case 1:
                range *= 1.25;
                break;
            case 2:
                range *= 1.25;
                break;
            case 3:
                range *= 1.25;
                cooldown = 80;
                pierce += 14;
                sprite = Assets.dartMonkeySpikeOPult0;
                break;
        }
    }

    @Override
    protected void postUpgradeRight(int upgradeLevel) {
        switch (upgradeLevel) {
            case 1:
                pierce += 1;
                break;
            case 2:
                pierce += 2;
                break;
            case 3:

                break;
        }
    }

    @Override
    public void tick(List<Bloon> bloons, List<Projectile> projectiles) {
        super.tick(bloons, projectiles);
        if (leftUpgrade == 3) {
            if (timer <= cooldown * 0.1)
                sprite = Assets.dartMonkeySpikeOPult0;
            else if (timer <= cooldown * 0.2)
                sprite = Assets.dartMonkeySpikeOPult1;
            else if (timer <= cooldown * 0.85)
                sprite = Assets.dartMonkeySpikeOPult2;
            else if (timer <= cooldown * 0.93)
                sprite = Assets.dartMonkeySpikeOPult1;
        }
    }

    @Override
    protected void shootAt(Bloon target, List<Projectile> projectiles) {
        turnToward(target);
        if (leftUpgrade == 3) {
            Pike p = new Pike(pos, target.pos.minus(pos), 0.005, 1, pierce);
            p.maxRange = range * 2;
            projectiles.add(p);
        } else {
            Dart d = new Dart(pos, target.pos.minus(pos), 0.03, 1, pierce);
            d.maxRange = range;
            projectiles.add(d);
        }
    }
}
