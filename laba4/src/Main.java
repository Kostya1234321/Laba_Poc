import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    private static final int ARRAY_SIZE = 10000;

    public static void main(String[] args) {
        int[] array1 = generateRandomArray();
        int[] array2 = generateRandomArray();

        long time1 = System.currentTimeMillis();
        int[] result1 = syncMultiplyArrays(array1, array2, 0);
        System.out.printf("Sync (sleep = 0): %d ms\n", System.currentTimeMillis() - time1);

        long time2 = System.currentTimeMillis();
        int[] result2 = syncMultiplyArrays(array1, array2, 1);
        System.out.printf("Sync (sleep = 1): %d ms\n", System.currentTimeMillis() - time2);

        long time3 = System.currentTimeMillis();
        int[] result3 = parallelMultiplyArrays(array1, array2, 0);
        System.out.printf("Parallel (sleep = 0): %d ms\n", System.currentTimeMillis() - time3);

        long time4 = System.currentTimeMillis();
        int[] result4 = parallelMultiplyArrays(array1, array2, 1);
        System.out.printf("Parallel (sleep = 1): %d ms\n", System.currentTimeMillis() - time4);
    }

    private static int[] syncMultiplyArrays(int[] array1, int[] array2, int sleep) {
        int[] result = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            result[i] = array1[i] * array2[i];
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    private static int[] parallelMultiplyArrays(int[] array1, int[] array2, int sleep) {
        int[] result = IntStream.range(0, ARRAY_SIZE)
                .parallel()
                .map(i -> {
                    int value = array1[i] * array2[i];
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return value;
                })
                .toArray();
        return result;
    }

    private static int[] generateRandomArray() {
        Random random = new Random();
        int[] array = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = random.nextInt(101);
        }
        return array;
    }
}