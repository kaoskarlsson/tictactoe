import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToeGame implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JLabel label = new JLabel();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel south_panel = new JPanel();
    JButton oneplayer_button = new JButton();
    JButton twoplayer_button = new JButton();
    JButton reset_button = new JButton();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    boolean twoPlayer_Mode;

    TicTacToeGame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(Color.BLACK);
        textfield.setForeground(Color.CYAN);
        textfield.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setPreferredSize(new Dimension(800, 50));

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        //----------new Add buttons to southpanel panel------------>
        south_panel.setLayout(new FlowLayout());
        south_panel.setPreferredSize(new Dimension(800, 50));
        south_panel.setBackground(Color.BLACK);
//-----------new add set text to buttons--------->
        oneplayer_button.setText("1 Player");
        twoplayer_button.setText("2 Player");
        reset_button.setText("Reset");

        //-----------new add-------------
        south_panel.add(oneplayer_button);
        south_panel.add(twoplayer_button);
        south_panel.add(reset_button);

        //----new add actionlistener to buttons---------
        oneplayer_button.addActionListener(this);
        twoplayer_button.addActionListener(this);
        reset_button.addActionListener(this);


        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        //---------new add frame add-------
        frame.add(south_panel, BorderLayout.SOUTH);

        disableAllButtons();

        frame.revalidate();
        frame.repaint();


    }

    private void firstTurn() {
        if (twoPlayer_Mode==false){
            //one_player();
            player1_turn = true;
            textfield.setText("X turn");


        }
         if (twoPlayer_Mode==true) {
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
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == oneplayer_button) {

            System.out.println("Du tryckte på 1 player knappen");
            twoPlayer_Mode =false;
            firstTurn();
            one_player();
            //disableAllButtons();
           // resetButtons();
            // enableButtons();
        } else if (e.getSource() == twoplayer_button) {

            System.out.println("Du tryckte på 2 player knappen");
            twoPlayer_Mode = true;
            resetButtons();

            //-------Add new first turn button here instead---------
            firstTurn();
        }
        //----------Adds new reset button-----------
        else if (e.getSource() == reset_button) {
            System.out.println("Du tryckte på reset button");

            resetButtons();
            firstTurn();
        }
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O turn");
                        check();
                        checkTie();
                    }
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X turn");
                        check();
                        checkTie();
                    }
                }
            }
        }
        //Infoga Try catch här? //
    }


    public void check() {
        //check X win conditions
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
        //check O win conditions
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

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X wins");
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O wins");
    }


    private void checkTie() {
        boolean tie = true;

        // Kolla om det finns några tomma celler kvar

        // JButton[] buttons = new JButton[0];
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                tie = false;
                break;
            }
        }

        // Om ingen vunnit och inga tomma celler finns kvar, är det oavgjort
        if (tie) {
            textfield.setText("It's a tie!");
            //--------Set background color red if tie------
            for (int i = 0; i < 9; i++) {
                buttons[i].setBackground(Color.RED);
            }

            disableAllButtons();
        }
    }

    private void disableAllButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    //----------Add new method enable buttons---------------
  /*  public void enableButtons(){
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(twoPlayer_Mode);
        }

    }*/
    //----------adds new reset button method here----------
    public void resetButtons() {

        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].setEnabled(true);

            // buttons[i].repaint();
            //buttons[i].revalidate();
        }
        twoPlayer_Mode = false;
        player1_turn = true;
        textfield.setText("Game reset");
    }

    public void one_player() {

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
            //if (e.getSource() == buttons[i]) {
                while (player1_turn&&twoPlayer_Mode==false) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(000, 0, 0));
                        //random.nextInt(0,3);
                        buttons[random.nextInt(0,9)].setText("X");
                        player1_turn = false;
                        textfield.setText("O turn");
                        check();
                        checkTie();

                        break;
                    }
                    while(buttons[i].getText() == "X"||buttons[i].getText() == "0"){
                        buttons[random.nextInt(0,9)].setText("X");
                        player1_turn = false;
                        textfield.setText("O turn");
                        check();
                        checkTie();
                    }
                }
                /*else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        //random.nextInt(0,3);
                        buttons[random.nextInt(0,9)].setText("O");
                        player1_turn = true;
                        textfield.setText("X turn");
                        check();
                        checkTie();
                    }
                }*/

            }
        }
    }


