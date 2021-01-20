package warcraftTD.Projectiles;

import warcraftTD.Assets;
import warcraftTD.Position;

public class Laser extends Dart {
    public Laser(Position startingPos, Position direction, double velocity, int damage, int pierce, double range) {
        super(startingPos, direction, velocity, damage, pierce, range);
        this.dir = new Position(direction).normalized();
        this.sprite = Assets.laser;
        this.canPopLead = true;
        this.velocity = velocity;
        this.pierce = pierce;
        this.damage = damage;
        this.maxRange = range;
    }
}
