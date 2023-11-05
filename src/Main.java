public class Main {
    public static void main(String[] args) {
        // Small Array
        int[] smallRadixSorted = ArrUtil.generateArray(1_000, "Sorted");
        int[] smallPeekSorted = ArrUtil.copyArray(smallRadixSorted);
        int[] smallRadixReversed = ArrUtil.generateArray(1_000, "Reversed");
        int[] smallPeekReversed = ArrUtil.copyArray(smallRadixReversed);
        int[] smallRadixRandom = ArrUtil.generateArray(1_000, "Random");
        int[] smallPeekRandom = ArrUtil.copyArray(smallRadixRandom);

        // Medium Array
        int[] mediumRadixSorted = ArrUtil.generateArray(10_000, "Sorted");
        int[] mediumPeekSorted = ArrUtil.copyArray(mediumRadixSorted);
        int[] mediumRadixReversed = ArrUtil.generateArray(10_000, "Reversed");
        int[] mediumPeekReversed = ArrUtil.copyArray(mediumRadixReversed);
        int[] mediumRadixRandom = ArrUtil.generateArray(10_000, "Random");
        int[] mediumPeekRandom = ArrUtil.copyArray(mediumRadixRandom);

        // Big Array
        int[] bigRadixSorted = ArrUtil.generateArray(100_000, "Sorted");
        int[] bigPeekSorted = ArrUtil.copyArray(bigRadixSorted);
        int[] bigRadixReversed = ArrUtil.generateArray(100_000, "Reversed");
        int[] bigPeekReversed = ArrUtil.copyArray(bigRadixReversed);
        int[] bigRadixRandom = ArrUtil.generateArray(100_000, "Random");
        int[] bigPeekRandom = ArrUtil.copyArray(bigRadixRandom);

        // Small Size
        System.out.println("############## Small Size; n = 1_000 ##############");
        // Sorted
        compare(smallRadixSorted, smallPeekSorted, "small sorted");
        // Reversed
        compare(smallRadixReversed, smallPeekReversed, "small reversed");
        // Random
        compare(smallRadixRandom, smallPeekRandom, "small random");

        // Medium Size
        System.out.println("############## Medium Size; n = 10_000 ##############");
        // Sorted
        compare(mediumRadixSorted, mediumPeekSorted, "medium sorted");
        // Reversed
        compare(mediumRadixReversed, mediumPeekReversed, "medium reversed");
        // Random
        compare(mediumRadixRandom, mediumPeekRandom, "medium random");

        // Big Size
        System.out.println("############## Big Size; n = 100_000 ##############");
        // Sorted
        compare(bigRadixSorted, bigPeekSorted, "big sorted");
        // Reversed
        compare(bigRadixReversed, bigPeekReversed, "big reversed");
        // Random
        compare(bigRadixRandom, bigPeekRandom, "big random");
    }

    private static void compare(int[] radix, int[] peek, String type) {
        // Check if same size
        int n = radix.length;
        if (n != peek.length) {
            System.out.println("Both array have different sizes");
            return;
        }
        Runtime runtime = Runtime.getRuntime();

        // Radix
        double startTimeRadix = System.nanoTime() / 1_000_000.0;
        long memoryBeforeRadix = runtime.totalMemory() - runtime.freeMemory();
        RadixSort.radixSort(radix, n);
        long memoryAfterRadix = runtime.totalMemory() - runtime.freeMemory();
        double endTimeRadix = System.nanoTime() / 1_000_000.0;

        // Peek
        double startTimePeek = System.nanoTime() / 1_000_000.0;
        long memoryBeforePeek = runtime.totalMemory() - runtime.freeMemory();
        PeekSort.peekSort(peek, 0, n-1);
        long memoryAfterPeek = runtime.totalMemory() - runtime.freeMemory();
        double endTimePeek = System.nanoTime() / 1_000_000.0;

        // Comparison && Check if sorted
        boolean radixSorted = ArrUtil.checkSorted(radix);
        boolean peekSorted = ArrUtil.checkSorted(peek);
        double timeRadix = (endTimeRadix-startTimeRadix);
        double timePeek = (endTimePeek-startTimePeek);
        long memRadix = memoryAfterRadix - memoryBeforeRadix;
        long memPeek = memoryAfterPeek - memoryBeforePeek;

        // Output
        System.out.println("Time for sorting " + type + " array input; n = " + n);
        System.out.println("Radix Sort: " + (timeRadix) + "ms; is sorted = " + radixSorted + "; Memory used by Radix Sort: " + memRadix + " memory");
        System.out.println("Peek Sort:  " + (timePeek) + "ms; is sorted = " + peekSorted+ "; Memory used by Peek Sort: " + memPeek + " memory");
        System.out.println((timeRadix < timePeek ? "Radix sort" : "Peek sort") + " is faster");
        System.out.println();
    }
}

