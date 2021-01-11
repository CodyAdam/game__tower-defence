package warcraftTD.Tiles.Monkeys;

import warcraftTD.Position;
import warcraftTD.StdDraw;
import warcraftTD.Bloons.Bloon;
import warcraftTD.Projectiles.Projectile;
import warcraftTD.Tiles.Empty;
import warcraftTD.Tiles.Tile;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe parente des toutes les tours du jeu
 */
public abstract class Monkey extends Tile {
    public String sprite; // le chemin vers l'image de la tour
    public Position spriteOffset; // le centre de la tour (pour l'affichage)
    public Position pos; // Position de la tour dans le référenciel de la fenêtre
    public double range; // Rayon de tir en nombre de tile
    public int cooldown; // le temps de recharchement du tir
    public double rotation; // orientation de la tour en degree
    protected int timer; // timer pour savoir quand tirer
    public int cost; // prix pour placer la tour

    protected String targetingMode;// variable permetant de choisir quel énemie ciblé
    private List<String> targetingModes;// liste des types de ciblage

    // système d'amélioration de la tour
    public int leftUpgrade = 0;
    public int rightUpgrade = 0;
    public List<Upgrade> leftUpgrades;
    public List<Upgrade> rightUpgrades;

    /**
     * Classe qui représente une amélioration de tour avec toutes ses informations
     */
    public class Upgrade {
        public String name;
        public String name2; // used for multi-lines rendering
        public String description;
        public String description2;// used for multi-lines rendering
        public int price;

        public Upgrade(String name, String name2, String description, int price) {
            this.name = name;
            this.name2 = name2;
            this.description = description;
            this.description2 = "";
            this.price = price;
        }

        public Upgrade(String name, String name2, String description, String description2, int price) {
            this.name = name;
            this.name2 = name2;
            this.description = description;
            this.description2 = description2;
            this.price = price;
        }
    }

    public Monkey(int x, int y) {
        super(x, y);
        pos = new Position(0, 0);
        isAvaliable = false;
        gridColor = new Color(200, 100, 0, 140);
        rotation = 0;
        timer = 0;
        leftUpgrades = new ArrayList<Upgrade>();
        rightUpgrades = new ArrayList<Upgrade>();
        targetingModes = new LinkedList<String>();
        targetingModes.add("First");
        targetingModes.add("Last");
        targetingModes.add("Strong");
        targetingModes.add("Close");
        targetingMode = targetingModes.get(0);
    }

    /**
     * @param isLeft est-ce qu'il s'agit de l'upgrade
     * @return rend la prochaine upgrade
     */
    public Upgrade getNextUpgrade(boolean isLeft) {
        int index = isLeft ? leftUpgrade : rightUpgrade;
        List<Upgrade> upgradeList = isLeft ? leftUpgrades : rightUpgrades;
        if (index < upgradeList.size())
            return upgradeList.get(index);
        return null;
    }

    /**
     * Upgrade la tour puis change ses propriétées
     * 
     * @param isLeft est-ce qu'il s'agit de l'upgrade de gauche ?
     */
    public void upgrade(boolean isLeft) {
        if (isLeft)
            postUpgradeLeft(++leftUpgrade);
        else
            postUpgradeRight(++rightUpgrade);
    }

    /**
     * @return rend le mode de ciblage
     */
    public String getTargetingMode() {
        return targetingMode;
    }

    /**
     * Change le mode de ciblage au mode précédent
     */
    public void prevTargetingMode() {
        int i = (targetingModes.indexOf(targetingMode) - 1) % targetingModes.size();
        if (i < 0)
            i += targetingModes.size();
        targetingMode = targetingModes.get(i);
    }

    /**
     * Change le mode de ciblage au mode suivant
     */
    public void nextTargetingMode() {
        int i = (targetingModes.indexOf(targetingMode) + 1) % targetingModes.size();
        targetingMode = targetingModes.get(i);
    }

    /**
     * @param target le Ballon à comparer
     * @return est-ce que la "target" est dans le rayon de tir de la tour ?
     */
    protected boolean inRange(Bloon target) {
        return pos.distInGridSpace(target.pos) <= range;
    }

