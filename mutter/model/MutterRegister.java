package webPractice.mutter.model;

import webPractice.mutter.DAO.LoginDAO;
import webPractice.mutter.DAO.RegisterDAO;

public class MutterRegister {
    private final String JDBC_URL =
            "jdbc:mysql://localhost:3306/practice?characterEncoding=utf-8&serverTimezone=JST";
    private final String DB_USER = "root";
    private final String DB_PASS = "root";

    //====== DBとの照合 ======
    public boolean existRegiter(MutterData data) {
        LoginDAO inDAO = new LoginDAO();
        boolean isRegister = inDAO.select(data, JDBC_URL, DB_USER, DB_PASS);

        return isRegister;
    }//existRegiter()

    //====== DBへ登録 ======
    public boolean register(MutterData data) {
        RegisterDAO regDAO = new RegisterDAO();
        boolean doneRegister = regDAO.insert(data, JDBC_URL, DB_USER, DB_PASS);

        return doneRegister;
    }//register()


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
