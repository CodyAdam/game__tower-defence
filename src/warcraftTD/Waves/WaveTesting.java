package warcraftTD.Waves;

import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

public class WaveTesting extends Wave {
    public WaveTesting(List<Position> pathing) {
        super("Wave Test");
        queue.add(new Pair(30, new RedBloon(pathing)));
        queue.add(new Pair(30, new BlueBloon(pathing)));
        queue.add(new Pair(30, new GreenBloon(pathing)));
        queue.add(new Pair(30, new YellowBloon(pathing)));
        queue.add(new Pair(30, new PinkBloon(pathing)));
        queue.add(new Pair(30, new BlackBloon(pathing)));
        queue.add(new Pair(30, new IceBloon(pathing)));
        queue.add(new Pair(30, new MetalBloon(pathing)));
        queue.add(new Pair(30, new ZebraBloon(pathing)));
        queue.add(new Pair(30, new RaimbowBloon(pathing)));
        queue.add(new Pair(30, new CeramicBloon(pathing)));
        queue.add(new Pair(4 * 60, new BlueAirBloon(pathing)));
        queue.add(new Pair(4 * 60, new RedAirBloon(pathing)));
        queue.add(new Pair(4 * 60, new BlackAirBloon(pathing)));
    }
}
