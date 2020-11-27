package webPractice.mutter.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import webPractice.mutter.model.MutterData;

public class MutterDAO {

    public void insertMutter(MutterData data, String JDBC_URL, String DB_USER, String DB_PASS) {
        List<String> mutterList = data.getMutterList();
        List<String> dateTimeList = data.getDateTimeList();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);){
            //MUTTER_USER_tbに登録
            String sql =
                "INSERT INTO MUTTER (NAME, USER_ID, MUTTER, DATETIME) VALUES (?, ?, ?, ?)";

            for(int i = 0; i < mutterList.size(); i++) {

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

        //return doneRegister;

    }//insertMutter()


//    //====== 単体テスト用 main() ======
//    public static void main(String[] args) {
//        MutterData data = new MutterData();
//        data.setName("shika");
//        data.setUserId( 2 );
//
//        List<String> mutterList = new ArrayList<>(
//            Arrays.asList("つぶやき１","つぶやき２"));
//        data.setMutterList(mutterList);
//
//        List<String> dateTimeList = new ArrayList<>(
//            Arrays.asList("2020-11-27(金) 16:00:00","2020-11-27(金) 16:27:15"));
//        data.setDateTimeList(dateTimeList);
//
//        MutterRegister regist = new MutterRegister();
//        regist.insertMutter(data);
//        System.out.println("mutterDAO実行完了");
//    }//main()

}//class

/*
◇main() 実行結果
mutterDAO実行完了

select * from mutter;
+-----------+-------+---------+-----------------+---------------------+
| MUTTER_ID | NAME  | USER_ID | MUTTER          | DATETIME            |
+-----------+-------+---------+-----------------+---------------------+
|         1 | name  |       1 | あいうえお      | 2020-11-26 10:41:36 |
|         2 | shika |       2 | つぶやき１      | 2020-11-27 16:00:00 |
|         3 | shika |       2 | つぶやき２      | 2020-11-27 16:27:15 |
+-----------+-------+---------+-----------------+---------------------+
*/