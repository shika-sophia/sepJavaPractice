/**
 * @title javaPractice / swing / DaialogSample
 * @refernce 日向俊二『JavaGUIプログラミング』カットシステム, 2020
 * @content List3.8, 3.9 / DlgSample.java
 * @date 2021-01-04
 */

package javaPractice.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DialogSample extends JFrame{

    public static void main(String[] args) {
        new DialogSample();
    }//main()

    public DialogSample() {
        JButton button = new JButton("Dialog Boxの表示");

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                    null,"単純なダイアログ","Dailog",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        };//ActionListener()

        button.addActionListener(listener);
        add(button);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("DialogSample");
        setSize(350, 250);
        setVisible(true);
    }//constructor
}//class

/*
C:\Users\6A16\Desktop>javac ./javaPractice/swing/DialogSample.java -encoding UTF-8

C:\Users\6A16\Desktop>java javaPractice.swing.DialogSample
*/