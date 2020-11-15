package webPractice.webJanken.model;

import java.util.Random;

public class WebJankenLogic {
    public final String[] HAND;//じゃんけんの文字列(固定値)

    public WebJankenLogic() {
        HAND = new String[] {
            "グー","チョキ","パー"
        };
    }

    //====== userとcomの手を準備 ======
    public void prepareHand(String userHand, WebJankenData data) {
        //---- set userHand to data.field ----
        int userIndex = Integer.parseInt(userHand);
        String userStr = HAND[userIndex];
        data.setUserHand(userStr);

        //---- set Random comHand to data.field ----
        Random rdm = new Random();
        int comIndex = rdm.nextInt(3);
        String comStr = HAND[comIndex];
        data.setComHand(comStr);

        //---- judgeWin ----
        judgeWin(userIndex, comIndex, data);

    }//prepareHand()

    //====== 勝敗判定 ======
    private void judgeWin(int userIndex, int comIndex, WebJankenData data) {
        String[] winArr = new String[] {"△","〇","×"};
        String result = "";
        int winIndex = -1;
        int winNum = data.getWinNum();

        switch((comIndex - userIndex + 3) % 3 ) {
        case 0:
            result = "【DRAW: あいこ 】";
            winIndex = 0;
            break;

        case 1:
            result = "【YOU WIN: 勝ち 】";
            winIndex = 1;
            winNum++;
            break;

        case 2:
            result = "【YOU LOSE: 負け 】";
            winIndex = 2;
            break;
        }//switch

        //---- 勝率の計算 ----
        //size() + 1 は下記で winListが追加されるから
        double winRate = (double)winNum / (data.getWinList().size() + 1);

        //---- set result, winNum, winRate, winList to data.field ----
        data.setResult(result);
        data.setWinNum(winNum);
        data.setWinRate(winRate);

        String win = winArr[winIndex];
        data.setWinList(win);

    }//judgeWin()
}//class
