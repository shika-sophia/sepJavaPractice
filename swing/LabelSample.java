/**
 * @title javaPractice / swing / LabelSample
 * @refernce 日向俊二『JavaGUIプログラミング』カットシステム, 2020
 * @content List4.1 / SmplLbl.java
 * @date 2021-01-06
 */

package javaPractice.swing;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class LabelSample extends JFrame{

    public LabelSample() {
        //---- Label ----
        JLabel label = new JLabel("Label Sample");
        label.setPreferredSize(new Dimension());
        label.setBackground(Color.PINK);


    }//conatructor

    public static void main(String[] args) {

    }//main()

}//class
