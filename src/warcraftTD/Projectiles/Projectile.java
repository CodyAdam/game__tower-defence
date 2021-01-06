package warcraftTD.Projectiles;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.StdDraw;
import warcraftTD.Bloons.Bloon;

public abstract class Projectile {
    public boolean remove;

    private Position pos;
    private Position dir;
    private double velocity;
    private String sprite;
    private double hitboxRadius;
    private double rotation = 0;

    public Projectile(Position startingPos, Position direction, double velocity, String imgPath, double hitboxRadius) {
        this.pos = new Position(startingPos);
        this.dir = new Position(direction);
        this.velocity = velocity;
        this.sprite = imgPath;
        this.hitboxRadius = hitboxRadius;
    }

    public boolean isOutOfBound() {
        return pos.x < 0 || pos.x > 1 || pos.y < 0 || pos.y > 1;
    }

    public void tick(List<Bloon> bloons) {
        for (Bloon b : bloons) {
            // TODO CHECK COLISION
        }
    }

    public void draw() {
        StdDraw.picture(this.pos.x, this.pos.y, this.sprite);
    }

}
