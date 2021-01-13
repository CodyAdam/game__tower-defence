package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class Wave6 extends Wave {
    public Wave6(List<Position> pathing) {
        super("Wave 6 / 65");
        queue.add(new Pair(60, new GreenBloon(pathing)));
        for (int i = 0; i < 3; i++) {
            queue.add(new Pair(30, new GreenBloon(pathing)));
        }
        queue.add(new Pair(60, new RedBloon(pathing)));
        for (int i = 0; i < 14; i++) {
            queue.add(new Pair(30, new RedBloon(pathing)));
        }
        queue.add(new Pair(60, new BlueBloon(pathing)));
        for (int i = 0; i < 14; i++) {
            queue.add(new Pair(30, new BlueBloon(pathing)));
        }
    }
}
