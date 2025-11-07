package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Step2Game {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Step 2 - Game Panel + Timer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GamePanel panel = new GamePanel(); // game panel
            frame.setContentPane(panel);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class GamePanel extends JPanel implements ActionListener {

    private Timer timer; //Timer game cycle
    private int x = 50; // Example ball position
    private int y = 50;

    public GamePanel() {
        setPreferredSize(new Dimension(640, 360));
        setBackground(Color.BLACK);

        //timer 60 times per second do calls method actionPerformed
        timer = new Timer(1000 / 60, this);
        timer.start();
    }

    //refresh logic
    private void updateGame() {
        //turn the ball to right:
        x += 2;
        if (x > 640) x = -30;
    }

    //Painting
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(x,y,30,30); // red ball
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame(); //updating logic
        repaint();
    }
}
