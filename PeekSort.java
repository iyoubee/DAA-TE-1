import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PeekSort {

    /**
     * Main method to demonstrate PeekSort.
     * 
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            // Membaca file
            // File yang tersedia: random_output.txt, reversed_output.txt, sorted_output.txt
            BufferedReader reader = new BufferedReader(new FileReader("random_output.txt"));

            // Membaca file
            ArrayList<Integer> numbersList = new ArrayList<>();
            String line = reader.readLine();
            String[] numberStrings = line.split(";");
            for (String numberString : numberStrings) {
                int number = Integer.parseInt(numberString);
                numbersList.add(number);
            }
            Integer[] numbersArray = numbersList.toArray(new Integer[numbersList.size()]);
            reader.close();

            // Lakukan PeekSort
            long startTime = System.currentTimeMillis();
            Runtime runtime = Runtime.getRuntime();
            peekSort(numbersArray);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            long usedMemory = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Waktu eksekusi: " + executionTime + " milidetik");
            System.out.println("Penggunaan memori: " + (usedMemory) + " byte");
            saveToTextFile(numbersArray, "output.txt");
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat membaca file.");
            e.printStackTrace();
        }
    }

    /**
     * Sorts an array using PeekSort algorithm.
     * 
     * @param A The array to be sorted.
     */
    public static void peekSort(Integer[] A) {
        int r = A.length - 1;
        peeksort(A, 0, r, 0, r);
    }

    /**
     * Recursive function to perform the PeekSort algorithm.
     * 
     * @param A The array to be sorted.
     * @param l The left index of the subarray.
     * @param r The right index of the subarray.
     * @param e The left endpoint.
     * @param s The right endpoint.
     */
    public static void peeksort(Integer[] A, int l, int r, int e, int s) { // Implementation of PeekSort algorithm
        if (r - l <= 0) {
            return;
        }

        if (e == r || s == l) {
            return;
        }

        int m = l + ((r - l) / 2);

        if (m <= e) {
            peeksort(A, e + 1, r, e + 1, s);
            merge(A, l, e, e + 1, r);
        } else if (m >= s) {
            peeksort(A, l, s - 1, e, s - 1);
            merge(A, l, s - 1, s, r);
        } else {
            int i = extendRunLeft(A, m, l);
            int j = extendRunRight(A, m, r);
            if (i == l && j == r) {
                return;
            }
            if (m - 1 < j - m) {
                peeksort(A, l, i - 1, e, i - 1);
                peeksort(A, i, r, j, s);
                merge(A, l, i - 1, i, r);
            } else {
                peeksort(A, l, j, e, i);
                peeksort(A, j + 1, r, j + 1, s);
                merge(A, l, j, j + 1, r);
            }
        }
    }

    /**
     * Extends the run of increasing elements to the right.
     * 
     * @param A     The array to be extended.
     * @param j     The current position.
     * @param right The right endpoint.
     * @return The new position.
     */
    public static int extendRunRight(Integer[] A, int j, int right) {
        while (j < right && A[j + 1] >= A[j])
            ++j;
        return j;
    }

    /**
     * Extends the run of increasing elements to the left.
     * 
     * @param A    The array to be extended.
     * @param i    The current position.
     * @param left The left endpoint.
     * @return The new position.
     */
    public static int extendRunLeft(Integer[] A, int i, int left) {
        while (i > left && A[i - 1] <= A[i])
            --i;
        return i;
    }

    /**
     * Merges two subarrays.
     * 
     * @param arr         The array containing the subarrays to be merged.
     * @param left        The left index of the first subarray.
     * @param middleLeft  The right index of the first subarray.
     * @param middleRight The left index of the second subarray.
     * @param right       The right index of the second subarray.
     */
    public static void merge(Integer[] arr, int left, int middleLeft, int middleRight, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = middleLeft - left + 1;
        int n2 = right - middleRight + 1;

        // Create temporary arrays
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data to temporary arrays leftArray[] and rightArray[]
        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = arr[middleRight + j];
        }

        // Merge the temporary arrays
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray[], if any
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray[], if any
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    /**
     * Prints the elements of an array to the console.
     *
     * @param arr The array to be printed.
     */
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * Saves an array of integers to a text file.
     *
     * @param data     The array of integers to be saved to the file.
     * @param fileName The destination file name for storing the data.
     */
    public static void saveToTextFile(Integer[] data, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName)); // Gunakan mode "append"

            // Gabungkan angka dalam satu baris dipisahkan oleh titik koma
            StringBuilder sb = new StringBuilder();
            for (int num : data) {
                sb.append(num);
                sb.append(";");
            }

            writer.print(sb.substring(0, sb.length() - 1)); // Hapus titik koma terakhir
            writer.print("\n");
            writer.close();
            System.out.println("Output telah disimpan ke " + fileName);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menyimpan ke file.");
            e.printStackTrace();
        }
    }
}
