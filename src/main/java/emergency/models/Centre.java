package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

import emergency.interfacesDefinition.*;

@Entity
@Table(name = "CENTRE")
public class Centre implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "ID_ADRESSE")
    private Adresse adresse;

    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL)
    private List<Ressource> ressources;

    public Centre() {
    }

    public Centre(String nom, Boolean isAvailable, Double latitude, Double longitude, Adresse adresse) {
        this.nom = nom;
        this.isAvailable = isAvailable;
        this.latitude = latitude;
        this.longitude = longitude;
        this.adresse = adresse;
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

    public List<Ressource> getRessource() {
        return ressources;
    }

    public void setRessource(List<Ressource> ressources) {
        this.ressources = ressources;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }


    public Centre Save(ServiceDefinitions ref, Boolean cascade) {
        Centre addr;
        try {
            if (getAdresse() != null && cascade == Boolean.TRUE) {
                setAdresse(getAdresse().Save(ref, cascade));
            }
            addr = (Centre)ref.getCentreService().CreateOrUpdateOrGet(this);
            if (cascade == Boolean.TRUE) {

                List<Ressource> ressources = getRessource();
                for (int c = 0; c < ressources.size(); c++) {
                    Ressource ressource = ressources.get(c);
                    ressource.setCentre(addr);
                    Ressource savedRessource = ressource.Save(ref, cascade);
                    if (savedRessource != null) {
                        ressources.set(c, savedRessource);
                    }
                    setRessource(ressources);
                }
            }

            return addr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Centre centre = (Centre) o;
        return Objects.equals(nom, centre.nom) && Objects.equals(isAvailable, centre.isAvailable) && Objects.equals(latitude, centre.latitude) && Objects.equals(longitude, centre.longitude) && Objects.equals(ressources, centre.ressources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, isAvailable, latitude, longitude, ressources);
    }
}