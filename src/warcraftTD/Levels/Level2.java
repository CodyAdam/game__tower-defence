package warcraftTD.Levels;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Tiles.Decor;
import warcraftTD.Tiles.Empty;
import warcraftTD.Tiles.Road;

public class Level2 extends Level {
    public Level2() {
        super();
        spritePath = Assets.level2;

        // remplie la liste de points par lequel les Bloons vont passer dans l'ordre.
        pathing.add(new Position(0.329, 0));
        pathing.add(new Position(0.329, 0.19));
        pathing.add(new Position(0.321, 0.211));
        pathing.add(new Position(0.301, 0.225));
        pathing.add(new Position(0.063, 0.225));
        pathing.add(new Position(0.047, 0.232));
        pathing.add(new Position(0.035, 0.267));
        pathing.add(new Position(0.035, 0.454));
        pathing.add(new Position(0.043, 0.482));
        pathing.add(new Position(0.059, 0.496));
        pathing.add(new Position(0.158, 0.496));
        pathing.add(new Position(0.18, 0.503));
        pathing.add(new Position(0.166, 0.538));
        pathing.add(new Position(0.127, 0.611));
        pathing.add(new Position(0.123, 0.632));
        pathing.add(new Position(0.131, 0.66));
        pathing.add(new Position(0.192, 0.768));
        pathing.add(new Position(0.204, 0.789));
        pathing.add(new Position(0.22, 0.796));
        pathing.add(new Position(0.236, 0.786));
        pathing.add(new Position(0.294, 0.694));
        pathing.add(new Position(0.315, 0.686));
        pathing.add(new Position(0.329, 0.719));
        pathing.add(new Position(0.329, 0.863));
        pathing.add(new Position(0.337, 0.89));
        pathing.add(new Position(0.361, 0.897));
        pathing.add(new Position(0.454, 0.897));
        pathing.add(new Position(0.474, 0.883));
        pathing.add(new Position(0.482, 0.849));
        pathing.add(new Position(0.482, 0.706));
        pathing.add(new Position(0.4995, 0.6799));
        pathing.add(new Position(0.519, 0.685));
        pathing.add(new Position(0.57, 0.785));
        pathing.add(new Position(0.59, 0.799));
        pathing.add(new Position(0.615, 0.778));
        pathing.add(new Position(0.682, 0.654));
        pathing.add(new Position(0.69, 0.626));
        pathing.add(new Position(0.682, 0.592));
        pathing.add(new Position(0.643, 0.525));
        pathing.add(new Position(0.63, 0.495));
        pathing.add(new Position(0.651, 0.49));
        pathing.add(new Position(0.748, 0.49));
        pathing.add(new Position(0.768, 0.469));
        pathing.add(new Position(0.772, 0.428));
        pathing.add(new Position(0.772, 0.264));
        pathing.add(new Position(0.76, 0.229));
        pathing.add(new Position(0.74, 0.215));
        pathing.add(new Position(0.506, 0.215));
        pathing.add(new Position(0.49, 0.201));
        pathing.add(new Position(0.486, 0.167));
        pathing.add(new Position(0.486, 0));

        // remplie la map de vide,d'obstacles ou de route...

        boolean[][] decor = new boolean[31][18];

        decor[0][5] = true;
        decor[0][3] = true;
        decor[0][14] = true;
        decor[0][13] = true;
        decor[0][4] = true;
        decor[0][12] = true;
        decor[0][11] = true;
        decor[22][17] = true;
        decor[23][12] = true;
        decor[23][9] = true;
        decor[23][10] = true;
        decor[23][6] = true;
        decor[23][11] = true;
        decor[24][11] = true;
        decor[23][7] = true;
        decor[24][7] = true;
        decor[23][4] = true;
        decor[24][4] = true;
        decor[23][5] = true;
        decor[24][5] = true;
        decor[24][6] = true;
        decor[24][10] = true;
        decor[24][9] = true;
        decor[24][12] = true;

        boolean[][] road = new boolean[31][18];

        road[8][2] = true;
        road[17][10] = true;
        road[5][9] = true;
        road[21][13] = true;
        road[16][5] = true;
        road[15][8] = true;
        road[20][14] = true;
        road[4][5] = true;
        road[5][0] = true;
        road[13][14] = true;
        road[17][4] = true;
        road[14][14] = true;
        road[3][8] = true;
        road[22][12] = true;
        road[9][3] = true;
        road[13][4] = true;
        road[14][16] = true;
        road[17][7] = true;
        road[19][13] = true;
        road[5][13] = true;
        road[9][8] = true;
        road[20][10] = true;
        road[6][11] = true;
        road[5][15] = true;
        road[9][11] = true;
        road[13][13] = true;
        road[21][12] = true;
        road[5][2] = true;
        road[8][4] = true;
        road[22][13] = true;
        road[10][13] = true;
        road[5][3] = true;
        road[8][16] = true;
        road[8][1] = true;
        road[13][16] = true;
        road[5][5] = true;
        road[5][1] = true;
        road[1][8] = true;
        road[17][15] = true;
        road[14][10] = true;
        road[11][14] = true;
        road[11][16] = true;
        road[14][12] = true;
        road[18][6] = true;
        road[4][2] = true;
        road[4][1] = true;
        road[9][2] = true;
        road[17][9] = true;
        road[8][13] = true;
        road[6][16] = true;
        road[10][1] = true;
        road[18][13] = true;
        road[5][8] = true;
        road[6][13] = true;
        road[15][4] = true;
        road[0][8] = true;
        road[3][7] = true;
        road[12][16] = true;
        road[17][6] = true;
        road[5][4] = true;
        road[15][13] = true;
        road[14][2] = true;
        road[18][7] = true;
        road[21][14] = true;
        road[19][14] = true;
        road[14][1] = true;
        road[10][16] = true;
        road[18][16] = true;
        road[18][14] = true;
        road[21][10] = true;
        road[4][16] = true;
        road[15][16] = true;
        road[9][6] = true;
        road[18][15] = true;
        road[19][10] = true;
        road[8][10] = true;
        road[1][7] = true;
        road[10][14] = true;
        road[22][11] = true;
        road[4][9] = true;
        road[11][1] = true;
        road[9][1] = true;
        road[4][15] = true;
        road[15][11] = true;
        road[15][9] = true;
        road[9][14] = true;
        road[4][13] = true;
        road[4][3] = true;
        road[9][9] = true;
        road[18][5] = true;
        road[17][16] = true;
        road[8][7] = true;
        road[17][13] = true;
        road[14][8] = true;
        road[8][5] = true;
        road[4][14] = true;
        road[12][1] = true;
        road[8][14] = true;
        road[4][7] = true;
        road[17][14] = true;
        road[4][10] = true;
        road[16][4] = true;
        road[8][3] = true;
        road[13][1] = true;
        road[9][5] = true;
        road[9][13] = true;
        road[6][10] = true;
        road[17][8] = true;
        road[8][8] = true;
        road[7][10] = true;
        road[9][4] = true;
        road[18][8] = true;
        road[4][8] = true;
        road[16][16] = true;
        road[14][9] = true;
        road[14][4] = true;
        road[20][13] = true;
        road[4][11] = true;
        road[21][11] = true;
        road[9][10] = true;
        road[15][12] = true;
        road[8][9] = true;
        road[15][10] = true;
        road[4][0] = true;
        road[18][9] = true;
        road[5][14] = true;
        road[11][13] = true;
        road[15][14] = true;
        road[18][10] = true;
        road[8][6] = true;
        road[7][14] = true;
        road[9][7] = true;
        road[7][16] = true;
        road[2][7] = true;
        road[6][14] = true;
        road[7][11] = true;
        road[8][11] = true;
        road[5][11] = true;
        road[7][13] = true;
        road[2][8] = true;
        road[12][14] = true;
        road[12][4] = true;
        road[5][10] = true;
        road[22][10] = true;
        road[12][13] = true;
        road[5][7] = true;
        road[14][11] = true;
        road[4][4] = true;
        road[0][7] = true;
        road[9][16] = true;
        road[15][1] = true;
        road[5][16] = true;
        road[14][5] = true;
        road[15][5] = true;
        road[13][5] = true;
        road[15][2] = true;
        road[14][13] = true;
        road[12][5] = true;
        road[17][5] = true;

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

}
