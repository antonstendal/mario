package org.example;

import javax.swing.*;

public class Step1Window {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Step 1 - Simple window");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(640,360);
                frame.setLocationRelativeTo(null);//centr window on display
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
    }

}
