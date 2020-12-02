package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.DataAccess;

public class LoadLogic {

    public void loadDB(MutterData data, MutterLogic logic) {
        //DBからロード
        DataAccess dataAcs = new DataAccess();
        dataAcs.loadMutter(data);

        //曜日を追加
        addDayOfWeek(data);

        //data.List -> logic.ListAll
        transList(data, logic);
    }//loadDB()


    //dateTimeListは「2020-11-27 16:27:15」で入っているので曜日を追加
    private void addDayOfWeek(MutterData data) {
        List<String> dateTimeList = data.getDateTimeList();

        for(int i = 0; i < dateTimeList.size(); i++) {
            String dateTime = dateTimeList.get(i);
            dateTime = dateTime.replace(" ", "T");

            LocalDateTime ldt = LocalDateTime.parse(dateTime);
            dateTime = ldt.format(DateTimeFormatter.ofPattern(
                    "Y-MM-dd(E) kk:mm:ss"));

            dateTimeList.set(i, dateTime);
        }//for dateTimeList

        data.setDateTimeList(dateTimeList);
    }//addDayOfWeek()

    //====== data.List -> logic.ListAll ======
    private void transList(MutterData data, MutterLogic logic) {
        List<String> mutterListAll = logic.getMutterListAll();
        List<String> dateTimeListAll = logic.getDateTimeListAll();

        mutterListAll.clear();
        dateTimeListAll.clear();

        //---- ディープコピー(要素ごとコピー) ----
        mutterListAll.addAll(data.getMutterList());
        dateTimeListAll.addAll(data.getDateTimeList());

        logic.setMutterListAll(mutterListAll);
        logic.setDateTimeListAll(dateTimeListAll);
    }//transList


//    //====== Test of [addDayOfWeek()] ======
//    public static void main(String[] args) {
//        //---- necessary initialize ----
//        List<String> dateTimeList = new ArrayList<>(
//            Arrays.asList(
//                "2020-11-26 10:41:36",
//                "2020-11-27 16:00:00",
//                "2020-11-27 16:27:15")
//        );
//
//        MutterData data = new MutterData();
//        data.setDateTimeList(dateTimeList);
//
//        //---- test target ----
//        LoadLogic load = new LoadLogic();
//        load.addDayOfWeek(data);
//
//        //---- print ----
//        dateTimeList = data.getDateTimeList();
//
//        for(String dateTime : dateTimeList) {
//            System.out.println(dateTime);
//        }//for dateTimeList
//    }//main()

}//class

/*
//====== Test of [addDayOfWeek()] 実行結果 ======
2020-11-26(木) 10:41:36
2020-11-27(金) 16:00:00
2020-11-27(金) 16:27:15
*/