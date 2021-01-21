package warcraftTD.Projectiles;

import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Bloons.Bloon;

public class Tack extends Dart {
    private boolean isShootingBlade;

    public Tack(Position startingPos, Position direction, double velocity, int damage, int pierce, double range,
            boolean isShootingBlade) {
        super(startingPos, direction, velocity, damage, pierce, range);
        this.isShootingBlade = isShootingBlade;
        if (!isShootingBlade) {
            hitboxRadius *= 0.5;
            this.sprite = Assets.tack;
        } else {
            this.sprite = Assets.blade;
        }
    }

    /**
     * Update du projectile
     * 
     * @Override ajoute une animation de rotation quand isShootingBlade==true
     * @param bloons la liste de tous les bloons
     */
    @Override
    public void tick(List<Bloon> bloons) {
        if (isOutOfBound() || traveledDistance > maxRange)
            remove = true;
        if (remove)
            return;
        if (isShootingBlade)
            rotation += 10;
        else
            rotation = dir.angle(new Position(0, 1));
        move();
        checkCollision(bloons);
    }
}
