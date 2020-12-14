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
        msgList.add("年, 月を入力してください。");
    }//msgForInput()

    public void msgNgInput(String msgFlag) {
        msgList.clear();

        switch(msgFlag) {
        case "decimal":
            setMsgList("< ! > 整数で入力してください。");
            break;

        //case "bound": //see InputLogic.matcherRande()

        }//switch
    }//msgNgInput()

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
