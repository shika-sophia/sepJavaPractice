/**
 * @title javaPractice / swing / BackGroundColor
 * @refernce 日向俊二『JavaGUIプログラミング』カットシステム, 2020
 * @content List3.12 / BkgColor.java
 * @date 2021-01-05
 */

package javaPractice.swing;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BackGroundColor {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Background Color");
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });
        frame.add(panel);
        frame.setSize(300, 220);
        frame.setVisible(true);

    }//main()

}//class

/*
C:\Users\6A16\Desktop>javac ./javaPractice/swing/BackGroundColor.java -encoding UTF-8

C:\Users\6A16\Desktop>java javaPractice.swing.BackGroundColor
*/