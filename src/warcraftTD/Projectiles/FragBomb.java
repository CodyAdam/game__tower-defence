package warcraftTD.Projectiles;

import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Bloons.BlackAirBloon;
import warcraftTD.Bloons.BlueAirBloon;
import warcraftTD.Bloons.RedAirBloon;
import warcraftTD.Bloons.BlackBloon;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Bloons.ZebraBloon;

public class FragBomb extends Missile {

    private boolean isMissile;
    private int bombType; // 0 = regular bomb || 1 = frag tack || 2 = frag bomb

    public FragBomb(Position startingPos, Position direction, double velocity, int damage, double range,
            double explosionRadius, int bombType, String sprite, boolean isMissile) {
        super(startingPos, direction, velocity, damage, range, explosionRadius);
        this.sprite = sprite;
        this.isMissile = isMissile;
        this.bombType = bombType;
    }

    /**
     * Remplace la fonction hit (hit fait du single target) mais applique des dégats
     * de zone
     * 
     * @param pos    position du centre de l'explosion
     * @param damage dégâts de l'explosion
     * @param radius rayon de l'explosion
     * @param bloons la liste de tous les bloons
     */
    @Override
    protected void explode(Position pos, int damage, double radius, List<Bloon> bloons) {
        exploding = true;
        for (Bloon b : bloons) {
            if (!(b instanceof BlackBloon || b instanceof ZebraBloon))
                if (b.pos.inGridSpace(false).dist(pos.inGridSpace(false)) < radius)
                    if (isMissile
                            && (b instanceof BlackAirBloon || b instanceof BlueAirBloon || b instanceof RedAirBloon))
                        b.hp -= damage * 10;
                    else
                        b.hp -= damage;
        }

        if (bombType != 0) {
            Projectile d0;
            Projectile d1;
            Projectile d2;
            Projectile d3;
            Projectile d4;
            Projectile d5;
            Projectile d6;
            Projectile d7;
            double SPEED = 0.02;
            double range = 3;
            if (bombType == 2) {// Shoot mini bombs or tacks
                d0 = new FragBomb(pos, new Position(1, 0).inFrameSpace(), SPEED, damage, range, explosionRadius * 0.6,
                        0, Assets.bomb, false);
                d1 = new FragBomb(pos, new Position(-1, 0).inFrameSpace(), SPEED, damage, range, explosionRadius * 0.6,
                        0, Assets.bomb, false);
                d2 = new FragBomb(pos, new Position(0, 1).inFrameSpace(), SPEED, damage, range, explosionRadius * 0.6,
                        0, Assets.bomb, false);
                d3 = new FragBomb(pos, new Position(0, -1).inFrameSpace(), SPEED, damage, range, explosionRadius * 0.6,
                        0, Assets.bomb, false);
                d4 = new FragBomb(pos, new Position(1, 1).inFrameSpace(), SPEED, damage, range, explosionRadius * 0.6,
                        0, Assets.bomb, false);
                d5 = new FragBomb(pos, new Position(1, -1).inFrameSpace(), SPEED, damage, range, explosionRadius * 0.6,
                        0, Assets.bomb, false);
                d6 = new FragBomb(pos, new Position(-1, 1).inFrameSpace(), SPEED, damage, range, explosionRadius * 0.6,
                        0, Assets.bomb, false);
                d7 = new FragBomb(pos, new Position(-1, -1).inFrameSpace(), SPEED, damage, range, explosionRadius * 0.6,
                        0, Assets.bomb, false);
            } else {
                d0 = new Tack(pos, new Position(1, 0).inFrameSpace(), SPEED, damage, 1, range, false);
                d1 = new Tack(pos, new Position(-1, 0).inFrameSpace(), SPEED, damage, 1, range, false);
                d2 = new Tack(pos, new Position(0, 1).inFrameSpace(), SPEED, damage, 1, range, false);
                d3 = new Tack(pos, new Position(0, -1).inFrameSpace(), SPEED, damage, 1, range, false);
                d4 = new Tack(pos, new Position(1, 1).inFrameSpace(), SPEED, damage, 1, range, false);
                d5 = new Tack(pos, new Position(1, -1).inFrameSpace(), SPEED, damage, 1, range, false);
                d6 = new Tack(pos, new Position(-1, 1).inFrameSpace(), SPEED, damage, 1, range, false);
                d7 = new Tack(pos, new Position(-1, -1).inFrameSpace(), SPEED, damage, 1, range, false);
            }
            this.porjectilesIterator.add(d0);
            this.porjectilesIterator.add(d1);
            this.porjectilesIterator.add(d2);
            this.porjectilesIterator.add(d3);
            this.porjectilesIterator.add(d4);
            this.porjectilesIterator.add(d5);
            this.porjectilesIterator.add(d6);
            this.porjectilesIterator.add(d7);
        }
    }

}
