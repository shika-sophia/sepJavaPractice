package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.DataAccess;

public class MemoLogic {
    private DataAccess dao;
    private final int MEMO_BOUND = 3; //memoSizeの最大値
    private List<String> memoList;

    public MemoLogic() {
        dao = new DataAccess();
        setMemoList(new ArrayList<String>());
    }//constractor

    public void treatDate(
            String yearStr, String monthStr, String dayStr, CalendarLogic calen) {
        int year = 0;
        int month = 0;
        int day = 0;

        //---- from CalendarServlet, FunctionServlet => calen -> year,month,day ----
        if (yearStr == null) {
            year = calen.getYear();
            month = calen.getMonth();
            day = calen.getDay();

        //---- from onclick date => String -> int  ----
        } else {
             try {
                 year = Integer.parseInt(yearStr);
                 month = Integer.parseInt(monthStr);
                 day = Integer.parseInt(dayStr);

             } catch (NumberFormatException e) {
                 System.out.println("NumberFormatException of \'day\' in Memo");
                 day = 1;
             }//try-catch

             calen.setYear(year);
             calen.setMonth(month);
             calen.setDay(day);

        }//if-else

        LocalDate memoDate = LocalDate.of(year, month, day);
        String memoDayWeek = memoDate.format(DateTimeFormatter.ofPattern("(E)"));
        calen.setMemoDayWeek(memoDayWeek);
    }//treatDate

    //====== from MemoServlet doGet() ======
    public void readMemoList(CalendarLogic calen, Message mess) {
        //---- load memoList ----
        memoList.clear();
        dao.loadMemo(memoList, calen);
        memoList = dao.getMemoList();
    }//readMemoList

    //====== from MemoServlet doPost() ======
    public void buildMemoList(
            String memoStr, CalendarLogic calen, Message mess) {
        //---- load memoList ----
        memoList.clear();
        dao.loadMemo(memoList, calen);
        memoList = dao.getMemoList();

        //---- memoStrの不正値チェック ----
        //50文字以上
        if(memoStr.length() > 50) {
            mess.ngMemo("overLiteral");
            return;
        }

        //重複memoの除外
        for(String memo : memoList) {
            if (memo.equals(memoStr)) {
                mess.ngMemo("overlap");
                return;
            }
        }//for

        //memoは３つまで
        if(memoList.size() >= MEMO_BOUND) {
            mess.ngMemo("overSize");
            return;
        }

        //---- add momo ----
        if(memoStr == null || memoStr.equals("")) {
            ;
        } else {
            memoList.add(memoStr);

            //---- save memoList ----
            dao.saveMemo(memoStr, calen);
        }

    }//buildMemoList()

    //====== from FunctionServlet doPost() ======
    public void deleteMemo(int[] deleteId, CalendarLogic calen) {
        //---- load memoList ----
        dao.loadMemo(memoList, calen);
        memoList = dao.getMemoList();

        //---- int[] deleteId -> String[] deleteMemoStr ----
        String[] deleteMemoStr = new String[deleteId.length];

        for(int i = 0; i < memoList.size(); i++) {
            for(int deleteIdBit : deleteId) {
                if(i == deleteIdBit) {
                  deleteMemoStr[i] = memoList.get(i);
                }
            }
        }

        dao.deleteMemo(deleteMemoStr, calen);
    }//deleteMemo()


    //====== getter, setter ======
    public List<String> getMemoList() {
        return memoList;
    }

    public void setMemoList(List<String> memoList) {
        this.memoList = memoList;
    }

}//class
