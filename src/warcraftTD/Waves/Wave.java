package warcraftTD.Waves;

import java.util.ArrayDeque;

/**
 * Classe parente de toutes les vagues du jeu
 */
public class Wave {
    public String name;
    public ArrayDeque<BloonSpawn> queue;

    public Wave(String name, ArrayDeque<BloonSpawn> queue) {
        this.name = name;
        this.queue = queue;
    }
}