package Models;


import jakarta.persistence.*;
import InterfacesDefinition.*;
import java.sql.Date;

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
}
