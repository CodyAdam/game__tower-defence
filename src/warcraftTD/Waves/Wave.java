package warcraftTD.Waves;

import java.util.ArrayDeque;

/**
 * Classe parente de toutes les vagues du jeu
 */
public class Wave {
    public String name;
    public ArrayDeque<Pair> queue;

    public Wave(String name, ArrayDeque<Pair> queue) {
        this.name = name;
        this.queue = queue;
    }
}