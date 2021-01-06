package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class Wave2 extends Wave {
    public Wave2(List<Position> pathing) {
        super("Wave 2");
        queue.add(new Pair(60, new RedBloon(pathing)));
        queue.add(new Pair(20, new RedBloon(pathing)));
        queue.add(new Pair(20, new RedBloon(pathing)));
        queue.add(new Pair(20, new RedBloon(pathing)));
        queue.add(new Pair(20, new RedBloon(pathing)));
        queue.add(new Pair(20, new RedBloon(pathing)));
        queue.add(new Pair(20, new RedBloon(pathing)));
        queue.add(new Pair(20, new RedBloon(pathing)));
        queue.add(new Pair(20, new RedBloon(pathing)));
        queue.add(new Pair(20, new RedBloon(pathing)));
        queue.add(new Pair(20, new RedBloon(pathing)));
        queue.add(new Pair(20, new RedBloon(pathing)));
        queue.add(new Pair(20, new RedBloon(pathing)));
    }
}
