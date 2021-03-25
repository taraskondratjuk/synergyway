package ua.com.aircompany.synergyway.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.com.aircompany.synergyway.dao.AirplaneDAO;
import ua.com.aircompany.synergyway.dao.FlightDAO;
import ua.com.aircompany.synergyway.models.Airplane;
import ua.com.aircompany.synergyway.models.Flight;
import ua.com.aircompany.synergyway.settings.FlightStatus;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/flight")
public class FlightController {
    private AirplaneDAO airplaneDAO;
    private FlightDAO flightDAO;

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

}
