/**
 * @title javaPractice / swing / SplashSample
 * @refernce 日向俊二『JavaGUIプログラミング』カットシステム, 2020
 * @content List3.10 / SplashScrn.java
 * @date 2021-01-05
 */
package javaPractice.swing;

import java.awt.AWTEvent;
import java.awt.SplashScreen;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class SplashSample extends JFrame{

    public SplashSample() {
        JLabel label = new JLabel("Main Window");
        add(label);
        setSize(300, 200);
        setAlwaysOnTop(true);
        setTitle("SplashSample");
        setVisible(true);
    }//constructor

    public static void main(String[] args) {
        //---- SplashScreen ----
        SplashScreen splash = SplashScreen.getSplashScreen();

        if (splash == null) {
            System.out.println("＊使い方(コマンド・プロンプト)＊");
            System.out.println(">java SplashSample -splash:Sample.gif");
            System.exit(0);
        }

        //---- 2.5秒待機 ----
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splash.close();

        //---- Main Window ----
        new SplashSample();
    }//main()

    //====== フレームを閉じたらプログラム終了 ======
    protected void processEvent(AWTEvent ev) {
        if (ev.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }//processEvent()
}//class

/*
C:\Users\6A16\Desktop>javac ./javaPractice/swing/SplashSample.java -encoding UTF-8

C:\Users\6A16\Desktop>java javaPractice.swing.SplashSample
＊使い方(コマンド・プロンプト)＊
>java SplashSample -splash:Sample.gif
*/