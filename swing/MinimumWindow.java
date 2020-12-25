package javaPractice.swing;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MinimumWindow {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("MinimumWindow");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(250, 160));
        frame.pack();
        frame.setVisible(true);

    }//main()

}//class

/*
C:\Users\6A16\Desktop>java MinimumWindow.java
*/