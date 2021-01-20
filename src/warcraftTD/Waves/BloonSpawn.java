package warcraftTD.Waves;

import warcraftTD.Bloons.Bloon;

class BloonSpawn {
    Bloon bloon;
    int waitTicks;
    int quantity;

    public BloonSpawn(int waitTicks, Bloon bloon, int quantity) {
        this.bloon = bloon;
        this.quantity = quantity;
        this.waitTicks = waitTicks; // number of update ticks before spawn
    }
}