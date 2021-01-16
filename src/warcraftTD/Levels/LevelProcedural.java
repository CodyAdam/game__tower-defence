package warcraftTD.Levels;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Tiles.Decor;
import warcraftTD.Tiles.Empty;
import warcraftTD.Tiles.Road;

public class LevelProcedural extends Level {

    public LevelProcedural() {
        super();
        spritePath = Assets.levelProcedural;

        pathing.add(getSidePathingPoint());
        for (int i = 0; i < 5; i++) {
            pathing.add(getRandomPointNotNear(6));
        }
        pathing.add(getSidePathingPoint());

        for (int y = 0; y < nbSquareY; y++) {
            for (int x = 0; x < nbSquareX; x++) {
                if (map[x][y] == null)
                    if (road[x][y])
                        map[x][y] = new Road(x, y);
                    else if (decor[x][y])
                        map[x][y] = new Decor(x, y);
                    else
                        map[x][y] = new Empty(x, y);
            }
        }
    }

    private Position getSidePathingPoint() {
        int maxX = nbSquareX - 6;
        int maxY = nbSquareY;
        Position ans;
        double randomizer = Math.random();
        if (randomizer < 0.25)
            ans = new Position(rInt(0, maxX), 0); // point sur le côté bas
        else if (randomizer < 0.5)
            ans = new Position(rInt(0, maxX), maxY); // point sur le côté haut
        else if (randomizer < 0.75)
            ans = new Position(0, rInt(0, maxY)); // point sur le côté gauche
        else
            ans = new Position(maxX, rInt(0, maxY)); // point sur le côté droite
        return ans.inFrameSpace();
    }

    /**
     * @param a a int
     * @param b a int
     * @return give a random int between a and b
     */
    private int rInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    /**
     * @param radius la distance minimal entre plusieurs points de pathing (in grid
     *               space)
     * @return un nouveau point qui n'est pas trop proche d'un autre point du
     *         pathing
     */
    private Position getRandomPointNotNear(double radius) {
        int maxX = nbSquareX - 6;
        int maxY = nbSquareY;
        Position p;
        boolean isTooClose = false;
        int tries = 0;

        do {
            p = new Position(rInt(0, maxX), rInt(0, maxY));
            for (Position pos : pathing) {
                if (pos.inGridSpace().distInGridSpace(p) < radius)
                    isTooClose = true;
            }
        } while (isTooClose && tries++ < 30);
        return p.inFrameSpace();
    }
}
