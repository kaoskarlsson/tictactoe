import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrameLayout implements ActionListener {
    JFrame frame = new JFrame() ;
    JTextField textfield = new JTextField();
    JLabel label = new JLabel();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JButton[] buttons = new JButton[9];

    FrameLayout(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(Color.BLACK);
        textfield.setForeground(Color.CYAN);
        textfield.setFont(new Font("Monospaced", Font.PLAIN,60));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,700,90);

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);

        button_panel.setLayout(new GridLayout(3,3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Monospaced", Font.BOLD,100));
            buttons[i].addActionListener(this);



        }


        frame.add(button_panel);




        frame.revalidate();
        frame.repaint();


    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("Du tryckte på en knapp! hihi");

    }

}

