package webPractice.mutter.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import webPractice.mutter.model.MutterData;

public class LoginDAO {

    public boolean selectUser(MutterData data, String JDBC_URL, String DB_USER, String DB_PASS) {
        boolean isRegister = false;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);){
            //Loginで入力したname, passはＤＢに登録されているか
            String sql = "SELECT USER_ID, NAME, PASS FROM MUTTER_USER WHERE NAME = ? AND PASS = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, data.getName());
            ps.setString(2, data.getPass());

            ResultSet rs = ps.executeQuery();

            if(rs == null) {
                isRegister = false;
            } else if (rs.next()) {
                isRegister = true;

                int userId = rs.getInt("USER_ID");
                //String name = rs.getString("NAME");
                //String pass = rs.getString("PASS");

                data.setUserId(userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return isRegister;
    }//select()


//  //====== 単体Test用 main() ======
//  public static void main(String[] args) {
//      MutterData data = new MutterData("name", "pass", "****");
//      //MutterData data = new MutterData();
//
//      LoginDAO inDAO = new LoginDAO();
//      boolean isRegister = inDAO.select(data);
//
//      System.out.println("isRegister: " + isRegister);
//
//      //◇実行結果
//      //MutterData data = new MutterData("name", "pass", "****");
//      //isRegister: true
//
//      //MutterData data = new MutterData();
//      //isRegister: false
//  }//main()

}//class
