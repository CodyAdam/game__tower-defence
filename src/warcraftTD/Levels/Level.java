package warcraftTD.Levels;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Position;
import warcraftTD.Tiles.Panel;
import warcraftTD.Tiles.Tile;
import warcraftTD.Tiles.BuyTiles.*;

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

    public Level() {
        nbSquareX = 31;
        nbSquareY = 18;
        map = new Tile[nbSquareX][nbSquareY];
        pathing = new ArrayList<Position>();

        // Set HUD to the correct location on the level map

        for (int y = 0; y < 18; y++)
            for (int x = 25; x < 31; x++)
                map[x][y] = new Panel();

        map[27][11] = new BuyDartMonkey();
        map[26][11] = new BuyDartMonkey();
        map[27][10] = new BuyDartMonkey();
        map[26][10] = new BuyDartMonkey();
    }
}
