/**
 * @title javaPractice / swing / BoundWindow
 * @refernce 日向俊二『JavaGUIプログラミング』カットシステム, 2020
 * @content List3.4 CounterWnd.java
 * @date 2020-12-25
 */

package javaPractice.swing;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BoundWindow extends JFrame{

    public BoundWindow() {
        JLabel label = new JLabel("ウィンドウを中心に表示するサンプル");
        add(label);

        //画面サイズを取得
        GraphicsEnvironment gEnv =
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gDevice = gEnv.getScreenDevices();
        GraphicsDevice gDev0 = gDevice[0];
        GraphicsConfiguration[] gConfig = gDev0.getConfigurations();
        GraphicsConfiguration gConf0 = gConfig[0];

        Rectangle rect = gConf0.getBounds();
        int preWidth = rect.width;
        int preHeight = rect.height;
        int postWidth = 600;
        int postHeight = 440;

        Rectangle rct = new Rectangle (
            (preWidth - postWidth) / 2, (preHeight - postHeight) / 2);
        this.setBounds(rct);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BoundWindow");
        setVisible(true);
    }//constructor

    public static void main(String[] args) {
        new BoundWindow();
    }//main()

}//class

/*
javac BoundWindow.java -encoding UTF-8

C:\Users\6A16\Desktop>java BoundWindow

*/