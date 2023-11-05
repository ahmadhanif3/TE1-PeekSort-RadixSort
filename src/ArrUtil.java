import java.util.Random;

public class ArrUtil {
    public static int[] copyArray(int[] arr) {
        int n = arr.length;
        int[] copy = new int[n];
        System.arraycopy(arr, 0, copy, 0, n);
        return copy;
    }

    public static boolean checkSorted(int[] arr) {
        boolean sorted = true;
        for(int i = 1; i < arr.length; i++) {
            if(arr[i-1] > arr[i]){
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public static int[] generateArray(int n, String type) {
        if (n < 1) {
            System.out.println("Sorry, invalid n input");
            return new int[0];
        }
        switch (type) {
            case "Sorted" -> {
                return generateSorted(n);
            }
            case "Reversed" -> {
                return generateReversed(n);
            }
            case "Random" -> {
                return generateRandom(n, new Random());
            }
            default -> {
                System.out.println("Sorry, invalid type input");
                return new int[0];
            }
        }
    }

    private static int[] generateSorted(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    private static int[] generateReversed(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }
        return arr;
    }

    private static int[] generateRandom(int n, Random random) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }
}
