package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class Wave2 extends Wave {
    public Wave2(List<Position> pathing) {
        super("Wave 2 / 65");
        queue.add(new Pair(60, new RedBloon(pathing)));
        for (int i = 0; i < 24; i++) {
            queue.add(new Pair(30, new RedBloon(pathing)));
        }
    }
}
