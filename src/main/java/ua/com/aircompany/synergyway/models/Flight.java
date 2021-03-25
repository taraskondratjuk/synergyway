package ua.com.aircompany.synergyway.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ua.com.aircompany.synergyway.settings.FlightStatus;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = {"airCompany","airplane"})


@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "flight_status")
    private FlightStatus flightStatus;

    @Column(name = "departure_country")
    private String departureCountry;

    @Column(name = "destination_country")
    private String destinationCountry;

    @Column(name = "distance")
    private double distance;

    @Column(name = "estimated_flight_time")
    private double estimatedFlightTime;

    @Column(name = "ended_at")
    private double endedAt;

    @Column(name = "delay_started_at")
    private double delayStartedAt;

    @Column(name = "created_at")
    private double createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="aircompany_id", insertable=false, updatable=false)
    @JsonIgnore
    private AirCompany airCompany;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    public Flight(String departureCountry, String destinationCountry, double distance, double estimatedFlightTime, double endedAt, double delayStartedAt, double createdAt) {
        this.flightStatus = FlightStatus.PENDING;
        this.departureCountry = departureCountry;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
        this.estimatedFlightTime = estimatedFlightTime;
        this.endedAt = endedAt;
        this.delayStartedAt = delayStartedAt;
        this.createdAt = createdAt;
    }
}
