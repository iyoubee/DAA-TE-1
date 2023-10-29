import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {
    public static void main(String[] args) {
        ArrayList<Integer> randomList = generateNonRepeatingRandomArray(1000); // Set SIZE here!

        // Simpan output ke file teks
        saveToTextFile(randomList, "random_output.txt");
    }

    public static ArrayList<Integer> generateNonRepeatingRandomArray(int size) {
        ArrayList<Integer> nonRepeatingRandomList = new ArrayList<>();
        Random random = new Random();

        // Generate numbers from 1 to size (inclusive)
        ArrayList<Integer> availableNumbers = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            availableNumbers.add(i);
        }

        for (int i = 0; i < size; i++) {
            int randomIndex = random.nextInt(availableNumbers.size());
            int randomNumber = availableNumbers.get(randomIndex);
            nonRepeatingRandomList.add(randomNumber);
            availableNumbers.remove(randomIndex);
        }

        return nonRepeatingRandomList;
    }

    public static void saveToTextFile(ArrayList<Integer> data, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));

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
