package warcraftTD.Projectiles;

import java.awt.Color;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import warcraftTD.Position;
import warcraftTD.StdDraw;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Bloons.MetalBloon;

/**
 * Classe parente de tous les projectiles
 */
public abstract class Projectile {

    protected Position pos;
    protected Position dir;
    protected double velocity;
    protected String sprite;
    protected double rotation = 0; // rotation du sprite
    protected double traveledDistance = 0;
    public boolean canPopLead = false; // est-ce que le projectile peut éclater les ballon de métal ?
    protected Set<Bloon> bloonsHitted; // On fait un historique des ballons touchés pour ne pas toucher plusieur fois
                                       // le même
    private final double SPEED_RATIO = ((double) 720 / 1240); // la fenêtre n'étant pas carré la vitesse X n'est pas la
                                                              // même que Y donc nous égalisont avevc cette constante
                                                              // pour que le ballon ce déplace à la même vitesse
                                                              // horizontalement et verticalement
    public double maxRange = 9999; // portée infini par défaut
    public int pierce = 1; // le nombre de ballons persable avant de disparaitre
    public boolean remove; // est-ce que ce projectile doit etre supprimer au prochain tick ?
    public int damage = 1; // damage infliger au bloon
    public double hitboxRadius = 0.75; // rayon de hitbox en grid space

    public Projectile(Position startingPos, String imgPath) {
        this.pos = new Position(startingPos);
        this.sprite = imgPath;
        bloonsHitted = new HashSet<Bloon>();
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
    protected boolean isColliding(Bloon b) {
        return b.targetable && pos.inGridSpace().dist(b.pos.inGridSpace()) < b.hitboxRadius + hitboxRadius;
    }

    /**
     * regarde s'il y a des collision avec des Bloons
     * 
     * @param bloons liste de tous les Bloon
     */
    protected void checkCollision(List<Bloon> bloons) {
        for (Bloon b : bloons) {
            if (remove)
                return;
            if (isColliding(b) && b.hp > 0 && !bloonsHitted.contains(b)) {
                if (b instanceof MetalBloon) {
                    if (canPopLead)
                        hit(b);
                } else
                    hit(b);
            }
        }
    }

    /**
     * Applique les dégats au ballon b
     * 
     * @param b le ballon cible
     */
    protected void hit(Bloon b) {
        bloonsHitted.add(b);
        b.hp -= damage;
        if (--pierce <= 0)
            remove = true;
    }

    /**
     * Déplace le projectiles
     */
    protected void move() {
        // Mesure le vecteur vitesse
        Position speedVec = dir.multi(Math.sqrt(Math.pow(dir.x * SPEED_RATIO, 2) + Math.pow(dir.y, 2))).multi(velocity);

        traveledDistance += speedVec.inGridSpace(false).norm();
        pos = pos.plus(speedVec);
    }

    /**
     * Update du projectile
     */
    public void tick(List<Bloon> bloons) {
        if (isOutOfBound() || traveledDistance > maxRange)
            remove = true;
        if (remove)
            return;
        rotation = dir.angle(new Position(0, 1));
        move();
        checkCollision(bloons);
    }

    /**
     * Affiche le projectile et si le mode debug est actif, affiche la hitbox
     * 
     * @param debug est-ce que le mode debug est actif ?
     */
    public void draw(boolean debug) {
        StdDraw.picture(this.pos.x, this.pos.y, this.sprite, rotation);

        if (debug) // Draw hitbox on debug
            drawHitbox();
    }

    /**
     * Affiche la hitbox du projectile
     */
    protected void drawHitbox() {
        Position range = new Position(hitboxRadius, hitboxRadius).inFrameSpace();
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(new Color(227, 252, 3, 200));
        StdDraw.ellipse(pos.x, pos.y, range.x, range.y);
        StdDraw.setPenColor(new Color(227, 252, 3, 60));
        StdDraw.filledEllipse(pos.x, pos.y, range.x, range.y);
    }

}
