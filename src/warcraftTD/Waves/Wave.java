package warcraftTD.Waves;

import java.util.ArrayDeque;

import warcraftTD.Bloons.Bloon;

public abstract class Wave {

    protected class Pair {
        Bloon bloon;
        int waitTicks;

        public Pair(int waitTicks, Bloon bloon) {
            this.bloon = bloon;
            this.waitTicks = waitTicks; // number of update ticks before spawn
        }
    }

    public String name;
    public ArrayDeque<Pair> queue;

    public Wave(String name) {
        this.name = name;
        this.queue = new ArrayDeque<Pair>();

    }
}