package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class WaveTest2 extends Wave {
    public WaveTest2(List<Position> pathing) {
        super("Wave Test2");
        queue.add(new Pair(30, new RedBloon(pathing)));
    }
}
