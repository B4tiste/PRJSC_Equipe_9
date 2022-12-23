package Models.SensorRelated;


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



    public CapteurType() {
    }

    public CapteurType(String type) {
        this.type = type;
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


}
