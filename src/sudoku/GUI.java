package sudoku;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private static final int SIZE = 9;
    private JTextField[][] cells;

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
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
