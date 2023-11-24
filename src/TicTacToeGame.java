// Innehåller klasser och metoder för att skapa GUI-komponenter
import javax.swing.*;
// Innehåller klasser för GUI-komponenter och layouthantering
import java.awt.*;
// Representerar en händelse som skapas när en användaråtgärd utförs
import java.awt.event.ActionEvent;
// Ett gränssnitt för att lyssna på händelser av typen ActionEvent
import java.awt.event.ActionListener;
// Används för att generera slumpmässiga tal
import java.util.Random;

// Klassen TicTacToeGame implementerar ActionListener för att hantera händelser från buttons
public class TicTacToeGame implements ActionListener {

    // En instans av Random för att hantera slumpmässiga val
    Random random = new Random();
    // Fönstret för spelet
    JFrame frame = new JFrame();
    // Visar meddelanden och spelstatus
    JTextField textfield = new JTextField();
    JLabel label = new JLabel();
    // JPanel för titeln
    JPanel title_panel = new JPanel();
    // JPanel för spelknapparna
    JPanel button_panel = new JPanel();
    JPanel south_panel = new JPanel();


    // Knappar för olika spelalternativ + reset
    JButton oneplayer_button = new JButton();
    JButton twoplayer_button = new JButton();
    JButton reset_button = new JButton();

    // Array för spelknapparna
    JButton[] buttons = new JButton[9];

    // Variabel för att hålla reda på vilken spelares tur det är
    boolean player1_turn;
    boolean twoPlayer_Mode;

    // Konstruktor för att initialisera och sätta upp spelet
    TicTacToeGame() {
        // Sätter avslutningsmetoden när användaren stänger fönstret
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Sätter storleken på fönstret till 400x400 pixlar
        frame.setSize(400, 400);
        // Sätter bakgrundsfärgen för innehållet i fönstret till en mörk grå färg
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        // Sätter layouthanteraren för innehållet i fönstret till BorderLayout
        frame.setLayout(new BorderLayout());
        // Gör fönstret synligt för användaren
        frame.setVisible(true);

        // Inställningar för textfält som visar meddelanden och spelstatus
        textfield.setBackground(Color.BLACK);
        textfield.setForeground(Color.CYAN);
        textfield.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        // Inställningar för titel_panel
        title_panel.setLayout(new BorderLayout());
        title_panel.setPreferredSize(new Dimension(800, 50));

        // Inställningar för panelen med spelknapparna
        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        // Inställningar för south_panel
        south_panel.setLayout(new FlowLayout());
        south_panel.setPreferredSize(new Dimension(800, 50));
        south_panel.setBackground(Color.BLACK);

        // Inställningar för knapparna för olika spelalternativ
        oneplayer_button.setText("1 Player");
        twoplayer_button.setText("2 Player");
        reset_button.setText("Reset");

        // Lägger till knapparna för olika spelalternativ på south_panel
        south_panel.add(oneplayer_button);
        south_panel.add(twoplayer_button);
        south_panel.add(reset_button);

        // Lägger till action listeners för knapparna för olika spelalternativ
        oneplayer_button.addActionListener(this);
        twoplayer_button.addActionListener(this);
        reset_button.addActionListener(this);

        // Skapar och lägger till knappar i button_panel
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        // Lägger till textfältet i panelen som används för titeln
        title_panel.add(textfield);
        // Lägger till titel_panel högst upp i frame
        frame.add(title_panel, BorderLayout.NORTH);
        // Lägger till panelen för spelknapparna i ramen
        frame.add(button_panel);
        // Lägger till söderpanelen längst ner i frame
        frame.add(south_panel, BorderLayout.SOUTH);

        // Inaktiverar alla spelknappar
        disableAllButtons();

        // Uppdaterar layouten för att säkerställa att alla komponenter placeras korrekt
        frame.revalidate();

        // Repaintar fönstret för att visa eventuella ändringar
        frame.repaint();
    }

    // Metoden för att bestämma vilken spelare som börjar spelet
    private void firstTurn() {
        if (twoPlayer_Mode==false){
            // Enspelarläge: spelare 1 (X) börjar
            player1_turn = true;
            textfield.setText("X turn");
        } else if (twoPlayer_Mode==true) {
            // Tvåspelarläge: slumpar fram vilken spelare som börjar
            if (random.nextInt(2) == 0) {
                player1_turn = true;
                textfield.setText("X turn");
            } else {
                player1_turn = false;
                textfield.setText("O turn");
            }
        }

    }

    @Override

