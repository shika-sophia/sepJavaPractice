package model;

import dao.LoadDAO;
import dao.LoginDAO;
import dao.MutterDAO;
import dao.RegisterDAO;

public class DataAccess {
    private final String JDBC_URL =
            "jdbc:mysql://localhost:3306/practice?characterEncoding=utf-8&serverTimezone=JST";
    private final String DB_USER = "root";
    private final String DB_PASS = "root";

    //====== DBとの照合 ======
    public boolean existRegiter(MutterData data) {
        LoginDAO inDAO = new LoginDAO();
        boolean isRegister = inDAO.selectUser(data, JDBC_URL, DB_USER, DB_PASS);

        return isRegister;
    }//existRegiter()

    //====== DBへ登録 ======
    public boolean register(MutterData data) {
        RegisterDAO regDAO = new RegisterDAO();
        boolean doneRegister = regDAO.insertUser(data, JDBC_URL, DB_USER, DB_PASS);

        return doneRegister;
    }//register()


    //====== mutterListをDBにsave ======
    public void insertMutter(MutterData data) {
        MutterDAO mutDAO = new MutterDAO();
        mutDAO.insertMutter(data, JDBC_URL, DB_USER, DB_PASS);
    }//insertMutter()

    //====== saveデータをload ======
    public void loadMutter(MutterData data) {
        LoadDAO loadDAO = new LoadDAO();
        loadDAO.selectMutter(data, JDBC_URL, DB_USER, DB_PASS);
    }//loadMutter()

    //====== getter ======
    public String getJDBC_URL() {
        return JDBC_URL;
    }

    public String getDB_USER() {
        return DB_USER;
    }

    public String getDB_PASS() {
        return DB_PASS;
    }

}//class
