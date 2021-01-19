package warcraftTD.Projectiles;

import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.StdDraw;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Bloons.MetalBloon;

public class Flame extends Projectile {

    private int cooldown = 0;
    private int maxCooldown = 7; // nombre de ticks neccésaire entre chaque dégats

    public Flame(Position startingPos, double range) {
        super(startingPos, Assets.fire);
        this.pierce = 999999;
        this.damage = 1;
        this.maxRange = range;
        this.canPopLead = true;
        this.hitboxRadius = 0;
    }

    @Override
    public void tick(List<Bloon> bloons) {
        if (traveledDistance > maxRange)
            remove = true;
        if (remove)
            return;
        traveledDistance += 3.0 / 60;
        double percent = traveledDistance / maxRange;
        hitboxRadius = maxRange * percent;
        rotation += 10;
        if (--cooldown < 0) {
            cooldown = maxCooldown;
            checkCollision(bloons);
        }
    }

    /**
     * regarde s'il y a des collision avec des Bloons
     * 
     * @Override ne check pas si les bloons on déja été touchées
     * @param bloons liste de tous les Bloon
     */
    @Override
    protected void checkCollision(List<Bloon> bloons) {
        for (Bloon b : bloons) {
            if (remove)
                return;
            if (isColliding(b) && b.hp > 0) {
                if (b instanceof MetalBloon) {
                    if (canPopLead)
                        hit(b);
                } else
                    hit(b);
            }
        }
    }

    @Override
    public void draw(boolean debug) {
        Position maxSize = new Position(232.0 / 1240, 232.0 / 720).multi(maxRange * 0.35);
        double percent = traveledDistance / maxRange;
        StdDraw.picture(this.pos.x, this.pos.y, this.sprite, (maxSize.x * percent), (maxSize.y * percent), rotation);

        if (debug) // Draw hitbox on debug
            drawHitbox();
    }

}
