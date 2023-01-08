package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.interfacesDefinition.IBaseModel;
import emergency.modelDto.TypeUrgenceDto;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TYPE_URGENCE")
public class TypeUrgence implements IBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "VALEUR")
    private int valeur;

    @OneToMany(mappedBy = "type")
    private Set<Urgence> urgences;

    public TypeUrgence() {
    }

    public TypeUrgence(String nom, int valeur) {
        this.nom = nom;
        this.valeur = valeur;
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

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Set<Urgence> getUrgences() {
        return urgences;
    }

    public void setUrgences(Set<Urgence> urgences) {
        this.urgences = urgences;
    }

    public TypeUrgenceDto toDto(Boolean onlyId)
    {
        TypeUrgenceDto dest = new TypeUrgenceDto();
        dest.setId(this.getId());
        try {
            dest.setNom(this.getNom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setValeur(this.getValeur());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }

    public TypeUrgence Save(ServiceDefinitions ref, Boolean cascade)
    {
        TypeUrgence addr;
        try{
            addr = (TypeUrgence) ref.getTypeUrgenceService().CreateOrUpdateOrGet(this);
            return addr;
        }catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public String toString() {
        return "TypeUrgence{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", valeur=" + valeur +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeUrgence that = (TypeUrgence) o;
        return valeur == that.valeur && Objects.equals(nom, that.nom) && Objects.equals(urgences, that.urgences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, valeur, urgences);
    }
}