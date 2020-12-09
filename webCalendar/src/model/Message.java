package model;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private List<String> msgList;

    public Message() {
        setMsgList(new ArrayList<String>());
    }

    public void msgForInput() {
        msgList.add("年, 月を入力してください。");
    }//msgForInput()


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
