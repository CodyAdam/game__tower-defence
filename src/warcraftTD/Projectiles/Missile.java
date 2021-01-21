package warcraftTD.Projectiles;

import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Bloons.BlackBloon;
import warcraftTD.Bloons.Bloon;

public class Missile extends Projectile {
    private int explosionDuration = 4; // nombre de tick que l'on affiche le sprite d'explosion
    private boolean exploding = false;

    public Missile(Position startingPos, Position direction, double velocity, int damage, double range) {
        super(startingPos, Assets.missile);
        this.dir = new Position(direction).normalized();
        this.velocity = velocity;
        this.damage = damage;
        this.maxRange = range;
        pierce = 1;
        canPopLead = true;
    }

    /**
     * regarde s'il y a des collision avec des Bloons
     * 
     * @Override applique une explosion quand on touche le premier enemie
     * @param bloons liste de tous les Bloon
     */
    @Override
    protected void checkCollision(List<Bloon> bloons) {
        if (!exploding)
            for (Bloon b : bloons) {
                if (remove)
                    return;
                if (isColliding(b) && b.hp > 0) {
                    explode(this.pos, damage, 2, bloons);
                }
            }
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
    protected void explode(Position pos, int damage, double radius, List<Bloon> bloons) {
        exploding = true;
        for (Bloon b : bloons) {
            if (!(b instanceof BlackBloon))
                if (b.pos.inGridSpace(false).dist(pos.inGridSpace(false)) < radius)
                    b.hp -= damage;
        }
    }

    /**
     * Update du projectile
     * 
     * @Override remplace le sprite avec une explosion si le projectile a touché un
     *           bloon
     * @param bloons la liste de tous les bloons
     */
    @Override
    public void tick(List<Bloon> bloons) {
        if (exploding && !remove) {
            hitboxRadius = 2;
            sprite = Assets.explosion;
            explosionDuration--;
            if (explosionDuration <= 0)
                remove = true;
        } else {
            super.tick(bloons);
        }
    }
}
