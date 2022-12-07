package Models;

import jakarta.persistence.*;
import InterfacesDefinition.*;

@Entity
@Table(name = "CAPTEUR_ETAT")
public class CapteurEtat implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALUE")
    private String value;

    public CapteurEtat() {
    }

    public CapteurEtat(String value) {
        this.value = value;
    }
    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}