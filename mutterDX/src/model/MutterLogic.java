package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MutterLogic {
    private List<String> mutterListAll;
    private List<String> dateTimeListAll;

    public MutterLogic() {
        mutterListAll = new ArrayList<>();
        dateTimeListAll = new ArrayList<>();
    }

    public void addMutter(String mutter, MutterData data) {
        //---- 現在の日時を取得 ----
        String dateTime = nowDateTime();

        //---- ユーザー用のListの先頭に追加 ----
        data.setMutterList(mutter);
        data.setDateTimeList(dateTime);

        //---- アプリ全体Listの先頭に追加 ----
        mutterListAll.add(0, mutter);
        dateTimeListAll.add(0, dateTime);
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


    //====== getter, setter =======
    public List<String> getMutterListAll() {
        return mutterListAll;
    }

    public void setMutterListAll(List<String> mutterListAll) {
        this.mutterListAll = mutterListAll;
    }

    public void setMutterListAll(String mutter) {
        mutterListAll.add(0, mutter);
    }

    public List<String> getDateTimeListAll() {
        return dateTimeListAll;
    }

    public void setDateTimeListAll(List<String> dateTimeListAll) {
        this.dateTimeListAll = dateTimeListAll;
    }

    public void setDateTimeListAll(String dateTime) {
        dateTimeListAll.add(0, dateTime);
    }
}//class
