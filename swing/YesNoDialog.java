package javaPractice.swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class YesNoDialog extends JFrame{

    public static void main(String[] args) {
        new YesNoDialog();
    }//main()

    public YesNoDialog() {
        JButton button = new JButton("Dialog表示");
        JLabel label = new JLabel("ボタンをクリックしてください。");
        setLayout(new GridLayout(2, 1, 5 ,5));
        add(button);
        add(label);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                    "Choose One", "Choose One",
                    JOptionPane.YES_NO_OPTION);

                if(result == JOptionPane.YES_OPTION) {
                    label.setText("Yes");
                } else {
                    label.setText("No");
                }
            }
        };//ActionListener()

        button.addActionListener(listener);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("YesNoDialog");
        setSize(350, 250);
        setVisible(true);
    }//constructor
}//class

/*
C:\Users\6A16\Desktop>javac ./javaPractice/swing/YesNoDialog.java -encoding UTF-8

C:\Users\6A16\Desktop>java javaPractice.swing.YesNoDialog
*/