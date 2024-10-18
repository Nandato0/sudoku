package sudoku;

import javax.swing.*;
import java.awt.*;

import static sudoku.Game.createRandomSudoku;

public class GUI extends JFrame {
    private static final int SIZE = 9;
    private JTextField[][] cells;
    int[][] matrix = createRandomSudoku(3);

    public GUI() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));
        cells = new JTextField[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Arial", Font.BOLD, 24));
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(cells[row][col]);
            }
        }

        setSize(800, 600); // Standardgröße des Fensters setzen
        setLocationRelativeTo(null); // Fenster in der Mitte des Bildschirms anzeigen

        // Beispielhafte Zahlen in einige Zellen einfügen
        setVisible(true);
    }

    private void addInitialNumbers() {
        // Hier kannst du festgelegte Zahlen in die Zellen setzen
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (matrix[x][y] != 0) {
                    cells[x][y].setText(Integer.toString(matrix[x][y]));
                    cells[x][y].setEditable(false);

                }
            }


            /* Optional: Diese Zellen nicht editierbar machen
            cells[0][0].setEditable(false);
            cells[0][1].setEditable(false);
            cells[1][0].setEditable(false);
            cells[4][4].setEditable(false);
            cells[8][8].setEditable(false);*/
        }


    }
}