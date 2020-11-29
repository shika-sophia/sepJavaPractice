package model;

public class SaveLogic {

    public void saveDB(MutterData data) {
        //---- DBに登録 ----
        MutterRegister regist = new MutterRegister();
        regist.insertMutter(data);
    }//saveDB()

}//class
