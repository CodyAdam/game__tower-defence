package warcraftTD.Levels;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Tiles.Decor;
import warcraftTD.Tiles.Empty;
import warcraftTD.Tiles.Road;

public class LevelProcedural extends Level {

    public LevelProcedural(int mode) {
        super();
        isProcedural = true;
        spritePath = Assets.levelProcedural;

        // STEP 1 : Adding the starting point on the border
        pathing.add(getRandomPoint(10, true));

        // STEP 2 : Completing with random points that are more than 15 tile away from
        // eachothers and angled > 90 deg
        for (int i = 0; i < rInt(4, 10); i++)
            pathing.add(getRandomPoint(5, false));

        // STEP 3 : Adding the ending point on the border
        pathing.add(getRandomPoint(10, true));

        // STEP 4 : Rounding every corners of the path depending on the mode
        switch (mode) {
            case 0:
                pathing = roundPath(this.pathing, 5);
                break;
            case 1:
                pathing = roundPath(this.pathing, 5, false, 0.07);
                break;
            case 2:
                break;
        }

        // STEP 5 : Place road tiles under the path
        SetupRoad();

        // Then fill the map grid
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

    /**
     * Ajoute toutes les routes au bons emplacements en utilisant un algorithme de
     * rasterisation de ligne (Basé sur l'algorithme Bresenham simplifier)
     */
    private void SetupRoad() {
        for (int i = 0; i < pathing.size() - 1; i++) {
            Position current = pathing.get(i).inGridSpace();
            Position next = pathing.get(i + 1).inGridSpace();
            for (Position p : getPixelsBetween(current, next)) {
                if (p.x >= 0 && p.y >= 0 && p.x < 31 && p.y < 18)
                    road[(int) (p.x)][(int) (p.y)] = true;
            }
        }
    }

    /**
     * @param curr un point dans l'espace dans la grille
     * @param next un point dans l'espace dans la grille
     * @return une liste points qui correspond au pixels qui sont entre les deux
     *         points curr et next
     */
    private List<Position> getPixelsBetween(Position curr, Position next) {
        curr = curr.plus(new Position(0.5, 0.5));
        next = next.plus(new Position(0.5, 0.5));
        List<Position> ans = new ArrayList<Position>();
        int accuracy = 20;
        double dist = next.dist(curr);
        Position increment = next.minus(curr).multi(1 / (accuracy * dist));
        for (int i = 0; i <= accuracy * dist; i++) {
            ans.add(curr.plus(increment.multi(i)));// Add the point

            // Also add some other point around the current point to increase "radius" of
            // the time
            ans.add(curr.plus(increment.multi(i)).plus(new Position(0, 0.2)));
            ans.add(curr.plus(increment.multi(i)).plus(new Position(0, -0.2)));
            ans.add(curr.plus(increment.multi(i)).plus(new Position(0.2, 0)));
            ans.add(curr.plus(increment.multi(i)).plus(new Position(-0.2, 0)));
        }
        return ans;
    }

    /**
     * @param radius      la distance minimal entre plusieurs points de pathing (in
     *                    grid space)
     * @param onPerimeter est-ce que le point est sur les bord de l'écran?
     * @return un nouveau point qui n'est pas trop proche d'un autre point du
     *         pathing
     */
    private Position getRandomPoint(double radius, boolean onPerimeter) {
        int maxX = nbSquareX - 6;
        int maxY = nbSquareY;
        Position p;
        int tries = 0;
        Position maxPoint = new Position(0, 0);
        double max = 0;
        outer: do {
            boolean isTooClose = false;
            boolean angleIsTooShallow = false;
            if (onPerimeter) {
                double randomizer = Math.random();
                if (randomizer < 0.25)
                    p = new Position(rInt(0, maxX) + 0.5, 0); // point sur le côté bas
                else if (randomizer < 0.5)
                    p = new Position(rInt(0, maxX) + 0.5, maxY); // point sur le côté haut
                else if (randomizer < 0.75)
                    p = new Position(0, rInt(0, maxY) + 0.5); // point sur le côté gauche
                else
                    p = new Position(maxX, rInt(0, maxY) + 0.5); // point sur le côté droite
            } else
                p = new Position(rInt(1, maxX - 1) + 0.5, rInt(1, maxY - 1) + 0.5);
            for (Position pos : pathing) {
                if (pos.inGridSpace(false).dist(p) < radius) {
                    // si le point est trop proche d'un autre alors
                    // on choisi un autre point
                    isTooClose = true;
                    continue outer;
                }
            }
            if (pathing.size() >= 2) { // si l'angle est trop faible alors on choisi un
                                       // autre point
                Position previousPoint = pathing.get(pathing.size() - 1);
                Position dir1 = p.inFrameSpace().minus(previousPoint).inGridSpace();
                Position dir2 = pathing.get(pathing.size() - 2).minus(previousPoint).inGridSpace();
                double angle = dir1.inGridSpace().angle(dir2.inGridSpace());
                if (angle < 90) {
                    angleIsTooShallow = true;
                    if (angle > max) {
                        maxPoint = p;
                        max = angle;
                    }
                    continue outer;
                }
            }
            if (!isTooClose && !angleIsTooShallow)
                break outer;

        } while (tries++ < 1000); // essaye jusqu'à 100 fois si le point est trop
                                  // proche d'un autre
        if (tries > 1000 && pathing.size() >= 2)
            return maxPoint.inFrameSpace();
        return p.inFrameSpace();
    }

    /**
     * @param a un int
     * @param b un int
     * @return Donne un entier random entre a et b
     */
    private int rInt(int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }
}
