package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MutterData;

public class LoadDAO {
    private List<String> mutterListLocal;
    private List<String> dateTimeListLocal;

    public LoadDAO() {
        mutterListLocal = new ArrayList<String>();
        dateTimeListLocal = new ArrayList<String>();
    }

    public boolean selectMutter(MutterData data, String JDBC_URL, String DB_USER, String DB_PASS) {
        boolean isLoad = false;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);){
            //Loginで入力したname, passはＤＢに登録されているか
            String sql = "SELECT USER_ID, NAME, MUTTER, DATETIME FROM MUTTER WHERE USER_ID = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, data.getUserId());

            ResultSet rs = ps.executeQuery();

            if(rs == null) {
                isLoad = false;
            } else {
                isLoad = true;
                while(rs.next()) {
                    //int userId = rs.getInt("USER_ID");
                    //String name = rs.getString("NAME");
                    String mutter = rs.getString("MUTTER");
                    String dateTime = rs.getString("DATETIME");

                    mutterListLocal.add(mutter);
                    dateTimeListLocal.add(dateTime);
                }//while
            }//if-else
        } catch (SQLException e) {
            e.printStackTrace();
            isLoad = false;
        }

        data.setMutterList(mutterListLocal);
        data.setDateTimeList(dateTimeListLocal);
        return isLoad;
    }//selectMutter()

    //====== getter, setter ======
    public List<String> getMutterListLocal() {
        return mutterListLocal;
    }

    public void setMutterListLocal(List<String> mutterListLocal) {
        this.mutterListLocal = mutterListLocal;
    }

    public List<String> getDateTimeListLocal() {
        return dateTimeListLocal;
    }

    public void setDateTimeListLocal(List<String> dateTimeListLocal) {
        this.dateTimeListLocal = dateTimeListLocal;
    }

//    //====== 単体Test用 main() ======
//    public static void main(String[] args) {
//        MutterData data = new MutterData();
//        data.setUserId( 2 );
//
//        DataAccess dataAcs = new DataAccess();
//        String JDBC_URL = dataAcs.getJDBC_URL();
//        String DB_USER = dataAcs.getDB_USER();
//        String DB_PASS = dataAcs.getDB_PASS();
//
//        LoadDAO loadDAO = new LoadDAO();
//        boolean isLoad = loadDAO.selectMutter(data, JDBC_URL, DB_USER, DB_PASS);
//
//        List<String> mutterList = data.getMutterList();
//        List<String> dateTimeList = data.getDateTimeList();
//
//        for(int i = 0; i < mutterList.size(); i++) {
//            System.out.println(mutterList.get(i) + " : " + dateTimeList.get(i));
//        }
//    }//main()
}//class

/*
//====== main() 実行結果 ======
data.setUserId( 1 ); //name
あいうえお : 2020-11-26 10:41:36

data.setUserId( 2 ); //shika
つぶやき１ : 2020-11-27 16:00:00
つぶやき２ : 2020-11-27 16:27:15
*/