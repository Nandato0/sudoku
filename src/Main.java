import java.util.Scanner;

public class Main {
    static int[][] matrix = {
            { 6, 0, 9, 0, 0, 0, 0, 0, 0 },
            { 8, 0, 1, 7, 6, 3, 0, 0, 9 },
            { 0, 4, 0, 9, 0, 8, 6, 5, 1 },
            { 0, 0, 7, 0, 0, 0, 9, 1, 0 },
            { 0, 8, 2, 0, 0, 6, 0, 0, 5},
            { 0, 0, 0, 1, 0, 0, 3, 8, 0},
            { 3, 0, 0, 6, 7, 2, 8, 0, 0},
            { 0, 9, 6, 8, 3, 0, 5, 2, 0},
            { 2, 0, 0, 0, 4, 0, 0, 0, 3},
    };

    public static void main(String[] args) {
        //int [][] matrix1 = new int[9][9];
        //matrix1[0][0] = 1;

        Scanner scanner = new Scanner(System.in);
        while (true){
            int[][] solution = matrixsolver.solveSudoku(matrix);
            printMatrix(matrix);
            System.out.print("Bitte geben Sie eine Position und die Zahl ein: (1 2 3) ");
            String input = scanner.nextLine();
            String[] input1 = input.split(" ");
            int row = Integer.parseInt(input1[0]);
            int col = Integer.parseInt(input1[1]);
            int num = Integer.parseInt(input1[2]);

            if (true_rules(row, col, num, matrix) == true) {
                paste_num(row,col,num,matrix);
                System.out.println("Right");
            }
            else  {
                System.out.println("Wrong");
            }
            if (MatrixComparison.areMatricesEqual(matrix, solution) == true) {
                System.out.println("Super");
                break;
            }
        }
        System.out.println("Fertig");


    }
    public static int[][] printMatrix(int [][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 3 | j == 6 | j == 9){
                    System.out.print("| ");
                }
                if (matrix[i][j] == 0){
                    System.out.print("\t");
                } else{
                    System.out.print(matrix[i][j] + "\t");  // Tabulator für Formatierung
                }
            }
            System.out.println();  // Neue Zeile nach jeder Zeile der Matrix
            if(i == 2 | i == 5 | i == 8) {
                System.out.print("------------------------------------");
                System.out.println();
            }
        }
        return matrix;
    }

    public static boolean true_rules(int row, int col, int num, int[][] matrix) {
        boolean include_num = true;

        for (int r = 0; r < matrix.length; r++) {
            if (matrix[r][col] == num) {
                include_num = false;
            }
        }
        for (int s = 0; s < matrix.length; s++) {
            if (matrix[row][s] == num) {
                include_num = false;
            }
        }

        if (row == 0 | row == 1 | row == 2) {
            row = 0;
        }
        if (row == 3 | row == 4 | row == 5) {
            row = 3;
        }
        if (row == 6 | row == 7 | row == 8) {
            row = 6;
        }
        if (col == 0 | col == 1 | col == 2) {
            col = 0;
        }
        if (col == 3 | col == 4 | col == 5) {
            col = 3;
        }
        if (col == 6 | col == 7 | col == 8) {
            col = 6;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (num == matrix[row+i][col+j]) {
                    include_num = false;
                }
            }
        }
        return include_num;
    }


    public static void paste_num(int row, int col, int num, int[][] matrix) {
        matrix[row][col] = num;
    }

}