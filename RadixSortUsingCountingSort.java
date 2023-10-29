import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class RadixSortUsingCountingSort {

    /**
     * Main method to demonstrate RadixSort Using CountingSort.
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

            // Lakukan RadixSort
            long startTime = System.currentTimeMillis();
            Runtime runtime = Runtime.getRuntime();
            radixSort(numbersArray);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            long usedMemory = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Waktu eksekusi: " + executionTime + " milidetik");
            System.out.println("Penggunaan memori: " + (usedMemory) + " byte");
            saveToTextFile(numbersArray, "output2.txt");
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat membaca file.");
            e.printStackTrace();
        }
    }

    /**
     * Sorts an array using Counting Sort based on the specified digit position
     * (exp).
     *
     * @param arr The array to be sorted.
     * @param exp The position of the digit to be considered (1 for least
     *            significant, 10 for tens, etc.).
     */
    public static void countingSort(Integer[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Initialize the count array
        Arrays.fill(count, 0);

        // Count the occurrences of each digit in the given position (exp)
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // Calculate the cumulative count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array using the count array
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy the output array to the original array
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    /**
     * Sorts an array of integers using the Radix Sort algorithm.
     *
     * @param arr The array to be sorted.
     */
    public static void radixSort(Integer[] arr) {
        int max = findMax(arr);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
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

    public static Integer findMax(Integer[] array) {
        if (array == null || array.length == 0) {
            return null; // Handle empty or null array
        }

        Integer max = array[0]; // Assume the first element is the maximum

        for (int i = 1; i < array.length; i++) {
            if (array[i] != null && array[i] > max) {
                max = array[i]; // Update max if a greater value is found
            }
        }

        return max;
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
