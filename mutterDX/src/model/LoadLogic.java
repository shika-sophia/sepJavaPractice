package model;

public class LoadLogic {

    public void loadDB(MutterData data) {
        MutterRegister regist = new MutterRegister();
        regist.loadMutter(data);
    }//loadDB()

}//class
