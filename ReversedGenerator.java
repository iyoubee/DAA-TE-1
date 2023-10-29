import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;

public class ReversedGenerator {
    public static void main(String[] args) {
        ArrayList<Integer> reverseSortedData = generateReverseSortedArray(1000); // Set SIZE here!

        // Simpan data yang terurut dari besar ke kecil ke dalam file teks
        saveToTextFile(reverseSortedData, "reversed_output.txt");
    }

    public static ArrayList<Integer> generateReverseSortedArray(int size) {
        ArrayList<Integer> reverseSortedData = new ArrayList<>();

        for (int i = size; i >= 1; i--) {
            reverseSortedData.add(i);
        }

        return reverseSortedData;
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
            System.out.println("Data yang terurut dari besar ke kecil telah disimpan ke " + fileName);

        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menyimpan ke file.");
            e.printStackTrace();
        }
    }
}
