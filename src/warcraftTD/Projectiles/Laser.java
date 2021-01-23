package warcraftTD.Projectiles;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Bloons.BlackAirBloon;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Bloons.BlueAirBloon;
import warcraftTD.Bloons.RedAirBloon;

/**
 * Classe utilisé par le DartlingGun Upgrade lvl 3 a gauche pour lancer des
 * rayons laser
 */
public class Laser extends Dart {
    public Laser(Position startingPos, Position direction, double velocity, int damage, int pierce, double range) {
        super(startingPos, direction, velocity, damage, pierce, range);
        this.sprite = Assets.laser;
        this.canPopLead = true;
    }

    /**
     * Applique les dégats au ballon b
     * 
     * @Override si la cible est un Air Bloon alors on inflige 2x plus de dégâts
     * @param b le ballon cible
     */
    @Override
    protected void hit(Bloon b) {
        if (b instanceof BlueAirBloon || b instanceof RedAirBloon || b instanceof BlackAirBloon)
            b.hp -= 2 * damage;
        else
            b.hp -= damage;

        if (--pierce <= 0)
            remove = true;
    }

}
