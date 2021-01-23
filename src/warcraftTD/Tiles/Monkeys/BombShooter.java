package warcraftTD.Tiles.Monkeys;

import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Projectiles.FragBomb;
import warcraftTD.Projectiles.Projectile;

public class BombShooter extends Monkey {

    private double explosionRadius;

    public BombShooter(int x, int y) {
        super(x, y);
        cooldown = 80;
        range = 3.5;
        sprite = Assets.bombShooter;
        spriteOffset = new Position(0, -0.0134);
        cost = 650;
        totalMoneySpend += cost;
        explosionRadius = 2;

        // Setup Upgrades
        leftUpgrades.add(new Upgrade("Extra", "Range", "Gives tower longer attack range.", 200));
        leftUpgrades.add(new Upgrade("Frag", "Bombs",
                "Each explosion throws out 8 sharp tacks that pop even more bloons.", 300));
        leftUpgrades.add(new Upgrade("Bloon", "Impact",
                "Throws out secondary bombs instead of frags causing widespread explosive poppage",
                "The secondary bombs can no longer pop blacks or zebras.", 800));

        rightUpgrades.add(new Upgrade("Bigger", "Bombs", "Bombs hit a larger area", 400));
        rightUpgrades.add(new Upgrade("Faster", "Shooting", "+40% faster shooting", 400));
        rightUpgrades.add(new Upgrade("MOAB", "Mauler",
                "Special missiles do 10x the damage to MOAB class bloons. +20% faster shooting.", 900));
    }

    @Override
    protected void postUpgradeLeft(int upgradeLevel) {
        switch (upgradeLevel) {
            case 1:
                range *= 1.25;
                break;
            case 2:
                break;
            case 3:
                sprite = Assets.bombShooterFragBombs;
                break;
        }
    }

    @Override
    protected void postUpgradeRight(int upgradeLevel) {
        switch (upgradeLevel) {
            case 1:
                explosionRadius = 2.6;
                break;
            case 2:
                cooldown *= 0.6;
                break;
            case 3:
                cooldown *= 0.8;
                sprite = Assets.bombShooterMissileLauncher;
                break;
        }
    }

    @Override
    protected void shootAt(Bloon target, List<Projectile> projectiles) {
        turnToward(target.pos);
        Projectile d;
        if (leftUpgrade == 2)
            d = new FragBomb(pos, target.pos.minus(pos), 0.02, 1, range, explosionRadius, 1,
                    rightUpgrade == 3 ? Assets.bombMissile : Assets.bomb, rightUpgrade == 3);
        else if (leftUpgrade == 3)
            d = new FragBomb(pos, target.pos.minus(pos), 0.02, 1, range, explosionRadius, 2, Assets.bomb, false);
        else
            d = new FragBomb(pos, target.pos.minus(pos), 0.02, 1, range, explosionRadius, 0, Assets.bomb, false);
        projectiles.add(d);
    }
}
