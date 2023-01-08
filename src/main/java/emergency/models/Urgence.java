package emergency.models;


import emergency.baseReferentiel.ServiceDefinitions;
import emergency.modelDto.RessourceDto;
import emergency.modelDto.UrgenceDto;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "URGENCE")
public class Urgence implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE_CREATION")
    private Date dateCreation;

    @Column(name = "DATE_UPDATE")
    private Date dateUpdate;

    @Column(name = "TITRE")
    private String titre;


    /*@OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "urgence")
    @JoinColumn(name = "ID_INCIDENT", unique = true, nullable = false)
    @NotNull*/
    @OneToOne(mappedBy = "urgence", cascade = CascadeType.ALL)
    private Incident incident;

    @ManyToOne
    @JoinColumn(name = "ID_TYPE")
    private TypeUrgence type;

    @ManyToOne
    @JoinColumn(name = "ID_STATUT")
    private Statut statut;


    @OneToMany(mappedBy = "urgence", cascade = CascadeType.ALL)
    private List<Ressource> ressources;

    public Urgence() {
    }

    public Urgence(Date dateCreation, Date dateUpdate, String titre, Incident incident, TypeUrgence type, Statut statut) {
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.titre = titre;
        this.incident = incident;
        this.type = type;
        this.statut = statut;
    }


    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public TypeUrgence getType() {
        return type;
    }

    public void setType(TypeUrgence type) {
        this.type = type;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public List<Ressource> getRessources() {
        return ressources;
    }

    public void setRessources(List<Ressource> ressources) {
        this.ressources = ressources;
    }


    public UrgenceDto toDto(Boolean onlyId)
    {
        UrgenceDto dest = new UrgenceDto();
        if(this.getIncident()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setIncidentId(this.getIncident().getId());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setIncident(this.getIncident().toDto(onlyId));
                    dest.setIncidentId(this.getIncident().getId());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(this.getRessources()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    List<Long> ids = new ArrayList<>();
                    for(Ressource ressource:this.getRessources())
                    {
                        ids.add(ressource.getId());
                    }
                    dest.setRessourcesId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    List<Ressource> ressources = this.getRessources();
                    List<RessourceDto> ressourcesDto = new ArrayList<>();
                    for(Ressource ressource:ressources)
                    {
                        ressourcesDto.add(ressource.toDto(onlyId));
                    }
                    dest.setRessources(ressourcesDto);
                    List<Long> ids = new ArrayList<>();
                    for(Ressource ressource:this.getRessources())
                    {
                        ids.add(ressource.getId());
                    }
                    dest.setRessourcesId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(this.getStatut()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setStatutId(Long.valueOf(this.getStatut().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setStatut(this.getStatut().toDto(onlyId));
                    dest.setStatutId(Long.valueOf(this.getStatut().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(this.getType()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setTypeId(Long.valueOf(this.getType().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setType(this.getType().toDto(onlyId));
                    dest.setTypeId(Long.valueOf(this.getType().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        dest.setId(this.getId());
        try {
            dest.setDateCreation(this.getDateCreation());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setDateUpdate(this.getDateUpdate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setTitre(this.getTitre());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }

    public Urgence Save(ServiceDefinitions ref, Boolean cascade) {
        Urgence addr;
        try {

            if (cascade == Boolean.TRUE) {
                if (getIncident() != null) {
                    setIncident(getIncident().Save(ref, cascade));
                }
                List<Ressource> ressources = getRessources();
                for (int c = 0; c < ressources.size(); c++) {
                    Ressource ressource = ressources.get(c);
                    Ressource savedRessource = ressource.Save(ref, cascade);
                    if (savedRessource != null) {
                        ressources.set(c, savedRessource);
                    }
                    setRessources(ressources);
                }
            }
            addr = (Urgence)ref.getUrgenceService().CreateOrUpdateOrGet(this);
            return addr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Urgence{" +
                "id=" + id +
                ", dateCreation=" + dateCreation +
                ", dateUpdate=" + dateUpdate +
                ", titre='" + titre + '\'' +
                ", incident=" + incident +
                ", type=" + type +
                ", statut=" + statut +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Urgence urgence = (Urgence) o;
        return Objects.equals(dateCreation, urgence.dateCreation) && Objects.equals(dateUpdate, urgence.dateUpdate) && Objects.equals(titre, urgence.titre) && Objects.equals(incident, urgence.incident) && Objects.equals(type, urgence.type) && Objects.equals(statut, urgence.statut) && Objects.equals(ressources, urgence.ressources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateCreation, dateUpdate, titre, incident, type, statut, ressources);
    }
}
