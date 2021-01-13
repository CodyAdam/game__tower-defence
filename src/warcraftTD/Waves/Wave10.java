package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class Wave10 extends Wave {
    public Wave10(List<Position> pathing) {
        super("Wave 10 / 65");

        queue.add(new Pair(60, new BlueBloon(pathing)));
        for (int i = 0; i < 69; i++) {
            queue.add(new Pair(30, new BlueBloon(pathing)));
        }
        for (int i = 0; i < 25; i++) {
            queue.add(new Pair(15, new BlueBloon(pathing)));
        }
    }
}
