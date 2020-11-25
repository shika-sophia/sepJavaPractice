package webPractice.mutter.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import webPractice.mutter.model.MutterData;

public class LoginDAO {

    public boolean select(MutterData data) {
        boolean isRegister = false;

        final String JDBC_URL =
                "jdbc:mysql://localhost:3306/practice?characterEncoding=utf-8&serverTimezone=JST";
        final String DB_USER = "root";
        final String DB_PASS = "root";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);){
            //Loginで入力したname, passはＤＢに登録されているか
            String sql = "SELECT NAME, PASS FROM MUTTER WHERE NAME = ? AND PASS = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, data.getName());
            ps.setString(2, data.getPass());

            ResultSet rs = ps.executeQuery();

            if(rs == null) {
                isRegister = false;
            } else if (rs.next()) {
                isRegister = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return isRegister;
    }//select


//  //====== Test用 main() ======
//  public static void main(String[] args) {
//      MutterData data = new MutterData();
//
//      LoginDAO inDAO = new LoginDAO();
//      boolean isRegister = inDAO.select(data);
//
//      System.out.println("isRegister: " + isRegister);
//      //◇実行結果
//      //isRegister: false -> 一応ＤＢに繋がることは確認
//  }//main()

}//class
