package toyshopgiveaway;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println ("Marvel themed toy draw today");
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the maximum number of toys for the prize draw in the database: ");
        Integer maxCount = scan.nextInt();

        ToysGenerate toyHelper = new ToysGenerate ();
        toyHelper.generateToys(maxCount);
        toyHelper.createToysAndWriteToDatabase();

        System.out.print("Enter the number of toys to be entered into the prize draw: ");

        Integer count = scan.nextInt();

        ToysManager manager = new ToysManager();
        PriorityQueue<Toys> firstQueue = new PriorityQueue<>();
        for (int i = 0; i < count; i++) {
            firstQueue.offer(manager.nextToy(false));
        }
        for (int i = 0; i < count; i++) {
            manager.appendResults(firstQueue.poll());
        }
    }
}