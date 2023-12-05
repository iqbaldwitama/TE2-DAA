import java.io.*;
import java.util.Random;

public class NonHamiltonGenerator {
    // Main Method
    public static void main(String[] args) {
        int V = 20; // Adjust the number of vertices
        String filename = String.format("non_hamilton_%d.txt", V);

        generateNonHamiltonianGraphToFile(V, filename);
        System.out.printf("Non Hamiltonian Graph Input with %d vertices successfully written as %s", V, filename);
    }

    // Function to generate a disconnected undirected graph and write it to a file
    static void generateNonHamiltonianGraphToFile(int V, String filename) {
        Random rand = new Random();
        int[][] graph = new int[V][V]; // Adjacency matrix

        // Create a disconnected undirected graph (two separate subgraphs)
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                // Randomly decide if an edge exists within the same subgraph
                int edge = (i / (V / 2) == j / (V / 2)) ? rand.nextInt(2) : 0;
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
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
