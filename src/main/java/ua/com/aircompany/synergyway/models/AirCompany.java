package ua.com.aircompany.synergyway.models;

import lombok.*;

import javax.persistence.*;
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

    @Column(name = "name")
    private String name;

    @Column(name = "company_type")
    private String type;

    @Column(name = "founded_at")
    private int foundedAt;

    @OneToMany
    @JoinColumn(name = "aircompany_id")
    private List<Airplane> airplanes = new LinkedList<>();


    @OneToMany
    @JoinColumn(name = "aircompany_id")
    private List<Flight> flights = new LinkedList<>();

    public AirCompany(String name, String type, int foundedAt) {
        this.name = name;
        this.type = type;
        this.foundedAt = foundedAt;
    }
}
