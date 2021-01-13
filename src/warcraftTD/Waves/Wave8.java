package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class Wave8 extends Wave {
    public Wave8(List<Position> pathing) {
        super("Wave 8 / 65");
        queue.add(new Pair(60, new BlueBloon(pathing)));
        for (int i = 0; i < 19; i++) {
            queue.add(new Pair(30, new BlueBloon(pathing)));
        }
        for (int i = 0; i < 2; i++) {
            queue.add(new Pair(30, new GreenBloon(pathing)));
        }
        queue.add(new Pair(60, new RedBloon(pathing)));
        for (int i = 0; i < 9; i++) {
            queue.add(new Pair(15, new RedBloon(pathing)));
        }
        queue.add(new Pair(120, new GreenBloon(pathing)));
        for (int i = 0; i < 12; i++) {
            queue.add(new Pair(60, new GreenBloon(pathing)));
        }
    }
}
