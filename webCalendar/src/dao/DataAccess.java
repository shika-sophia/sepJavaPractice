package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.CalendarLogic;

public class DataAccess {
    private final String JDBC_URL =
        "jdbc:mysql://localhost:3306/practice?characterEncoding=utf-8&serverTimezone=JST";
    private final String DB_USER = "root";
    private final String DB_PASS = "root";
    private Connection conn;
    private PreparedStatement ps;
    private List<String> memoList;


    public DataAccess() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//constractor


    public boolean loadMemo(List<String> memoList, CalendarLogic calen){
        boolean isLoad = false;
        String sql = "SELECT MEMO,DATE FROM CALENDAR_MEMO WHERE DATE = ?";

        //---- 日付の整形 ----
        String dateFormat = buildDate(calen);

        //---- SQL文への挿入 / SQL文の実行 ----
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, dateFormat);

            ResultSet rs = ps.executeQuery();

            if(rs == null) {
                isLoad = false;
            } else {
                isLoad = true;
                while(rs.next()) {
                    //int Id = rs.getInt("USER_ID");
                    //String date = rs.getString("DATE");
                    String memoDB = rs.getString("MEMO");

                    this.memoList.add(memoDB);

                }//while
            }//if-else

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return isLoad;
    }//loadMemo()


    //====== year, month, day -> format as '2020-12-28' ======
    private String buildDate(CalendarLogic calen) {
        int year = calen.getYear();
        int month = calen.getMonth();
        int day = calen.getDay();

        String dateFormat = year + "-" + month + "-" + day;

        return dateFormat;
    }

    //====== getter, setter ======
    public List<String> getMemoList() {
        return memoList;
    }

    public void setMemoList(List<String> memoList) {
        this.memoList = memoList;
    }

//    //====== Test main() ======
//    public static void main(String[] args) {
//        DataAccess dao = new DataAccess();
//
//        //---- setting 'calen' ----
//        CalendarLogic calen = new CalendarLogic();
//        calen.setYear(2020);
//        calen.setMonth(12);
//        calen.setDay(28);
//
//        //---- Test buildDate() ----
//        String dateFormat = dao.buildDate(calen);
//
//        //---- Test laadMemo() ----
//        dao.memoList = new ArrayList<>();
//        boolean isLoad = dao.loadMemo(dao.memoList, calen);
//
//        //---- print parameters ----
//        System.out.println("isLoad: " + isLoad);
//        System.out.println("dateFormat: " + dateFormat);
//        System.out.println("memoList: " + dao.memoList);
//    }//main()

}//class

/*
//====== Test Result of main() ======
isLoad: true
dateFormat: 2020-12-28
memoList: [Test memo]
 */
