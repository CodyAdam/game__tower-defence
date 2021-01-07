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

    public Waves(List<Position> pathing, List<Bloon> bloons) {
        this.bloonsList = bloons;
        waves = new ArrayList<Wave>();
        waves.add(new Wave1(pathing));
        waves.add(new Wave2(pathing));
        waves.add(new Wave3(pathing));
        waves.add(new WaveTesting(pathing));
        waves.add(new WaveTesting(pathing));
        waves.add(new WaveTesting(pathing));
        waves.add(new WaveTesting(pathing));
        waves.add(new WaveTesting(pathing));
        waves.add(new WaveTesting(pathing));
    }

    public String getName() {
        if (waves.isEmpty())
            return "VICTORY!";
        else if (currentWave == null)
            return "Press the play button to start!";
        else
            return currentWave.name;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean hasStarted() {
        return currentWave != null;
    }

    public void startNextWave() {

        if (!running) {
            if (currentWave == null)
                if (waves != null && waves.size() > 0) {
                    currentWave = waves.get(0);
                    running = true;
                } else
                    System.err.println("The started wave is empty");
            else {
                int nextIndex = 1 + waves.indexOf(currentWave);
                if (nextIndex < waves.size()) {
                    currentWave = waves.get(1 + waves.indexOf(currentWave));
                    running = true;
                }
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
                                                                           // et le suprime de la queue puis relance le
                                                                           // compteur
                    tickCounter = 0;
                }
            }

        }
    }

}
