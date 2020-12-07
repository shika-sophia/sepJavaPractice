package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CorrespondDAO {

    public List<String> selectCorrespond(List<String> list,
            String JDBC_URL, String DB_USER, String DB_PASS) {
        List<String> mutterListCrs = new ArrayList<>();

        for(int i = 0; i < list.size(); i++) {
            String dateTime = list.get(i);
            dateTime = dateTime.replace("(\\W)", "");

            try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);){
                //distinctListに対応するmutterを取得
                String sql = "SELECT DISTINCT MUTTER, DATETIME FROM MUTTER WHERE DATETIME = ?";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, dateTime);

                ResultSet rs = ps.executeQuery();

                while(rs.next()) {

                    String mutter = rs.getString("MUTTER");
                    //String dateTime = rs.getString("DATETIME");

                   mutterListCrs.add(mutter);
                }//while

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }//for distinctList

        return mutterListCrs;
    }//selectCorrespond()

//    //====== CorrespondDAO 単体Test用 main() ======
//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>(
//            Arrays.asList(
//                    "2020-12-02 18:17:15",
//                    "2020-12-02 18:17:15",
//                    "2020-11-27 16:00:00",
//                    "2020-11-27 16:27:15",
//                    "2020-11-27 16:00:00",
//                    "2020-11-27 16:27:15")
//        );
//
//        DataAccess dataAcs = new DataAccess();
//        String JDBC_URL = dataAcs.getJDBC_URL();
//        String DB_USER = dataAcs.getDB_USER();
//        String DB_PASS = dataAcs.getDB_PASS();
//
//        CorrespondDAO crsDAO = new CorrespondDAO();
//        List<String> mutterListCrs = crsDAO.selectCorrespond(
//           list,JDBC_URL, DB_USER, DB_PASS);
//
//        for(int i = 0; i < list.size(); i++) {
//            System.out.printf("%s : [%s] \n", mutterListCrs.get(i), list.get(i));
//        }
//    }//main()
}//class

/*
//====== 実行結果 ======
invalidate : [2020-12-02 18:17:15]
invalidate : [2020-12-02 18:17:15]
つぶやき１ : [2020-11-27 16:00:00]
つぶやき１ : [2020-11-27 16:27:15]
つぶやき２ : [2020-11-27 16:00:00]
つぶやき２ : [2020-11-27 16:27:15]
Exception in thread "main" java.lang.IndexOutOfBoundsException:
Index 6 out of bounds for length 6
at dao.DistinctDAO.main(DistinctDAO.java:70)

【考察】
上記 Exceptionは、distinctListの日時に対応した、mutterを取得するので
DBに重複があると mutterのほうが多くなることによる。

for(int i = 0; i < mutterListDistinct.size(); i++) {
    System.out.printf("%s : [%s] \n", mutterListDistinct.get(i), distinctList.get(i));
}

日付に対応したmutterを検索できることは分かった
*/
