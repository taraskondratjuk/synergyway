package ua.com.aircompany.synergyway.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"airCompany","flight"})


@Entity
@Table(name = "airplane")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(name = "factory_serial_number")
    private int factorySerialNumber;

    @Column(name = "number_of_flights")
    private int numberOfFlights;

    @Column(name = "flight_distance")
    private double flightsDistance;

    @Column(name = "fuel_capacity")
    private double fuelCapacity;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date createdAt;

    @OneToOne()
    @JsonIgnore
    private Flight flight;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="aircompany_id", insertable=false, updatable=false)
    @JsonIgnore
    private AirCompany airCompany;


    public Airplane(String name, int factorySerialNumber, int numberOfFlights, double flightsDistance, double fuelCapacity, String type, Date createdAt) {
        this.name = name;
        this.factorySerialNumber = factorySerialNumber;
        this.numberOfFlights = numberOfFlights;
        this.flightsDistance = flightsDistance;
        this.fuelCapacity = fuelCapacity;
        this.type = type;
        this.createdAt = createdAt;
    }
}

