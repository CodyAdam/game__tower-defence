package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class Wave4 extends Wave {
    public Wave4(List<Position> pathing) {
        super("Wave 4 / 65");
        queue.add(new Pair(60, new RedBloon(pathing)));
        for (int i = 0; i < 19; i++) {
            queue.add(new Pair(30, new RedBloon(pathing)));
        }
        for (int i = 0; i < 15; i++) {
            queue.add(new Pair(30, new BlueBloon(pathing)));
        }
        for (int i = 0; i < 10; i++) {
            queue.add(new Pair(15, new RedBloon(pathing)));
        }
    }
}
