package javaPractice.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ButtonWindow {

    public static void main(String[] args) {
        JFrame frame = new JFrame("ButtonWindow");
        JButton button = new JButton("Close");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.getContentPane().add(button);

        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //frame.setSize(600, 440);
        frame.pack();
        frame.setVisible(true);
    }//main()

}//class

/*
C:\Users\6A16\Desktop>java ButtonWindow.java
*/