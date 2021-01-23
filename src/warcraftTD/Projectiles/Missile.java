package warcraftTD.Projectiles;

import java.util.List;
import java.util.ListIterator;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.StdDraw;
import warcraftTD.Bloons.BlackBloon;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Bloons.ZebraBloon;

public class Missile extends Projectile {
    private int explosionDuration = 4; // nombre de tick que l'on affiche le sprite d'explosion
    protected boolean exploding = false;
    protected double explosionRadius;

    public Missile(Position startingPos, Position direction, double velocity, int damage, double range,
            double explosionRadius) {
        super(startingPos, Assets.missile);
        this.dir = new Position(direction).normalized();
        this.velocity = velocity;
        this.damage = damage;
        this.maxRange = range;
        hitboxRadius = 0.25;
        this.explosionRadius = explosionRadius;
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
                    explode(this.pos, damage, explosionRadius, bloons);
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
            if (!(b instanceof BlackBloon || b instanceof ZebraBloon))
                if (b.pos.inGridSpace(false).dist(pos.inGridSpace(false)) < radius)
                    b.hp -= damage;
        }
    }

    /**
     * Update du projectile
     * 
     * @Override remplace le sprite avec une explosion si le projectile a touché un
     *           bloon et explose si hors portée
     * @param bloons la liste de tous les bloons
     */
    @Override
    public void tick(List<Bloon> bloons, ListIterator<Projectile> i) {
        this.porjectilesIterator = i;
        if (exploding && !remove) {
            hitboxRadius = explosionRadius;
            explosionDuration--;
            if (explosionDuration <= 0)
                remove = true;
        } else {
            if (remove)
                return;
            else if (isOutOfBound() || traveledDistance > maxRange)
                explode(this.pos, damage, explosionRadius, bloons);
            else {
                rotation = dir.inGridSpace().angle(new Position(0, 1));
                move();
                checkCollision(bloons);
            }
        }
    }

    /**
     * Affiche le projectile et si le mode debug est actif, affiche la hitbox
     * 
     * @Override Affiche l'explosion avec la bonne taille
     * @param debug est-ce que le mode debug est actif ?
     */
    @Override
    public void draw(boolean debug) {
        if (exploding) {
            Position size = new Position(199.0 / (2 * 1240), 171.0 / (2 * 720)).multi(explosionRadius);
            StdDraw.picture(this.pos.x, this.pos.y, Assets.explosion, size.x, size.y, rotation);
        } else
            StdDraw.picture(this.pos.x, this.pos.y, this.sprite, rotation);

        if (debug) // Draw hitbox on debug
            drawHitbox();
    }
}
