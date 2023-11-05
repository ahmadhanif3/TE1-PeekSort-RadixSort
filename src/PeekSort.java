public class PeekSort {
    public static void peekSort(final int[] a, final int l, final int r) {
        int n = r - l + 1;
        peekSort(a, l, r, l, r, new int[n]);
    }

    private static void peekSort(int[] arrA, int left, int right, int end, int start, int[] arrB) {
        if (end == right || start == left)
            return;
        int mid = left + ((right - left) >> 1);
        if (mid <= end) {
            peekSort(arrA, end+1, right, end+1, start, arrB);
            mergeRuns(arrA, left, end+1, right, arrB);
        }
        else if (mid >= start) {
            peekSort(arrA, left, start-1, end, start-1, arrB);
            mergeRuns(arrA, left, start, right, arrB);
        }
        else {
            int i;
            int j;
            if (arrA[mid] <= arrA[mid+1]) {
                i = extendIncreasingRunLeft(arrA, mid, end+1);
                j = mid+1 == start ? mid : extendIncreasingRunRight(arrA, mid+1, start-1);
            }
            else {
                i = extendDecreasingRunLeft(arrA, mid, end+1);
                j = mid+1 == start ? mid : extendDecreasingRunRight(arrA, mid+1, start-1);
                reverseRange(arrA, i, j);
            }
            if (i == left && j == right)
                return;
            if (mid-i < j-mid) {
                peekSort(arrA, left, i-1, end, i-1, arrB);
                peekSort(arrA, i, right, j, start, arrB);
                mergeRuns(arrA, left, i, right, arrB);
            }
            else {
                peekSort(arrA, left, j, end, i, arrB);
                peekSort(arrA, j+1, right, j+1, start, arrB);
                mergeRuns(arrA, left, j+1, right, arrB);
            }
        }

    }

    private static void mergeRuns(int[] arrA, int l, int m, int r, int[] aux) {
        --m;
        int i;
        int j;
        for (i = m+1; i > l; --i)
            aux[i-1] = arrA[i-1];
        for (j = m; j < r; ++j)
            aux[r+m-j] = arrA[j+1];
        for (int k = l; k <= r; ++k)
            arrA[k] = aux[j] < aux[i] ? aux[j--] : aux[i++];
    }

    private static int extendIncreasingRunLeft(int[] arrA, int i, int l) {
        while (i > l && arrA[i-1] <= arrA[i])
            --i;
        return i;
    }

    private static int extendIncreasingRunRight(int[] arrA, int i, int r) {
        while (i < r && arrA[i-1] >= arrA[i])
            ++i;
        return i;
    }

    private static int extendDecreasingRunLeft(int[] arrA, int i, int l) {
        while (i > l && arrA[i-1] > arrA[i])
            --i;
        return i;
    }

    private static int extendDecreasingRunRight(int[] arrA, int i, int r) {
        while (i < r && arrA[i-1] < arrA[i])
            ++i;
        return i;
    }

    private static void reverseRange(int[] arrA, int lo, int hi) {
        while (lo < hi) {
            int t = arrA[lo];
            arrA[lo++] = arrA[hi];
            arrA[hi--] = t;
        }
    }
}
