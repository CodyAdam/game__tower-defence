package warcraftTD.Tiles.Monkeys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.StdDraw;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Projectiles.Dart;
import warcraftTD.Projectiles.Laser;
import warcraftTD.Projectiles.Plasma;
import warcraftTD.Projectiles.Projectile;
import warcraftTD.Projectiles.Sunball;
import warcraftTD.Tiles.Tile;

public class SuperMonkey extends Monkey {

    private int pierce;
    private double projSpeed;

    public SuperMonkey(int x, int y) {
        super(x, y);
        cooldown = 10;
        range = 4;
        sprite = Assets.superMonkey;
        spriteOffset = new Position(0, 0);
        cost = 3500;
        pierce = 1;
        projSpeed = 0.03;

        // Setup Upgrades
        leftUpgrades.add(new Upgrade("Laser", "Blasts", "Shoots super-powerful lasers instead of throwing darts.",
                "(Each laser can pop 2 bloons instead of 1 and laser blasts can do double damage to Air Bloons)",
                3500));
        leftUpgrades.add(new Upgrade("Plasma", "Blasts", "Plasma vaporizes everything it touches!",
                "(Plasma shoots 1.4x as fast as an individual laser, each shot pops 4 bloons and can pop lead bloons.)",
                5000));
        leftUpgrades.add(new Upgrade("Temple of the", "Monkey God", "The Temple demands sacrifice", 100000));

        rightUpgrades.add(new Upgrade("Super", "Range", "Super Monkey + super range = good!", 1000));
        rightUpgrades.add(new Upgrade("Epic", "Range", "Why settle for super when you can have EPIC?", 1500));
        rightUpgrades.add(new Upgrade("Triple", "Darts", "Half Super Monkey, half killer robot of death.",
                " Super Monkey's arms become pulse cannons of annihilation, able to with two independent laser-blaster",
                9000));
    }

    @Override
    protected void postUpgradeLeft(int upgradeLevel) {
        switch (upgradeLevel) {
            case 1:
                pierce = 2;
                cooldown = 10;
                break;
            case 2:
                cooldown = 7;
                break;
            case 3:
                range *= 1.5;
                sprite = Assets.superMonkeyTempleHead;
                break;
        }
    }

    @Override
    protected void postUpgradeRight(int upgradeLevel) {
        switch (upgradeLevel) {
            case 1:
                range *= 1.3;
                break;
            case 2:
                range *= 1.3;
                break;
            case 3:
                cooldown -= 2;
                sprite = Assets.superMonkeyRobot;
                break;
        }
    }

    /**
     * @Override Les tiles occupées par cette tour sont plus nombreuses
     * @param x coordonnée X
     * @param y coordonnée Y
     * @return revoi les coordonnées des cases adjacentes de la tour de coordonnées
     *         (x,y)
     */
    public ArrayList<Integer[]> getAdjacent(int x, int y) {
        ArrayList<Integer[]> list = new ArrayList<Integer[]>();

        list.add(new Integer[] { x + 1, y });
        list.add(new Integer[] { x, y + 1 });
        list.add(new Integer[] { x - 1, y });
        list.add(new Integer[] { x, y - 1 });
        list.add(new Integer[] { x + 1, y + 1 });
        list.add(new Integer[] { x - 1, y + 1 });
        list.add(new Integer[] { x - 1, y - 1 });
        list.add(new Integer[] { x + 1, y - 1 });
        list.add(new Integer[] { x + 2, y - 2 });
        list.add(new Integer[] { x + 2, y - 1 });
        list.add(new Integer[] { x + 2, y - 0 });
        list.add(new Integer[] { x + 2, y + 1 });
        list.add(new Integer[] { x + 2, y + 2 });
        list.add(new Integer[] { x - 2, y - 2 });
        list.add(new Integer[] { x - 2, y - 1 });
        list.add(new Integer[] { x - 2, y - 0 });
        list.add(new Integer[] { x - 2, y + 1 });
        list.add(new Integer[] { x - 2, y + 2 });
        list.add(new Integer[] { x - 1, y + 2 });
        list.add(new Integer[] { x - 0, y + 2 });
        list.add(new Integer[] { x + 1, y + 2 });
        list.add(new Integer[] { x - 1, y - 2 });
        list.add(new Integer[] { x - 0, y - 2 });
        list.add(new Integer[] { x + 1, y - 2 });

        // Check si c'est hors tableau, si oui alors suprime la position
        Iterator<Integer[]> i = list.iterator();
        Integer[] l;
        while (i.hasNext()) {
            l = i.next();
            if (l[0] < 0 || l[0] >= 25 || l[1] < 0 || l[1] >= 18)
                i.remove();
        }
        return list;
    }

    @Override
    protected void shootAt(Bloon target, List<Projectile> projectiles) {
        turnToward(target.pos);
        Projectile p;
        if (rightUpgrade == 3) {
            Position offsetSpawn = target.pos.minus(pos).normalized().inGridSpace().rotate(90).multi(0.002);
            p = new Plasma(pos.plus(offsetSpawn), target.pos.minus(pos.plus(offsetSpawn)), projSpeed, range);
            Projectile p2 = new Plasma(pos.minus(offsetSpawn), target.pos.minus(pos.minus(offsetSpawn)), projSpeed,
                    range);
            projectiles.add(p2);
        } else if (leftUpgrade == 3)
            p = new Sunball(pos, target.pos.minus(pos), projSpeed);
        else if (leftUpgrade == 1)
            p = new Laser(pos, target.pos.minus(pos), projSpeed, 1, pierce, range);
        else if (leftUpgrade == 2)
            p = new Plasma(pos, target.pos.minus(pos), projSpeed, range);
        else
            p = new Dart(pos, target.pos.minus(pos), projSpeed, 1, pierce, range);
        projectiles.add(p);
    }

    /**
     * Affiche la tour ainsi que sa portée si elle est sélectionnée
     * 
     * @Override Affiche aussi le temple si il est amélioré a
     * @param selectedTile la Tile selectionné
     */
    @Override
    public void draw(Tile selectedTile) {
        if (leftUpgrade == 3)
            StdDraw.picture(pos.x, pos.y, Assets.superMonkeyTemple);
        super.draw(selectedTile);
    }
}
