package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class Wave3 extends Wave {
    public Wave3(List<Position> pathing) {
        super("Wave 3");
        queue.add(new Pair(60, new RedBloon(pathing)));
        queue.add(new Pair(30, new RedBloon(pathing)));
        queue.add(new Pair(30, new RedBloon(pathing)));
        queue.add(new Pair(30, new RedBloon(pathing)));
        queue.add(new Pair(30, new RedBloon(pathing)));
        queue.add(new Pair(30, new RedBloon(pathing)));
        queue.add(new Pair(30, new RedBloon(pathing)));
        queue.add(new Pair(30, new RedBloon(pathing)));
        queue.add(new Pair(30, new RedBloon(pathing)));
        queue.add(new Pair(30, new RedBloon(pathing)));
        queue.add(new Pair(30, new RedBloon(pathing)));
        queue.add(new Pair(30, new RedBloon(pathing)));
        queue.add(new Pair(30, new BlueBloon(pathing)));
        queue.add(new Pair(30, new BlueBloon(pathing)));
        queue.add(new Pair(30, new BlueBloon(pathing)));
        queue.add(new Pair(30, new BlueBloon(pathing)));
        queue.add(new Pair(30, new BlueBloon(pathing)));
        queue.add(new Pair(30, new BlueBloon(pathing)));
        queue.add(new Pair(30, new BlueBloon(pathing)));
        queue.add(new Pair(30, new BlueBloon(pathing)));
        queue.add(new Pair(30, new BlueBloon(pathing)));
        queue.add(new Pair(30, new BlueBloon(pathing)));
    }
}
