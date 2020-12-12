package warcraftTD.Waves;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.Bloon;

public class Waves {

    private int tickCounter = 0;
    private boolean running = false; // Est-ce que une vague est en cours actuelement
    private List<Bloon> bloonsList; // référence à la liste des ballons de World
    private List<Wave> waves;
    private Wave currentWave = null;

    public Waves(Position spawn, List<Bloon> bloons) {
        this.bloonsList = bloons;
        waves = new ArrayList<Wave>();
        waves.add(new Wave1(spawn));
        waves.add(new Wave1(spawn));
        waves.add(new Wave1(spawn));
        waves.add(new Wave1(spawn));
        System.out.println("waves init");
        System.out.println(waves.size());
    }

    public boolean isRunning() {
        return running;
    }

    public void startNextWave() {
        if (!running) {
            if (currentWave == null)
                if (waves != null && waves.size() > 0)
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
    }

    public void update() {
        if (currentWave != null) {
            if (currentWave.queue.isEmpty()) {
                running = false;
                return;
            } else {
                tickCounter++;
                if (tickCounter >= currentWave.queue.getFirst().waitTicks) {
                    bloonsList.add(currentWave.queue.removeFirst().bloon); // Fait spawn le premier ballon de la queue
                                                                           // et le
                    // suprime de la queue puis relance le compteur
                    tickCounter = 0;
                }
            }

        }
    }

}
