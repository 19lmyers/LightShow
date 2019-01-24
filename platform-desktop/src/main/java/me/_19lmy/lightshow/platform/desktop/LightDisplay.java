package me._19lmy.lightshow.platform.desktop;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Original LightDisplay code written by Donovan Cunningham
 * Modified to work with my API interface
 */
class LightDisplay extends JFrame {

    private JLabel[] pin = new JLabel[26];

    private ImageIcon horizontalOff = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/horizontalOFF.jpg")));
    private ImageIcon horizontalOn = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/horizontalON.jpg")));
    private ImageIcon starOff = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/starOFF.jpg")));
    private ImageIcon starOn = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/starON.jpg")));
    private ImageIcon verticalOff = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/verticalOFF.jpg")));
    private ImageIcon verticalOn = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("imgs/verticalON.jpg")));

    LightDisplay() {
        super("Simulated Light Show");

        JLayeredPane lp = getLayeredPane();
        setMinimumSize(new Dimension(500, 500));
        setSize(500, 500); //TODO make it bigger

        getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        for (int i = 0; i <= 25; i++) {
            pin[i] = new JLabel();
        }

        for (int i = 0; i <= 11; i++) {
            pin[i].setIcon(verticalOff);
        }

        for (int i = 12; i <= 19; i++) {
            pin[i].setIcon(horizontalOff);
        }

        for (int i = 20; i <= 25; i++) {
            pin[i].setIcon(starOff);
        }

        pin[0].setBackground(Color.white);
        pin[0].setBounds(0, 200, 12, 200);  // vertical
        pin[1].setBackground(Color.white);
        pin[1].setBounds(50, 200, 12, 200);
        pin[2].setBackground(Color.white);
        pin[2].setBounds(75, 200, 12, 200);
        pin[3].setBackground(Color.white);
        pin[3].setBounds(125, 200, 12, 200);
        pin[4].setBackground(Color.white);
        pin[4].setBounds(150, 200, 12, 200);
        pin[5].setBackground(Color.white);
        pin[5].setBounds(200, 200, 12, 200);
        pin[6].setBackground(Color.white);
        pin[6].setBounds(225, 200, 12, 200);
        pin[7].setBackground(Color.white);
        pin[7].setBounds(275, 200, 12, 200);
        pin[8].setBackground(Color.white);
        pin[8].setBounds(300, 200, 12, 200);
        pin[9].setBackground(Color.white);
        pin[9].setBounds(350, 200, 12, 200);
        pin[10].setBackground(Color.white);
        pin[10].setBounds(375, 200, 12, 200);
        pin[11].setBackground(Color.white);
        pin[11].setBounds(425, 200, 12, 200);

        for (int i = 12; i <= 15; i++) {
            pin[i].setBackground(Color.white);
            pin[i].setBounds(0, (i - 12) * 67 + 200, 200, 12);  // horizontal left
        }

        for (int i = 16; i <= 19; i++) {
            pin[i].setBackground(Color.white);
            pin[i].setBounds(225, (i - 16) * 67 + 200, 200, 12);  // horizontal right
        }

        pin[20].setBackground(Color.white);
        pin[20].setBounds(0, 134, 50, 50);  // star
        pin[21].setBackground(Color.white);
        pin[21].setBounds(200, 134, 50, 50);  // star
        pin[22].setBackground(Color.white);
        pin[22].setBounds(390, 134, 50, 50);  // star
        pin[23].setBackground(Color.white);
        pin[23].setBounds(115, 67, 50, 50);  // star
        pin[24].setBackground(Color.white);
        pin[24].setBounds(285, 67, 50, 50);  // star
        pin[25].setBackground(Color.white);
        pin[25].setBounds(200, 0, 50, 50);  // star

        // Place the buttons in different layers
        for (int i = 0; i <= 11; i++) {
            lp.add(pin[i], Integer.valueOf(1));    // vertical
        }

        for (int i = 12; i <= 19; i++) {
            lp.add(pin[i], Integer.valueOf(2));  // horizontal
        }

        for (int i = 20; i <= 25; i++) {
            lp.add(pin[i], Integer.valueOf(3));  // star
        }

        setLocationRelativeTo(null);
        setVisible(true);
    }

    void set(int pinNumber, boolean state) {
        if (pinNumber >= 0 && pinNumber <= 11) {
            if (state) {
                pin[pinNumber].setIcon(verticalOn);
            } else {
                pin[pinNumber].setIcon(verticalOff);
            }
        }

        if (pinNumber >= 12 && pinNumber <= 19) {
            if (state) {
                pin[pinNumber].setIcon(horizontalOn);
            } else {
                pin[pinNumber].setIcon(horizontalOff);
            }
        }

        if (pinNumber >= 20 && pinNumber <= 25) {
            if (state) {
                pin[pinNumber].setIcon(starOn);
            } else {
                pin[pinNumber].setIcon(starOff);
            }
        }
    }
}
