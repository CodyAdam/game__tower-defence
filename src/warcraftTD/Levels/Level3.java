package warcraftTD.Levels;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Tiles.Decor;
import warcraftTD.Tiles.Empty;
import warcraftTD.Tiles.Road;

public class Level3 extends Level {
    public Level3() {
        super();
        spritePath = Assets.level3;

        // remplie la liste de points par lequel les Bloons vont passer dans l'ordre.
        pathing.add(new Position(0.022, 1));
        pathing.add(new Position(0.12, 0.84));
        pathing.add(new Position(0.125, 0.826));
        pathing.add(new Position(0.122, 0.808));
        pathing.add(new Position(0.115, 0.772));
        pathing.add(new Position(0.116, 0.726));
        pathing.add(new Position(0.132, 0.692));
        pathing.add(new Position(0.154, 0.668));
        pathing.add(new Position(0.183, 0.664));
        pathing.add(new Position(0.206, 0.682));
        pathing.add(new Position(0.22, 0.717));
        pathing.add(new Position(0.225, 0.754));
        pathing.add(new Position(0.223, 0.799));
        pathing.add(new Position(0.207, 0.831));
        pathing.add(new Position(0.181, 0.85));
        pathing.add(new Position(0.151, 0.846));
        pathing.add(new Position(0.127, 0.821));
        pathing.add(new Position(0.114, 0.779));
        pathing.add(new Position(0.107, 0.733));
        pathing.add(new Position(0.104, 0.694));
        pathing.add(new Position(0.107, 0.658));
        pathing.add(new Position(0.119, 0.622));
        pathing.add(new Position(0.136, 0.59));
        pathing.add(new Position(0.154, 0.569));
        pathing.add(new Position(0.188, 0.55));
        pathing.add(new Position(0.202, 0.543));
        pathing.add(new Position(0.209, 0.524));
        pathing.add(new Position(0.237, 0.367));
        pathing.add(new Position(0.233, 0.343));
        pathing.add(new Position(0.219, 0.322));
        pathing.add(new Position(0.209, 0.276));
        pathing.add(new Position(0.209, 0.238));
        pathing.add(new Position(0.222, 0.193));
        pathing.add(new Position(0.244, 0.168));
        pathing.add(new Position(0.274, 0.164));
        pathing.add(new Position(0.3, 0.182));
        pathing.add(new Position(0.315, 0.211));
        pathing.add(new Position(0.319, 0.249));
        pathing.add(new Position(0.315, 0.29));
        pathing.add(new Position(0.303, 0.319));
        pathing.add(new Position(0.286, 0.34));
        pathing.add(new Position(0.26, 0.35));
        pathing.add(new Position(0.246, 0.368));
        pathing.add(new Position(0.218, 0.525));
        pathing.add(new Position(0.218, 0.547));
        pathing.add(new Position(0.224, 0.558));
        pathing.add(new Position(0.242, 0.568));
        pathing.add(new Position(0.257, 0.586));
        pathing.add(new Position(0.262, 0.594));
        pathing.add(new Position(0.276, 0.626));
        pathing.add(new Position(0.284, 0.661));
        pathing.add(new Position(0.287, 0.681));
        pathing.add(new Position(0.295, 0.694));
        pathing.add(new Position(0.315, 0.694));
        pathing.add(new Position(0.38, 0.689));
        pathing.add(new Position(0.398, 0.69));
        pathing.add(new Position(0.406, 0.701));
        pathing.add(new Position(0.41, 0.731));
        pathing.add(new Position(0.417, 0.764));
        pathing.add(new Position(0.429, 0.786));
        pathing.add(new Position(0.453, 0.8));
        pathing.add(new Position(0.477, 0.801));
        pathing.add(new Position(0.502, 0.781));
        pathing.add(new Position(0.514, 0.747));
        pathing.add(new Position(0.517, 0.706));
        pathing.add(new Position(0.513, 0.672));
        pathing.add(new Position(0.499, 0.639));
        pathing.add(new Position(0.491, 0.61));
        pathing.add(new Position(0.493, 0.581));
        pathing.add(new Position(0.523, 0.464));
        pathing.add(new Position(0.525, 0.428));
        pathing.add(new Position(0.515, 0.401));
        pathing.add(new Position(0.493, 0.357));
        pathing.add(new Position(0.486, 0.314));
        pathing.add(new Position(0.484, 0.258));
        pathing.add(new Position(0.494, 0.206));
        pathing.add(new Position(0.515, 0.162));
        pathing.add(new Position(0.543, 0.135));
        pathing.add(new Position(0.574, 0.128));
        pathing.add(new Position(0.611, 0.133));
        pathing.add(new Position(0.623, 0.135));
        pathing.add(new Position(0.644, 0.117));
        pathing.add(new Position(0.665, 0.081));
        pathing.add(new Position(0.71, 0));

        // remplie la map de vide,d'obstacles ou de route...

        decor[0][8] = true;
        decor[0][7] = true;
        decor[1][7] = true;
        decor[1][6] = true;
        decor[0][6] = true;
        decor[0][5] = true;
        decor[20][17] = true;
        decor[20][16] = true;
        decor[21][16] = true;
        decor[21][17] = true;
        decor[22][16] = true;
        decor[22][17] = true;

        road[5][10] = true;
        road[6][10] = true;
        road[0][17] = true;
        road[1][17] = true;
        road[1][16] = true;
        road[2][16] = true;
        road[2][15] = true;
        road[3][15] = true;
        road[3][14] = true;
        road[4][15] = true;
        road[5][15] = true;
        road[6][15] = true;
        road[6][14] = true;
        road[7][15] = true;
        road[8][14] = true;
        road[8][13] = true;
        road[8][12] = true;
        road[9][12] = true;
        road[10][12] = true;
        road[11][12] = true;
        road[12][12] = true;
        road[12][13] = true;
        road[13][14] = true;
        road[14][14] = true;
        road[15][14] = true;
        road[15][11] = true;
        road[13][11] = true;
        road[15][10] = true;
        road[15][9] = true;
        road[16][8] = true;
        road[16][7] = true;
        road[15][6] = true;
        road[15][7] = true;
        road[14][5] = true;
        road[15][5] = true;
        road[14][4] = true;
        road[15][3] = true;
        road[16][2] = true;
        road[17][2] = true;
        road[18][2] = true;
        road[19][2] = true;
        road[20][3] = true;
        road[20][4] = true;
        road[20][5] = true;
        road[20][6] = true;
        road[19][7] = true;
        road[18][7] = true;
        road[20][1] = true;
        road[21][0] = true;
        road[6][13] = true;
        road[6][12] = true;
        road[4][12] = true;
        road[3][13] = true;
        road[3][12] = true;
        road[3][11] = true;
        road[4][10] = true;
        road[6][9] = true;
        road[7][10] = true;
        road[8][11] = true;
        road[7][7] = true;
        road[7][6] = true;
        road[8][6] = true;
        road[9][5] = true;
        road[9][3] = true;
        road[7][3] = true;
        road[6][3] = true;
        road[6][4] = true;
        road[6][5] = true;
        road[11][4] = true;
        road[10][4] = true;
        road[12][4] = true;
        road[13][4] = true;
        road[6][8] = true;
        road[5][12] = true;
        road[15][13] = true;
        road[14][11] = true;
        road[17][7] = true;
        road[6][8] = true;
        road[8][3] = true;
        road[15][12] = true;
        road[16][12] = true;
        road[8][10] = true;
        road[9][6] = true;
        road[7][14] = true;
        road[17][5] = true;
        road[16][5] = true;
        road[17][6] = true;

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