    /**
     * @param bloons la liste de tous les ballons
     * @return le Bloon le plus proche de cet tour dans le rayon d'attaque
     */
    protected Bloon getClosest(List<Bloon> bloons) {
        Bloon ans = null;
        double dist = -1;
        for (Bloon b : bloons) {
            double value = this.pos.distInGridSpace(b.pos);
            if (inRange(b) && b.targetable && (value < dist || dist == -1)) {
                dist = value;
                ans = b;
            }
        }
        return ans;
    }

    /**
     * @param bloons la liste de tous les ballons
     * @return le Bloon le plus fort dans le rayon d'attaque
     */
    protected Bloon getStrongest(List<Bloon> bloons) {
        Bloon ans = null;
        double max = -1;
        for (Bloon b : bloons) {
            double value = b.power;
            if (inRange(b) && b.targetable && (value > max || max == -1)) {
                max = value;
                ans = b;
            }
        }
        return ans;
    }

    /**
     * @param bloons la liste de tous les ballons
     * @return le Bloon le plus loins dans le chemin dans le rayon d'attaque
     */
    protected Bloon getFirst(List<Bloon> bloons) {
        Bloon ans = null;
        double max = -1;
        for (Bloon b : bloons) {
            double value = b.traveledDistance;
            if (inRange(b) && b.targetable && (value > max || max == -1)) {
                max = value;
                ans = b;
            }
        }
        return ans;
    }

    /**
     * @param bloons la liste de tous les ballons
     * @return le Bloon le moins loins dans le chemin dans le rayon d'attaque
     */
    protected Bloon getLast(List<Bloon> bloons) {
        Bloon ans = null;
        double max = -1;
        for (Bloon b : bloons) {
            double value = b.traveledDistance;
            if (inRange(b) && b.targetable && (value < max || max == -1)) {
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
        rotation = this.pos.minus(target.pos).angle() + 90;
    }

    /**
     * Update la tour
     */
    public void tick(List<Bloon> bloons, List<Projectile> projectiles) {
        if (!bloons.isEmpty() && timer <= 0) {
            Bloon target;
            switch (targetingMode) {
                case "First":
                    target = getFirst(bloons);
                    break;
                case "Last":
                    target = getLast(bloons);
                    break;
                case "Close":
                    target = getClosest(bloons);
                    break;
                case "Strong":
                    target = getStrongest(bloons);
                    break;
                default:
                    target = null;
                    System.err.println("ERROR : targeting mode not found");
            }

            if (target != null) {
                shootAt(target, projectiles);
                timer = cooldown;
            }
        }
        timer -= 1;
    }

    /**
     * Affiche la tour ainsi que sa portée si elle est sélectionnée
     */
    public void draw(Tile selectedTile) {
        if (selectedTile == this) // Affiche le rayon si la tour est sélectionnée ou en train d'etre pose
            drawRange();
        Position offset = spriteOffset.inGridSpace(false).rotate(rotation).inFrameSpace();
        StdDraw.picture(pos.x + offset.x, pos.y + offset.y, sprite, rotation);
    }

    /**
     * Affiche la portée de la tour en rouge
     */
    private void drawRange() {
        Position range = new Position(this.range, this.range).inFrameSpace();
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(new Color(252, 3, 65, 110));
        StdDraw.ellipse(pos.x, pos.y, range.x, range.y);
        StdDraw.setPenColor(new Color(252, 3, 65, 60));
        StdDraw.filledEllipse(pos.x, pos.y, range.x, range.y);
    }

    /**
     * Permet au différentes tours de tirer
     * 
     * @param bloons      la liste de tous les bloon
     * @param projectiles la liste de tous les projectiles
     */
    protected abstract void shootAt(Bloon bloons, List<Projectile> projectiles);

    /**
     * actualise les propriétées de la tour après l'avoir amélioré
     * 
     * @param upgradeLevel le niveau d'amilioration actuel du côté gauche
     */
    protected abstract void postUpgradeLeft(int upgradeLevel);

    /**
     * actualise les propriétées de la tour après l'avoir amélioré
     * 
     * @param upgradeLevel le niveau d'amilioration actuel du côté droit
     */
    protected abstract void postUpgradeRight(int upgradeLevel);
}
