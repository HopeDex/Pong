import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        int a = 0;
        JFrame frame = new JFrame("Ping Pong");
        JButton single = new JButton("Single PLayer");
        single.setActionCommand("1");
        single.setBounds(130, 0, 100, 40);
        frame.add(single);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        single.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Urp!");
                Game game = new Game();
                JFrame frame1 = new JFrame();
                frame1.setTitle("Two player ping pong");
                frame1.setBounds(10, 10, 1000, 600);
                frame1.setResizable(false);
                frame1.setVisible(true);
                frame1.isAlwaysOnTop();
                frame1.add(game);
            }
        });

    }

}