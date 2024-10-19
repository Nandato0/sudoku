package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static sudoku.Game.*;

public class SudokuGUI extends JFrame {
    private static final int SIZE = 9;
    private JTextField[][] cells;
    public static int sudokuDifficulty;
    private int[][] matrix = createRandomSudoku(sudokuDifficulty);
            /*{
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 0, 2, 8, 6, 1, 7, 9}
    };*/


    public SudokuGUI(int sudokuDifficulty) {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));
        cells = new JTextField[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Arial", Font.BOLD, 24));

                // Erstelle dickere Linien für 3x3-Blöcke
                int top = (row % 3 == 0) ? 3 : 1;
                int left = (col % 3 == 0) ? 3 : 1;
                int bottom = (row == SIZE - 1) ? 3 : 1;
                int right = (col == SIZE - 1) ? 3 : 1;

                cells[row][col].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                // Wenn die Zelle gegebenn ist, nicht editierbar machen
                if (matrix[row][col] != 0) {
                    cells[row][col].setText(Integer.toString(matrix[row][col]));
                    cells[row][col].setEditable(false);
                } else {
                    // benutzerdefinierte Eingaben hinzufügen
                    cells[row][col].addActionListener(new CellActionListener(row, col));
                }
                add(cells[row][col]);
            }
        }


        setSize(800, 600); // größe vom fenster
        setLocationRelativeTo(null);
        setVisible(true);
        if (MatrixComparison.areMatricesEqual(matrix,matrixsolver.solveSudoku(matrix)) == true){
            JOptionPane.showMessageDialog(null, "Gewonnen!", "Spielstatus", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // eingabe von zahlen
    private class CellActionListener implements ActionListener {
        private int row, col;

        public CellActionListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField source = (JTextField) e.getSource();
            String text = source.getText();



            if (isValidNumber(text)) {
                int number = Integer.parseInt(text);
                System.out.println("Zahl " + number + " an Position (" + row + ", " + col + ") eingegeben.");
                if (number == 0) {
                    paste_num(row,col,number,matrix);
                    cells[row][col].setForeground(Color.RED); // Schriftfarbe auf Schwarz setzen
                }
                else if (Game.true_rules(row,col,number,matrix)) {
                    paste_num(row,col,number,matrix);
                    cells[row][col].setForeground(Color.GREEN); // Schriftfarbe auf Schwarz setzen
                }
                else {
                    paste_num(row,col,0,matrix);
                    cells[row][col].setForeground(Color.RED); // Schriftfarbe auf Schwarz setzen
                }

                /*System.out.println(Game.printMatrix(matrix));
                System.out.println(Game.printMatrix(matrixsolver.solveSudoku(matrix)));*/
            } else {
                JOptionPane.showMessageDialog(null, "Bitte eine Zahl zwischen 1 und 9 eingeben.");
                source.setText("");  // Lösche falsche Eingabe
            }
            if (MatrixComparison.areMatricesEqual(matrix,matrixsolver.solveSudoku(matrix))){
                JOptionPane.showMessageDialog(null, "Gewonnen!", "Spielstatus", JOptionPane.INFORMATION_MESSAGE);
            } else if (matrixsolver.matrixchecker(matrix)) {
                JOptionPane.showMessageDialog(null, "Gewonnen!", "Spielstatus", JOptionPane.INFORMATION_MESSAGE);
            }
        }


        private boolean isValidNumber(String text) {
            try {
                int number = Integer.parseInt(text);
                return number >= 0 && number <= 9;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        // fragt nach schwierigkeit
        Object[] options = {"Leicht", "Mittel", "Schwer"};
        int difficulty = JOptionPane.showOptionDialog(null,
                "Wähle eine Schwierigkeitsstufe:",
                "Schwierigkeit auswählen",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        switch (difficulty) {
            case 0:
                sudokuDifficulty = 500; // Leicht
                break;
            case 1:
                sudokuDifficulty = 120; // Mittel
                break;
            case 2:
                sudokuDifficulty = 40; // Schwer
                break;
            default:
                sudokuDifficulty = 1; // Standard: Mittel
                break;
        }

        SwingUtilities.invokeLater(() -> new SudokuGUI(sudokuDifficulty));
    }
}
