package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.DataAccess;

public class SaveLogic {
    private DataAccess dataAcs;

    public SaveLogic() {
        dataAcs = new DataAccess();
    }

    public void saveDB(MutterData data, MutterLogic logic) {
        //---- DBに登録 ----
        dataAcs.insertMutter(data);

      //----重複「つぶやき」を修正 -----
        exceptDistinct(data);
    }//saveDB()

    //====== 重複した「つぶやき」を除去 ======
    private void exceptDistinct(MutterData data) {
        List<String> dateTimeList = data.getDateTimeList();

        if (dateTimeList.isEmpty()) {
            return;
        }

        Set<String> distinctSet = new HashSet<>(dateTimeList);

        List<String> distinctList = new ArrayList<>(distinctSet);
        Collections.sort(distinctList);
        Collections.reverse(distinctList);

        List<String> mutterListDistinct= dataAcs.selectDistinct(distinctList);

        data.setMutterList(mutterListDistinct);
        data.setDateTimeList(distinctList);
    }//exceptDistinct()

}//class
