package model;

public class LoadLogic {

    public void loadDB(MutterData data) {
        DataAccess dataAcs = new DataAccess();
        dataAcs.loadMutter(data);
    }//loadDB()

}//class
