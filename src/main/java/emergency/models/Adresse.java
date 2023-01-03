package emergency.models;

import jakarta.persistence.*;
import emergency.interfacesDefinition.*;

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
    private int codePostal;

    @Column(name = "PAYS")
    private String pays;

    public Adresse() {
    }

    public Adresse(String rueAdresse, String villeAdresse, String etatAdresse, int codePostal, String paysAdresse) {
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

    public String getRueAdresse() {
        return rue;
    }

    public void setRueAdresse(String rueAdresse) {
        this.rue = rueAdresse;
    }

    public String getVilleAdresse() {
        return ville;
    }

    public void setVilleAdresse(String villeAdresse) {
        this.ville = villeAdresse;
    }

    public String getEtatAdresse() {
        return etat;
    }

    public void setEtatAdresse(String etatAdresse) {
        this.etat = etatAdresse;
    }

    public int getCodePostalAdresse() {
        return codePostal;
    }

    public void setCodePostalAdresse(int codePostalAdresse) {
        this.codePostal = codePostalAdresse;
    }

    public String getPaysAdresse() {
        return pays;
    }

    public void setPaysAdresse(String paysAdresse) {
        this.pays = paysAdresse;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", rueAdresse='" + rue + '\'' +
                ", villeAdresse='" + ville + '\'' +
                ", etatAdresse='" + etat + '\'' +
                ", codePostalAdresse=" + codePostal +
                ", paysAdresse='" + pays + '\'' +
                '}';
    }
}
