package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.MutterData;

public class SaveDAO {

    public void insertMutter(MutterData data, String JDBC_URL, String DB_USER, String DB_PASS) {
        List<String> mutterList = data.getMutterList();
        List<String> dateTimeList = data.getDateTimeList();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);){
            //MUTTER_tbに登録
            String sql =
                "INSERT INTO MUTTER (NAME, USER_ID, MUTTER, DATETIME) VALUES (?, ?, ?, ?)";

            for(int i = 0; i < dateTimeList.size(); i++) {

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, data.getName());
                ps.setInt(2, data.getUserId());
                ps.setString(3, mutterList.get(i));

                String dateTimeStr = dateTimeList.get(i).replaceAll("(\\W)", "");
                ps.setString(4, dateTimeStr);

                ps.executeUpdate();

            }//for i
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }//insertMutter()


    //====== 単体テスト用 main() ======
//    public static void main(String[] args) {
//        MutterData data = new MutterData();
//        data.setName("shika");
//        data.setUserId( 3 );
//
//        List<String> mutterList = new ArrayList<>(
//            Arrays.asList("つぶやき３","つぶやき４"));
//        data.setMutterList(mutterList);
//
//        List<String> dateTimeList = new ArrayList<>(
//            Arrays.asList("2020-12-02(日) 16:00:00","2020-12-02(日) 16:27:15"));
//        data.setDateTimeList(dateTimeList);
//
//        DataAccess dataAcs = new DataAccess();
//        dataAcs.insertMutter(data);
//        System.out.println("SaveDAO実行完了");
//    }//main()

}//class

/*
◇main() 実行結果
SaveDAO実行完了

select * from mutter;
+-----------+-------+---------+-----------------+---------------------+
| MUTTER_ID | NAME  | USER_ID | MUTTER          | DATETIME            |
+-----------+-------+---------+-----------------+---------------------+
|         1 | name  |       1 | あいうえお      | 2020-11-26 10:41:36 |
|         2 | shika |       3 | つぶやき１      | 2020-11-27 16:00:00 |
|         3 | shika |       3 | つぶやき２      | 2020-11-27 16:27:15 |
|         4 | shika |       3 | つぶやき３      | 2020-12-02 16:00:00 |
|         5 | shika |       3 | つぶやき４      | 2020-12-02 16:27:15 |
+-----------+-------+---------+-----------------+---------------------+

◇mutterDX.Save機能の実行結果
◆対応済 -> SaveLogic
新規のmutterだけでなく、Loadしたmutterも再登録される問題
+-----------+-------+---------+-----------------+---------------------+
| MUTTER_ID | NAME  | USER_ID | MUTTER          | DATETIME            |
+-----------+-------+---------+-----------------+---------------------+
|         1 | name  |       1 | あいうえお      | 2020-11-26 10:41:36 |
|         2 | shika |       3 | つぶやき１      | 2020-11-27 16:00:00 |
|         3 | shika |       3 | つぶやき２      | 2020-11-27 16:27:15 |
|         4 | shika |       3 | つぶやき３      | 2020-12-02 16:00:00 |
|         5 | shika |       3 | つぶやき４      | 2020-12-02 16:27:15 |
|         6 | shika |       3 | mutter          | 2020-12-02 18:17:21 |
|         7 | shika |       3 | invalidate      | 2020-12-02 18:17:15 |
|         8 | shika |       3 | つぶやき１      | 2020-11-27 16:00:00 |
|         9 | shika |       3 | つぶやき２      | 2020-11-27 16:27:15 |
|        10 | shika |       3 | つぶやき３      | 2020-12-02 16:00:00 |
|        11 | shika |       3 | つぶやき４      | 2020-12-02 16:27:15 |
+-----------+-------+---------+-----------------+---------------------+
*/