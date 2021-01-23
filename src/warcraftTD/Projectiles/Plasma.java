package warcraftTD.Projectiles;

import warcraftTD.Assets;
import warcraftTD.Position;

/**
 * Classe utilisé par le DartlingGun Upgrade lvl 3 a gauche pour lancer des
 * rayons laser
 */
public class Plasma extends Laser {
    public Plasma(Position startingPos, Position direction, double velocity, double range) {
        super(startingPos, direction, velocity, 1, 4, range);
        this.sprite = Assets.plasma;
        this.velocity *= 1.4;
        this.canPopLead = true;
    }
}
