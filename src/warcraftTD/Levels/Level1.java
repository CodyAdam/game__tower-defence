package warcraftTD.Levels;

import java.util.ArrayList;

import warcraftTD.Position;
import warcraftTD.Tiles.Empty;
import warcraftTD.Tiles.Obstructed;
import warcraftTD.Tiles.Panel;
import warcraftTD.Tiles.Tile;

public class Level1 extends Level {

    public Level1() {
        spritePath = "/Assets/Sprites/monkey_lane_thumb.jpg";
        nbSquareX = 31;
        nbSquareY = 18;

        // remplie la liste de points par lequel les Bloons vont passer dans l'ordre.
        pathing = new ArrayList<Position>();
        pathing.add(new Position(0, 0.45));
        pathing.add(new Position(0.155, 0.45));
        pathing.add(new Position(0.155, 0.600));
        pathing.add(new Position(0.295, 0.600));
        pathing.add(new Position(0.295, 0.085));
        pathing.add(new Position(0.485, 0.085));
        pathing.add(new Position(0.485, 0.769));
        pathing.add(new Position(0.162, 0.769));
        pathing.add(new Position(0.162, 0.929));
        pathing.add(new Position(0.590, 0.929));
        pathing.add(new Position(0.590, 0.775));
        pathing.add(new Position(0.710, 0.775));
        pathing.add(new Position(0.710, 0.600));
        pathing.add(new Position(0.590, 0.600));
        pathing.add(new Position(0.590, 0.290));
        pathing.add(new Position(0.157, 0.290));
        pathing.add(new Position(0.157, 0.000));

        map = new Tile[nbSquareX][nbSquareY];

        // remplie la map de vide ou avec des obstacles
        // on fait ça en brut car plus rapide
        boolean[][] obstructed = new boolean[31][18];
        obstructed[8][2] = true;
        obstructed[23][12] = true;
        obstructed[17][10] = true;
        obstructed[5][9] = true;
        obstructed[21][13] = true;
        obstructed[16][5] = true;
        obstructed[15][8] = true;
        obstructed[20][14] = true;
        obstructed[4][5] = true;
        obstructed[5][0] = true;
        obstructed[13][14] = true;
        obstructed[0][5] = true;
        obstructed[17][4] = true;
        obstructed[14][14] = true;
        obstructed[3][8] = true;
        obstructed[22][12] = true;
        obstructed[9][3] = true;
        obstructed[13][4] = true;
        obstructed[14][16] = true;
        obstructed[17][7] = true;
        obstructed[19][13] = true;
        obstructed[5][13] = true;
        obstructed[9][8] = true;
        obstructed[23][9] = true;
        obstructed[20][10] = true;
        obstructed[6][11] = true;
        obstructed[5][15] = true;
        obstructed[9][11] = true;
        obstructed[13][13] = true;
        obstructed[0][3] = true;
        obstructed[21][12] = true;
        obstructed[5][2] = true;
        obstructed[8][4] = true;
        obstructed[22][13] = true;
        obstructed[10][13] = true;
        obstructed[5][3] = true;
        obstructed[8][16] = true;
        obstructed[8][1] = true;
        obstructed[13][16] = true;
        obstructed[5][5] = true;
        obstructed[5][1] = true;
        obstructed[1][8] = true;
        obstructed[17][15] = true;
        obstructed[14][10] = true;
        obstructed[11][14] = true;
        obstructed[11][16] = true;
        obstructed[14][12] = true;
        obstructed[18][6] = true;
        obstructed[4][2] = true;
        obstructed[4][1] = true;
        obstructed[0][14] = true;
        obstructed[9][2] = true;
        obstructed[17][9] = true;
        obstructed[8][13] = true;
        obstructed[6][16] = true;
        obstructed[23][10] = true;
        obstructed[10][1] = true;
        obstructed[18][13] = true;
        obstructed[5][8] = true;
        obstructed[6][13] = true;
        obstructed[15][4] = true;
        obstructed[0][8] = true;
        obstructed[3][7] = true;
        obstructed[12][16] = true;
        obstructed[17][6] = true;
        obstructed[5][4] = true;
        obstructed[15][13] = true;
        obstructed[14][2] = true;
        obstructed[18][7] = true;
        obstructed[21][14] = true;
        obstructed[19][14] = true;
        obstructed[14][1] = true;
        obstructed[10][16] = true;
        obstructed[18][16] = true;
        obstructed[18][14] = true;
        obstructed[21][10] = true;
        obstructed[4][16] = true;
        obstructed[15][16] = true;
        obstructed[9][6] = true;
        obstructed[18][15] = true;
        obstructed[19][10] = true;
        obstructed[8][10] = true;
        obstructed[1][7] = true;
        obstructed[23][6] = true;
        obstructed[10][14] = true;
        obstructed[22][11] = true;
        obstructed[4][9] = true;
        obstructed[11][1] = true;
        obstructed[9][1] = true;
        obstructed[4][15] = true;
        obstructed[15][11] = true;
        obstructed[15][9] = true;
        obstructed[0][11] = true;
        obstructed[9][14] = true;
        obstructed[4][13] = true;
        obstructed[4][3] = true;
        obstructed[9][9] = true;
        obstructed[18][5] = true;
        obstructed[17][16] = true;
        obstructed[8][7] = true;
        obstructed[23][11] = true;
        obstructed[17][13] = true;
        obstructed[14][8] = true;
        obstructed[8][5] = true;
        obstructed[4][14] = true;
        obstructed[12][1] = true;
        obstructed[23][7] = true;
        obstructed[8][14] = true;
        obstructed[4][7] = true;
        obstructed[17][14] = true;
        obstructed[4][10] = true;
        obstructed[16][4] = true;
        obstructed[8][3] = true;
        obstructed[13][1] = true;
        obstructed[9][5] = true;
        obstructed[9][13] = true;
        obstructed[6][10] = true;
        obstructed[0][13] = true;
        obstructed[17][8] = true;
        obstructed[8][8] = true;
        obstructed[0][4] = true;
        obstructed[7][10] = true;
        obstructed[9][4] = true;
        obstructed[18][8] = true;
        obstructed[4][8] = true;
        obstructed[16][16] = true;
        obstructed[14][9] = true;
        obstructed[14][4] = true;
        obstructed[20][13] = true;
        obstructed[4][11] = true;
        obstructed[21][11] = true;
        obstructed[9][10] = true;
        obstructed[15][12] = true;
        obstructed[8][9] = true;
        obstructed[15][10] = true;
        obstructed[4][0] = true;
        obstructed[18][9] = true;
        obstructed[5][14] = true;
        obstructed[11][13] = true;
        obstructed[15][14] = true;
        obstructed[18][10] = true;
        obstructed[8][6] = true;
        obstructed[7][14] = true;
        obstructed[9][7] = true;
        obstructed[7][16] = true;
        obstructed[2][7] = true;
        obstructed[6][14] = true;
        obstructed[7][11] = true;
        obstructed[8][11] = true;
        obstructed[23][4] = true;
        obstructed[5][11] = true;
        obstructed[7][13] = true;
        obstructed[2][8] = true;
        obstructed[12][14] = true;
        obstructed[12][4] = true;
        obstructed[5][10] = true;
        obstructed[22][10] = true;
        obstructed[12][13] = true;
        obstructed[5][7] = true;
        obstructed[14][11] = true;
        obstructed[4][4] = true;
        obstructed[0][7] = true;
        obstructed[0][12] = true;
        obstructed[9][16] = true;
        obstructed[15][1] = true;
        obstructed[5][16] = true;
        obstructed[14][5] = true;
        obstructed[15][5] = true;
        obstructed[13][5] = true;
        obstructed[15][2] = true;
        obstructed[14][13] = true;
        obstructed[12][5] = true;
        obstructed[17][5] = true;
        obstructed[23][5] = true;
        obstructed[22][17] = true;
        for (int y = 0; y < nbSquareY; y++) {
            for (int x = 0; x < nbSquareX; x++) {
                if (x >= nbSquareX - 6)
                    map[x][y] = new Panel();
                else if (obstructed[x][y] || x == 24) {
                    map[x][y] = new Obstructed();
                } else {
                    map[x][y] = new Empty();
                }
            }
        }
    }

}
