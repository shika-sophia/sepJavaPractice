package model;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private List<String> msgList;

    public Message() {
        setMsgList(new ArrayList<String>());
    }

    public void msgClear() {
        msgList.clear();
    }//msgClear()

    public void msgForInput() {
        msgClear();
        msgList.add("年, 月を入力してください。");
    }//msgForInput()

    public void msgNgInput(String msgFlag) {
        msgClear();

        switch(msgFlag) {
        case "decimal":
            setMsgList("< ! > 整数で入力してください。");
            break;

        //case "bound": //see InputLogic.matcherRande()

        }//switch
    }//msgNgInput()


    public void ngMemo(String msgFlag) {
        msgClear();

        switch(msgFlag) {
        case "overLiteral":
            msgList.add("< ! > メモは50文字以内で入力してください。");
            break;

        case "overlap":
            msgList.add("< ! > 同一内容のメモは登録できません。");
            break;

        case "overSize":
            msgList.add("< ! > メモは３つまでです。<br>"
                + "追加するなら どれかを削除してください。");
            break;

        }//switch
    }//ngMemo()


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
