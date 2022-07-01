package com.booking.slots.view;
import com.booking.shows.ShowService;
import com.booking.shows.respository.Show;
import com.booking.slots.repository.Slot;
import com.booking.slots.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SlotService {

        private final SlotRepository slotRepository;

        private final ShowService showService;

        @Autowired
        public SlotService(SlotRepository slotRepository, ShowService showService) {
            this.slotRepository = slotRepository;
            this.showService = showService;
        }

    public List<Slot> fetchAllEndTimes() {
        return slotRepository.findAll();
    }
    public List<Show> fetchAllOccupiedSlots(Date date) {
        return showService.fetchAll(date);
    }
    public void disableSlots(Date date){

    }
    }
