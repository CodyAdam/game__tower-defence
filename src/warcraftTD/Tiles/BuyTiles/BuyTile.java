package warcraftTD.Tiles.BuyTiles;

import java.awt.Color;

import warcraftTD.Tiles.Tile;
import warcraftTD.Tiles.Monkeys.Monkey;

/**
 * Classe parente des tiles d'interface d'achat de tours
 */
public abstract class BuyTile extends Tile {
    public Monkey toPlace;
    public int cost;

    public BuyTile(int x, int y) {
        super(x, y);
        isAvaliable = false;
        gridColor = new Color(203, 247, 245, 110);

    }

    /**
     * Quand on place une tour on recréer une nouvelle instance de la tour à placer
     */
    abstract public void reset();
}