package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

public class CalendarLogic implements Serializable{
    private int year; //dayListのyear
    private int month;//dayListのmonth
    private int day;  //memo用のday
    private int prevYear;//prevListの年
    private int prevMonth;//prevListの月
    private int nextYear; //nextListの年
    private int nextMonth;//nextListの月

    private int lastDay;//その月の最終日
    private int dayWeek;//初日の曜日

    private List<String> baseList; //year,monthに対応したカレンダーList
    private List<String> prevList;//前月のカレンダーList
    private List<String> nextList;//翌月のカレンダーList


    public CalendarLogic() {
        setBaseList(new ArrayList<String>(42));
        setPrevList(new ArrayList<String>(42));
        setNextList(new ArrayList<String>(42));
    }//constractor

    public void dateNow() {
        LocalDate now = LocalDate.now();
        year = now.getYear();
        month = now.getMonthValue();
        day = now.getDayOfMonth();

        buildList();
    }//dateNow()

    public void dateInput(int year, int month) {
        this.year = year;
        this.month = month;

        buildList();
    }//dateInput()


    public void buildList() {
        //---- 日付データ -> List ----
        LocalDate baseDate = LocalDate.of(year, month, 1);
        baseList = buildCalendar(baseDate, baseList);

        //---- prev adapter ----
        LocalDate prevDate = baseDate.minusMonths( 1 );
        prevYear = prevDate.getYear();
        prevMonth = prevDate.getMonthValue();
        prevList = buildCalendar(prevDate, prevList);

        //---- next adapter ----
        LocalDate nextDate = baseDate.plusMonths( 1 );
        nextYear = nextDate.getYear();
        nextMonth = nextDate.getMonthValue();
        nextList = buildCalendar(nextDate, nextList);
    }//buildList()


    public List<String> buildCalendar(LocalDate date, List<String> list) {
        //その月の最終日
        lastDay = date.lengthOfMonth();
        //初日の曜日(月1,火2,水3,木4,金5,土6,日7)
        dayWeek = date.get(ChronoField.DAY_OF_WEEK);

        //日曜日7を 0に変換(最初の曜日を日曜にする)
        if (dayWeek == 7) {
            dayWeek = 0;
        }

        //---- 各listを生成 ----
        list.clear();
        //最初の空白を挿入
        for (int space = 1; space <= dayWeek; space++) {
            list.add("　"); //&emsp;
        }//for space

        //日付を追加
        for (int i = 1; i <= lastDay; i++) {
            list.add(String.valueOf( i ));
        }//for lastDay

        //最後の空白を挿入
        for(int space = (dayWeek + lastDay + 1); space <= 42; space++) {
            list.add("　"); //&emsp;
        }//for space

        return list;
    }//buildCalendar()


    public void moveSwitch(String move) {

        switch(move) {
        case "prev":
            year = prevYear;
            month = prevMonth;
            buildList();
            break;

        case "memo":
            dateNow();
            break;

        case "next":
            year = nextYear;
            month = nextMonth;
            buildList();
            break;
        }//switch

    }//moveSwitch()

    //====== getter, setter ======
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getPrevYear() {
        return prevYear;
    }

    public int getPrevMonth() {
        return prevMonth;
    }

    public int getNextYear() {
        return nextYear;
    }

    public int getNextMonth() {
        return nextMonth;
    }

    public int getLastDay() {
        return lastDay;
    }

    public int getDayWeek() {
        return dayWeek;
    }

    public List<String> getBaseList() {
        return baseList;
    }

    public void setBaseList(List<String> baseList) {
        this.baseList = baseList;
    }

    public List<String> getPrevList() {
        return prevList;
    }

    public void setPrevList(List<String> prevList) {
        this.prevList = prevList;
    }

    public List<String> getNextList() {
        return nextList;
    }

    public void setNextList(List<String> nextList) {
        this.nextList = nextList;
    }


//    //====== Test main() ======
//    public static void main(String[] args) {
//        CalendarLogic calen = new CalendarLogic();
//        //calen.dateNow();
//        calen.dateInput(2020, 11);
//
//        System.out.printf("%d年 %d月\n", calen.year, calen.month);
//        System.out.println("dayList.size(): " + calen.dayList.size());
//        System.out.println("dayList: " + calen.dayList);
//
//        System.out.printf("%d年 %d月\n", calen.prevYear, calen.prevMonth);
//        System.out.println("prevList.size(): " + calen.prevList.size());
//        System.out.println("prevList: " + calen.prevList);
//
//        System.out.printf("%d年 %d月\n", calen.nextYear, calen.nextMonth);
//        System.out.println("nextList.size(): " + calen.nextList.size());
//        System.out.println("nextList: " + calen.nextList);
//
//    }//main()
}//class

/*
//====== Test main() ======
//calen.dateNow();//2020-12-10
dayList.size(): 42
dayList: [　, 　, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 　, 　, 　, 　, 　, 　, 　, 　, 　]

//calen.buildCalendar(2020, 11);
dayList.size(): 42
dayList: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 　, 　, 　, 　, 　, 　, 　, 　, 　, 　, 　, 　]

calen.buildCalendar(2020, 2);
dayList.size(): 42
dayList: [　, 　, 　, 　, 　, 　, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 　, 　, 　, 　, 　, 　, 　]

//---- dayList, prevList, nextList ----
2020年 11月
dayList.size(): 42
dayList: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 　, 　, 　, 　, 　, 　, 　, 　, 　, 　, 　, 　]

2020年 10月
prevList.size(): 42
prevList: [　, 　, 　, 　, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 　, 　, 　, 　, 　, 　, 　]

2020年 12月
nextList.size(): 42
nextList: [　, 　, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 　, 　, 　, 　, 　, 　, 　, 　, 　]

 */
