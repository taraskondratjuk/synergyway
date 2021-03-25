package ua.com.aircompany.synergyway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.aircompany.synergyway.models.AirCompany;

public interface AirCompanyDAO extends JpaRepository<AirCompany, Integer> {
}
