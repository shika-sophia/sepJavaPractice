package model;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

public class MyCalendar {
    private int year;
    private int month;
    private List<String> dayList;
    private List<String> prevList;
    private List<String> nextList;

    public MyCalendar() {
        setDayList(new ArrayList<String>(42));
        setPrevList(new ArrayList<String>(42));
        setNextList(new ArrayList<String>(42));
    }//constractor

    public void dateNow() {
        LocalDate now = LocalDate.now();
        year = now.getYear();
        month = now.getMonthValue();

        buildList();
    }//dateNow()

    public void dateInput(int year, int month) {
        this.year = year;
        this.month = month;

        buildList();
    }//dateInput()

    public void buildList() {
        dayList = buildCalendar(year, month, dayList);
        prevList = buildCalendar(year - 1, month - 1, prevList);
        nextList = buildCalendar(year + 1, month + 1, nextList);
    }//buildList()

    public List<String> buildCalendar(int year, int month ,List<String> list) {
        LocalDate dateFirst = LocalDate.of(year, month, 1);
        //その月の最終日
        int lastDay = dateFirst.lengthOfMonth();
        //初日の曜日(月1,火2,水3,木4,金5,土6,日7)
        int dayWeek = dateFirst.get(ChronoField.DAY_OF_WEEK);

        //日曜日7を 0に変換(最初の曜日を日曜にする)
        if (dayWeek == 7) {
            dayWeek = 0;
        }

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

    public List<String> getDayList() {
        return dayList;
    }

    public void setDayList(List<String> dayList) {
        this.dayList = dayList;
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
//        MyCalendar calen = new MyCalendar();
//        calen.dateNow();
//        //calen.buildCalendar(2020, 11);
//        //calen.buildCalendar(2020, 2);
//        System.out.println("dayList.size(): " + calen.dayList.size());
//        System.out.println("dayList: " + calen.dayList);
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

 */
