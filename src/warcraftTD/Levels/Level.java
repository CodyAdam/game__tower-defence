package warcraftTD.Levels;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Position;
import warcraftTD.Tiles.ChangeTargetLeft;
import warcraftTD.Tiles.ChangeTargetRight;
import warcraftTD.Tiles.Panel;
import warcraftTD.Tiles.PlayButton;
import warcraftTD.Tiles.RemoveTower;
import warcraftTD.Tiles.SpeedupButton;
import warcraftTD.Tiles.Tile;
import warcraftTD.Tiles.UpgradeLeft;
import warcraftTD.Tiles.UpgradeRight;
import warcraftTD.Tiles.BuyTiles.*;

/**
 * Classe parente des tous les niveaux du jeu
 */
public abstract class Level {
    // Répertorie le chemin de l'image du niveau
    public String spritePath;

    // ensemble de point qui forme le chemin que les Bloons vont parcourir (soit le
    // premier étant le spawn)
    public List<Position> pathing;

    // grille qui determine qu'est-ce que qu'il y a sur la carte à sa création
    // (ex : Rien, Route, Arbre et rochers)
    public Tile[][] map;

    // taille de la grille en hauteur (nbSquareY) et largeur (nbSquareX)
    public int nbSquareX;
    public int nbSquareY;

    public boolean[][] road;
    public boolean[][] decor;

    public Level() {
        nbSquareX = 31;
        nbSquareY = 18;
        road = new boolean[nbSquareX][nbSquareY];
        decor = new boolean[nbSquareX][nbSquareY];
        map = new Tile[nbSquareX][nbSquareY];
        pathing = new ArrayList<Position>();

        // Set HUD to the correct location on the map grid

        for (int y = 0; y < 18; y++)
            for (int x = 25; x < 31; x++)
                map[x][y] = new Panel(x, y);

        // Set the HUD tiless to the correct location

        map[23][0] = new PlayButton(23, 0);
        map[23][1] = new PlayButton(23, 1);
        map[24][1] = new PlayButton(24, 1);
        map[24][0] = new PlayButton(24, 0);

        map[23][17] = new SpeedupButton(23, 17);
        map[23][16] = new SpeedupButton(23, 16);
        map[24][16] = new SpeedupButton(24, 16);
        map[24][17] = new SpeedupButton(24, 17);

        map[27][11] = new BuyDartMonkey(27, 11);
        map[26][11] = new BuyDartMonkey(26, 11);
        map[27][12] = new BuyDartMonkey(27, 10);
        map[26][12] = new BuyDartMonkey(26, 10);

        map[26][14] = new ChangeTargetLeft(26, 14);
        map[29][14] = new ChangeTargetRight(29, 14);

        map[30][17] = new RemoveTower(30, 17);

        map[26][15] = new UpgradeLeft(26, 15);
        map[26][16] = new UpgradeLeft(26, 16);
        map[27][16] = new UpgradeLeft(27, 16);
        map[27][15] = new UpgradeLeft(27, 15);

        map[28][15] = new UpgradeRight(28, 15);
        map[28][16] = new UpgradeRight(28, 16);
        map[29][16] = new UpgradeRight(29, 16);
        map[29][15] = new UpgradeRight(29, 15);
    }
}
