package warcraftTD.Tiles.Monkeys;

import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Projectiles.Projectile;
import warcraftTD.Projectiles.Tack;

public class TackShooter extends Monkey {

    private int pierce;
    private int damage;

    public TackShooter(int x, int y) {
        super(x, y);
        cooldown = 50;
        range = 2;
        sprite = Assets.tackShooter;
        spriteOffset = new Position(0, 0);
        cost = 360;
        pierce = 1;
        damage = 1;

        // Setup Upgrades
        leftUpgrades.add(new Upgrade("Faster ", "Shooting", "Shoots tacks faster", 210));
        leftUpgrades.add(new Upgrade("Even Faster", "Shooting", "Further increases attack speed", 325));
        leftUpgrades.add(new Upgrade("Ring of", "Fire",
                "Upgrades to a fast firing burst tower that shoots a deadly ring of flame instead of tacks!",
                "Also increases range", 2500));

        rightUpgrades.add(new Upgrade("Extra Range", "Tacks", "Tacks have increased range", 100));
        rightUpgrades.add(new Upgrade("Super Range", "Tacks", "Tacks have further increased range", 225));
        rightUpgrades.add(new Upgrade("Blade", "Shooter",
                "Converts the tower into a blade shooter that shoots out razor sharp blades that deak more damage!",
                680));
    }

    @Override
    protected void postUpgradeLeft(int upgradeLevel) {
        switch (upgradeLevel) {
            case 1:
                cooldown = 40;
                break;
            case 2:
                cooldown = 30;
                break;
            case 3:
                range *= 1.13;
                sprite = Assets.tackShooterFlame;
                break;
        }
    }

    @Override
    protected void postUpgradeRight(int upgradeLevel) {
        switch (upgradeLevel) {
            case 1:
                range *= 1.15;
                break;
            case 2:
                range *= 1.15;
                break;
            case 3:
                damage = 3;
                sprite = Assets.tackShooterBlade0;
                break;
        }
    }

    @Override
    public void tick(List<Bloon> bloons, List<Projectile> projectiles) {
        super.tick(bloons, projectiles);
        final int ANIM_SPEED = 4;
        if (rightUpgrade == 3) {
            if ((timer / ANIM_SPEED) % 2 == 0)
                sprite = Assets.tackShooterBlade0;
            else
                sprite = Assets.tackShooterBlade1;
        }
    }

    @Override
    protected void shootAt(Bloon target, List<Projectile> projectiles) {
        Tack d0 = new Tack(pos, new Position(1, 0).inFrameSpace(), 0.03, damage, pierce, range, rightUpgrade == 3);
        Tack d1 = new Tack(pos, new Position(-1, 0).inFrameSpace(), 0.03, damage, pierce, range, rightUpgrade == 3);
        Tack d2 = new Tack(pos, new Position(0, 1).inFrameSpace(), 0.03, damage, pierce, range, rightUpgrade == 3);
        Tack d3 = new Tack(pos, new Position(0, -1).inFrameSpace(), 0.03, damage, pierce, range, rightUpgrade == 3);
        Tack d4 = new Tack(pos, new Position(1, 1).inFrameSpace(), 0.03, damage, pierce, range, rightUpgrade == 3);
        Tack d5 = new Tack(pos, new Position(1, -1).inFrameSpace(), 0.03, damage, pierce, range, rightUpgrade == 3);
        Tack d6 = new Tack(pos, new Position(-1, 1).inFrameSpace(), 0.03, damage, pierce, range, rightUpgrade == 3);
        Tack d7 = new Tack(pos, new Position(-1, -1).inFrameSpace(), 0.03, damage, pierce, range, rightUpgrade == 3);
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
