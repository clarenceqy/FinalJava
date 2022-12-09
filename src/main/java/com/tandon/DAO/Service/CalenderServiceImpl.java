package com.tandon.DAO.Service;

import com.tandon.DAO.POJOs.Calender;
import com.tandon.DAO.Repo.CalenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalenderServiceImpl implements CalenderService{

    @Autowired
    private CalenderRepository repository;

    @Override
    public boolean add(Calender calender) {
        if(getCalender(calender.getDate(), calender.getDay(), calender.getTime()) == null){
            repository.save(calender);
            return true;
        }
        return false;
    }

    @Override
    public void remove(String date, int day, String time) {
        repository.delete(getCalender(date, day, time));
    }

    @Override
    public Calender getCalender(String date, int day, String time) {
        Calender res = repository.findCalenderByDateAndDayAndTime(date,day,time);
        return res;
    }

    @Override
    public List<Calender> getList(String date){
        return repository.findCalendersByDate(date);
    }
}
