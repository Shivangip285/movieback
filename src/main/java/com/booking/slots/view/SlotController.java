package com.booking.slots.view;

import com.booking.movieGateway.exceptions.FormatException;
import com.booking.slots.repository.Slot;
import com.booking.shows.respository.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

//@Api(tags = "Slots")
@RestController
@RequestMapping("/slots")
public class SlotController {

    private final SlotService slotService;
    //private SlotRepository slotRepository;

    @Autowired
    SlotController(SlotService slotService){
        this.slotService =slotService;
    }


//    @GetMapping
//    public Map<String, List<Slot>> fetchTime1() throws IOException, FormatException {
//        //List<Slot> slotResponse = new ArrayList<>();
//        Map<String, List<Slot>> slotResponse = new HashMap<>();
//        //Slot slot=new Slot();
//        slotResponse.put("slotEndTime",slotService.fetchAllSlot());
//        return slotResponse;
//    }
//@GetMapping
//public Map<String, List<String>> fetchTime1() throws IOException, FormatException {
//    //List<Slot> slotResponse = new ArrayList<>();
//    Map<String, List<String>> slotResponse = new HashMap<>();
//    //Slot slot=new Slot();
//    slotResponse.put("slotEndTime",slotService.fetchAllSlot());
//    return slotResponse;
//}
//@GetMapping("/startTime")
//public List<String> fetchAllStartTimes() throws IOException, FormatException {
//    List<String> slotResponse = new ArrayList<>();
//    slotResponse.addAll(slotService.fetchAllStartTimes());
//    return slotResponse;
//}
@GetMapping("/endTime")
public List<Slot> fetchAllSlots() throws IOException, FormatException {
    List<Slot> slotResponse = new ArrayList<>();
    slotResponse.addAll(slotService.fetchAllEndTimes());
    return slotResponse;
}
    @GetMapping("/occupied")
    public List<Object> fetchAllOccupiedSlots(@Valid @RequestParam(name = "date") java.sql.Date date) throws IOException, FormatException {
        List<Object> slotOccupied=new ArrayList<>();
        slotOccupied.addAll(slotService.fetchAllOccupiedSlots(date));
        return slotOccupied;
    }
}