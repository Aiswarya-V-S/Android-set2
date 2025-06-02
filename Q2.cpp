#include <iostream>
#include <vector>
#include <vector>
#include <string>

class Solution {
private:
    bool isCyclicUtil(int v, std::vector<std::vector<int>>& adj, std::vector<int>& visited, std::vector<int>& recursionStack) {
        visited[v] = 1; // Mark current node as visiting
        recursionStack[v] = 1;

        for (int neighbor : adj[v]) {
            if (!visited[neighbor]) {
                if (isCyclicUtil(neighbor, adj, visited, recursionStack))
                    return true;
            } else if (recursionStack[neighbor]) {
                return true;
            }
        }

        recursionStack[v] = 0; // Remove the node from recursion stack
        return false;
    }

public:
    bool hasCircularDependency(int n, std::vector<std::vector<int>>& edges) {
        std::vector<std::vector<int>> adj(n);
        for (const auto& edge : edges) {
            adj[edge[0]].push_back(edge[1]); // a depends on b
        }

        std::vector<int> visited(n, 0);
        std::vector<int> recursionStack(n, 0);

        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                if (isCyclicUtil(i, adj, visited, recursionStack))
                    return true;
            }
        }
        return false;
    }
};

int main() {
    Solution solution;
    int n1 = 4;
    std::vector<std::vector<int>> edges1 = {{0, 1}, {1, 2}, {2, 3}};
    std::cout << "circular dependency 1: " << (solution.hasCircularDependency(n1, edges1) ? "true" : "false") << std::endl;

    int n2 = 4;
    std::vector<std::vector<int>> edges2 = {{0, 1}, {1, 2}, {2, 0}};
    std::cout << "circular dependency 2: " << (solution.hasCircularDependency(n2, edges2) ? "true" : "false") << std::endl;
    int n3 = 2;
    std::vector<std::vector<int>> edges3 = {{0, 0}, {0, 1}};
    std::cout << "Self-dependency: " << (solution.hasCircularDependency(n3, edges3) ? "true" : "false") << std::endl;

    int n4 = 5;
    std::vector<std::vector<int>> edges4 = {{0, 1}, {3, 4}, {4, 3}};
    std::cout << "Disconnected cycle: " << (solution.hasCircularDependency(n4, edges4) ? "true" : "false") << std::endl;

    return 0;
}