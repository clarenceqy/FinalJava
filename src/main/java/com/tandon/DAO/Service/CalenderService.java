package com.tandon.DAO.Service;

import com.tandon.DAO.POJOs.Calender;

import java.util.List;

public interface CalenderService {
    boolean add(Calender calender);
    void remove(String date, int day, String time);
    Calender getCalender(String date, int day, String time);
    List<Calender> getList(String date);
}
