// The DP code was taken from https://www.geeksforgeeks.org/hamiltonian-path-using-dynamic-programming/

import java.io.*;

class DP{
	// Driver Code
	public static void main(String[] args)
	{
		// Adjust the number of vertices (16, 18, or 20)
        int V = 20;

        // Adjust the type of the input file (non_hamilton or hamilton)
        String type = "non_hamilton";

        String filename = String.format("%s_%d.txt", type, V); // Adjust the name of the input file

        try {
            int[][] adj = InputReader.readGraphFromFile(filename, V);
			int N = adj.length;

			Runtime rt = Runtime.getRuntime();
            long startTime = System.currentTimeMillis();
            long startMem = rt.totalMemory() - rt.freeMemory();

			// Function Call
			if (Hamiltonian_path(adj, N))
				System.out.println("Solution Exists");
			else
				System.out.println("Solution Does Not Exist");

			long endMem = rt.totalMemory() - rt.freeMemory();
			long endTime = System.currentTimeMillis();

			long duration = endTime - startTime;
			long used_mem = endMem - startMem;

			System.out.printf("Program with %d vertices finished running with: %dms execution time, and %d memory", V, duration, used_mem);

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
	}

	// Function to check whether there
	// exists a Hamiltonian Path or not
	static boolean Hamiltonian_path(int adj[][], int N)
	{
		boolean dp[][] = new boolean[N][(1 << N)];

		// Set all dp[i][(1 << i)] to
		// true
		for(int i = 0; i < N; i++)
			dp[i][(1 << i)] = true;

		// Iterate over each subset
		// of nodes
		for(int i = 0; i < (1 << N); i++) 
		{
			for(int j = 0; j < N; j++) 
			{
				
				// If the jth nodes is included
				// in the current subset
				if ((i & (1 << j)) != 0)
				{

					// Find K, neighbour of j
					// also present in the
					// current subset
					for(int k = 0; k < N; k++) 
					{
						
						if ((i & (1 << k)) != 0 && 
							adj[k][j] == 1 && j != k && 
							dp[k][i ^ (1 << j)])
						{
							
							// Update dp[j][i]
							// to true
							dp[j][i] = true;
							break;
						}
					}
				}
			}
		}

		// Traverse the vertices
		for(int i = 0; i < N; i++) 
		{
			
			// Hamiltonian Path exists
			if (dp[i][(1 << N) - 1])
				return true;
		}

		// Otherwise, return false
		return false;
	}
}
// This code is contributed by Kingash