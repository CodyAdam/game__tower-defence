package warcraftTD.Projectiles;

import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Bloons.Bloon;

public class Tack extends Dart {

    private int tickCounter = 0;
    private boolean isShootingBlade;

    public Tack(Position startingPos, Position direction, double velocity, int damage, int pierce, double range,
            boolean isShootingBlade) {
        super(startingPos, direction, velocity, damage, pierce, range);
        this.isShootingBlade = isShootingBlade;
        if (!isShootingBlade)
            this.sprite = Assets.dart;
        else
            this.sprite = Assets.blade;
    }

    @Override
    public void tick(List<Bloon> bloons) {
        super.tick(bloons);

        if (isShootingBlade) {
            final int ROTATION_SPEED = 4;
            tickCounter++;
            if (tickCounter % ROTATION_SPEED == 0)
                rotation++;
        }

    }

}
