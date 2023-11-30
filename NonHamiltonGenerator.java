import java.io.*;
import java.util.Random;

public class NonHamiltonGenerator {
    // Main Method
    public static void main(String[] args) {
        int V = 16; // Adjust the number of vertices
        String filename = String.format("non_hamilton_%d.txt", V);

        generateNonHamiltonianGraphToFile(V, filename);
        System.out.printf("Non Hamiltonian Graph Input with %d vertices successfully written as %s", V, filename);
    }

    // Function to generate a disconnected graph and write it to a file
    static void generateNonHamiltonianGraphToFile(int V, String filename) {
        Random rand = new Random();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // Create a disconnected graph (two separate subgraphs)
                    int edge = (i != j) && (i / 2 == j / 2) ? rand.nextInt(2) : 0;
                    writer.write(edge + ((j < V - 1) ? " " : ""));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
