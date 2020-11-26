package webPractice.mutter.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import webPractice.mutter.model.MutterData;
//import webPractice.mutter.model.MutterRegister;

public class RegisterDAO {

    public boolean insert(MutterData data, String JDBC_URL, String DB_USER, String DB_PASS) {
        boolean doneRegister = false;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);){
            //MUTTER_USER_tbに登録
            String sql =
                "INSERT INTO MUTTER_USER (NAME, PASS, MAIL) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, data.getName());
            ps.setString(2, data.getPass());
            ps.setString(3, data.getMail());

            int rs = ps.executeUpdate();

            if(rs == 0) {
                doneRegister = false;

            } else if (rs == 1) {
                doneRegister = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return doneRegister;
    }//insert()


//    //====== 単体Test用 main() ======
//    public static void main(String[] args) {
//        MutterData data = new MutterData("testName", "testPass", "********");
//        data.setMail("shika-sophia@dammy.co.jp");
//
//        MutterRegister regist = new MutterRegister();
//        String JDBC_URL = regist.getJDBC_URL();
//        String DB_USER = regist.getDB_USER();
//        String DB_PASS = regist.getDB_PASS();
//
//        RegisterDAO regDAO = new RegisterDAO();
//        boolean doneRegister = regDAO.insert(data, JDBC_URL, DB_USER, DB_PASS);
//
//        System.out.println("doneRegister: " + doneRegister);
//
//    }//main()

}//class

/*
//====== ◇実行結果 ======
doneRegister: true

mysql> select * from mutter_user;
+---------+----------+----------+--------------------------+
| USER_ID | NAME     | PASS     | MAIL                     |
+---------+----------+----------+--------------------------+
|       1 | name     | pass     | mail@dammy               |
|       2 | testName | testPass | shika-sophia@dammy.co.jp |
+---------+----------+----------+--------------------------+

delete from mutter_user where user_id = 2;
Query OK, 1 row affected (0.00 sec)

mysql> select * from mutter_user;
+---------+------+------+------------+
| USER_ID | NAME | PASS | MAIL       |
+---------+------+------+------------+
|       1 | name | pass | mail@dammy |
+---------+------+------+------------+
1 row in set (0.00 sec)

*/