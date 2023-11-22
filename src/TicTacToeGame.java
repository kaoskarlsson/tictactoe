import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToeGame implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame() ;
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

TicTacToeGame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(Color.BLACK);
        textfield.setForeground(Color.CYAN);
        textfield.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

            title_panel.setLayout(new BorderLayout());
            title_panel.setBounds(0,0,800,100);

     button_panel.setLayout(new GridLayout(3,3));
     button_panel.setBackground(new Color(150,150,150));

     for(int i=0;i<9;i++) {
         buttons[i] = new JButton();
         button_panel.add(buttons[i]);
         buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
         buttons[i].setFocusable(false);
         buttons[i].addActionListener(this);
     }

     title_panel.add(textfield);
     frame.add(title_panel,BorderLayout.NORTH);
     frame.add(button_panel);

     firstTurn();
 }

    private void firstTurn() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=0;i<9;i++) {
            if(e.getSource()==buttons[i]) {
                if(player1_turn) {
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        player1_turn=false;
                        textfield.setText("O turn");
                        check();
                    }
                }
                else {
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        player1_turn=true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }
        }
    //Infoga Try catch här? //

    if(random.nextInt(2)==0) {
        player1_turn=true;
        textfield.setText("X turn");
    }
		else {
        player1_turn=false;
        textfield.setText("O turn");
    }
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
