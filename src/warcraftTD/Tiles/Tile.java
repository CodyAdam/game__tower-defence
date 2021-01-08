package warcraftTD.Tiles;

import java.awt.Color;

/**
 * Classe parente des autre Tile, la grille de jeu est faite uniquement de Tile
 */
public abstract class Tile {

    public boolean isAvaliable; // Une tour peut être placer à côté de cette tile ?
    public Color gridColor; // Couleur de la tile
    public int x; // coordonée x de la tile dans la grille
    public int y; // coordonée y de la tile dans la grille

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
