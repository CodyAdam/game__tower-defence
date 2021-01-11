package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class Wave1 extends Wave {
    public Wave1(List<Position> pathing) {
        super("Wave 1 / 65");
        queue.add(new Pair(60, new RedBloon(pathing)));
        for (int i = 0; i < 19; i++) {
            queue.add(new Pair(50, new RedBloon(pathing)));
        }
    }
}
