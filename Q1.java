import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        Set<Integer> cols = new HashSet<>();
        Set<Integer> diagonals = new HashSet<>(); // row + col
        Set<Integer> antiDiagonals = new HashSet<>(); // row - col + (n - 1)
        solveNQueensUtil(0, board, cols, diagonals, antiDiagonals, solutions, n);
        return solutions;
    }

    private void solveNQueensUtil(int row, char[][] board, Set<Integer> cols, Set<Integer> diagonals, Set<Integer> antiDiagonals, List<List<String>> solutions, int n) {
        if (row == n) {
            List<String> solution = new ArrayList<>();
            for (char[] rowArr : board) {
                solution.add(new String(rowArr));
            }
            solutions.add(solution);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (cols.contains(col) || diagonals.contains(row + col) || antiDiagonals.contains(row - col + (n - 1))) {
                continue;
            }

            board[row][col] = 'Q';
            cols.add(col);
            diagonals.add(row + col);
            antiDiagonals.add(row - col + (n - 1));

            solveNQueensUtil(row + 1, board, cols, diagonals, antiDiagonals, solutions, n);

            board[row][col] = '.'; // Backtrack
            cols.remove(col);
            diagonals.remove(row + col);
            antiDiagonals.remove(row - col + (n - 1));
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n1 = 4;
        List<List<String>> result1 = solution.solveNQueens(n1);
        System.out.println("Solutions for n = " + n1 + ":");
        for (List<String> sol : result1) {
            System.out.println(sol);
        }

        int n2 = 1;
        List<List<String>> result2 = solution.solveNQueens(n2);
        System.out.println("\nSolutions for n = " + n2 + ":");
        for (List<String> sol : result2) {
            System.out.println(sol);
        }
    }
}