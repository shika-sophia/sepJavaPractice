/**
 * @title javaPractice / swing / PanelSample
 * @refernce 日向俊二『JavaGUIプログラミング』カットシステム, 2020
 * @content List3.13 / PanelSmp.java
 * @date 2021-01-05
 */

package javaPractice.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PanelSample {

    public static void main(String[] args) {
        //---- frame ----
        JFrame frame = new JFrame("PanelSample");

        //---- label ----
        JLabel label = new JLabel("ラベル");
        label.setPreferredSize(new Dimension(220, 40));
        LineBorder border = new LineBorder(Color.PINK, 1, true);
        label.setBorder(border);
        label.setHorizontalAlignment(JLabel.CENTER);

        //---- button ----
        JButton button = new JButton("閉じる");
        button.setPreferredSize(new Dimension(220, 40));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });//addActionListener()

        //---- panel ----
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(button);

        //---- frame setting ----
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setSize(300, 220);
        frame.setVisible(true);
    }//main()

}//class

/*
C:\Users\6A16\Desktop>javac ./javaPractice/swing/PanelSample.java -encoding UTF-8

C:\Users\6A16\Desktop>java javaPractice.swing.PanelSample
*/