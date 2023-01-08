package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.modelDto.TypeRessourceDto;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TYPE_RESSOURCE")
public class TypeRessource implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "VALEUR")
    private int valeur;

    @OneToMany(mappedBy = "type")
    private Set<Ressource> ressources;

    public TypeRessource() {
    }

    public TypeRessource(String type, int valeur) {
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String nom) {
        this.type = nom;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Set<Ressource> getRessources() {
        return ressources;
    }

    public void setRessources(Set<Ressource> ressources) {
        this.ressources = ressources;
    }

    public TypeRessourceDto toDto(Boolean onlyId)
    {
        TypeRessourceDto dest = new TypeRessourceDto();
        dest.setId(this.getId());
        try {
            dest.setNom(this.getType());
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

    public TypeRessource Save(ServiceDefinitions ref, Boolean cascade)
    {
        TypeRessource addr;
        try{
            addr = (TypeRessource) ref.getTypeRessourceService().CreateOrUpdateOrGet(this);
            return addr;
        }catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public String toString() {
        return "TypeRessource{" +
                "id=" + id +
                ", nom='" + type + '\'' +
                ", valeur=" + valeur +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeRessource that = (TypeRessource) o;
        return valeur == that.valeur && Objects.equals(type, that.type) && Objects.equals(ressources, that.ressources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, valeur, ressources);
    }
}