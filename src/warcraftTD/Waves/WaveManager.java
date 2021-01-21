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
        ArrayDeque<BloonSpawn> queue;

        // Creation des Vagues :
        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(50, new RedBloon(pathing), 20));
        wave = new Wave("Wave 1 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 25));
        wave = new Wave("Wave 2 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new BlueBloon(pathing), 5));
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 10));
        wave = new Wave("Wave 3 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 20));
        queue.add(new BloonSpawn(30, new BlueBloon(pathing), 15));
        queue.add(new BloonSpawn(15, new RedBloon(pathing), 10));
        wave = new Wave("Wave 4 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new BlueBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 5));
        queue.add(new BloonSpawn(30, new BlueBloon(pathing), 15));
        wave = new Wave("Wave 5 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new GreenBloon(pathing), 4));
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 15));
        queue.add(new BloonSpawn(30, new BlueBloon(pathing), 15));
        wave = new Wave("Wave 6 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new BlueBloon(pathing), 10));
        queue.add(new BloonSpawn(60, new GreenBloon(pathing), 5));
        queue.add(new BloonSpawn(25, new RedBloon(pathing), 25));
        queue.add(new BloonSpawn(15, new BlueBloon(pathing), 15));
        wave = new Wave("Wave 7 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new BlueBloon(pathing), 20));
        queue.add(new BloonSpawn(30, new GreenBloon(pathing), 2));
        queue.add(new BloonSpawn(15, new RedBloon(pathing), 10));
        queue.add(new BloonSpawn(60, new GreenBloon(pathing), 13));
        wave = new Wave("Wave 8 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(60, new GreenBloon(pathing), 30));
        wave = new Wave("Wave 9 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new BlueBloon(pathing), 70));
        queue.add(new BloonSpawn(15, new BlueBloon(pathing), 25));
        wave = new Wave("Wave 10 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(45, new GreenBloon(pathing), 13));
        queue.add(new BloonSpawn(30, new BlueBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 10));
        wave = new Wave("Wave 11 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(60, new GreenBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new BlueBloon(pathing), 15));
        queue.add(new BloonSpawn(90, new YellowBloon(pathing), 5));
        wave = new Wave("Wave 12 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(15, new RedBloon(pathing), 30));
        queue.add(new BloonSpawn(30, new YellowBloon(pathing), 5));
        queue.add(new BloonSpawn(15, new RedBloon(pathing), 30));
        queue.add(new BloonSpawn(60, new GreenBloon(pathing), 10));
        queue.add(new BloonSpawn(15, new RedBloon(pathing), 30));
        wave = new Wave("Wave 13 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 10));
        queue.add(new BloonSpawn(15, new BlueBloon(pathing), 5));
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 10));
        queue.add(new BloonSpawn(15, new GreenBloon(pathing), 5));
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 10));
        queue.add(new BloonSpawn(15, new YellowBloon(pathing), 5));
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 10));
        queue.add(new BloonSpawn(15, new BlueBloon(pathing), 5));
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 10));
        queue.add(new BloonSpawn(15, new YellowBloon(pathing), 5));
        wave = new Wave("Wave 14 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new RedBloon(pathing), 20));
        queue.add(new BloonSpawn(15, new PinkBloon(pathing), 3));
        queue.add(new BloonSpawn(60, new GreenBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new YellowBloon(pathing), 5));
        wave = new Wave("Wave 15 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new GreenBloon(pathing), 20));
        queue.add(new BloonSpawn(30, new PinkBloon(pathing), 5));
        queue.add(new BloonSpawn(30, new YellowBloon(pathing), 8));
        queue.add(new BloonSpawn(90, new PinkBloon(pathing), 2));
        wave = new Wave("Wave 16 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(60, new YellowBloon(pathing), 15));
        queue.add(new BloonSpawn(30, new PinkBloon(pathing), 2));
        wave = new Wave("Wave 17 / 65", queue);
        waves.add(wave);
        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(20, new GreenBloon(pathing), 20));
        queue.add(new BloonSpawn(7, new GreenBloon(pathing), 20));
        wave = new Wave("Wave 18 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(20, new YellowBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new YellowBloon(pathing), 15));
        queue.add(new BloonSpawn(90, new PinkBloon(pathing), 8));
        wave = new Wave("Wave 19 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(60, new BlackBloon(pathing), 5));
        wave = new Wave("Wave 20 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new PinkBloon(pathing), 15));
        wave = new Wave("Wave 21 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new IceBloon(pathing), 5));
        wave = new Wave("Wave 22 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(20, new YellowBloon(pathing), 30));
        wave = new Wave("Wave 23 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new PinkBloon(pathing), 30));
        wave = new Wave("Wave 24 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(10, new PinkBloon(pathing), 30));
        queue.add(new BloonSpawn(10, new YellowBloon(pathing), 10));
        wave = new Wave("Wave 25 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new ZebraBloon(pathing), 2));
        wave = new Wave("Wave 26 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(10, new YellowBloon(pathing), 30));
        queue.add(new BloonSpawn(7, new GreenBloon(pathing), 40));
        queue.add(new BloonSpawn(7, new BlueBloon(pathing), 40));
        queue.add(new BloonSpawn(4, new RedBloon(pathing), 100));
        wave = new Wave("Wave 27 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(7, new BlueBloon(pathing), 40));
        queue.add(new BloonSpawn(14, new YellowBloon(pathing), 10));
        queue.add(new BloonSpawn(7, new GreenBloon(pathing), 10));
        queue.add(new BloonSpawn(4, new RedBloon(pathing), 43));
        wave = new Wave("Wave 28 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new YellowBloon(pathing), 20));
        queue.add(new BloonSpawn(20, new PinkBloon(pathing), 30));
        wave = new Wave("Wave 29 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(100, new MetalBloon(pathing), 10));
        wave = new Wave("Wave 30 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new ZebraBloon(pathing), 10));
        wave = new Wave("Wave 31 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(40, new BlackBloon(pathing), 25));
        queue.add(new BloonSpawn(40, new IceBloon(pathing), 25));
        queue.add(new BloonSpawn(30, new MetalBloon(pathing), 8));
        wave = new Wave("Wave 32 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(15, new YellowBloon(pathing), 100));
        wave = new Wave("Wave 33 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(20, new PinkBloon(pathing), 20));
        queue.add(new BloonSpawn(30, new ZebraBloon(pathing), 5));
        queue.add(new BloonSpawn(20, new PinkBloon(pathing), 20));
        queue.add(new BloonSpawn(30, new ZebraBloon(pathing), 5));
        queue.add(new BloonSpawn(20, new PinkBloon(pathing), 20));
        queue.add(new BloonSpawn(30, new ZebraBloon(pathing), 5));
        queue.add(new BloonSpawn(20, new PinkBloon(pathing), 20));
        wave = new Wave("Wave 34 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new IceBloon(pathing), 30));
        queue.add(new BloonSpawn(30, new RaimbowBloon(pathing), 3));
        queue.add(new BloonSpawn(20, new PinkBloon(pathing), 30));
        wave = new Wave("Wave 35 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(10, new PinkBloon(pathing), 30));
        queue.add(new BloonSpawn(10, new RaimbowBloon(pathing), 2));
        queue.add(new BloonSpawn(10, new PinkBloon(pathing), 30));
        queue.add(new BloonSpawn(10, new RaimbowBloon(pathing), 2));
        queue.add(new BloonSpawn(10, new PinkBloon(pathing), 30));
        queue.add(new BloonSpawn(10, new RaimbowBloon(pathing), 2));
        queue.add(new BloonSpawn(10, new PinkBloon(pathing), 30));
        wave = new Wave("Wave 36 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new BlackBloon(pathing), 20));
        queue.add(new BloonSpawn(30, new IceBloon(pathing), 20));
        queue.add(new BloonSpawn(30, new MetalBloon(pathing), 20));
        queue.add(new BloonSpawn(40, new ZebraBloon(pathing), 10));
        wave = new Wave("Wave 37 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new IceBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new PinkBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new MetalBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new IceBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new BlackBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new PinkBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new BlackBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new MetalBloon(pathing), 10));
        queue.add(new BloonSpawn(40, new ZebraBloon(pathing), 10));
        wave = new Wave("Wave 38 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(60, new RaimbowBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new PinkBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new RaimbowBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new MetalBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new IceBloon(pathing), 10));
        queue.add(new BloonSpawn(60, new RaimbowBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new BlackBloon(pathing), 10));
        queue.add(new BloonSpawn(30, new PinkBloon(pathing), 10));
        queue.add(new BloonSpawn(60, new RaimbowBloon(pathing), 10));
        wave = new Wave("Wave 39 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(20, new MetalBloon(pathing), 10));
        queue.add(new BloonSpawn(40, new RaimbowBloon(pathing), 10));
        queue.add(new BloonSpawn(20, new BlackBloon(pathing), 10));
        queue.add(new BloonSpawn(40, new RaimbowBloon(pathing), 10));
        queue.add(new BloonSpawn(20, new IceBloon(pathing), 10));
        queue.add(new BloonSpawn(40, new RaimbowBloon(pathing), 10));
        queue.add(new BloonSpawn(20, new MetalBloon(pathing), 10));
        queue.add(new BloonSpawn(40, new RaimbowBloon(pathing), 10));
        queue.add(new BloonSpawn(20, new PinkBloon(pathing), 10));
        queue.add(new BloonSpawn(20, new PinkBloon(pathing), 10));
        queue.add(new BloonSpawn(20, new MetalBloon(pathing), 10));
        wave = new Wave("Wave 39 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(25, new RaimbowBloon(pathing), 10));
        queue.add(new BloonSpawn(60, new CeramicBloon(pathing), 5));
        wave = new Wave("Wave 40 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(20, new BlackBloon(pathing), 40));
        queue.add(new BloonSpawn(20, new ZebraBloon(pathing), 40));
        queue.add(new BloonSpawn(60, new CeramicBloon(pathing), 5));
        wave = new Wave("Wave 41 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(20, new RaimbowBloon(pathing), 40));
        queue.add(new BloonSpawn(45, new CeramicBloon(pathing), 10));
        wave = new Wave("Wave 42 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new RaimbowBloon(pathing), 30));
        queue.add(new BloonSpawn(35, new CeramicBloon(pathing), 20));
        queue.add(new BloonSpawn(30, new RaimbowBloon(pathing), 30));
        wave = new Wave("Wave 43 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new ZebraBloon(pathing), 50));
        wave = new Wave("Wave 44 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(7, new PinkBloon(pathing), 200));
        queue.add(new BloonSpawn(20, new MetalBloon(pathing), 8));
        queue.add(new BloonSpawn(20, new RaimbowBloon(pathing), 25));
        wave = new Wave("Wave 45 / 65 (Be careful for the next wave)", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new BlueAirBloon(pathing), 1));
        wave = new Wave("Wave 46 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(5, new PinkBloon(pathing), 200));
        queue.add(new BloonSpawn(15, new CeramicBloon(pathing), 12));
        wave = new Wave("Wave 47 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new CeramicBloon(pathing), 22));
        queue.add(new BloonSpawn(7, new BlackBloon(pathing), 200));
        wave = new Wave("Wave 48 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(3, new GreenBloon(pathing), 343));
        queue.add(new BloonSpawn(20, new CeramicBloon(pathing), 18));
        queue.add(new BloonSpawn(20, new ZebraBloon(pathing), 20));
        queue.add(new BloonSpawn(20, new RaimbowBloon(pathing), 30));
        wave = new Wave("Wave 49 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(4, new RedBloon(pathing), 40));
        queue.add(new BloonSpawn(20, new MetalBloon(pathing), 8));
        queue.add(new BloonSpawn(4, new RedBloon(pathing), 40));
        queue.add(new BloonSpawn(20, new CeramicBloon(pathing), 20));
        queue.add(new BloonSpawn(500, new BlueAirBloon(pathing), 2));
        wave = new Wave("Wave 50 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(10, new RaimbowBloon(pathing), 10));
        queue.add(new BloonSpawn(20, new CeramicBloon(pathing), 30));
        wave = new Wave("Wave 51 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(10, new RaimbowBloon(pathing), 25));
        queue.add(new BloonSpawn(20, new CeramicBloon(pathing), 10));
        queue.add(new BloonSpawn(300, new BlueAirBloon(pathing), 2));
        wave = new Wave("Wave 52 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(200, new BlueAirBloon(pathing), 3));
        queue.add(new BloonSpawn(4, new PinkBloon(pathing), 100));
        wave = new Wave("Wave 53 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(30, new CeramicBloon(pathing), 35));
        queue.add(new BloonSpawn(200, new BlueAirBloon(pathing), 2));
        wave = new Wave("Wave 54 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(10, new CeramicBloon(pathing), 45));
        queue.add(new BloonSpawn(10, new BlueAirBloon(pathing), 1));
        wave = new Wave("Wave 55 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(20, new RaimbowBloon(pathing), 45));
        queue.add(new BloonSpawn(10, new BlueAirBloon(pathing), 1));
        wave = new Wave("Wave 56 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(10, new RaimbowBloon(pathing), 45));
        queue.add(new BloonSpawn(100, new BlueAirBloon(pathing), 4));
        wave = new Wave("Wave 57 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(10, new CeramicBloon(pathing), 30));
        queue.add(new BloonSpawn(100, new BlueAirBloon(pathing), 5));
        wave = new Wave("Wave 58 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(10, new CeramicBloon(pathing), 10));
        queue.add(new BloonSpawn(10, new MetalBloon(pathing), 10));
        queue.add(new BloonSpawn(10, new CeramicBloon(pathing), 10));
        queue.add(new BloonSpawn(10, new MetalBloon(pathing), 10));
        queue.add(new BloonSpawn(10, new CeramicBloon(pathing), 10));
        queue.add(new BloonSpawn(10, new MetalBloon(pathing), 10));
        queue.add(new BloonSpawn(10, new CeramicBloon(pathing), 10));
        queue.add(new BloonSpawn(10, new MetalBloon(pathing), 10));
        wave = new Wave("Wave 59 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(10, new RedAirBloon(pathing), 1));
        wave = new Wave("Wave 60 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(10, new BlueAirBloon(pathing), 1));
        queue.add(new BloonSpawn(20, new ZebraBloon(pathing), 150));
        wave = new Wave("Wave 61 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(20, new ZebraBloon(pathing), 150));
        queue.add(new BloonSpawn(50, new BlueAirBloon(pathing), 5));
        wave = new Wave("Wave 62 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(5, new PinkBloon(pathing), 300));
        queue.add(new BloonSpawn(5, new RaimbowBloon(pathing), 15));
        queue.add(new BloonSpawn(500, new RedAirBloon(pathing), 2));
        wave = new Wave("Wave 63 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(300, new RedAirBloon(pathing), 3));
        wave = new Wave("Wave 64 / 65", queue);
        waves.add(wave);

        queue = new ArrayDeque<BloonSpawn>();
        queue.add(new BloonSpawn(10, new BlackAirBloon(pathing), 1));
        wave = new Wave("Wave 65 / 65", queue);
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

    public int getMoneyFromWave() {
        if (currentWave == null)
            return 0;
        else
            return waves.indexOf(currentWave) + 100;
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
        if (!finished)
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
                    BloonSpawn nextToSawn = currentWave.queue.getFirst();
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
