package javaPractice.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class CountWindow {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("CountWindow");

        JButton button = new JButton("クリック");
        JLabel label = new JLabel("0");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int count = Integer.parseInt(label.getText());
                count++;
                label.setText(Integer.toString(count));
            }
        });

        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.getContentPane().add(button);
        frame.getContentPane().add(label);

        frame.setSize(250,150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//main()

}//class

/*
C:\Users\6A16\Desktop>javac CountWindow.java -encoding UTF-8
C:\Users\6A16\Desktop>java CountWindow.java
*/