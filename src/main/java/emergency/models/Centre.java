package emergency.models;

import jakarta.persistence.*;

import java.util.List;
import emergency.interfacesDefinition.*;

@Entity
@Table(name = "CENTRE")
public class Centre implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "IS_AVAILABLE")
    private Boolean isAvailable;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    @OneToMany(mappedBy = "centre")
    private List<RessourceComposante> ressourceComposantes;

    public Centre() {
    }

    public Centre(String nom, Boolean isAvailable, Double latitude, Double longitude) {
        this.nom = nom;
        this.isAvailable = isAvailable;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<RessourceComposante> getRessourceComposantes() {
        return ressourceComposantes;
    }

    public void setRessourceComposantes(List<RessourceComposante> ressourceComposantes) {
        this.ressourceComposantes = ressourceComposantes;
    }
}