package warcraftTD.Projectiles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import warcraftTD.Position;
import warcraftTD.StdDraw;
import warcraftTD.Bloons.Bloon;

public abstract class Projectile {

    private Position pos;
    private Position dir;
    private double velocity;
    private String sprite;
    private double rotation = 0; // rotation du sprite
    private double traveledDistance = 0;
    private List<String> types;

    private final double SPEED_RATIO = ((double) 720 / 1240); // la fenêtre n'étant pas carré la vitesse X n'est pas la
                                                              // même que Y donc nous égalisont avevc cette constante
                                                              // pour que le ballon ce déplace à la même vitesse
                                                              // horizontalement et verticalement
    public double maxRange = 9999; // portée infini par défaut
    public int pierce = 1; // le nombre de ballons persable avant de disparaitre
    public boolean remove; // est-ce que ce projectile doit etre supprimer au prochain tick ?
    public int damage = 1; // damage infliger au bloon
    public double hitboxRadius = 0.25; // rayon de hitbox en grid space

    public Projectile(Position startingPos, Position direction, double velocity, String imgPath) {
        types = new ArrayList<String>();
        this.pos = new Position(startingPos);
        this.dir = new Position(direction).normalized();
        this.velocity = velocity;
        this.sprite = imgPath;
    }

    /**
     * @return est-ce que le projectile est hors de la fenêtre ?
     */
    public boolean isOutOfBound() {
        return pos.x < 0 || pos.x > 1 || pos.y < 0 || pos.y > 1;
    }

    /**
     * @param b le bloon à comparer
     * @return est-ce que le projectile touche le bloon b ?
     */
    private boolean isColliding(Bloon b) {
        return b.targetable && pos.distInGridSpace(b.pos) < b.hitboxRadius + hitboxRadius;
    }

    /**
     * regarde s'il y a des collision avec des Bloons
     * 
     * @param bloons liste de tous les Bloon
     */
    private void checkCollision(List<Bloon> bloons) {
        for (Bloon b : bloons) {
            if (remove)
                return;
            if (isColliding(b) && b.hp > 0) {
                b.hp -= damage;
                if (--pierce <= 0)
                    remove = true;
            }
        }
    }

    private void move() {
        // Mesure le vecteur vitesse
        Position speedVec = dir.multi(Math.sqrt(Math.pow(dir.x * SPEED_RATIO, 2) + Math.pow(dir.y, 2))).multi(velocity);
        traveledDistance += speedVec.normInGridSpace();
        pos = pos.plus(speedVec);
    }

    public void tick(List<Bloon> bloons) {
        if (isOutOfBound() || traveledDistance > maxRange)
            remove = true;
        if (remove)
            return;
        rotation = dir.angle() - 90;
        move();
        checkCollision(bloons);
    }

    /**
     * Affiche le projectile et si le mode debug est actif, affiche la hitbox
     */
    public void draw(boolean debug) {
        StdDraw.picture(this.pos.x, this.pos.y, this.sprite, rotation);
        if (debug) { // Draw hitbox on debug
            Position range = new Position(hitboxRadius, hitboxRadius).inFrameSpace();
            StdDraw.setPenRadius(0.005);
            StdDraw.setPenColor(new Color(252, 3, 65, 110));
            StdDraw.ellipse(pos.x, pos.y, range.x, range.y);
            StdDraw.setPenColor(new Color(252, 3, 65, 60));
            StdDraw.filledEllipse(pos.x, pos.y, range.x, range.y);
        }
    }

    public void draw() {
        draw(false);
    }

}
