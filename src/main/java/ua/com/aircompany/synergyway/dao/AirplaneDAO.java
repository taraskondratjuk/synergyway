package ua.com.aircompany.synergyway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.aircompany.synergyway.models.Airplane;

public interface AirplaneDAO extends JpaRepository<Airplane, Integer> {
}
