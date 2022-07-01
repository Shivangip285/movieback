package com.booking.slots.view;

import com.booking.movieGateway.exceptions.FormatException;
import com.booking.movieGateway.models.Movie;
import com.booking.shows.ShowService;

import com.booking.shows.respository.Show;
import com.booking.shows.respository.ShowRepository;
import com.booking.slots.repository.SlotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SlotServiceTest {
    private SlotRepository slotRepository;

    private SlotService slotService;
    public static final List<String> MockSlotResponse = new ArrayList<>();
    public static final List<String> MockShowResponse = new ArrayList<>();

    @BeforeEach
    public void beforeEach() {
        slotService = mock(SlotService.class);
        slotRepository = mock(SlotRepository.class);
        MockSlotResponse.add("9:00 AM");
        MockSlotResponse.add("1:00 PM");
        MockSlotResponse.add("4:00 PM");
        MockShowResponse.add("9:00 AM");
        MockShowResponse.add("1:00 PM");
    }
//
//    @Test
//    public void should_get_movie_by_id() throws IOException, FormatException {
//
//        when(slotService.disableSlots()).thenReturn(movie);
//        ShowService showService = new ShowService(showRepository, movieGateway);
//
//        showService.getMovieById("movie_1");
//
//        verify(movieGateway).getMovieFromId("movie_1");
//    }

}