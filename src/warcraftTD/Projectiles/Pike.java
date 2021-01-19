package warcraftTD.Projectiles;

import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Bloons.CeramicBloon;

public class Pike extends Projectile {
    private int animationCounter; // compteur de frame pour savoir quand changer de sprite
    private final double ANIMATION_SPEED = 0.13;

    public Pike(Position startingPos, Position direction, double velocity, int damage, int pierce) {
        super(startingPos, direction, velocity, Assets.pike0);
        this.pierce = pierce;
        this.damage = damage;
        this.hitboxRadius *= 1.3;
    }

    @Override
    public void tick(List<Bloon> bloons) {
        super.tick(bloons);
        animationCounter += 1;
        if ((int) (animationCounter * ANIMATION_SPEED) % 2 == 0)
            sprite = Assets.pike0;
        else
            sprite = Assets.pike1;
    }

    /**
     * Applique les dégats au ballon b, si b est un céramic alors inflige x + 2
     * 
     * @param b le ballon cible
     */
    @Override
    protected void hit(Bloon b) {
        if (b instanceof CeramicBloon) {
            bloonsHitted.add(b);
            b.hp -= damage + 2;
            if (--pierce <= 0)
                remove = true;
        } else
            super.hit(b);

    }

}
