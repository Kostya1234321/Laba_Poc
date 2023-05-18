import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static volatile int counter = 0;

    public static void main(String[] args) {
        Thread thread1 = createThread("Thread 1", 250);
        Thread thread2 = createThread("Thread 2", 500);
        Thread thread3 = createThread("Thread 3", 1000);

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static Thread createThread(String threadName, int delay) {
        return new Thread(() -> {
            try {
                FileWriter writer = new FileWriter("output.txt", true);
                while (counter < 240) {
                    counter++;
                    String currentTime = getCurrentTime();
                    writer.write(threadName + " | " + currentTime + " | Counter: " + counter + "\n");
                    writer.flush();
                    Thread.sleep(delay);
                }
                writer.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return currentTime.format(formatter);
    }
}
