package model;

import dao.DataAccess;

public class SaveLogic {

    public void saveDB(MutterData data) {
        //---- DBに登録 ----
        DataAccess dataAcs = new DataAccess();
        dataAcs.insertMutter(data);
    }//saveDB()

}//class
