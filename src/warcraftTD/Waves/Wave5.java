package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class Wave5 extends Wave {
    public Wave5(List<Position> pathing) {
        super("Wave 5 / 65");
        queue.add(new Pair(60, new BlueBloon(pathing)));
        for (int i = 0; i < 9; i++) {
            queue.add(new Pair(30, new BlueBloon(pathing)));
        }
        queue.add(new Pair(60, new RedBloon(pathing)));
        for (int i = 0; i < 4; i++) {
            queue.add(new Pair(30, new RedBloon(pathing)));
        }
        for (int i = 0; i < 15; i++) {
            queue.add(new Pair(30, new BlueBloon(pathing)));
        }
    }
}
