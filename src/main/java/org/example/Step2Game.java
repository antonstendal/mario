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

    private  boolean leftPressed = false;
    private  boolean rightPressed = false;

    //player on display
    private int playerX = 50;
    private int playerY = 200;
    private int playerSpeed = 3;

    private Timer timer; //Timer game cycle
    private int x = 20; // Example ball position
    private int y = 150;

    public GamePanel() {
        setPreferredSize(new Dimension(640, 360));
        setBackground(Color.BLACK);
        setupKeyBindings();

        //timer 60 times per second do calls method actionPerformed
        timer = new Timer(1000 / 60, this);
        timer.start();
    }

    private void setupKeyBindings() {
        //left arrow press
        getInputMap().put(KeyStroke.getKeyStroke("pressed LEFT"), "leftPressed");
        getActionMap().put("leftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPressed = true;
            }
        });

        //left arrow released
        getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "leftReleased");
        getActionMap().put("leftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPressed = false;
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke("pressed RIGHT"), "rightPressed");
        getActionMap().put("rightPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPressed = true;
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "rightReleased");
        getActionMap().put("rightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPressed = false;
            }
        });
    }

    //refresh logic
    private void updateGame() {
        //turn the ball to right:
        if (leftPressed) {
            playerX -= playerSpeed;
        }
        if (rightPressed){
            playerX +=playerSpeed;
        }
        if (playerX < 0)
            playerX = 0;
        if (playerX + 30 > getWidth())
            playerX = getWidth() - 30;
        double vx = 0;
        double acceleration = 0.5;
        double friction = 0.2;
        if (leftPressed)
            vx -= acceleration;
        if (rightPressed)
            vx += acceleration;
        vx *= (1 - friction); // простая модель торможения
        playerX += (int) Math.round(vx);
    }

    //Painting
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        g.fillRect(playerX,playerY,30,30); // red ball
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame(); //updating logic
        repaint();
    }
}
