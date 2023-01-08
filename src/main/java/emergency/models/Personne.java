package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.modelDto.PersonneDto;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;

import java.util.Objects;

@Entity
@Table(name = "PERSONNE")
public class Personne implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "PRENOM")
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "ID_ROLE")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "ID_RESSOURCECOMPOSANTE")
    private RessourceComposante ressourceComposante;


    public Personne() {
    }

    public Personne(String nom, String prenom, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public RessourceComposante getRessourceComposante() {
        return this.ressourceComposante;
    }

    public void setRessourceComposante(RessourceComposante ressourceComposante) {
        this.ressourceComposante = ressourceComposante;
    }

    public PersonneDto toDto(Boolean onlyId)
    {
        PersonneDto dest = new PersonneDto();
        if(this.getRole()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setRoleId(Long.valueOf(this.getRole().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setRole(this.getRole().toDto(onlyId));
                    dest.setRoleId(Long.valueOf(this.getRole().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        dest.setId(this.getId());
        try {
            dest.setNom(this.getNom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setPrenom(this.getPrenom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }

    public Personne Save(ServiceDefinitions ref, Boolean cascade)
    {
        Personne addr;
        try{
            addr = (Personne) ref.getPersonneService().CreateOrUpdateOrGet(this);
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
        Personne personne = (Personne) o;
        return Objects.equals(nom, personne.nom) && Objects.equals(prenom, personne.prenom) && Objects.equals(role, personne.role) && Objects.equals(ressourceComposante, personne.ressourceComposante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, role, ressourceComposante);
    }
}