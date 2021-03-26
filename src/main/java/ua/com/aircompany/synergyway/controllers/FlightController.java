package ua.com.aircompany.synergyway.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.com.aircompany.synergyway.dao.AirplaneDAO;
import ua.com.aircompany.synergyway.dao.FlightDAO;
import ua.com.aircompany.synergyway.models.Airplane;
import ua.com.aircompany.synergyway.models.Flight;
import ua.com.aircompany.synergyway.settings.FlightStatus;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor

@RestController
@RequestMapping("/flight")
public class FlightController {
    private AirplaneDAO airplaneDAO;
    private FlightDAO flightDAO;

//    @PostMapping("/test")
//    public void test(@RequestBody Flight flight) {
//        flightDAO.save(flight);
//    }


    @PostMapping("/save")
    public void saveFlight(@RequestBody Flight flight) {
        if (flight != null) {
            Flight createFlight = flight;
            createFlight.setFlightStatus(FlightStatus.PENDING);
            flightDAO.save(createFlight);
        }
    }

    @GetMapping("/getAll")
    public List<Flight> getAllFlight() {
        return flightDAO.findAll();
    }

    @PostMapping("/update/{id}")
    public void updateFlightById(@RequestBody Flight flight, @PathVariable int id) {
        Flight updateFlight = flightDAO.getOne(id);
        if (updateFlight.getId() != 0) {
            updateFlight.setDepartureCountry(flight.getDepartureCountry());
            updateFlight.setDestinationCountry(flight.getDestinationCountry());
            updateFlight.setDistance(flight.getDistance());
            updateFlight.setCreatedAt(flight.getCreatedAt());
            updateFlight.setEstimatedFlightTime(flight.getEstimatedFlightTime());
            updateFlight.setStartedAt(flight.getStartedAt());
            updateFlight.setEndedAt(flight.getEndedAt());
            updateFlight.setDelayStartedAt(flight.getDelayStartedAt());
            flightDAO.save(updateFlight);
        }
    }


    @GetMapping("/add/flight/{flightId}/airplane/{airplaneId}")
    public void addAirplaneIdToFlightId(@PathVariable int flightId, @PathVariable int airplaneId) {
        Airplane findAirplane = airplaneDAO.getOne(airplaneId);
        Flight findFlightToAddAirplane = flightDAO.getOne(flightId);
        if (findAirplane.getId() != 0 && findFlightToAddAirplane.getId() != 0) {
            findAirplane.setFlight(findFlightToAddAirplane);
            airplaneDAO.save(findAirplane);
            findFlightToAddAirplane.setAirplane(findAirplane);
            flightDAO.save(findFlightToAddAirplane);

        }
    }

    @GetMapping("/delete/{id}")
    public void deleteFlightByID(@PathVariable int id) {
        Flight deletedFlight = flightDAO.getOne(id);
        flightDAO.delete(deletedFlight);
    }

    @PostMapping("/changeStatus/flight/{flightId}/status/{status}")
    public void changeStatus(@PathVariable int flightId, @PathVariable String status) {
        Flight flight = flightDAO.getOne(flightId);

        switch (status.toUpperCase()) {
            case "DELAYED":
                flight.setFlightStatus(FlightStatus.DELAYED);
                flight.setDelayStartedAt(new Date());
                break;
            case "ACTIVE":
                flight.setFlightStatus(FlightStatus.ACTIVE);
                flight.setStartedAt(new Date());
                break;
            case "COMPLETED":
                flight.setFlightStatus(FlightStatus.COMPLETED);
                flight.setEndedAt(new Date());
                break;
        }
        flightDAO.save(flight);
    }


    @PostMapping("/findAllStatus/{status}")
    public List<Flight> findAllFlightsWhereStatusActiveMore24(@PathVariable String status) {
        List<Flight> allFlights = flightDAO.findAll();
        Date date = new Date();
        int presentDay = date.getDate();
        int presentHours = date.getHours();

        int totalPresentHours = presentDay * 24 + presentHours;

        try {
            List<Flight> flightsForActiveStatusMore24 = allFlights.stream().
                    filter(flight -> flight.getFlightStatus().toString().equals(status)).map(flight -> {
                int startHours = flight.getStartedAt().getHours();
                int startDay = flight.getStartedAt().getDate();
                int totalPastHours = startDay * 24 + startHours;

                if ((totalPresentHours - totalPastHours) > 24) {
                    return flight;
                }
                return null;

            }).collect(Collectors.toList());
            return flightsForActiveStatusMore24;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @PostMapping("/findAllFlights/{status}")
    public List<Flight> findAllFlightsWhereStatusIsCompleteAndDifferenceBetweenStartedEndedTimeBiggerEstimatedFlightTime(@PathVariable String status) {
        List<Flight> allFlights = flightDAO.findAll();

        try {
            List<Flight> allFlightsWithStatusCompleteAndDifferenceBetweenStartedAndEndedTimeBiggerEstimatedFlightTime = allFlights.stream()
                    .filter(flight -> flight.getFlightStatus().toString().equals(status)).map(flight -> {
                        int endedDay = flight.getEndedAt().getDate();
                        int endedHour = flight.getEndedAt().getHours();
                        int totalEndedTime = endedDay * 24 + endedHour;

                        int startedDay = flight.getStartedAt().getDate();
                        int startedHours = flight.getStartedAt().getHours();
                        int totalStartedTime = startedDay * 24 + startedHours;

                        if (totalEndedTime - totalStartedTime > flight.getEstimatedFlightTime()) {
                            return flight;
                        }

                        return null;
                    }).collect(Collectors.toList());

            return allFlightsWithStatusCompleteAndDifferenceBetweenStartedAndEndedTimeBiggerEstimatedFlightTime;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

}