    // Metoden som hanterar händelser från knapptryckningar
    public void actionPerformed(ActionEvent e) {

        // Hanterar knapptryckningen beroende på vilken spelare som är näst på tur
        if (e.getSource() == oneplayer_button) {

            System.out.println("Du tryckte på 1 player knappen");
            twoPlayer_Mode = false;

            one_player();
            disableAllButtons();

        } else if (e.getSource() == twoplayer_button) {

            System.out.println("Du tryckte på 2 player knappen");
            twoPlayer_Mode = true;
            resetButtons();

            firstTurn();
        }

        else if (e.getSource() == reset_button) {
            System.out.println("Du tryckte på reset button");

            resetButtons();
            firstTurn();
        }
        // Hanterar spelarinteraktion på spelbrädet
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O turn");
                        checkTie();
                        check();

                    }
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X turn");
                        checkTie();
                        check();
                    }
                }
            }
        }
    }

    // Metod för att kontrollera om någon har vunnit eller om det är oavgjort
    public void check() {
        // Kontrollerar X-vinster i rader, kolumner och diagonaler
        if (
                (buttons[0].getText() == "X") &&
                        (buttons[1].getText() == "X") &&
                        (buttons[2].getText() == "X")
        ) {
            xWins(0, 1, 2);
        }
        if (
                (buttons[3].getText() == "X") &&
                        (buttons[4].getText() == "X") &&
                        (buttons[5].getText() == "X")
        ) {
            xWins(3, 4, 5);
        }
        if (
                (buttons[6].getText() == "X") &&
                        (buttons[7].getText() == "X") &&
                        (buttons[8].getText() == "X")
        ) {
            xWins(6, 7, 8);
        }


        if (
                (buttons[0].getText() == "X") &&
                        (buttons[3].getText() == "X") &&
                        (buttons[6].getText() == "X")
        ) {
            xWins(0, 3, 6);
        }
        if (
                (buttons[1].getText() == "X") &&
                        (buttons[4].getText() == "X") &&
                        (buttons[7].getText() == "X")
        ) {
            xWins(1, 4, 7);
        }
        if (
                (buttons[2].getText() == "X") &&
                        (buttons[5].getText() == "X") &&
                        (buttons[8].getText() == "X")
        ) {
            xWins(2, 5, 8);
        }
        if (
                (buttons[0].getText() == "X") &&
                        (buttons[4].getText() == "X") &&
                        (buttons[8].getText() == "X")
        ) {
            xWins(0, 4, 8);
        }


        if (
                (buttons[2].getText() == "X") &&
                        (buttons[4].getText() == "X") &&
                        (buttons[6].getText() == "X")
        ) {
            xWins(2, 4, 6);
        }
        // Kontrollerar O-vinster i rader, kolumner och diagonaler
        if (
                (buttons[0].getText() == "O") &&
                        (buttons[1].getText() == "O") &&
                        (buttons[2].getText() == "O")
        ) {
            oWins(0, 1, 2);
        }
        if (
                (buttons[3].getText() == "O") &&
                        (buttons[4].getText() == "O") &&
                        (buttons[5].getText() == "O")
        ) {
            oWins(3, 4, 5);
        }
        if (
                (buttons[6].getText() == "O") &&
                        (buttons[7].getText() == "O") &&
                        (buttons[8].getText() == "O")
        ) {
            oWins(6, 7, 8);
        }
        if (
                (buttons[0].getText() == "O") &&
                        (buttons[3].getText() == "O") &&
                        (buttons[6].getText() == "O")
        ) {
            oWins(0, 3, 6);
        }
        if (
                (buttons[1].getText() == "O") &&
                        (buttons[4].getText() == "O") &&
                        (buttons[7].getText() == "O")
        ) {
            oWins(1, 4, 7);
        }
        if (
                (buttons[2].getText() == "O") &&
                        (buttons[5].getText() == "O") &&
                        (buttons[8].getText() == "O")
        ) {
            oWins(2, 5, 8);
        }
        if (
                (buttons[0].getText() == "O") &&
                        (buttons[4].getText() == "O") &&
                        (buttons[8].getText() == "O")
        ) {
            oWins(0, 4, 8);
        }
        if (
                (buttons[2].getText() == "O") &&
                        (buttons[4].getText() == "O") &&
                        (buttons[6].getText() == "O")
        ) {
            oWins(2, 4, 6);
        }
    }

    // Metoden som kallas när X vinner, markerar vinnande celler och inaktiverar knappar
    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        // Inaktiverar alla knappar
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        // Visar meddelande att X har vunnit
        textfield.setText("X wins");
    }

    // Metoden som kallas när O vinner, markerar vinnande celler och inaktiverar knappar
    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        // Inaktiverar alla knappar
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O wins");
    }

    // Metoden för att kontrollera om det är oavgjort
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
            textfield.setText("It's a tie!");

            for (int i = 0; i < 9; i++) {
                buttons[i].setBackground(Color.RED);
            }

            disableAllButtons();
        }
    }

    // Metod för att inaktivera alla knappar
    private void disableAllButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }


    // Återställer alla spelknappar till startläget
    public void resetButtons() {
        // Loopar genom alla spelknappar
        for (int i = 0; i < 9; i++) {
            // Återställer texten i knappen till tom
            buttons[i].setText("");
            // Återställer bakgrundsfärgen till ljusgrå
            buttons[i].setBackground(Color.LIGHT_GRAY);
            // Aktiverar knappen igen
            buttons[i].setEnabled(true);
        }

        // Återställer spelläget för två spelare och sätter det till spelare X:s tur
        twoPlayer_Mode = false;
        player1_turn = true;
        // Uppdaterar meddelandetexten för att indikera att spelet har återställts
        textfield.setText("Game reset");
    }

    // Metoden för att hantera läget för one_player
    public void one_player() {
        for (int i = 0; i < 9; i++) {

            // Kontrollerar om det är spelarens X tur
            if (player1_turn) {
                // Kontrollerar om cellen är tom
                if (buttons[i].getText().equals("")) {
                    // Sätter X i den valda cellen
                    buttons[i].setForeground(Color.red);
                    buttons[i].setText("X");
                    // Byter tur till spelare O
                    player1_turn = false;
                    // Uppdaterar meddelandetexten för nästa tur
                    textfield.setText("O turn");
                    // Kontrollerar om någon har vunnit
                    check();
                    // Kontrollerar om det är oavgjort
                    checkTie();
                    // Avslutar loopen för knapptryckningen
                    break;
                }
            }

            if (!player1_turn && buttons[i].getText().equals("")) {
                // Sätter O i den valda cellen
                buttons[i].setText("O");
                // Byter tur till spelare X
                player1_turn = true;
                // Uppdaterar meddelandetexten för nästa tur
                textfield.setText("X turn");
                // Kontrollerar om någon har vunnit
                check();
                // Kontrollerar om det är oavgjort
                checkTie();
                // Avslutar loopen för knapptryckningen
                break;
            }
        }
    }
}
