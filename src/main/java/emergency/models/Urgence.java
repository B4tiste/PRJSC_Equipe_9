package emergency.models;


import emergency.baseReferentiel.ServiceDefinitions;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;
import java.sql.Date;
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

    @ManyToOne
    @JoinColumn(name = "ID_INCIDENT")
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
