import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class HamiltonGenerator {
    public static void main(String[] args) {
        int V = 20; // Adjust the number of vertices

        String filename = String.format("hamilton_%d.txt",V);
        generateRandomGraphToFile(V, filename);
    }

    static void generateRandomGraphToFile(int V, String filename) {
        Random rand = new Random();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    int edge = (i != j) ? rand.nextInt(2) : 0; // No self-loops
                    writer.write(edge + ((j < V - 1) ? " " : ""));
                }
                writer.newLine();
            }
            System.out.printf("Hamiltonian Graph Input with %d vertices successfully written as %s", V, filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }    
}
