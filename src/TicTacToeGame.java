import javax.swing.*; // Innehåller klasser och metoder för att skapa GUI-komponenter
import java.awt.*;  // Innehåller klasser för GUI-komponenter och layouthantering
import java.awt.event.ActionEvent;  // Representerar en händelse som skapas när en användaråtgärd utförs
import java.awt.event.ActionListener;  // Ett gränssnitt för att lyssna på händelser av typen ActionEvent.
import java.util.Random;  // Används för att generera slumpmässiga tal

// Klassen TicTacToeGame implementerar ActionListener för att hantera händelser från buttons
public class TicTacToeGame implements ActionListener {

    // En instans av Random för att hantera slumpmässiga val
    Random random = new Random();
    // Fönstret för spelet
    JFrame frame = new JFrame() ;
    // Visar meddelanden och spelstatus
    JTextField textfield = new JTextField();
    JLabel label = new JLabel();
    // JPanel för titeln
    JPanel title_panel = new JPanel();
    // JPanel för spelknapparna
    JPanel button_panel = new JPanel();

    JPanel south_panel = new JPanel();
    // Knappar för olika spelalternativ
    JButton oneplayer_button = new JButton();
    JButton twoplayer_button = new JButton();
    JButton reset_button = new JButton();
    // Array för spelknapparna
    JButton[] buttons = new JButton[9];
    // Variabel för att hålla reda på vilken spelares tur det är
    boolean player1_turn;

// Konstruktor för att initialisera och sätta upp spelet
TicTacToeGame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
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
            title_panel.setBounds(0,0,800,100);

    // Inställningar för button_panel
     button_panel.setLayout(new GridLayout(3,3));
     button_panel.setBackground(new Color(150,150,150));

    // Skapar och lägger till knappar i button_panel
     for(int i=0;i<9;i++) {
         buttons[i] = new JButton();
         button_panel.add(buttons[i]);
         buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
         buttons[i].setFocusable(false);
         buttons[i].addActionListener(this);
     }
    // Lägger till textfält i titel_panel och titel_panel i frame
     title_panel.add(textfield);
     frame.add(title_panel,BorderLayout.NORTH);
    // Lägger till button_panel i frame
     frame.add(button_panel);

