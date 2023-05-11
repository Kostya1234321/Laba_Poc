import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class Main {
    private static volatile int counter = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (counter < 240) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
                writeToFile("Thread 1", counter);
            }
        });

        Thread t2 = new Thread(() -> {
            while (counter < 240) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                writeToFile("Thread 2", counter);
            }
        });

        Thread t3 = new Thread(() -> {
            while (counter < 240) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                writeToFile("Thread 3", counter);
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    private static synchronized void writeToFile(String threadName, int counter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true))) {
            writer.write(String.format("%s: %s, Counter: %d\n", threadName, LocalTime.now(), counter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}