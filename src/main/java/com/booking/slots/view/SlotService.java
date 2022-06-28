package com.booking.slots.view;
import com.booking.slots.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SlotService {

        private final SlotRepository slotRepository;

        @Autowired
        public SlotService(SlotRepository slotRepository) {
            this.slotRepository = slotRepository;
        }

    public List<String> fetchAllEndTimes() {
      //return  slotRepository.findAll();

        //return slotRepository.findByEndTimeIsNotNull();
        return slotRepository.findEndTimes();
    }
    public List<String> fetchAllStartTimes() {
        return slotRepository.findStartTimes();
    }
    }
