import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class HamiltonGenerator {
    public static void main(String[] args) {
        int V = 20; // Adjust the number of vertices

        String filename = String.format("hamilton_%d.txt", V);
        generateRandomGraphToFile(V, filename);
    }

    static void generateRandomGraphToFile(int V, String filename) {
        Random rand = new Random();
        int[][] graph = new int[V][V]; // Adjacency matrix

        // Generate a symmetric matrix for undirected graph
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                int edge = rand.nextInt(2); // Randomly decide if an edge exists
                graph[i][j] = edge;
                graph[j][i] = edge; // Symmetric edge
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    writer.write(graph[i][j] + ((j < V - 1) ? " " : ""));
                }
                writer.newLine();
            }
            System.out.printf("Undirected Hamiltonian Graph Input with %d vertices successfully written as %s", V, filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
