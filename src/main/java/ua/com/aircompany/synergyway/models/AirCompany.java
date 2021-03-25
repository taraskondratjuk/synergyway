package ua.com.aircompany.synergyway.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = {"airplanes", "flights"})

@Entity
@Table(name = "aircompany")
public class AirCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "company_type")
    private String type;

    @Column(name = "founded_at")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date foundedAt;

    @OneToMany
    @JoinColumn(name = "aircompany_id")
    private List<Airplane> airplanes = new LinkedList<>();


    @OneToMany
    @JoinColumn(name = "aircompany_id")
    private List<Flight> flights = new LinkedList<>();

    public AirCompany(String name, String type, Date foundedAt) {
        this.name = name;
        this.type = type;
        this.foundedAt = foundedAt;
    }


}
