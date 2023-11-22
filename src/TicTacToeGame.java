import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame implements ActionListener {

    private JFrame frame = new JFrame();
    private JTextField textField = new JTextField();
    private JButton[] buttons = new JButton[9];
    private boolean playerXTurn = true;

    public TicTacToeGame() {
        initializeGUI();
    }

    private void initializeGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.CYAN);
        textField.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Monospaced", Font.BOLD, 30));
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }

        frame.add(textField, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        for (int i = 0; i < 9; i++) {
            if (clickedButton.equals(buttons[i]) && clickedButton.getText().isEmpty()) {
                makeMove(clickedButton);
                if (checkWin("X")) {
                    textField.setText("Player X wins!");
                } else if (checkWin("O")) {
                    textField.setText("Player O wins!");
                } else {
                    checkTie();
                }
                break;
            }
        }
    }

    private void makeMove(JButton button) {
        if (playerXTurn) {
            button.setText("X");
            textField.setText("Player O's turn");
        } else {
            button.setText("O");
            textField.setText("Player X's turn");
        }

        playerXTurn = !playerXTurn;
    }

    private boolean checkWin(String symbol) {
        // Implementera logiken för att kontrollera om någon spelare har vunnit
        // ...
        return false;
    }

    private void checkTie() {
        // Implementera logiken för att kontrollera om det är oavgjort
        // ...
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeGame::new);
    }

    public void initializeGame() {
    }
}