    // Anropar firstTurn för att bestämma vilken spelare som börjar
     firstTurn();
 }
    // Metoden för att bestämma vilken spelare som börjar spelet
    private void firstTurn() {
        // Random för att bestämma om det är X eller O som börjar
        if(random.nextInt(2)==0) {
            player1_turn=true;
            textfield.setText("X turn");
        }
        else {
            player1_turn=false;
            textfield.setText("O turn");
        }
    }
    // Metoden som hanterar händelser från knapptryckningar
    @Override
    public void actionPerformed(ActionEvent e) {
        // Loopar igenom spelknapparna för att avgöra vilken knapp som tryckts
        for(int i=0;i<9;i++) {
            // Hanterar knapptryckningen beroende på vilken spelare som är näst på tur
            if(e.getSource()==buttons[i]) {
                if(player1_turn) {
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        player1_turn=false;
                        textfield.setText("O turn");
                        // Kontrollerar om någon har vunnit eller om det är oavgjort
                        check();
                    }
                }
                else {
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        player1_turn=true;
                        textfield.setText("X turn");
                        // Kontrollerar om någon har vunnit eller om det är oavgjort
                        check();
                    }
                }
            }
        }


}
    // Metod för att kontrollera om någon har vunnit eller om det är oavgjort
    public void check() {
        // Kontrollerar X-vinster i rader, kolumner och diagonaler
        if(
                (buttons[0].getText()=="X") &&
                        (buttons[1].getText()=="X") &&
                        (buttons[2].getText()=="X")
        ) {
            xWins(0,1,2);
        }
        if(
                (buttons[3].getText()=="X") &&
                        (buttons[4].getText()=="X") &&
                        (buttons[5].getText()=="X")
        ) {
            xWins(3,4,5);
        }
        if(
                (buttons[6].getText()=="X") &&
                        (buttons[7].getText()=="X") &&
                        (buttons[8].getText()=="X")
        ) {
            xWins(6,7,8);
        }

        if(
                (buttons[0].getText()=="X") &&
                        (buttons[3].getText()=="X") &&
                        (buttons[6].getText()=="X")
        ) {
            xWins(0,3,6);
        }
        if(
                (buttons[1].getText()=="X") &&
                        (buttons[4].getText()=="X") &&
                        (buttons[7].getText()=="X")
        ) {
            xWins(1,4,7);
        }
        if(
                (buttons[2].getText()=="X") &&
                        (buttons[5].getText()=="X") &&
                        (buttons[8].getText()=="X")
        ) {
            xWins(2,5,8);
        }
        if(
                (buttons[0].getText()=="X") &&
                        (buttons[4].getText()=="X") &&
                        (buttons[8].getText()=="X")
        ) {
            xWins(0,4,8);
        }

        if(
                (buttons[2].getText()=="X") &&
                        (buttons[4].getText()=="X") &&
                        (buttons[6].getText()=="X")
        ) {
            xWins(2,4,6);
        }

        // Kontrollerar O-vinster i rader, kolumner och diagonaler
        if(
                (buttons[0].getText()=="O") &&
                        (buttons[1].getText()=="O") &&
                        (buttons[2].getText()=="O")
        ) {
            oWins(0,1,2);
        }
        if(
                (buttons[3].getText()=="O") &&
                        (buttons[4].getText()=="O") &&
                        (buttons[5].getText()=="O")
        ) {
            oWins(3,4,5);
        }
        if(
                (buttons[6].getText()=="O") &&
                        (buttons[7].getText()=="O") &&
                        (buttons[8].getText()=="O")
        ) {
            oWins(6,7,8);
        }
        if(
                (buttons[0].getText()=="O") &&
                        (buttons[3].getText()=="O") &&
                        (buttons[6].getText()=="O")
        ) {
            oWins(0,3,6);
        }
        if(
                (buttons[1].getText()=="O") &&
                        (buttons[4].getText()=="O") &&
                        (buttons[7].getText()=="O")
        ) {
            oWins(1,4,7);
        }
        if(
                (buttons[2].getText()=="O") &&
                        (buttons[5].getText()=="O") &&
                        (buttons[8].getText()=="O")
        ) {
            oWins(2,5,8);
        }
        if(
                (buttons[0].getText()=="O") &&
                        (buttons[4].getText()=="O") &&
                        (buttons[8].getText()=="O")
        ) {
            oWins(0,4,8);
        }
        if(
                (buttons[2].getText()=="O") &&
                        (buttons[4].getText()=="O") &&
                        (buttons[6].getText()=="O")
        ) {
            oWins(2,4,6);
        }
    }
    // Metoden som kallas när X vinner, markerar vinnande celler och inaktiverar knappar
    public void xWins(int a,int b,int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        // Inaktiverar alla knappar
        for(int i=0;i<9;i++) {
            buttons[i].setEnabled(false);
        }
        // Visar meddelande att X har vunnit
        textfield.setText("X wins");
    }
    // Metoden som kallas när O vinner, markerar vinnande celler och inaktiverar knappar
    public void oWins(int a,int b,int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        // Inaktiverar alla knappar
        for(int i=0;i<9;i++) {
            buttons[i].setEnabled(false);
        }
        // Visar meddelande att O har vunnit
        textfield.setText("O wins");
    }
    // Metoden för att kontrollera om det är oavgjort
    private void checkTie() {
        boolean tie = true;
        JButton[] buttons = new JButton[9];
        JTextField textfield = new JTextField();
        
        // Kollar om det finns några tomma celler kvar
        JButton[] buttons = new JButton[0];

        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                tie = false;
                break;
            }
        }

        // Om ingen vunnit och inga tomma celler finns kvar, är det oavgjort
        if (tie) {
            textfield.setText("It's a tie!");
            disableAllButtons();
        }
    }
    // Metod för att inaktivera alla knappar
    private void disableAllButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
}

