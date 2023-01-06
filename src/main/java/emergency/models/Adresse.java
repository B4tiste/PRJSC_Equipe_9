package emergency.models;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.baseReferentiel.ServiceDefinitions;
import emergency.models.sensorRelated.Capteur;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;

import java.util.Objects;

/* IMPORTANT : Model MUST contain an overwritten equals method for update to work (sinon pas de compare+update)*/
@Entity
@Table(name = "ADRESSE")
public class Adresse implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RUE")
    private String rue;

    @Column(name = "VILLE")
    private String ville;

    @Column(name = "ETAT")
    private String etat;

    @Column(name = "CODE_POSTAL")
    private String codePostal;

    @Column(name = "PAYS")
    private String pays;


    public Adresse() {
    }

    public Adresse(String rueAdresse, String villeAdresse, String etatAdresse, String codePostal, String paysAdresse) {
        this.rue = rueAdresse;
        this.ville = villeAdresse;
        this.etat = etatAdresse;
        this.codePostal = codePostal;
        this.pays = paysAdresse;
    }
    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Adresse Save(ServiceDefinitions ref, Boolean cascade)
    {
        Adresse addr;
        try{
            addr = (Adresse)ref.getAdresseService().CreateOrUpdateOrGet(this);
            return addr;
        }catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", rue='" + rue + '\'' +
                ", ville='" + ville + '\'' +
                ", etat='" + etat + '\'' +
                ", codePostal=" + codePostal +
                ", pays='" + pays + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return Objects.equals(rue, adresse.rue) && Objects.equals(ville, adresse.ville)
                && Objects.equals(etat, adresse.etat) && Objects.equals(codePostal, adresse.codePostal)
                && Objects.equals(pays, adresse.pays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rue, ville, etat, codePostal, pays);
    }
}
