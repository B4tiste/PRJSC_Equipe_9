package Models.SensorRelated;

import Models.SensorRelated.Microcontroller;
import jakarta.persistence.*;
import InterfacesDefinition.*;

import java.util.List;


@Entity
@Table(name = "CAPTEUR_TYPE")
public class CapteurType implements IBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE")
    private String type;

    @OneToMany(mappedBy = "capteurType")
    private List<Capteur> capteurs;

    public CapteurType() {
    }

    public CapteurType(String type, List<Capteur> capteurs) {
        this.type = type;
        this.capteurs = capteurs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Capteur> getCapteurs() {
        return capteurs;
    }

    public void setCapteurs(List<Capteur> capteurs) {
        this.capteurs = capteurs;
    }
}
