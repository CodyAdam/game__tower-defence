package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class Wave7 extends Wave {
    public Wave7(List<Position> pathing) {
        super("Wave 7 / 65");
        queue.add(new Pair(60, new BlueBloon(pathing)));
        for (int i = 0; i < 9; i++) {
            queue.add(new Pair(30, new BlueBloon(pathing)));
        }
        for (int i = 0; i < 5; i++) {
            queue.add(new Pair(60, new GreenBloon(pathing)));
        }
        queue.add(new Pair(60, new RedBloon(pathing)));
        for (int i = 0; i < 19; i++) {
            queue.add(new Pair(25, new RedBloon(pathing)));
        }
        for (int i = 0; i < 15; i++) {
            queue.add(new Pair(15, new BlueBloon(pathing)));
        }
    }
}
