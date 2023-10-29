import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;

public class SortedGenerator {
    public static void main(String[] args) {
        ArrayList<Integer> sortedList = generateSortedArray(1000); // Set SIZE here!

        // Simpan data terurut ke dalam file teks
        saveToTextFile(sortedList, "sorted_output.txt");
    }

    public static ArrayList<Integer> generateSortedArray(int size) {
        ArrayList<Integer> sortedList = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            sortedList.add(i);
        }

        return sortedList;
    }

    public static void saveToTextFile(ArrayList<Integer> data, String fileName) {
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
            System.out.println("Data terurut telah disimpan ke " + fileName);

        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menyimpan ke file.");
            e.printStackTrace();
        }
    }
}
