import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputReader {
    static int[][] readGraphFromFile(String filename, int V) throws IOException {
        int[][] adj = new int[V][V];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < V) {
                String[] values = line.split(" ");
                for (int col = 0; col < V; col++) {
                    adj[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
        }
        return adj;
    }
}
