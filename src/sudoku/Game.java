package sudoku;

import java.util.Scanner;
import sudoku.matrixsolver;
import sudoku.MatrixComparison;

import javax.swing.*;

public class Game {
    int low = 300;
    int mid = 150;
    int high = 80;
    public static int difficulty;
    private JPanel panel1;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Schwierigkeit 1 bis 3?: ");
        String inputDif = scanner.nextLine();
        int dif = Integer.parseInt(inputDif);

        if (dif == 3) {
            difficulty = 70;
        }
        if (dif == 2) {
            difficulty = 100;
        }
        if (dif == 1) {
            difficulty = 125;
        }

        int[][] matrix = createRandomSudoku(difficulty);
        int[][] solution = matrixsolver.solveSudoku(matrix);

        while (true){
            printMatrix(matrix);
            //printMatrix(solution);
            System.out.println("Bitte geben Sie eine Position und die Zahl ein: (1 2 3) ");
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
                if (j == 3 || j == 6 || j == 9){
                    System.out.print("| ");
                }
                if (matrix[i][j] == 0){
                    System.out.print("\t");
                } else{
                    System.out.print(matrix[i][j] + "\t");  // Tabulator für Formatierung
                }
            }
            System.out.println();  // Neue Zeile nach jeder Zeile der Matrix
            if(i == 2 || i == 5 || i == 8) {
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
                if (num == 0){
                    include_num = true;
                }
                else {
                    include_num = false;
                }
            }
        }
        for (int s = 0; s < matrix.length; s++) {
            if (matrix[row][s] == num) {
                if (num == 0){
                    include_num = true;
                }
                else {
                    include_num = false;
                }
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
                    if (num == 0){
                        include_num = true;
                    }
                    else{
                        include_num = false;
                    }
                }
            }
        }
        return include_num;
    }


    public static void paste_num(int row, int col, int num, int[][] matrix) {
        matrix[row][col] = num;
    }

    public static void delete_num(int row, int col, int[][] matrix) {
        matrix[row][col] = 0;
    }


    public static int[][] createRandomSudoku(int difficulty) {
        int[][] matrix = new int[9][9];

        boolean able = true;
            for (int i = 0; i < difficulty; i++) {
                int randRow = (int) (Math.random()*9);
                int randCol = (int) (Math.random()*9);
                int randNum = (int) (Math.random() * 9) + 1;


                if (matrix[randRow][randCol] == 0){
                    if (true_rules(randRow, randCol, randNum, matrix) == true) {
                        if (matrixsolver.solveSudoku(matrix) != null) {
                            paste_num(randRow,randCol,randNum,matrix);
                            if (matrixsolver.solveSudoku(matrix) == null) {
                                delete_num(randRow,randCol,matrix);
                            }
                        }
                        else {
                            able = false;
                        }
                    }
                }
            }

        return matrix;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}