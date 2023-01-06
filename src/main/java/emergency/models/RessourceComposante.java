package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "RESSOURCECOMPOSANTE")
@Inheritance(strategy = InheritanceType.JOINED)
public class RessourceComposante implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "IS_AVAILABLE")
    private boolean isAvailable;

    @OneToMany(mappedBy = "ressourceComposante", cascade = CascadeType.ALL)
    private List<Personne> personnes;

    @ManyToOne
    @JoinColumn(name = "ID_RESSOURCE")
    private Ressource ressource;




    public RessourceComposante() {
    }

    public RessourceComposante(String nom, boolean isAvailable) {
        this.nom = nom;
        this.isAvailable = isAvailable;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Ressource getRessource() {
        return this.ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }


    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }

    public RessourceComposante Save(ServiceDefinitions ref, Boolean cascade)
    {
        RessourceComposante addr;
        try{
            addr = (RessourceComposante)ref.getRessourceComposanteService().CreateOrUpdateOrGet(this);
            if(cascade == Boolean.TRUE)
            {
                var personnes = getPersonnes();
                for(int c = 0;c<personnes.size();c++)
                {
                    var personne = personnes.get(c);
                    personne.setRessourceComposante(addr);
                    var pers = personne.Save(ref, cascade);
                    if(pers != null)
                    {
                        personnes.set(c, pers);
                    }
                    setPersonnes(personnes);

                }
            }

            return addr;
        } catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public String toString() {
        return "RessourceComposante{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RessourceComposante that = (RessourceComposante) o;
        return isAvailable == that.isAvailable && Objects.equals(nom, that.nom) && Objects.equals(personnes, that.personnes) && Objects.equals(ressource, that.ressource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, isAvailable, personnes, ressource);
    }
}