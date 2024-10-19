package sudoku;

public class matrixsolver {


    static int N = 9;

    static boolean solveSudoku(int[][] grid, int row, int col) {
        if (row == N - 1 && col == N)
            return true;

        if (col == N) {
            row++;
            col = 0;
        }

        if (grid[row][col] != 0)
            return solveSudoku(grid, row, col + 1);

        for (int num = 1; num <= 9; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku(grid, row, col + 1))
                    return true;
            }
            grid[row][col] = 0;
        }
        return false;
    }

    static boolean isSafe(int[][] grid, int row, int col, int num) {
        for (int x = 0; x < N; x++)
            if (grid[row][x] == num || grid[x][col] == num)
                return false;

        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }

    static int[][] solveSudoku(int[][] inputMatrix) {
        int[][] solution = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                solution[i][j] = inputMatrix[i][j];
            }
        }

        if (solveSudoku(solution, 0, 0)) {
            return solution;
        } else {
            return null;
        }
    }

    public static boolean matrixchecker(int[][] matrix) {
        int check = 0;
        if (checkempty(matrix) == true){
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; i++) {
                if (Game.true_rules(i,j,matrix[i][j],matrix) == true){
                    check++;
                }
            }
        }
        if (check == 81){
            return true;
        }
        else {
            return false;
        }
    }


    public static boolean checkempty(int[][] matrix) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0){
                    return true;
                }
            }
        }
        return false;
    }


}

