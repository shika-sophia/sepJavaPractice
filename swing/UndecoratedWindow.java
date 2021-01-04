/**
 * @title javaPractice / swing / UndecoratedWindow
 * @refernce 日向俊二『JavaGUIプログラミング』カットシステム, 2020
 * @content List3.5 UndecoratWnd.java
 * @date 2021-01-04
 */
package javaPractice.swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class UndecoratedWindow extends JFrame{

    public static void main(String[] args) {
        new UndecoratedWindow();
    }//main()

    public UndecoratedWindow() {
        JButton button = new JButton("閉じる");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });//addActionListener()

        //JFrame
        getContentPane().add(button);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(250,160));
        setUndecorated(true);
        pack();
        setVisible(true);
    }//constructor

}//class

/*
C:\Users\6A16\Desktop>javac UndecoratedWindow.java -encoding UTF-8

C:\Users\6A16\Desktop>java UndecoratedWindow
*/