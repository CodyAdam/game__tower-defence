package warcraftTD.Waves;

import warcraftTD.Position;
import warcraftTD.Bloons.RedBloon;

public class Wave1 extends Wave {
    public Wave1(Position spawn) {
        super("Wave 1");
        queue.add(new Pair(50, new RedBloon(spawn)));
        queue.add(new Pair(50, new RedBloon(spawn)));
        queue.add(new Pair(50, new RedBloon(spawn)));
        queue.add(new Pair(50, new RedBloon(spawn)));
        queue.add(new Pair(50, new RedBloon(spawn)));
        queue.add(new Pair(50, new RedBloon(spawn)));
    }
}
