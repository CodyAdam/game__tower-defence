package warcraftTD.Projectiles;

import warcraftTD.Assets;
import warcraftTD.Position;

/**
 * Classe utilisé par le DartlingGun Upgrade lvl 3 a gauche pour lancer des
 * rayons laser
 */
public class Sunball extends Plasma {
    public Sunball(Position startingPos, Position direction, double velocity) {
        super(startingPos, direction, velocity, 99999);
        this.sprite = Assets.sunball;
        this.canPopLead = true;
        this.damage = 1;
        this.pierce = 50;
        hitboxRadius = 3;
    }
}
