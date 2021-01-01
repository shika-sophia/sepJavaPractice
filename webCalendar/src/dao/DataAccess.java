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
        this.memoList = memoList;

        String sql =
            "SELECT MEMO,DATE FROM CALENDAR_MEMO WHERE DATE = ?";

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


    public int saveMemo(List<String> memoList, CalendarLogic calen) {
        int isSave = 1;
        this.memoList = memoList;

        String sql =
            "INSERT INTO CALENDAR_MEMO(MEMO, DATE) VALUES( ?, ?)";

        //---- 日付の整形 ----
        String dateFormat = buildDate(calen);

      //---- SQL文への挿入 / SQL文の実行 ----
        for(int i = 0; i < memoList.size(); i++) {
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, memoList.get(i));
                ps.setString(2, dateFormat);

                //INSERT成功で1が入る。どこかに0があると積は0。
                //Thanks Doman-Logic (sepJava2020).
                isSave *= ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }//try-catch

        }//for memoList

        return isSave;
    }//saveMemo()


    public int deleteMemo(String[] deleteMemoStr, CalendarLogic calen) {
        int isDelete = 1; //全て成功で1、いずれか失敗で0

        //---- 日付の整形 ----
        String dateFormat = buildDate(calen);

        String sql = "DELETE FROM CALENDAR_MEMO WHERE MEMO = ? AND DATE = ?";

        for(int i = 0; i < deleteMemoStr.length; i++) {
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, deleteMemoStr[i]);
                ps.setString(2, dateFormat);

                isDelete *= ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }//for

        return isDelete;
    }//deleteMemo()


    //====== year, month, day -> format as '2020-12-28' ======
    private String buildDate(CalendarLogic calen) {
        int year = calen.getYear();
        int month = calen.getMonth();
        int day = calen.getDay();

        String dateFormat = year + "-" + month + "-" + day;

        return dateFormat;
    }//buildDate()


    //====== getter, setter ======
    public List<String> getMemoList() {
        return memoList;
    }

    public void setMemoList(List<String> memoList) {
        this.memoList = memoList;
    }


//    //====== Test main() for loadMemo() ======
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

//    //===== Test main() for saveMemo() ======
//    public static void main(String[] args) {
//        DataAccess dao = new DataAccess();
//
//        //---- setting 'calen' ----
//        CalendarLogic calen = new CalendarLogic();
//        calen.setYear(2020);
//        calen.setMonth(12);
//        calen.setDay(31);
//
//        //---- setting 'memoList' ----
//        List<String> memoList = new ArrayList<>(
//            Arrays.asList(
//                "15:00 おやつ",
//                "24:00 年越そば"
//            ));
//
//        int isSave = dao.saveMemo(memoList, calen);
//
//        System.out.println("isSave: " + isSave);
//    }//main()

//    //===== Test main() for deleteMemo() ======
//    public static void main(String[] args) {
//        DataAccess dao = new DataAccess();
//
//        //---- setting 'calen' ----
//        CalendarLogic calen = new CalendarLogic();
//        calen.setYear(2020);
//        calen.setMonth(12);
//        calen.setDay(28);
//
//        //---- setting 'deleteMemoStr[]' ----
//        String[] deleteMemoStr = new String[] {
//                "15:00 おやつ",
//                "24:00 年越そば"
//        };
//
//        int isDelete = dao.deleteMemo(deleteMemoStr, calen);
//
//        System.out.println("isDelete: " + isDelete);
//    }//main()

}//class

/*
//====== Result main() for loadMemo() ======
isLoad: true
dateFormat: 2020-12-28
memoList: [Test memo]

//====== Result main() for saveMemo() ======
isSave: 1

select * from calendar_memo;
+----+--------------------+------------+
| ID | MEMO               | DATE       |
+----+--------------------+------------+
|  1 | Test memo          | 2020-12-28 |
|  2 | 15:00 おやつ       | 2020-12-28 |
|  3 | 24:00 年越そば     | 2020-12-28 |
+----+--------------------+------------+


//====== Result main() for deleteMemo() ======
(preview)
+----+--------------------+------------+
| ID | MEMO               | DATE       |
+----+--------------------+------------+
|  1 | Test memo          | 2020-12-28 |
|  2 | 15:00 おやつ       | 2020-12-28 |
|  3 | 24:00 年越そば     | 2020-12-28 |
|  4 | 15:00 おやつ       | 2020-12-31 |
|  5 | 24:00 年越そば     | 2020-12-31 |
+----+--------------------+------------+

isDelete: 1

 select * from calendar_memo;
+----+--------------------+------------+
| ID | MEMO               | DATE       |
+----+--------------------+------------+
|  1 | Test memo          | 2020-12-28 |
|  4 | 15:00 おやつ       | 2020-12-31 |
|  5 | 24:00 年越そば     | 2020-12-31 |
+----+--------------------+------------+
 */
