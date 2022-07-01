package com.booking.slots.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.shows.respository.Show;
import com.booking.shows.respository.ShowRepository;
import java.sql.Time;
import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Integer> {
    List<Slot> findAll();
    List<Slot> findByEndTimeIsNotNull();

//    @Query("SELECT startTime,endTime FROM Slot slot")
//    List<Object> findStartTimesAndEndTimes();

//    @Query("SELECT startTime FROM Slot slot")
//    List<String> findStartTimes();

//    @Query("SELECT startTime FROM Show show")
//    List<Slot> findSlot();


}
