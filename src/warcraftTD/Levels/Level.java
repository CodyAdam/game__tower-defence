package warcraftTD.Levels;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Tiles.Tile;

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
}
