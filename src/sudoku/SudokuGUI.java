package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static sudoku.Game.createRandomSudoku;
import static sudoku.Game.paste_num;

public class SudokuGUI extends JFrame {
    private static final int SIZE = 9;
    private JTextField[][] cells;
    private int[][] matrix =  {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 0, 5, 2, 8, 6, 1, 7, 9}
    };//createRandomSudoku(150);  // Beispielmatrix mit Zahlen

    public SudokuGUI() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));
        cells = new JTextField[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Arial", Font.BOLD, 24));

                // Erstelle dickere Linien für die 3x3-Blöcke
                int top = (row % 3 == 0) ? 3 : 1;
                int left = (col % 3 == 0) ? 3 : 1;
                int bottom = (row == SIZE - 1) ? 3 : 1;
                int right = (col == SIZE - 1) ? 3 : 1;

                cells[row][col].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                // Wenn die Zelle vorgegeben ist, nicht editierbar machen
                if (matrix[row][col] != 0) {
                    cells[row][col].setText(Integer.toString(matrix[row][col]));
                    cells[row][col].setEditable(false);
                } else {
                    // ActionListener für benutzerdefinierte Eingaben hinzufügen
                    cells[row][col].addActionListener(new CellActionListener(row, col));
                }

                add(cells[row][col]);
            }
        }

        setSize(800, 600); // Standardgröße des Fensters setzen
        setLocationRelativeTo(null); // Fenster in der Mitte des Bildschirms anzeigen
        setVisible(true);
    }

    // ActionListener-Klasse für die Eingabe in den Zellen
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

            // Überprüfe, ob die Eingabe eine Zahl zwischen 1 und 9 ist
            if (isValidNumber(text)) {
                int number = Integer.parseInt(text);
                System.out.println("Zahl " + number + " an Position (" + row + ", " + col + ") eingegeben.");
                if (Game.true_rules(row,col,number,matrix)) {
                    paste_num(row,col,number,matrix);
                }
                System.out.println(Game.printMatrix(matrix));
            } else {
                JOptionPane.showMessageDialog(null, "Bitte eine Zahl zwischen 1 und 9 eingeben.");
                source.setText("");  // Lösche falsche Eingabe
            }
        }

        // Überprüft, ob der Text eine gültige Zahl ist
        private boolean isValidNumber(String text) {
            try {
                int number = Integer.parseInt(text);
                return number >= 0 && number <= 9;  // Zahl muss zwischen 1 und 9 liegen
            } catch (NumberFormatException e) {
                return false;  // Keine gültige Zahl
            }
        }
    }

    public static void main(String[] args) {
        // Erstelle die GUI im Swing-Thread
        SwingUtilities.invokeLater(SudokuGUI::new);
    }
}
