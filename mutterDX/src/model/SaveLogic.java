package model;

import java.util.List;

import dao.DataAccess;
import dao.LoadDAO;

public class SaveLogic {
    private DataAccess dataAcs;

    public SaveLogic() {
        dataAcs = new DataAccess();
    }

    public void saveDB(MutterData data, MutterLogic logic) {
        //---- すでにDBに収録されている「つぶやき」を取得 -----
        //dataAcs.loadMutterは data.Listに登録されてしまうため、loadDAO.Listにアクセスする
        String JDBC_URL = dataAcs.getJDBC_URL();
        String DB_USER = dataAcs.getDB_USER();
        String DB_PASS = dataAcs.getDB_PASS();

        LoadDAO loadDAO = new LoadDAO();
        loadDAO.selectMutter(data, JDBC_URL, DB_USER, DB_PASS);
        List<String> mutterListLocal = loadDAO.getMutterListLocal();
        List<String> dateTimeListLocal = loadDAO.getDateTimeListLocal();

        //---- DBに登録 ----
        dataAcs.insertMutter(data);
    }//saveDB()

}//class
