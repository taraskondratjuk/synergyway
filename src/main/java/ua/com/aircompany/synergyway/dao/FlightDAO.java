package ua.com.aircompany.synergyway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.aircompany.synergyway.models.Flight;

public interface FlightDAO extends JpaRepository<Flight,Integer> {
}
