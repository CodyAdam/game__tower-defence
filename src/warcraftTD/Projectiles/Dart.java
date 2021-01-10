package warcraftTD.Projectiles;

import warcraftTD.Assets;
import warcraftTD.Position;

public class Dart extends Projectile {
    public Dart(Position startingPos, Position direction, double velocity, int damage, int pierce) {
        super(startingPos, direction, velocity, Assets.dart);
        this.pierce = pierce;
        this.damage = damage;
    }
}
