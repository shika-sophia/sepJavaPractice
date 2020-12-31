package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.DataAccess;

public class MemoLogic {
    private List<String> memoList;

    public MemoLogic() {
        setMemoList(new ArrayList<String>());
    }


    public void treatDate(String yearStr, String monthStr, String dayStr, CalendarLogic calen) {
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


    public void buildMemoList(String memoStr, CalendarLogic calen) {
        //---- load memoList ----
        DataAccess dao = new DataAccess();
        boolean isLoad= dao.loadMemo(memoList, calen);
        memoList = dao.getMemoList();

        //=> if(isLoad == false)の処理

        //---- add momo ----
        if(memoStr == null || memoStr.equals("")) {
            ;
        } else {
            memoList.add(memoStr);
        }

        //=> memoは３つまでのロジック


        //---- save memoList ----
        int isSave = dao.saveMemo(memoList, calen);

    }//buildMemoList()


    //====== getter, setter ======
    public List<String> getMemoList() {
        return memoList;
    }

    public void setMemoList(List<String> memoList) {
        this.memoList = memoList;
    }

}//class
