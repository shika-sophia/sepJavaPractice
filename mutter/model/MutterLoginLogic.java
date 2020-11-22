package webPractice.mutter.model;

import java.util.ArrayList;
import java.util.List;

public class MutterLoginLogic {
    private List<String> msgList;

    public MutterLoginLogic() {
        msgList = new ArrayList<String>();
    }

    public boolean loginLogic(String name, String pass) {
        boolean isLogin = false;
        msgList.clear();

        if (name.length() == 1) {
            msgList.add("Nameは１文字以上で入力してください。");
        } else if (name.length() >= 50) {
            msgList.add("Nameは50文字以下で入力して下さい。");
        }

        if ( !pass.matches("\\w{4,20}")){
            msgList.add("Passは [ 4～20 ]文字で入力してください。");
        }

        if(msgList.isEmpty()) {
            isLogin = true;
        }

        //---- DBとの照合 ----
        //MutterInDAO inDAO = new MutterInDAO();
        //
        return isLogin;
    }//loginLogic()


    //====== make passCode as like "****" of passLength ======
    public String passCode(String pass) {
        String passCode = "";

        for (int i = 1; i <= pass.length(); i++) {
            passCode += "*";
        }

        //---- Test Print ----
        //System.out.println(pass + " : " +passCode);
        //1234 : ****

        return passCode;
    }//passCode()


    //====== getter, setter ======
    public List<String> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<String> msgList) {
        this.msgList = msgList;
    }

    public void setMsgList(String message) {
        msgList.add(message);
    }
}//class
