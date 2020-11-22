package webPractice.mutter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MutterData implements Serializable{
    private String name;
    private String pass;
    private String passCode;
    private List<String> mutterList;

    public MutterData() {
        name = "";
        pass = "";
        passCode = "";
        setMutterList(new ArrayList<String>());
    }

    public MutterData(String name, String pass, String passCode) {
        setName(name);
        setPass(pass);
        setPassCode(passCode);
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

}//class
