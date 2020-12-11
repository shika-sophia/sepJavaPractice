package model;

public class InputLogic {
    private int year;
    private int month;
    private final int YEAR_BOUND = 3000;
    private final int MONTH_BOUND = 12;

    public boolean transInt(Message mess, String yearStr, String monthStr) {
        boolean isMatch = true;

        try {
            year = Integer.parseInt(yearStr);
            month = Integer.parseInt(monthStr);

        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException in StartServlet.doGet()");
            mess.msgNgInput("decimal");
            isMatch = false;
        }

        return isMatch;
    }//transInt()

    public boolean inputMatch(Message mess) {
        boolean isMatch = false;

        matcherRange(mess, year, YEAR_BOUND);
        matcherRange(mess, month, MONTH_BOUND);

        if(mess.getMsgList().isEmpty()) {
            isMatch = true;
        } else {
            isMatch = false;
        }

        return isMatch;
    }//inpuMatch()

    private void matcherRange(Message mess, int num, int BOUND){
        //---- decide subject ----
        String subject = "";
        if(BOUND == YEAR_BOUND) {
            subject = "年";
        } else if(BOUND == MONTH_BOUND){
            subject = "月";
        }

        //---- judge to match range----
        if(1 <= num && num <= BOUND) {
            ;
        } else {
            mess.msgNgInput("bound"); //msgList.clear();の代わり
            mess.setMsgList(
                    String.format("< ! > %sは 1～%d の範囲で入力してください。",
                        subject, BOUND));
        }
    }//matcherInt()

    //====== getter ======
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

}//class
