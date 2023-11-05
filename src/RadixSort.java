import java.util.Arrays;

public class RadixSort {
    public static void radixSort(int[] arrA, int n) {
        int max = arrA[0];
        for (int i = 0; i < n; i++) {
            int temp = arrA[i];
            if (temp > max)
                max = temp;
        }

        for (int exp = 1; max / exp > 0; exp *= 10)
            countingSort(arrA, n, exp);
    }

    private static void countingSort(int[] arrA, int n, int exp) {
        int[] output = new int[n];
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++)
            count[(arrA[i] / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i-1];

        for (i = n-1; i >= 0; i--) {
            output[count[(arrA[i] / exp) % 10] - 1] = arrA[i];
            count[(arrA[i] / exp) % 10]--;
        }

        for (i = 0; i < n; i++) {
            arrA[i] = output[i];
        }
    }
}
