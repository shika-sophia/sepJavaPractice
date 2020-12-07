package model;

import dao.DataAccess;

public class SaveLogic {
    private DataAccess dataAcs;

    public SaveLogic() {
        dataAcs = new DataAccess();
    }

    public void saveDB(MutterData data, MutterLogic logic) {
//        //新規つぶやきかロードしたものかを区別する
//        //直接DAOにアクセスするのはDistinctDAO.dateTimeListLocalを得たいから
//        String JDBC_URL = dataAcs.getJDBC_URL();
//        String DB_USER = dataAcs.getDB_USER();
//        String DB_PASS = dataAcs.getDB_PASS();
//
//        LoadDAO loadDAO = new LoadDAO();
//        loadDAO.selectMutter(data, JDBC_URL, DB_USER, DB_PASS);
//
//        List<String> mutterListLocal = loadDAO.getMutterListLocal();
//        List<String> dateTimeListLocal = loadDAO.getDateTimeListLocal();
//
//        //ロードデータがないときは空になる
//        if(dateTimeListLocal.isEmpty()) {
//            ;
//        } else {
//            //Loadと重複する部分をdata.Listから削除
//            List<String> mutterList = data.getDateTimeList();
//            List<String> dateTimeList = data.getDateTimeList();
//
//            mutterList.removeAll(mutterListLocal);
//            dateTimeList.removeAll(dateTimeListLocal);
//
//            data.setMutterList(mutterList);
//            data.setDateTimeList(dateTimeList);
//        }

        //---- DBに登録 ----
        dataAcs.insertMutter(data);

    }//saveDB()

}//class
