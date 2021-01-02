package warcraftTD.Tiles.Monkeys;

import warcraftTD.Position;
import warcraftTD.StdDraw;
import warcraftTD.Tiles.Empty;
import warcraftTD.Tiles.Tile;
import java.awt.Color;
import java.util.ArrayList;

public abstract class Monkey extends Tile {
    public String sprite;
    public Position framePos;
    public double rotation;

    public Monkey(int x, int y) {
        super(x, y);
        this.framePos = new Position(0, 0);
        isAvaliable = false;
        gridColor = new Color(200, 100, 0, 140);
        rotation = 0;
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

    public void draw() {
        StdDraw.picture(framePos.x, framePos.y, sprite, rotation);
    }
}
