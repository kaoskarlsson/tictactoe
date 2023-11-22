import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameLayout implements ActionListener {

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


    FrameLayout(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(50,50,100));
        frame.setVisible(true);

        textfield.setBackground(Color.BLACK);
        textfield.setForeground(Color.CYAN);
        textfield.setFont(new Font("Monospaced", Font.PLAIN,60));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,700,90);

        button_panel.setBackground(new Color(150,150,150));
        oneplayer_button.setPreferredSize(new Dimension(150,100));
        oneplayer_button.setFont(new Font("Monospaced",Font.BOLD,15));
        oneplayer_button.setText("1 Player");

        twoplayer_button.setPreferredSize(new Dimension(150,100));
        twoplayer_button.setFont(new Font("Monospaced",Font.BOLD,15));
        twoplayer_button.setText("2 Player");

        reset_button.setPreferredSize(new Dimension(150,100));
        reset_button.setFont(new Font("Monospaced", Font.BOLD,15));
        reset_button.setText("Reset");

        south_panel.setSize(700,100);
        south_panel.setLayout(new FlowLayout());
        south_panel.setBackground(Color.BLACK);
        south_panel.add(oneplayer_button);
        south_panel.add(twoplayer_button);
        south_panel.add(reset_button);

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);

        button_panel.setLayout(new GridLayout(3,3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Monospaced", Font.BOLD,100));
            buttons[i].addActionListener(this);

        }

        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        frame.add(south_panel, BorderLayout.SOUTH);//Adds southpanel to frame

        oneplayer_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Du tryckte på one player knappen");

                firstTurn();
            }
        });
        twoplayer_button.addActionListener(new ActionListener() {
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
            }

            public void firstTurn() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if(random.nextInt(2)==0) {
                    player1_turn=true;
                    textfield.setText("X turn");
                }
                else {
                    player1_turn=false;
                    textfield.setText("O turn");
                }
            }

            System.out.println("Du tryckte på two player knappen");

            }
        });
        reset_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                System.out.println("Du tryckte på reset knappen");

            }
        });


        frame.revalidate();
        frame.repaint();


    }

    private LayoutManager newGridLayout(int i, int i1) {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("Du tryckte på en knapp! hihi");

    }

}

