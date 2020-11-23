package webPractice.mutter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MutterData implements Serializable{
    private String name;  //ユーザー名
    private String pass;  //パスワード
    private String passCode; //passを「****」に置換
    private List<String> mutterList;//ユーザー用のList
    private List<String> dateTimeList;

    public MutterData() {
        name = "";
        pass = "";
        passCode = "";
    }

    public MutterData(String name, String pass, String passCode) {
        setName(name);
        setPass(pass);
        setPassCode(passCode);
        mutterList = new ArrayList<String>();
        setDateTimeList(new ArrayList<String>());
    }

    //====== getter, setter ======
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }

    public List<String> getMutterList() {
        return mutterList;
    }

    public void setMutterList(List<String> mutterList) {
        this.mutterList = mutterList;
    }

    public void setMutterList(String mutter) {
        this.mutterList.add(mutter);
    }

    public List<String> getDateTimeList() {
        return dateTimeList;
    }

    public void setDateTimeList(List<String> dateTimeList) {
        this.dateTimeList = dateTimeList;
    }

    public void setDateTimeList(String dateTime) {
        this.dateTimeList.add(dateTime);
    }
}//class
