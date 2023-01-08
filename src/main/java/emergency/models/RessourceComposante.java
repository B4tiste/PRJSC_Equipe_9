package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.modelDto.PersonneDto;
import emergency.modelDto.RessourceComposanteDto;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "RESSOURCECOMPOSANTE")
//@DiscriminatorColumn(name="type")
@Inheritance(strategy = InheritanceType.JOINED)
public class RessourceComposante implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "NOM")
    private String nom;


    @ManyToOne
    @JoinColumn(name = "ID_STATUT")
    private Statut statut;


    @Column(name = "IS_AVAILABLE")
    private boolean isAvailable;

    @OneToMany(mappedBy = "ressourceComposante", cascade = CascadeType.ALL)
    private List<Personne> personnes;

    @ManyToOne
    @JoinColumn(name = "ID_RESSOURCE")
    private Ressource ressource;

    @ManyToOne
    @JoinColumn(name = "ID_CENTRE")
    private Centre centre;




    public RessourceComposante() {
    }

    public RessourceComposante(String nom, boolean isAvailable) {
        this.nom = nom;
        this.isAvailable = isAvailable;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
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


    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }


    public RessourceComposanteDto toDto(Boolean onlyId)
    {
        RessourceComposanteDto dest = new RessourceComposanteDto();
        if(this.getPersonnes()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    List<Long> ids = new ArrayList<>();
                    for(Personne personne:this.getPersonnes())
                    {
                        ids.add(personne.getId());
                    }
                    dest.setPersonnesId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    List<Personne> personnes = this.getPersonnes();
                    List<PersonneDto> personnesDto = new ArrayList<>();
                    for(Personne personne:personnes)
                    {
                        personnesDto.add(personne.toDto(onlyId));
                    }
                    dest.setPersonnes(personnesDto);
                    List<Long> ids = new ArrayList<>();
                    for(Personne personne:this.getPersonnes())
                    {
                        ids.add(personne.getId());
                    }
                    dest.setPersonnesId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(this.getStatut()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setStatutId(Long.valueOf(this.getStatut().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setStatut(this.getStatut().toDto(onlyId));
                    dest.setStatutId(Long.valueOf(this.getStatut().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(this.getCentre()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setCentreId(this.getCentre().getId());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setCentre(this.getCentre().toDto(Boolean.TRUE));
                    dest.setCentreId(this.getCentre().getId());
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
            dest.setAvailable(this.isAvailable());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
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
                ", statut=" + statut +
                ", centre=" + centre +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RessourceComposante that = (RessourceComposante) o;
        return isAvailable == that.isAvailable && Objects.equals(nom, that.nom) && Objects.equals(personnes, that.personnes) && Objects.equals(ressource, that.ressource)  && Objects.equals(statut, that.statut) && Objects.equals(centre, that.centre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, isAvailable, personnes, ressource,  statut, centre);
    }
}