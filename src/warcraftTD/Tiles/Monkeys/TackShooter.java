package warcraftTD.Tiles.Monkeys;

import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Projectiles.Dart;
import warcraftTD.Projectiles.Projectile;

public class TackShooter extends Monkey {

    private int pierce;

    public TackShooter(int x, int y) {
        super(x, y);
        cooldown = 50;
        range = 3.5;
        sprite = Assets.tackShooter;
        spriteOffset = new Position(0, 0);
        cost = 360;
        pierce = 1;

        // Setup Upgrades
        leftUpgrades.add(new Upgrade("Long range ", "darts", "Makes the Dart Monkey shoot further than normal", 90));
        leftUpgrades.add(new Upgrade("Enhanced", "Eyesight", "Further increases attack", 100));
        leftUpgrades.add(new Upgrade("Spike-O", "Pult",
                "Converts the Dart Monkey into a Spike-O-Pult, a powerful tower that hurls a large spiked ball instead of darts.",
                "Increases range slightly more, but has slower attack speed. Each ball can pop 18 bloons", 500));

        rightUpgrades.add(new Upgrade("Sharp", "Shots", "Can pop 1 extra bloon per shot", 140));
        rightUpgrades.add(new Upgrade("Razor Sharp", "Shots", "Can pop 2 extra bloons per shot (4 in total)", 170));
        rightUpgrades.add(new Upgrade("Triple", "Darts",
                "Converts the Dart Monkey into a archer monkey that throws 3 darts at a time instead of 1.",
                "Also increase fire rate", 330));
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
                range *= 1.13;
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
                cooldown = 40;
                spriteOffset = new Position(-0.0020, -0.0069);
                sprite = Assets.dartMonkeyTripleDart;
                break;
        }
    }

    @Override
    public void tick(List<Bloon> bloons, List<Projectile> projectiles) {
        super.tick(bloons, projectiles);
        final int ANIM_SPEED = 4;
        if (leftUpgrade == 3) {
            if ((timer / ANIM_SPEED) % 2 == 0)
                sprite = Assets.tackShooterBlade0;
            else
                sprite = Assets.tackShooterBlade1;
        }
    }

    @Override
    protected void shootAt(Bloon target, List<Projectile> projectiles) {
        Dart d0 = new Dart(pos, new Position(1, 0).inFrameSpace(), 0.03, 1, pierce);
        Dart d1 = new Dart(pos, new Position(-1, 0).inFrameSpace(), 0.03, 1, pierce);
        Dart d2 = new Dart(pos, new Position(0, 1).inFrameSpace(), 0.03, 1, pierce);
        Dart d3 = new Dart(pos, new Position(0, -1).inFrameSpace(), 0.03, 1, pierce);
        Dart d4 = new Dart(pos, new Position(1, 1).inFrameSpace(), 0.03, 1, pierce);
        Dart d5 = new Dart(pos, new Position(1, -1).inFrameSpace(), 0.03, 1, pierce);
        Dart d6 = new Dart(pos, new Position(-1, 1).inFrameSpace(), 0.03, 1, pierce);
        Dart d7 = new Dart(pos, new Position(-1, -1).inFrameSpace(), 0.03, 1, pierce);
        projectiles.add(d0);
        projectiles.add(d1);
        projectiles.add(d2);
        projectiles.add(d3);
        projectiles.add(d4);
        projectiles.add(d5);
        projectiles.add(d6);
        projectiles.add(d7);
    }
}
