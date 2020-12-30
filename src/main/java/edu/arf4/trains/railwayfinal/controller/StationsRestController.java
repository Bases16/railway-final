package edu.arf4.trains.railwayfinal.controller;

import edu.arf4.trains.railwayfinal.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/stations")
public class StationsRestController {

    private final StationService stationService;

    public StationsRestController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping
    public void createStation(String stationName) {
        stationService.createStation(stationName);
    }

    @DeleteMapping("/{id}")
    public void deleteStation(@PathVariable("id") Long id) {
        stationService.deleteStation(id);
    }


    @GetMapping
    public String allStations() {
        String s = "hello from rest/stations GET";
        return s;
    }
}
