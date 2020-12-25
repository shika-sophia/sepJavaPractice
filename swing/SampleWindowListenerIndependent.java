package javaPractice.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class SampleWindowListenerIndependent {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("SampleWindowLitenerIndependent");
        frame.addWindowListener(new AppCloser());
        frame.setSize(600, 440);
        frame.setVisible(true);
    }//main()

}//class


final class AppCloser extends WindowAdapter{
    @Override
    public void windowClosing(final WindowEvent e) {
        System.exit(0);
    }
}//class

/*
C:\Users\6A16\Desktop>java SampleWindowListener

C:\Users\6A16\Desktop>java SampleWindowListenerIndependent.java
*/