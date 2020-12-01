package model;

public class SaveLogic {

    public void saveDB(MutterData data) {
        //---- DBに登録 ----
        DataAccess regist = new DataAccess();
        regist.insertMutter(data);
    }//saveDB()

}//class
