package com.booking.shows.view;

import com.booking.handlers.models.ErrorResponse;
import com.booking.movieGateway.exceptions.FormatException;
import com.booking.movieGateway.models.Movie;
import com.booking.shows.ShowService;
import com.booking.shows.respository.Show;
import com.booking.shows.respository.ShowRepository;
import com.booking.shows.view.models.ShowResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "Shows")
@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    @ApiOperation(value = "Fetch shows")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Fetched shows successfully"),
            @ApiResponse(code = 500, message = "Something failed in the server", response = ErrorResponse.class)
    })
    public List<ShowResponse> fetchAll(@Valid @RequestParam(name = "date") Date date) throws IOException, FormatException {
        List<ShowResponse> showResponse = new ArrayList<>();
        List<Object> showDetails= new ArrayList<>();
        for (Show show : showService.fetchAll(date)) {
            showResponse.add(constructShowResponse(show));
            showDetails.add(constructShowResponse(show).getSlot());
            showDetails.add(constructShowResponse(show).getDate());
            showDetails.add(constructShowResponse(show).getMovie());
        }

        return showResponse;
    }

    @GetMapping("/allshow")
    public List<Movie> fetchAllMovies() throws IOException, FormatException {
        List<ShowResponse> showResponse = new ArrayList<>();
        return showService.getAllMoviesList();
    }
    private ShowResponse constructShowResponse(Show show) throws IOException, FormatException {
        Movie movie = showService.getMovieById(show.getMovieId());
        return new ShowResponse(movie, show.getSlot(), show);
    }

}
