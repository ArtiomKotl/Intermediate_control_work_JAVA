package toyshopgiveaway;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Random;

public class ToysManager {
    private PriorityQueue<Toys> allToys = new PriorityQueue<>();
    private int totalWeight = 0;
    private final String pathToDB = "db_toys_avengers.csv";
    private final Random r = new Random();

    public ToysManager() {
        setQueue();
    }

    private void setQueue() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(this.pathToDB), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                this.totalWeight += Integer.parseInt(split[2]);
                allToys.add(new Toys (split[0], split[1], split[2]));
            }
        } catch (IOException e) {
            System.out.println("There's been an IO exception while dealing with datafile");
            System.out.println(e.getMessage());
        }
    }

    public Toys nextToy(boolean removeAfterGetting) {
        if (allToys.isEmpty()) return null;

        int randInt = r.nextInt(totalWeight) + 1;
        Toys chosenToy = null;
        int currentWeight = 0;

        for (Toys toys : allToys) {
            currentWeight += Integer.parseInt(toys.getFreqFall());
            if (currentWeight >= randInt) {
                chosenToy = toys;
                break;
            }
        }

        if (removeAfterGetting) {
            removeToy(chosenToy);
        }
        return chosenToy;
    }

    public void appendResults(Toys toys) throws IOException {
        if (toys == null) return;

        String pathToResult = "result.txt";
        append(toys, pathToResult);
    }

    private void append(Toys toys, String path) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path, true));
            writer.write(toys.toString() + '\n');
        } catch (IOException e) {
            throw e;
        } finally {
            writer.close();
        }
    }

    private void removeToy(Toys toys) {
        allToys.remove(toys);

        try (Writer writer = Files.newBufferedWriter(Paths.get(this.pathToDB), StandardCharsets.UTF_8)) {
            for (Toys t : allToys) {
                writer.write(t.toString() + '\n');
            }
        } catch (IOException e) {
            System.out.println("There's been an IO exception in method removeToy");
            e.printStackTrace();
        }

        allToys.clear();
        totalWeight = 0;
        setQueue();
    }
}
