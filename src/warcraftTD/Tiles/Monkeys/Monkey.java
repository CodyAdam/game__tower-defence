package warcraftTD.Tiles.Monkeys;

import warcraftTD.Position;
import warcraftTD.StdDraw;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Tiles.Empty;
import warcraftTD.Tiles.Tile;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public abstract class Monkey extends Tile {
    public String sprite; // le chemin vers l'image de la tour
    public Position framePos; // Position de la tour dans le référenciel de la fenêtre
    public double range; // Rayon de tir en nombre de tile
    public int cooldown; // le temps de recharchement du tir
    public double rotation; // orientation de la tour
    protected int timer; // timer pour savoir quand tirer

    public Monkey(int x, int y) {
        super(x, y);
        this.framePos = new Position(0, 0);
        isAvaliable = false;
        gridColor = new Color(200, 100, 0, 140);
        rotation = 0;
        timer = 0;
    }

    /**
     * @param target le Ballon à comparer
     * @return est-ce que la "target" est dans le rayon de tir de la tour ?
     */
    protected boolean inRange(Bloon target) {
        return framePos.distInGridSpace(target.pos) <= range;
    }

    /**
     * @param bloons la liste de tout les ballons
     * @return le Bloon le plus proche de cet tour dans le rayon d'attaque
     */
    protected Bloon getClosiest(List<Bloon> bloons) {
        Bloon ans = null;
        double dist = -1;
        for (Bloon b : bloons) {
            double value = this.framePos.distInGridSpace(b.pos);
            if (inRange(b) && (value < dist || dist == -1)) {
                dist = value;
                ans = b;
            }
        }
        return ans;
    }

    /**
     * @param bloons la liste de tout les ballons
     * @return le Bloon le plus fort dans le rayon d'attaque
     */
    protected Bloon getStrongest(List<Bloon> bloons) {
        Bloon ans = null;
        double max = -1;
        for (Bloon b : bloons) {
            double value = b.power;
            if (inRange(b) && (value > max || max == -1)) {
                max = value;
                ans = b;
            }
        }
        return ans;
    }

    /**
     * @param bloons la liste de tout les ballons
     * @return le Bloon le plus loins dans le chemin dans le rayon d'attaque
     */
    protected Bloon getFirst(List<Bloon> bloons) {
        Bloon ans = null;
        double max = -1;
        for (Bloon b : bloons) {
            double value = b.traveledDistance;
            if (inRange(b) && (value > max || max == -1)) {
                max = value;
                ans = b;
            }
        }
        return ans;
    }

    /**
     * @param bloons la liste de tout les ballons
     * @return le Bloon le moins loins dans le chemin dans le rayon d'attaque
     */
    protected Bloon getLast(List<Bloon> bloons) {
        Bloon ans = null;
        double max = -1;
        for (Bloon b : bloons) {
            double value = b.traveledDistance;
            if (inRange(b) && (value < max || max == -1)) {
                max = value;
                ans = b;
            }
        }
        return ans;
    }

    /**
     * @param cx  la coordonnée X de la grille
     * @param cy  la coordonnée Y de la grille
     * @param map la grille avec ses tuiles
     * @return est-ce que l'emplacement (cx, cy) est un emplacement valide pour
     *         placer cette tour ?
     */
    public boolean isPlacableAt(int cx, int cy, Tile[][] map) {
        boolean answer = (map[cx][cy] instanceof Empty);
        if (cx + 1 >= 0 && cx + 1 < 25)
            answer = answer && map[cx + 1][cy].isAvaliable;
        if (cy + 1 >= 0 && cy + 1 < 18)
            answer = answer && map[cx][cy + 1].isAvaliable;
        if (cx - 1 >= 0 && cx - 1 < 25)
            answer = answer && map[cx - 1][cy].isAvaliable;
        if (cy - 1 >= 0 && cy - 1 < 18)
            answer = answer && map[cx][cy - 1].isAvaliable;
        return answer;
    }

    /**
     * @return revoi les coordonnées des cases adjacentes de la tour
     */
    public ArrayList<Integer[]> getAdjacent() {
        ArrayList<Integer[]> list = new ArrayList<Integer[]>();

        if (x + 1 >= 0 && x + 1 < 25 && y >= 0 && y < 18) // ne rend pas les cases si elle sont hors grille
            list.add(new Integer[] { x + 1, y });
        if (x >= 0 && x < 25 && y + 1 >= 0 && y + 1 < 18)
            list.add(new Integer[] { x, y + 1 });
        if (x - 1 >= 0 && x - 1 < 25 && y >= 0 && y < 18)
            list.add(new Integer[] { x - 1, y });
        if (x >= 0 && x < 25 && y - 1 >= 0 && y - 1 < 18)
            list.add(new Integer[] { x, y - 1 });
        return list;
    }

    /**
     * Modifie l'angle de la tour pour l'orienté vers la "target"
     * 
     * @param target un Bloon
     */
    protected void turnToward(Bloon target) {
        rotation = this.framePos.minus(target.pos).angle() + 90;
    }

    public void tick(List<Bloon> bloons) {
        if (!bloons.isEmpty() && timer <= 0) {
            Bloon target = getFirst(bloons);
            if (target != null) {
                shootAt(target);
                timer = cooldown;
            }
        }
        timer -= 1;
    }

    /**
     * Permet au différentes tours de tirer
     */
    protected abstract void shootAt(Bloon bloons);

    public void draw(Tile selectedTile) {
        if (selectedTile == this) {
            Position range = new Position(this.range, this.range).inFrameSpace();
            StdDraw.ellipse(framePos.x, framePos.y, range.x, range.y);
        }
        StdDraw.picture(framePos.x, framePos.y, sprite, rotation);
    }
}
