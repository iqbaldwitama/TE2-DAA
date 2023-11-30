// The Backtracking code was taken from https://www.geeksforgeeks.org/hamiltonian-cycle/

import java.io.IOException;

public class Backtracking
{
    public static void main(String args[])
    {
        // Adjust the number of vertices (16, 18, or 20)
        int V = 20; 

        // Adjust the type of the graph input file (non_hamilton or hamilton)
        String type = "non_hamilton";
        
        String filename = String.format("%s_%d.txt", type, V); 

        try {
            Backtracking hamiltonian = new Backtracking();

            int[][] adj = InputReader.readGraphFromFile(filename, V);

            Runtime rt = Runtime.getRuntime();
            long startTime = System.currentTimeMillis();
            long startMem = rt.totalMemory() - rt.freeMemory();

            hamiltonian.hamCycle(adj, V);

            long endMem = rt.totalMemory() - rt.freeMemory();
            long endTime = System.currentTimeMillis();

			long duration = endTime - startTime;
            long used_mem = endMem - startMem;

			System.out.printf("Program with %d vertices finished running with: %dms execution time, and %d memory", V, duration, used_mem);

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    int path[];

    /* A utility function to check if the vertex v can be
       added at index 'pos'in the Hamiltonian Cycle
       constructed so far (stored in 'path[]') */
    boolean isSafe(int v, int graph[][], int path[], int pos)
    {
        /* Check if this vertex is an adjacent vertex of
           the previously added vertex. */
        if (graph[path[pos - 1]][v] == 0)
            return false;

        /* Check if the vertex has already been included.
           This step can be optimized by creating an array
           of size V */
        for (int i = 0; i < pos; i++)
            if (path[i] == v)
                return false;

        return true;
    }

    /* A recursive utility function to solve hamiltonian
       cycle problem */
    boolean hamCycleUtil(int graph[][], int path[], int pos, int V)
    {
        /* base case: If all vertices are included in
           Hamiltonian Cycle */
        if (pos == V)
        {
            // And if there is an edge from the last included
            // vertex to the first vertex
            if (graph[path[pos - 1]][path[0]] == 1)
                return true;
            else
                return false;
        }

        // Try different vertices as a next candidate in
        // Hamiltonian Cycle. We don't try for 0 as we
        // included 0 as starting point in hamCycle()
        for (int v = 1; v < V; v++)
        {
            /* Check if this vertex can be added to Hamiltonian
               Cycle */
            if (isSafe(v, graph, path, pos))
            {
                path[pos] = v;

                /* recur to construct rest of the path */
                if (hamCycleUtil(graph, path, pos + 1, V) == true)
                    return true;

                /* If adding vertex v doesn't lead to a solution,
                   then remove it */
                path[pos] = -1;
            }
        }

        /* If no vertex can be added to Hamiltonian Cycle
           constructed so far, then return false */
        return false;
    }

    /* This function solves the Hamiltonian Cycle problem using
       Backtracking. It mainly uses hamCycleUtil() to solve the
       problem. It returns false if there is no Hamiltonian Cycle
       possible, otherwise return true and prints the path.
       Please note that there may be more than one solutions,
       this function prints one of the feasible solutions. */
    int hamCycle(int graph[][], int V)
    {
        path = new int[V];
        for (int i = 0; i < V; i++)
            path[i] = -1;

        /* Let us put vertex 0 as the first vertex in the path.
           If there is a Hamiltonian Cycle, then the path can be
           started from any point of the cycle as the graph is
           undirected */
        path[0] = 0;
        if (hamCycleUtil(graph, path, 1, V) == false)
        {
            System.out.println("\nSolution does not exist");
            return 0;
        }

        printSolution(path, V);
        return 1;
    }

    /* A utility function to print solution */
    void printSolution(int path[], int V)
    {
        System.out.println("Solution Exists");
    }
}
// This code is contributed by Abhishek Shankhadhar