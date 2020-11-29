package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MutterLogic {

    public List<String> addMutter(
            String mutter, List<String> mutterListAll, MutterData data) {
        //---- 現在の日時を取得 ----
        String dateTime = nowDateTime();

        //---- ユーザー用のListの先頭に追加 ----
        data.setMutterList(mutter);
        data.setDateTimeList(dateTime);

        //---- アプリ全体Listの先頭に追加 ----
        mutterListAll.add(0, mutter);

        return mutterListAll;
    }//addMutter()

    //====== 現在の日時を取得し整形 ======
    private String nowDateTime() {
        String dateTime = "";

        LocalDateTime now = LocalDateTime.now();
        dateTime = now.format(DateTimeFormatter.ofPattern(
            "Y-MM-dd(E) kk:mm:ss"));

        return dateTime;
    }//nowDateTime()


    //====== mutterの文字数チェック=======
    public String checkMutter(String mutter) {
        final int MUTTER_LIMIT = 150;

        if (0 < mutter.length() && mutter.length() <= MUTTER_LIMIT) {
            ;
        } else {
            return "overText";
        }

        return "postMutter";
    }//checkMutter()

}//class

/*
【考察】ユーザー用List に追加すると application scorpに反映されてしまう。(２つ登録される)
        一度、追加したインスタンスを参照先として事後的な変更も反映するのかも
        このクラスの呼び出し前に application.removeAttribute()をしてみたが効果なし

        あっ、mutterListAll用の newをしていなかったので、
        各メソッドで変数名にAllを付けて呼ぼうと List自体は同じものだったわ。
        新たに別物のnewを定義して解決す。
*/