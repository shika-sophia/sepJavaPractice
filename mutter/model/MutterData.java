package webPractice.mutter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MutterData implements Serializable{
    private int userId;
    private String name;  //ユーザー名
    private String pass;  //パスワード
    private String passCode; //passを「****」に置換
    private String mail;  //メール
    private String mailCode; //mailを「la.s******@docomo.co.jp」に置換
    private List<String> mutterList;  //ユーザー用のList(最新のものが先頭)
    private List<String> dateTimeList;//つぶやき時の日時(最新のものが先頭)

    //Constractor for init()
    public MutterData() {
        setUserId(0);
        name = "";
        pass = "";
        passCode = "";
        mail = "";
        mailCode = "";
    }

    //Constractor for Login
    public MutterData(String name, String pass, String passCode) {
        setName(name);
        setPass(pass);
        setPassCode(passCode);
        setMutterList(new ArrayList<String>());
        setDateTimeList(new ArrayList<String>());
    }


    //====== getter, setter ======
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMailCode() {
        return mailCode;
    }

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    public List<String> getMutterList() {
        return mutterList;
    }

    public void setMutterList(List<String> mutterList) {
        this.mutterList = mutterList;
    }

    public void setMutterList(String mutter) {
        this.mutterList.add(0, mutter);
    }

    public List<String> getDateTimeList() {
        return dateTimeList;
    }

    public void setDateTimeList(List<String> dateTimeList) {
        this.dateTimeList = dateTimeList;
    }

    public void setDateTimeList(String dateTime) {
        this.dateTimeList.add(0, dateTime);
    }
}//class
