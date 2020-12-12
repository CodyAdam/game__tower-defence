package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.RedBloon;

public class Wave1 extends Wave {
    public Wave1(List<Position> pathing) {
        super("Wave 1");
        queue.add(new Pair(50, new RedBloon(pathing)));
        queue.add(new Pair(50, new RedBloon(pathing)));
        queue.add(new Pair(50, new RedBloon(pathing)));
        queue.add(new Pair(50, new RedBloon(pathing)));
        queue.add(new Pair(50, new RedBloon(pathing)));
        queue.add(new Pair(50, new RedBloon(pathing)));
    }
}
