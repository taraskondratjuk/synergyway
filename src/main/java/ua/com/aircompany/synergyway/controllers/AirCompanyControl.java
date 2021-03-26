package ua.com.aircompany.synergyway.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.com.aircompany.synergyway.dao.AirCompanyDAO;
import ua.com.aircompany.synergyway.dao.AirplaneDAO;
import ua.com.aircompany.synergyway.dao.FlightDAO;
import ua.com.aircompany.synergyway.models.AirCompany;
import ua.com.aircompany.synergyway.models.Airplane;
import ua.com.aircompany.synergyway.models.Flight;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@RestController
@RequestMapping("/aircompany")
@CrossOrigin
public class AirCompanyControl {

    private AirCompanyDAO airCompanyDAO;
    private AirplaneDAO airplaneDAO;
    private FlightDAO flightDAO;

    @PostMapping("/save")
    public void saveCompany(@RequestBody AirCompany airCompany) {
        List<AirCompany> airCompanyList = airCompanyDAO.findAll();
        Optional<AirCompany> isAlreadyCompany = airCompanyList.stream().filter((company -> company.getName().equals(airCompany.getName()))).findAny();
        if (!isAlreadyCompany.isPresent()) {
            airCompanyDAO.save(airCompany);
        }
    }

    @GetMapping("/getAll")
    public List<AirCompany> getAllAirCompany() {
        return airCompanyDAO.findAll();
    }

    @PostMapping("/update/{id}")
    public void updateAirCompanyById(@RequestBody AirCompany airCompany, @PathVariable int id) {
        AirCompany updateAirCompany = airCompanyDAO.getOne(id);
        updateAirCompany.setName(airCompany.getName());
        updateAirCompany.setType(airCompany.getType());
        updateAirCompany.setFoundedAt(airCompany.getFoundedAt());
        airCompanyDAO.save(updateAirCompany);
    }

    @PostMapping("/delete/{id}")
    public void deleteCompanyById(@PathVariable int id) {
        AirCompany findDeleteCompany = airCompanyDAO.getOne(id);
        airCompanyDAO.delete(findDeleteCompany);
    }

    @PostMapping("/add/aircompany/{airCompanyId}/airplane/{airPlaneId}")
    public void addAirplaneByIdToAirCompanyById(@PathVariable int airCompanyId, @PathVariable int airPlaneId) {
        try {
            AirCompany airCompany = airCompanyDAO.getOne(airCompanyId);
            List<Airplane> airplanes = airplaneDAO.findAll();
            List<Airplane> airCompanyAirplanes = airCompany.getAirplanes();
            List<Airplane> findPlane = airplanes.stream().filter(airplane -> airplane.getId() == airPlaneId).collect(Collectors.toList());

            airCompanyAirplanes.addAll(findPlane);
            airCompanyDAO.save(airCompany);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @PostMapping("/add/aircompany/{airCompanyId}/flight/{flightId}")
    public void addFlightByIdToAirCompanyById(@PathVariable int airCompanyId, @PathVariable int flightId) {
        try {
            AirCompany airCompany = airCompanyDAO.getOne(airCompanyId);
            List<Flight> flights = flightDAO.findAll();
            List<Flight> airCompanyFlight = airCompany.getFlights();
            List<Flight> findFlight = flights.stream().filter(flight -> flight.getId() == flightId).collect(Collectors.toList());

            airCompanyFlight.addAll(findFlight);
            airCompanyDAO.save(airCompany);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @PostMapping("/change/aircompany/{airCompany1}/airplane/{airplaneCompany1}/to/aircompany/{airCompany2}/airplane/{airplaneCompany2}")
    public void moveAirCompany1AirplaneAirCompany1ToAirCompany2AirplaneAirCompany2
            (@PathVariable int airCompany1, @PathVariable int airplaneCompany1, @PathVariable int airCompany2, @PathVariable int airplaneCompany2) {
        try {
            AirCompany fromMoveCompany = airCompanyDAO.getOne(airCompany1);
            Airplane planeFromMoveCompany = airplaneDAO.getOne(airplaneCompany1);
            List<Airplane> airplanesFromMoveCompany = fromMoveCompany.getAirplanes();


            AirCompany toMoveCompany = airCompanyDAO.getOne(airCompany2);
            Airplane planeTOMoveCompany = airplaneDAO.getOne(airplaneCompany2);
            List<Airplane> airplanesToMoveCompany = toMoveCompany.getAirplanes();

            airplanesFromMoveCompany.add(planeTOMoveCompany);
            airCompanyDAO.save(fromMoveCompany);

            airplanesToMoveCompany.add(planeFromMoveCompany);
            airCompanyDAO.save(toMoveCompany);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @PostMapping("/{aircompanyId}/status/{status}")
    public List<Flight> findAirCompanyFlightsByStatus(@PathVariable int aircompanyId, @PathVariable String status) {
        AirCompany airCompany = airCompanyDAO.getOne(aircompanyId);
        List<Flight> airCompanyFlight = airCompany.getFlights();
        List<Flight> statusFlightList = airCompanyFlight.stream().filter(flight -> flight.getFlightStatus().toString().equals(status)).collect(Collectors.toList());
        return statusFlightList;
    }





}

