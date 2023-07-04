package toyshopgiveaway;

import java.io.*;
import java.util.Random;

public class ToysGenerate {
    private Toys[] toysArray;
    public void generateToys(int maxToys) {
        toysArray = new Toys[maxToys];
        Random random = new Random();

        for (int i = 0; i < maxToys; i++) {
            Toys toy = new Toys (Integer.toString(i + 1),
                    generateToyName(random), Integer.toString(random.nextInt(101)));
            toysArray[i] = toy;
        }
    }

    public void createToysAndWriteToDatabase() {
        try {
            FileWriter writer = new FileWriter("db_toys_avengers.csv");

            for (int i = 0; i < toysArray.length; i++) {
                writer.append(toysArray[i].getId());
                writer.append(",");
                writer.append(toysArray[i].getName());
                writer.append(",");
                writer.append(toysArray[i].getFreqFall());
                writer.append("\n");
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateToyName(Random random) {
        String[] toyNames = {"Avengers", "iron Man", "Spider man", "Thor", "black Widow", "Hulk", "Hawkeye", "Loki",
                "Nick Fury", "Thanos", "Vision"};
        return toyNames[random.nextInt(toyNames.length)];
    }
}
