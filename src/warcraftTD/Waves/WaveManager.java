package warcraftTD.Waves;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;

import warcraftTD.Position;
import warcraftTD.Bloons.*;

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

        Wave wave;
        ArrayDeque<Pair> queue;
        // Creation des Vagues :

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(50, new RedBloon(pathing), 20));
        wave = new Wave("Wave 1 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(30, new RedBloon(pathing), 25));
        wave = new Wave("Wave 2 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(30, new RedBloon(pathing), 10));
        queue.add(new Pair(30, new BlueBloon(pathing), 5));
        queue.add(new Pair(30, new RedBloon(pathing), 10));
        wave = new Wave("Wave 3 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(30, new RedBloon(pathing), 20));
        queue.add(new Pair(30, new BlueBloon(pathing), 15));
        queue.add(new Pair(15, new RedBloon(pathing), 10));
        wave = new Wave("Wave 4 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(30, new BlueBloon(pathing), 10));
        queue.add(new Pair(30, new RedBloon(pathing), 5));
        queue.add(new Pair(30, new BlueBloon(pathing), 15));
        wave = new Wave("Wave 5 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(30, new GreenBloon(pathing), 4));
        queue.add(new Pair(30, new RedBloon(pathing), 15));
        queue.add(new Pair(30, new BlueBloon(pathing), 15));
        wave = new Wave("Wave 6 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(30, new BlueBloon(pathing), 10));
        queue.add(new Pair(60, new GreenBloon(pathing), 5));
        queue.add(new Pair(25, new RedBloon(pathing), 25));
        queue.add(new Pair(15, new BlueBloon(pathing), 15));
        wave = new Wave("Wave 7 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(30, new BlueBloon(pathing), 20));
        queue.add(new Pair(30, new GreenBloon(pathing), 2));
        queue.add(new Pair(15, new RedBloon(pathing), 10));
        queue.add(new Pair(60, new GreenBloon(pathing), 13));
        wave = new Wave("Wave 8 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(60, new GreenBloon(pathing), 30));
        wave = new Wave("Wave 9 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(30, new BlueBloon(pathing), 70));
        queue.add(new Pair(15, new BlueBloon(pathing), 25));
        wave = new Wave("Wave 10 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(45, new GreenBloon(pathing), 13));
        queue.add(new Pair(30, new BlueBloon(pathing), 10));
        queue.add(new Pair(30, new RedBloon(pathing), 10));
        wave = new Wave("Wave 11 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(60, new GreenBloon(pathing), 10));
        queue.add(new Pair(30, new BlueBloon(pathing), 15));
        queue.add(new Pair(90, new YellowBloon(pathing), 5));
        wave = new Wave("Wave 12 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(15, new RedBloon(pathing), 30));
        queue.add(new Pair(30, new YellowBloon(pathing), 5));
        queue.add(new Pair(15, new RedBloon(pathing), 30));
        queue.add(new Pair(60, new GreenBloon(pathing), 10));
        queue.add(new Pair(15, new RedBloon(pathing), 30));
        wave = new Wave("Wave 13 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(30, new RedBloon(pathing), 10));
        queue.add(new Pair(15, new BlueBloon(pathing), 5));
        queue.add(new Pair(30, new RedBloon(pathing), 10));
        queue.add(new Pair(15, new GreenBloon(pathing), 5));
        queue.add(new Pair(30, new RedBloon(pathing), 10));
        queue.add(new Pair(15, new YellowBloon(pathing), 5));
        queue.add(new Pair(30, new RedBloon(pathing), 10));
        queue.add(new Pair(15, new BlueBloon(pathing), 5));
        queue.add(new Pair(30, new RedBloon(pathing), 10));
        queue.add(new Pair(15, new YellowBloon(pathing), 5));
        wave = new Wave("Wave 14 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(30, new RedBloon(pathing), 20));
        queue.add(new Pair(15, new PinkBloon(pathing), 3));
        queue.add(new Pair(60, new GreenBloon(pathing), 10));
        queue.add(new Pair(30, new YellowBloon(pathing), 5));
        wave = new Wave("Wave 15 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(30, new GreenBloon(pathing), 20));
        queue.add(new Pair(30, new PinkBloon(pathing), 5));
        queue.add(new Pair(30, new YellowBloon(pathing), 8));
        queue.add(new Pair(90, new PinkBloon(pathing), 2));
        wave = new Wave("Wave 16 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(60, new YellowBloon(pathing), 15));
        queue.add(new Pair(30, new PinkBloon(pathing), 2));
        wave = new Wave("Wave 17 / 65", queue);
        waves.add(wave);
        queue = new ArrayDeque<Pair>();
        queue.add(new Pair(20, new GreenBloon(pathing), 20));
        queue.add(new Pair(7, new GreenBloon(pathing), 20));
        wave = new Wave("Wave 18 / 65", queue);
        waves.add(wave);

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
            if (!running && (waves.indexOf(currentWave) == waves.size() - 1))
                finished = true;
            if (currentWave.queue.isEmpty()) {
                running = false;
                return;
            } else {
                tickCounter++;
                if (tickCounter >= currentWave.queue.getFirst().waitTicks) {
                    // Fait spawn le premier ballon de la queue et le suprime de la queue puis
                    // relance le compteur
                    Pair nextToSawn = currentWave.queue.getFirst();
                    if (nextToSawn.quantity-- <= 1)
                        bloonsList.add(currentWave.queue.removeFirst().bloon.copy());
                    else
                        bloonsList.add(nextToSawn.bloon.copy());
                    tickCounter = 0;
                }
            }
        }
    }

}
