package emergency.models.sensorRelated;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.interfacesDefinition.IBaseModel;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "MICROCONTROLLER")
public class Microcontroller implements IBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LATITUDE")
    private Float latitude;

    @Column(name = "LONGITUDE")
    private Float longitude;

    @Column(name = "NOM")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "ID_ETAT")
    private Etat etat;

    @OneToMany(mappedBy = "microcontroller", cascade = CascadeType.ALL)
    private List<Capteur> capteurs;

    public Microcontroller() {
    }

    public Microcontroller(Float latitude, Float longitude, String nom, Etat etat, List<Capteur> capteurs) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.etat = etat;
        this.capteurs = capteurs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public List<Capteur> getCapteurs() {
        return capteurs;
    }

    public void setCapteurs(List<Capteur> capteurs) {
        this.capteurs = capteurs;
    }

    public Microcontroller Save(ServiceDefinitions ref, Boolean cascade) {
        Microcontroller mc;
        try {

            if (cascade == Boolean.TRUE) {

                List<Capteur> capteurs = getCapteurs();
                for (int c = 0; c < capteurs.size(); c++) {
                    Capteur capteur = capteurs.get(c);
                    Capteur savedCapteur = capteur.Save(ref, cascade);
                    if (savedCapteur != null) {
                        capteurs.set(c, savedCapteur);
                    }
                    setCapteurs(capteurs);
                }
            }
            mc = (Microcontroller)ref.getMicrocontrollerService().CreateOrUpdateOrGet(this);
            return mc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Microcontroller that = (Microcontroller) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(etat, that.etat) &&
                Objects.equals(capteurs, that.capteurs);
    }
}
