package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class Wave9 extends Wave {
    public Wave9(List<Position> pathing) {
        super("Wave 9 / 65");
        for (int i = 0; i < 30; i++) {
            queue.add(new Pair(60, new GreenBloon(pathing)));
        }
    }
}
