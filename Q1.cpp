#include <iostream>
#include <vector>
#include <string>

class Solution {
private:
    std::vector<std::vector<std::string>> solutions;
    int n;

    bool isSafe(int row, int col, const std::vector<std::string>& board) {
        for (int i = 0; i < row; ++i) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; --i, ++j) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
    void solveNQueensUtil(int row, std::vector<std::string>& board) {
        if (row == n) {
            solutions.push_back(board);
            return;
        }
        for (int col = 0; col < n; ++col) {
            if (isSafe(row, col, board)) {
                board[row][col] = 'Q';
                solveNQueensUtil(row + 1, board);
                board[row][col] = '.'; 
            }
        }
    }
public:
    std::vector<std::vector<std::string>> solveNQueens(int n) {
        this->n = n;
        solutions.clear();
        std::vector<std::string> board(n, std::string(n, '.'));
        solveNQueensUtil(0, board);
        return solutions;
    }
};

int main() {
    Solution solution;
    int n1 = 4;
    std::vector<std::vector<std::string>> result1 = solution.solveNQueens(n1);
    std::cout << "Solutions for n = " << n1 << ":" << std::endl;
    for (const auto& sol : result1) {
        for (const auto& row : sol) {
            std::cout << row << std::endl;
        }
        std::cout << std::endl;
    }
    int n2 = 1;
    std::vector<std::vector<std::string>> result2 = solution.solveNQueens(n2);
    std::cout << "n = " << n2 << ":" << std::endl;
    for (const auto& sol : result2) {
        for (const auto& row : sol) {
            std::cout << row << std::endl;
        }
        std::cout << std::endl;
    }
    return 0;
}