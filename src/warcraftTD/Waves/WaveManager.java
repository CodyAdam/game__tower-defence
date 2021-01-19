package warcraftTD.Waves;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Position;
import warcraftTD.Bloons.Bloon;

/**
 * Objet qui est utilisé en tant que gestionnaire de vagues
 */
public class WaveManager {

    private int tickCounter = 0; // compteur de temps, permettant de savoir quand lancer le prochain bloon de la
                                 // file
    private boolean running = false; // Est-ce que une vague est en cours actuellement
    private List<Bloon> bloonsList; // référence à la liste des ballons de World
    private List<Wave> waves; // Liste des vagues
    private Wave currentWave = null;
    public boolean finished = false;

    public WaveManager(List<Position> pathing, List<Bloon> bloons) {
        this.bloonsList = bloons;
        waves = new ArrayList<Wave>();
        waves.add(new Wave1(pathing));
        waves.add(new Wave2(pathing));
        waves.add(new Wave3(pathing));
        waves.add(new Wave4(pathing));
        waves.add(new Wave5(pathing));
        waves.add(new Wave6(pathing));
        waves.add(new Wave7(pathing));
        waves.add(new Wave8(pathing));
        waves.add(new Wave9(pathing));
        waves.add(new Wave10(pathing));
        waves.add(new WaveTesting(pathing));
    }

    /**
     * @return le nom de la vague actuel, si aucune vague n'a été commencé, rend :
     *         "Press the play button to start!"
     */
    public String getName() {
        if (waves.isEmpty())
            return "Empty";
        else if (!hasStarted())
            return "Press the play button to start!";
        else
            return currentWave.name;
    }

    /**
     * @return une vague est actuellement en cours ?
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @return est-ce que la première vague à été lancé ?
     */
    public boolean hasStarted() {
        return currentWave != null;
    }

    /**
     * Lance la prochaine vague si c'est possible
     */
    public void startNextWave() {
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

    /**
     * Update le géstonnaire de vague
     */
    public void update() {
        if (currentWave != null) {
            if (!running && (waves.indexOf(currentWave) == waves.size() - 1) && currentWave.queue.size() == 0)
                finished = true;
            if (currentWave.queue.isEmpty()) {
                running = false;
                return;
            } else {
                tickCounter++;
                if (tickCounter >= currentWave.queue.getFirst().waitTicks) {
                    bloonsList.add(currentWave.queue.removeFirst().bloon); // Fait spawn le premier ballon de la
                    // queue
                    // et le suprime de la queue puis relance
                    // le
                    // compteur
                    tickCounter = 0;
                }
            }
        }
    }

}
