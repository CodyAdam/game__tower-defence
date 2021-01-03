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
    public String sprite;
    public Position framePos;
    public double range;
    public int cooldown;
    public double rotation;
    protected int timer;

    public Monkey(int x, int y) {
        super(x, y);
        this.framePos = new Position(0, 0);
        isAvaliable = false;
        gridColor = new Color(200, 100, 0, 140);
        rotation = 0;
        timer = 0;
    }

    protected Bloon getClosiest(List<Bloon> bloons) {
        Bloon ans = null;
        double dist = -1;
        for (Bloon b : bloons) {
            double x = this.framePos.distInGridSpace(b.pos);
            if (dist > x || dist == -1) {
                dist = x;
                ans = b;
            }
        }
        return ans;
    }

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
    public ArrayList<Integer[]> getOccupiedTiles() {
        ArrayList<Integer[]> list = new ArrayList<Integer[]>();

        if (x + 1 >= 0 && x + 1 < 25 && y >= 0 && y < 18)
            list.add(new Integer[] { x + 1, y });
        if (x >= 0 && x < 25 && y + 1 >= 0 && y + 1 < 18)
            list.add(new Integer[] { x, y + 1 });
        if (x - 1 >= 0 && x - 1 < 25 && y >= 0 && y < 18)
            list.add(new Integer[] { x - 1, y });
        if (x >= 0 && x < 25 && y - 1 >= 0 && y - 1 < 18)
            list.add(new Integer[] { x, y - 1 });
        return list;
    }

    protected void turnToward(Bloon target) {
        rotation = this.framePos.minus(target.pos).angle() + 90;
    }

    public void tick(List<Bloon> bloons) {
        if (!bloons.isEmpty() && timer <= 0) {
            Bloon target = getClosiest(bloons);
            if (target != null) {
                shootAt(target);
                timer = cooldown;
            }
        }
        timer -= 1;
    }

    protected abstract void shootAt(Bloon bloons);

    public void draw() {
        StdDraw.picture(framePos.x, framePos.y, sprite, rotation);
    }
}
