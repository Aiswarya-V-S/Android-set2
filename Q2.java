import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean hasCircularDependency(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]); // Module a depends on module b
        }

        int[] visited = new int[n]; // 0: not visited, 1: visiting, 2: visited

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                if (isCyclicUtil(i, adj, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclicUtil(int node, List<List<Integer>> adj, int[] visited) {
        visited[node] = 1; // Mark as currently visiting

        for (int neighbor : adj.get(node)) {
            if (visited[neighbor] == 1) {
                return true; // Cycle detected
            }
            if (visited[neighbor] == 0) {
                if (isCyclicUtil(neighbor, adj, visited)) {
                    return true;
                }
            }
        }

        visited[node] = 2; // Mark as visited
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int n1 = 4;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 3}};
        System.out.println("Has circular dependency (Example 1): " + solution.hasCircularDependency(n1, edges1));

        // Example 2
        int n2 = 4;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("Has circular dependency (Example 2): " + solution.hasCircularDependency(n2, edges2));

        // Example with self-dependency
        int n3 = 2;
        int[][] edges3 = {{0, 0}, {0, 1}};
        System.out.println("Has circular dependency (Example with self-dependency): " + solution.hasCircularDependency(n3, edges3));

        // Example with disconnected components and a cycle
        int n4 = 5;
        int[][] edges4 = {{0, 1}, {3, 4}, {4, 3}};
        System.out.println("Has circular dependency (Example with disconnected cycle): " + solution.hasCircularDependency(n4, edges4));
    }
}