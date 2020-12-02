package model;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private List<String> msgList;

    public Message() {
        setMsgList(new ArrayList<String>());
    }

    public void msgMutterDoGet(String msgFlag) {
        msgList.clear();

        switch(msgFlag) {
        case "admit":
            setMsgList("ログインしました。");
            setMsgList("つぶやきを投稿してください。");
            break;

        case "doneRegister":
            setMsgList("登録完了。ログインしました。");
            setMsgList("つぶやきを投稿してください。");
            break;

        case "load":
            setMsgList("ロード完了。");
            break;

        case "noLoad":
            setMsgList("ロードを中止しました。");
            break;

        default:
            ;
            break;
        }//switch

    }//msgMutterDoGet()


    public void msgMutterDoPost(String msgFlag, MutterData data) {
        msgList.clear();

        switch(msgFlag) {
        case "postMutter":
            msgList.add(String.format("%sさんが投稿しました。", data.getName()));
            break;

        case "overText":
            msgList.add("つぶやきは 150文字以内で入力してください。");
            break;

        case "reload":
            msgList.add("同じ内容は投稿できません。");
            msgList.add("Ｗｅｂページのリロードは使わないでください。");
            break;

        }//switch


    }//msgMutterDoPost()

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
