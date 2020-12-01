package model;

import java.util.ArrayList;
import java.util.List;

public class LoginLogic {
    private List<String> msgList;


    public LoginLogic() {
        msgList = new ArrayList<String>();
    }

    //====== name, pass の文字数チェック ======
    public boolean checkInput(String name, String pass) {
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
        return isLogin;
    }//checkInput()

    //====== mailの文字数チェック ======
    public void checkMail(String mail) {
        int mailLength = mail.length();

        if(10 < mailLength && mailLength < 50) {
            ;
        } else {
            msgList.add("Mailは[ 10～50 ]文字で入力してください。");
        }

        String[] divideMail = mail.split("@");
        if (divideMail[0].length() < 6) {
            msgList.add("「@」の前のアカウント部分は６文字以上で入力してください。");
        }
    }//checkMail()

    //====== Login時のpassと Register時のpassが一致するか ======
    public boolean identifyPass(String pass, MutterData data) {
        String loginPass = data.getPass();

        if (loginPass.equals(pass)) {
            return true;

        } else {
            msgList.add("ログイン時と登録時の Passが違います。");
            msgList.add("確認のためログインからやり直してください。");
            return false;
        }
    }//identifyPass

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

    //==== make mailCode as like "shi********@domain.co.jp" ======
    public String mailCode(String mail) {
        StringBuilder bld = new StringBuilder();

        String initial = mail.substring(0, 3);
        bld.append(initial);

        String[] divideMail = mail.split("@");

        for (int i = 4; i <= divideMail[0].length(); i++) {
            bld.append("*");
        }
        bld.append("@");
        bld.append(divideMail[1]);

        return bld.toString();
    }//mailCode()


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

//  //====== 単体テスト用 main() ======
//  public static void main(String[] args) {
//      MutterLoginLogic inLogic = new MutterLoginLogic();
//      String mail = "shika-sophia@dammy.co.jp";
//      String mailCode = inLogic.mailCode(mail);
//
//      System.out.println(mail + " -> " + mailCode);
//      //◇実行結果
//      //shika-sophia@dammy.co.jp -> shi*********@dammy.co.jp
//  }//main()

}//class
