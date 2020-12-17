package model;

public class InputLogic {
    private int year;//int化 year
    private int month;//int化 month
    private final int YEAR_BOUND = 3000;//年数の最大値
    private final int MONTH_BOUND = 12; //月の最大値

    //====== String -> int ======
    //====== 不正値チェック(非整数) ======
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

    //====== 不正値チェック(範囲外)======
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
    }//inputMatch()

    //====== エラーメッセージの作成 ======
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
            mess.setMsgList(
                    String.format("< ! > %sは 1～%d で入力してください",
                        subject, BOUND));
        }
    }//matcherRange()

    //====== getter ======
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

}//class
