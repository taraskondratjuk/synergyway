package ua.com.aircompany.synergyway.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.aircompany.synergyway.dao.AirCompanyDAO;
import ua.com.aircompany.synergyway.models.AirCompany;


@AllArgsConstructor
@RestController
@RequestMapping("/aircompany")
public class AirCompanyControl {

    private AirCompanyDAO airCompanyDAO;

    @PostMapping("/save")
    public void saveCompany(@RequestBody AirCompany airCompany) {
        if (airCompany != null) {
            airCompanyDAO.save(airCompany);
        }
    }
}
