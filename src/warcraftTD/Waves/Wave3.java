package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class Wave3 extends Wave {
    public Wave3(List<Position> pathing) {
        super("Wave 3 / 65");
        queue.add(new Pair(60, new RedBloon(pathing)));
        for (int i = 0; i < 9; i++) {
            queue.add(new Pair(30, new RedBloon(pathing)));
        }
        for (int i = 0; i < 5; i++) {
            queue.add(new Pair(30, new BlueBloon(pathing)));
        }
        for (int i = 0; i < 10; i++) {
            queue.add(new Pair(30, new RedBloon(pathing)));
        }
    }
}
