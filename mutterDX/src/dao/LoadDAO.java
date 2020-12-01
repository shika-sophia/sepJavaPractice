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

    public void selectMutter(MutterData data, String JDBC_URL, String DB_USER, String DB_PASS) {
        boolean isLoad = false;
        List<String> mutterList = new ArrayList<>();
        List<String> dateTimeList = new ArrayList<>();

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

                    mutterList.add(mutter);
                    dateTimeList.add(dateTime);
                }//while
            }//if-else
        } catch (SQLException e) {
            e.printStackTrace();
            isLoad = false;
        }

        data.setMutterList(mutterList);
        data.setDateTimeList(dateTimeList);
        //return isLoad;
    }//selectMutter()

    //====== 単体Test用 main() ======
    public static void main(String[] args) {


    }//main()
}//class
