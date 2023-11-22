private void checkTie() {
        boolean tie = true;

        // Kolla om det finns några tomma celler kvar
        for (JButton button : buttons) {
        if (button.getText().isEmpty()) {
        tie = false;
        break;
        }
        }

        // Om ingen vunnit och inga tomma celler finns kvar, är det oavgjort
        if (tie) {
        textField.setText("It's a tie!");
        disableAllButtons();
        }
        }

private void disableAllButtons() {
        for (JButton button : buttons) {
        button.setEnabled(false);
        }
        }
