/**
 * @title javaPractice / swing / SplashFrame
 * @refernce 日向俊二『JavaGUIプログラミング』カットシステム, 2020
 * @content List3.11 / SplashWnd.java
 * @date 2021-01-05
 */

package javaPractice.swing;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SplashFrame extends JFrame{
    public SplashFrame() {
        JLabel label = new JLabel("Main Frame");
        add(label);
        setSize(400, 360);
        setAlwaysOnTop(true);
        setTitle("SplashFrame");
        setVisible(true);
    }//constructor

    //====== Splash Frame ======
    public static void main(String[] args) {
        JFrame splashFrame = new JFrame("Sprash Frame");
        JLabel spLabel = new JLabel("Splash!");
        JPanel panel = new JPanel();
            panel.setBackground(Color.CYAN);
            panel.add(spLabel);

        splashFrame.add(panel);
        splashFrame.setSize(400, 300);
        splashFrame.setVisible(true);

        //---- 2.5秒待機 ----
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //---- Main Frame 呼び出し ----
        new SplashFrame();

        //---- Splashの終了 ----
        splashFrame.setVisible(false);
        splashFrame.dispose();
    }//main()


    //====== フレームを閉じたらプログラム終了 ======
    protected void processEvent(AWTEvent ev) {
        if (ev.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }//processEvent()
}//class

/*
C:\Users\6A16\Desktop>javac ./javaPractice/swing/SplashFrame.java -encoding UTF-8

C:\Users\6A16\Desktop>java javaPractice.swing.SplashFrame
*/