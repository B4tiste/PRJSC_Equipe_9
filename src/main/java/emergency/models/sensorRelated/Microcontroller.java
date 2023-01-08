package emergency.models.sensorRelated;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.interfacesDefinition.IBaseModel;
import emergency.modelDto.sensorRelated.CapteurDto;
import emergency.modelDto.sensorRelated.MicrocontrollerDto;
import jakarta.persistence.*;

import java.util.ArrayList;
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
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    @Column(name = "NOM")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "ID_ETAT")
    private Etat etat;

    @OneToMany(mappedBy = "microcontroller", cascade = CascadeType.ALL)
    private List<Capteur> capteurs;

    public Microcontroller() {
    }

    public Microcontroller(Double latitude, Double longitude, String nom, Etat etat, List<Capteur> capteurs) {
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
            mc = (Microcontroller)ref.getMicrocontrollerService().CreateOrUpdateOrGet(this);

            if (cascade == Boolean.TRUE) {

                List<Capteur> capteurs = getCapteurs();
                for (int c = 0; c < capteurs.size(); c++) {
                    Capteur capteur = capteurs.get(c);
                    capteur.setMicrocontroller(mc);
                    Capteur savedCapteur = capteur.Save(ref, cascade);
                    if (savedCapteur != null) {
                        capteurs.set(c, savedCapteur);
                    }
                    setCapteurs(capteurs);
                }
                mc.setCapteurs(capteurs);
            }
            return mc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MicrocontrollerDto toDto(Boolean onlyId)
    {
        MicrocontrollerDto dest = new MicrocontrollerDto();
        if(this.getCapteurs()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    List<Long> ids = new ArrayList<>();
                    for(Capteur capteur:this.getCapteurs())
                    {
                        ids.add(capteur.getId());
                    }
                    dest.setCapteursId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    List<Capteur> capteurs = this.getCapteurs();
                    List<CapteurDto> capteursDto = new ArrayList<>();
                    for(Capteur capteur:capteurs)
                    {
                        capteursDto.add(capteur.toDto(onlyId));
                    }
                    dest.setCapteurs(capteursDto);
                    List<Long> ids = new ArrayList<>();
                    for(Capteur capteur:this.getCapteurs())
                    {
                        ids.add(capteur.getId());
                    }
                    dest.setCapteursId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(this.getEtat()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setEtatId(Long.valueOf(this.getEtat().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setEtat(this.getEtat().toDto(onlyId));
                    dest.setEtatId(Long.valueOf(this.getEtat().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        dest.setId(this.getId());
        try {
            dest.setLatitude(this.getLatitude());
            dest.setLongitude(this.getLongitude());
            dest.setNom(this.getNom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
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
