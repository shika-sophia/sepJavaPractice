/**
 * @title javaPractice / swing / DaialogWindow
 * @refernce 日向俊二『JavaGUIプログラミング』カットシステム, 2020
 * @content List3.6, 3.7 / DaialogWnd.java
 * @date 2021-01-04
 */

package javaPractice.swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class DialogWindow extends JFrame {
    JFrame frame;

    public static void main(String[] args) {
        new DialogWindow();
    }//main()

    public DialogWindow() {
        frame = this;
        this.setTitle("DialogWindow");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(250, 160));

        JButton button = new JButton("表示");

        ActionListener listener = buildListener();

        button.addActionListener(listener);

        getContentPane().add(button);
        setVisible(true);
    }//constructor


    private ActionListener buildListener() {
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dlg = new JDialog(frame, "Dialog", true);
                dlg.setMinimumSize(new Dimension(200, 140));
                JButton dlgButton = new JButton("閉じる");

                dlgButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dlg.setVisible(false);
                        dlg.dispose();
                    }
                });//dlgButton.addActionListener()
                dlg.getContentPane().add(dlgButton);
                dlg.setVisible(true);
            }
        };//ActionListener()

        return listener;
    }//buildListener()

}//class

/*
C:\Users\6A16\Desktop>javac ./javaPractice/swing/DialogWindow.java -encoding UTF-8

C:\Users\6A16\Desktop>java javaPractice.swing.DialogWindow
*/