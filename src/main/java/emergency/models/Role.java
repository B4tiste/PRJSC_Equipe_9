package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.modelDto.RoleDto;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ROLE")
public class Role implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "VALEUR")
    private Long valeur;


    /*@OneToMany(mappedBy = "role")
    private Set<Personne> personnes;*/

    public Role() {
    }

    public Role(String nom, Long valeur) {
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

    public Long getValeur() {
        return valeur;
    }

    public void setValeur(Long valeur) {
        this.valeur = valeur;
    }

    /*public Set<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(Set<Personne> personnes) {
        this.personnes = personnes;
    }*/

    public RoleDto toDto(Boolean onlyId)
    {
        RoleDto dest = new RoleDto();

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

    public Role Save(ServiceDefinitions ref, Boolean cascade)
    {
        Role addr;
        try{
            addr = (Role)ref.getRoleService().CreateOrUpdateOrGet(this);
            return addr;
        }catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(nom, role.nom) && Objects.equals(valeur, role.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, valeur);
    }
}