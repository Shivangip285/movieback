package com.booking.slots.view;

import com.booking.movieGateway.exceptions.FormatException;
import com.booking.slots.repository.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@GetMapping
public Map<String, List<String>> fetchTime1() throws IOException, FormatException {
    //List<Slot> slotResponse = new ArrayList<>();
    Map<String, List<String>> slotResponse = new HashMap<>();
    //Slot slot=new Slot();
    slotResponse.put("slotStartTime",slotService.fetchAllStartTimes());
    slotResponse.put("slotEndTime",slotService.fetchAllEndTimes());
    return slotResponse;
}
}
