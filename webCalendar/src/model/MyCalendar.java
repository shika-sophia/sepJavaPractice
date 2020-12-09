package model;

import java.util.ArrayList;
import java.util.List;

public class MyCalendar {
    private List<String> dayList;

    public MyCalendar() {
        setDayList(new ArrayList<String>(42));
    }

    public void buildCalendar(int year, int month) {

    }//buildCalendar()


    //====== getter, setter ======
    public List<String> getDayList() {
        return dayList;
    }

    public void setDayList(List<String> dayList) {
        this.dayList = dayList;
    }

    public void setDayList(String day) {
        dayList.add(day);
    }
}//class
