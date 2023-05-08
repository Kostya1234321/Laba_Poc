import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        int n = 11;
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> fibonacci(n));
        System.out.println("Started computing Fibonacci(" + n + ")");
        try {
            int result = future.get();
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static int fibonacci(int n) {
        if (n < 2) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}