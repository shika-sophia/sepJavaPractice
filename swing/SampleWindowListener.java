package javaPractice.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class SampleWindowListener {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("SampleWindowLister");

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setSize(600,440);
        frame.setVisible(true);
    }//main()

}//class

/*
C:\Users\6A16\Desktop>javac SampleWindowListener.java

C:\Users\6A16\Desktop>java SampleWindowListener
*/