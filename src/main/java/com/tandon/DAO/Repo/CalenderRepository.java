package com.tandon.DAO.Repo;

import com.tandon.DAO.POJOs.Calender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalenderRepository extends JpaRepository<Calender,Integer> {
    List<Calender> findCalendersByDate(String date);
    Calender findCalenderByDateAndDayAndTime(String date, int day, String time);
}
