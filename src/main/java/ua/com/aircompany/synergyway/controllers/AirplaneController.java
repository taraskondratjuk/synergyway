package ua.com.aircompany.synergyway.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.com.aircompany.synergyway.dao.AirplaneDAO;
import ua.com.aircompany.synergyway.models.Airplane;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/airplane")
public class AirplaneController {
    private AirplaneDAO airplaneDAO;

    @PostMapping("/save")
    public void saveAirplane(@RequestBody Airplane airplane) {
        airplaneDAO.save(airplane);
    }

    @GetMapping("/getAll")
    public List<Airplane> getAllPlains() {
        return airplaneDAO.findAll();
    }

    @PostMapping("update/{id}")
    public void updateAirplaneByID(@RequestBody Airplane airplane, @PathVariable int id) {
        Airplane updatePlain = airplaneDAO.getOne(id);
        updatePlain.setName(airplane.getName());
        updatePlain.setFactorySerialNumber(airplane.getFactorySerialNumber());
        updatePlain.setNumberOfFlights(airplane.getNumberOfFlights());
        updatePlain.setFlightsDistance(airplane.getFlightsDistance());
        updatePlain.setFuelCapacity(airplane.getFuelCapacity());
        updatePlain.setType(airplane.getType());
        updatePlain.setCreatedAt(airplane.getCreatedAt());
        airplaneDAO.save(updatePlain);
    }


    @GetMapping("/delete/{id}")
    public void deletePlaneByID(@PathVariable int id) {
        Airplane deletePlane = airplaneDAO.getOne(id);
        airplaneDAO.delete(deletePlane);
    }
}
