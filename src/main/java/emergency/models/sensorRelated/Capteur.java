package emergency.models.sensorRelated;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.interfacesDefinition.*;

import emergency.modelDto.sensorRelated.CapteurDonneesDto;
import emergency.modelDto.sensorRelated.CapteurDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "CAPTEUR")
public class Capteur implements IBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IDENTIFIER")
    private String identifier;

    @Column(name = "RADIUS")
    private Double radius;

    @ManyToOne
    @JoinColumn(name = "ID_MICROCONTROLLER")
    private Microcontroller microcontroller;

    @OneToMany(mappedBy = "capteur", cascade = CascadeType.ALL)
    private List<CapteurDonnees> capteurDonnees;

    @ManyToOne
    @JoinColumn(name = "ID_CAPTEUR_TYPE")
    private CapteurType capteurType;

    public Capteur() {
    }

    public Capteur(String identifier, Microcontroller microcontroller, List<CapteurDonnees> capteurDonnees, CapteurType capteurType) {
        this.identifier = identifier;
        this.microcontroller = microcontroller;
        this.capteurDonnees = capteurDonnees;
        this.capteurType = capteurType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Microcontroller getMicrocontroller() {
        return microcontroller;
    }

    public void setMicrocontroller(Microcontroller microcontroller) {
        this.microcontroller = microcontroller;
    }

    public List<CapteurDonnees> getCapteurDonnees() {
        return capteurDonnees;
    }

    public void setCapteurDonnees(List<CapteurDonnees> capteurDonnees) {
        this.capteurDonnees = capteurDonnees;
    }

    public CapteurType getCapteurType() {
        return capteurType;
    }

    public void setCapteurType(CapteurType capteurType) {
        this.capteurType = capteurType;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }


    public Capteur Save(ServiceDefinitions ref, Boolean cascade) {
        Capteur addr;
        try {
            addr = (Capteur)ref.getCapteurService().CreateOrUpdateOrGet(this);
            if (cascade == Boolean.TRUE) {

                List<CapteurDonnees> capteurDonnees = getCapteurDonnees();
                for (int c = 0; c < capteurDonnees.size(); c++) {
                    CapteurDonnees capteurDonnee = capteurDonnees.get(c);
                    capteurDonnee.setCapteur(addr);
                    CapteurDonnees savedCapteurDonnee = capteurDonnee.Save(ref, cascade);
                    if (savedCapteurDonnee != null) {
                        capteurDonnees.set(c, savedCapteurDonnee);
                    }
                    setCapteurDonnees(capteurDonnees);
                }
                addr.setCapteurDonnees(capteurDonnees);
            }

            return addr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public CapteurDto toDto(Boolean onlyId)
    {
        CapteurDto dest = new CapteurDto();
        if(this.getCapteurType()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setCapteurTypeId(Long.valueOf(this.getCapteurType().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setCapteurType(this.getCapteurType().toDto(onlyId));
                    dest.setCapteurTypeId(Long.valueOf(this.getCapteurType().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(this.getMicrocontroller()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setMicrocontrolleurId(Long.valueOf(this.getMicrocontroller().getId()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setMicrocontrolleur(this.getMicrocontroller().toDto(Boolean.TRUE));
                    dest.setMicrocontrolleurId(Long.valueOf(this.getMicrocontroller().getId()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(this.getCapteurDonnees()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    List<Long> ids = new ArrayList<>();
                    for(CapteurDonnees capteurDonnee:this.getCapteurDonnees())
                    {
                        ids.add(capteurDonnee.getId());
                    }
                    dest.setCapteurDonneesId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    List<CapteurDonnees> capteurDonnees = this.getCapteurDonnees();
                    List<CapteurDonneesDto> capteurDonneesDto = new ArrayList<>();
                    for(CapteurDonnees capteurDonnee:capteurDonnees)
                    {
                        capteurDonneesDto.add(capteurDonnee.toDto(onlyId));
                    }
                    dest.setCapteurDonnees(capteurDonneesDto);
                    List<Long> ids = new ArrayList<>();
                    for(CapteurDonnees capteurDonnee:this.getCapteurDonnees())
                    {
                        ids.add(capteurDonnee.getId());
                    }
                    dest.setCapteurDonneesId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        dest.setId(this.getId());
        try {
            dest.setIdentifier(this.getIdentifier());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setRadius(this.getRadius());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capteur capteur = (Capteur) o;
        return
                Objects.equals(identifier, capteur.identifier) &&
                Objects.equals(microcontroller, capteur.microcontroller) &&
                Objects.equals(capteurDonnees, capteur.capteurDonnees) &&
                Objects.equals(radius, capteur.radius) &&
                Objects.equals(capteurType, capteur.capteurType);
    }
}
