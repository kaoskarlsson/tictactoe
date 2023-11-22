// Lägg till dessa metoder i TicTacToeGame-klassen


import javax.swing.*;

public class CheckWin {
    private boolean checkWin(String symbol) {
        // Kolla rader
        AbstractButton[] buttons;
        for (int i = 0; i < 3; i++) {
            if (buttons[i * 3].getText().equals(symbol) &&
                    buttons[i * 3 + 1].getText().equals(symbol) &&
                    buttons[i * 3 + 2].getText().equals(symbol)) {
                highlightWinningCells(i * 3, i * 3 + 1, i * 3 + 2);
                return true;
            }
        }

        // Kolla kolumner
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(symbol) &&
                    buttons[i + 3].getText().equals(symbol) &&
                    buttons[i + 6].getText().equals(symbol)) {
                highlightWinningCells(i, i + 3, i + 6);
                return true;
            }
        }

        // Kolla diagonaler
        if (buttons[0].getText().equals(symbol) &&
                buttons[4].getText().equals(symbol) &&
                buttons[8].getText().equals(symbol)) {
            highlightWinningCells(0, 4, 8);
            return true;
        }

        if (buttons[2].getText().equals(symbol) &&
                buttons[4].getText().equals(symbol) &&
                buttons[6].getText().equals(symbol)) {
            highlightWinningCells(2, 4, 6);
            return true;
        }

        return false;
    }

    private void highlightWinningCells(int cell1, int cell2, int cell3) {
        buttons[cell1].setBackground(Color.GREEN);
        buttons[cell2].setBackground(Color.GREEN);
        buttons[cell3].setBackground(Color.GREEN);

        disableAllButtons();
    }

    private void disableAllButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().isEmpty()) {
            makeMove(clickedButton);
            if (checkWin("X")) {
                textfield.setText("X wins!");
            } else if (checkWin("O")) {
                textfield.setText("O wins!");
            } else {
                checkTie();
            }
        }
    }

    private void checkTie() {
        boolean tie = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                tie = false;
                break;
            }
        }

        if (tie) {
            textfield.setText("It's a tie!");
        }
    }
}