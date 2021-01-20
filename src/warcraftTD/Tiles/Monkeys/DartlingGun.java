package warcraftTD.Tiles.Monkeys;

import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.StdDraw;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Projectiles.Dart;
import warcraftTD.Projectiles.Laser;
import warcraftTD.Projectiles.Missile;
import warcraftTD.Projectiles.Projectile;

public class DartlingGun extends Monkey {

    private int pierce;
    private double accuracyDegree;
    private double dartSpeed;

    public DartlingGun(int x, int y) {
        super(x, y);
        cooldown = 30;
        range = 15;
        sprite = Assets.dartlingGun0;
        spriteOffset = new Position(0, 0);
        cost = 1250;
        pierce = 1;
        accuracyDegree = 23;
        dartSpeed = 0.03;

        // Setup Upgrades
        leftUpgrades.add(new Upgrade("Focused", "Firing", "Greatly reduces the spread of the gun. (23° to 8.5°)", 250));
        leftUpgrades.add(new Upgrade("Faster Barrel", "Spin", "Further increases attack speed", 1200));
        leftUpgrades.add(new Upgrade("Laser", "Cannon", "Converts the gun into a super powerful laser cannon",
                "Blasts from this cannon can pop 13 bloons each, and have increased attack rate", 6000));

        rightUpgrades.add(new Upgrade("Ultra Range", "Darts", "Darts have infinite range!", 600));
        rightUpgrades.add(new Upgrade("Powerful", "Darts", "Makes darts shoot with greater velocity.",
                "Darts move faster and can pop 3 bloons each.", 600));
        rightUpgrades.add(new Upgrade("Blade", "Shooter", "Shoots vicious little missiles instead of darts.",
                "The missiles explode on contact, dealing massive amount of damage all around them!", 7000));
    }

    @Override
    public void tick(List<Bloon> bloons, List<Projectile> projectiles) {
        super.tick(bloons, projectiles);
        Position mousePos = new Position(StdDraw.mouseX(), StdDraw.mouseY());
        turnToward(mousePos);

        if (leftUpgrade != 3 && rightUpgrade != 3)
            sprite = (timer / 4) % 2 == 0 ? Assets.dartlingGun0 : Assets.dartlingGun1;
    }

    @Override
    protected void postUpgradeLeft(int upgradeLevel) {
        switch (upgradeLevel) {
            case 1:
                accuracyDegree = 8.5;
                break;
            case 2:
                cooldown = 15;
                break;
            case 3:
                cooldown = 3;
                sprite = Assets.dartlingGunLaser;
                break;
        }
    }

    @Override
    protected void postUpgradeRight(int upgradeLevel) {
        switch (upgradeLevel) {
            case 1:
                range = 30;
                break;
            case 2:
                dartSpeed = 0.06;
                pierce = 3;
                break;
            case 3:
                sprite = Assets.dartlingGunRocket;
                break;
        }
    }

    @Override
    protected void shootAt(Bloon target, List<Projectile> projectiles) {
        Position mousePos = new Position(StdDraw.mouseX(), StdDraw.mouseY());
        Projectile d;
        Position direction = mousePos.minus(pos).rotate(Math.random() * accuracyDegree - accuracyDegree / 2);
        if (rightUpgrade == 3)
            d = new Missile(pos, direction, dartSpeed, 5, range);
        else if (leftUpgrade == 3)
            d = new Laser(pos, direction, dartSpeed, 2, pierce, range);
        else
            d = new Dart(pos, direction, dartSpeed, 1, pierce, range);
        projectiles.add(d);
    }
}
