package warcraftTD.Waves;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.Bloon;

public class Waves {

    // Est-ce que une vague est en cours actuelement
    private boolean running = false;

    private Wave currentWave = null;

    private List<Bloon> bloonsList;

    public List<Wave> waves;

    private int tickCounter = 0;

    public Waves(Position spawn, List<Bloon> bloons) {
        this.bloonsList = bloons;
        List<Wave> waves = new ArrayList<Wave>();

        waves.add(new Wave1(spawn));
        waves.add(new Wave1(spawn));
        waves.add(new Wave1(spawn));
        waves.add(new Wave1(spawn));
    }

    public boolean isRunning() {
        return running;
    }

    public void startNextWave() {
        if (currentWave == null)
            if (waves.size() > 0)
                currentWave = waves.get(0);
            else
                System.err.println("The started wave is empty");
        else {
            int nextIndex = 1 + waves.indexOf(currentWave);
            if (nextIndex >= waves.size())
                System.err.println("The started wave out of range");
            else
                currentWave = waves.get(1 + waves.indexOf(currentWave));
        }
    }

    public void update() {
        if (currentWave != null) {
            tickCounter++;

            if (tickCounter >= currentWave.queue.getFirst().waitTicks) {
                bloonsList.add(currentWave.queue.removeFirst().bloon); // Fait spawn le premier ballon de la queue et le
                                                                       // suprime de la queue puis relance le compteur
                tickCounter = 0;
            }

        }
    }

}
