package warcraftTD.Levels;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.Tiles.Decor;
import warcraftTD.Tiles.Empty;
import warcraftTD.Tiles.Road;

public class Level1 extends Level {
    public Level1() {
        super();
        spritePath = Assets.level1;

        // remplie la liste de points par lequel les Bloons vont passer dans l'ordre.
        pathing.add(new Position(0, 0.447222222));
        pathing.add(new Position(0.1596774193548387, 0.447222222));
        pathing.add(new Position(0.1596774193548387, 0.615));
        pathing.add(new Position(0.29274193548387095, 0.615));
        pathing.add(new Position(0.29274193548387095, 0.0805555555555556));
        pathing.add(new Position(0.4846774193548387, 0.0805555555555556));
        pathing.add(new Position(0.4846774193548387, 0.7652777777777777));
        pathing.add(new Position(0.1620967741935484, 0.7652777777777777));
        pathing.add(new Position(0.1620967741935484, 0.93));
        pathing.add(new Position(0.5903225806451613, 0.93));
        pathing.add(new Position(0.5903225806451613, 0.7708333333333334));
        pathing.add(new Position(0.7088709677419355, 0.7708333333333334));
        pathing.add(new Position(0.7088709677419355, 0.6069444444444445));
        pathing.add(new Position(0.5943548387096774, 0.6069444444444445));
        pathing.add(new Position(0.5943548387096774, 0.3041666666666667));
        pathing.add(new Position(0.1620967741935484, 0.3041666666666667));
        pathing.add(new Position(0.1620967741935484, 0));
        pathing = roundPath(pathing, 4, false, 0.03); // Arrondi les angles

        // Ajout des ponts
        // 1
        pathing.add(61, new Position(0.4846774193548387, 0.4444444444444444, true));
        pathing.add(61, new Position(0.4846774193548387, 0.1513888888888889));
        // 2
        pathing.add(171, new Position(0.20403225806451614, 0.3027777777777778, true));
        pathing.add(171, new Position(0.38387096774193546, 0.3041666666666667));
        pathing.remove(pathing.get(173));
        pathing.remove(pathing.get(173));
        pathing.remove(pathing.get(173));
        pathing.remove(pathing.get(173));

        // remplie la map de vide,d'obstacles ou de route... en fonction de l'arriere
        // planaaaa
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
