package Models;

import jakarta.persistence.*;
import InterfacesDefinition.*;

@Entity
@Table(name = "ADRESSE")
public class Adresse implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RUE_ADRESSE")
    private String rueAdresse;

    @Column(name = "VILLE_ADRESSE")
    private String villeAdresse;

    @Column(name = "ETAT_ADRESSE")
    private String etatAdresse;

    @Column(name = "CODE_POSTAL_ADRESSE")
    private int codePostalAdresse;

    @Column(name = "PAYS_ADRESSE")
    private String paysAdresse;

    public Adresse() {
    }

    public Adresse(String rueAdresse, String villeAdresse, String etatAdresse, int codePostalAdresse, String paysAdresse) {
        this.rueAdresse = rueAdresse;
        this.villeAdresse = villeAdresse;
        this.etatAdresse = etatAdresse;
        this.codePostalAdresse = codePostalAdresse;
        this.paysAdresse = paysAdresse;
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
        return rueAdresse;
    }

    public void setRueAdresse(String rueAdresse) {
        this.rueAdresse = rueAdresse;
    }

    public String getVilleAdresse() {
        return villeAdresse;
    }

    public void setVilleAdresse(String villeAdresse) {
        this.villeAdresse = villeAdresse;
    }

    public String getEtatAdresse() {
        return etatAdresse;
    }

    public void setEtatAdresse(String etatAdresse) {
        this.etatAdresse = etatAdresse;
    }

    public int getCodePostalAdresse() {
        return codePostalAdresse;
    }

    public void setCodePostalAdresse(int codePostalAdresse) {
        this.codePostalAdresse = codePostalAdresse;
    }

    public String getPaysAdresse() {
        return paysAdresse;
    }

    public void setPaysAdresse(String paysAdresse) {
        this.paysAdresse = paysAdresse;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", rueAdresse='" + rueAdresse + '\'' +
                ", villeAdresse='" + villeAdresse + '\'' +
                ", etatAdresse='" + etatAdresse + '\'' +
                ", codePostalAdresse=" + codePostalAdresse +
                ", paysAdresse='" + paysAdresse + '\'' +
                '}';
    }
}
