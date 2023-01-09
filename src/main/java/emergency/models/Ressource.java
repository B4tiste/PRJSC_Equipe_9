package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.modelDto.RessourceComposanteDto;
import emergency.modelDto.RessourceDto;
import emergency.modelDto.VehiculeDto;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "RESSOURCE")
public class Ressource implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "ID_TYPE")
    private TypeRessource type;

    @ManyToOne
    @JoinColumn(name = "ID_STATUT")
    private Statut statut;

    @OneToMany(mappedBy = "ressource", cascade = CascadeType.ALL)
    private List<RessourceComposante> ressourceComposantes;

    @OneToMany(mappedBy = "ressource", cascade = CascadeType.ALL)
    private List<Vehicule> vehicules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CENTRE")
    private Centre centre;

    @ManyToOne
    @JoinColumn(name = "ID_URGENCE")
    private Urgence urgence;



    public Ressource() {
    }

    public Ressource(String nom, TypeRessource type, Statut statut, List<RessourceComposante> ressourceComposantes, Centre centre) {
        this.nom = nom;
        this.type = type;
        this.statut = statut;
        this.ressourceComposantes = ressourceComposantes;
        this.centre = centre;
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

    public TypeRessource getType() {
        return type;
    }

    public void setType(TypeRessource type) {
        this.type = type;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public List<RessourceComposante> getRessourceComposantes() {
        return ressourceComposantes;
    }

    public void setRessourceComposantes(List<RessourceComposante> ressources) {
        this.ressourceComposantes = ressources;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public Urgence getUrgence() {
        return urgence;
    }

    public void setUrgence(Urgence urgence) {
        this.urgence = urgence;
    }

    @Override
    public String toString() {
        return "TypeRessource{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type=" + type +
                ", statut=" + statut +
                '}';
    }

    public List<Vehicule> getVehicules() {
        return vehicules;
    }

    public void setVehicules(List<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

    public RessourceDto toDto(Boolean onlyId)
    {
        RessourceDto dest = new RessourceDto();
        if(this.getCentre()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setCentreId(Long.valueOf(this.getCentre().getId()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setCentre(this.getCentre().toDto(Boolean.TRUE));
                    dest.setCentreId(Long.valueOf(this.getCentre().getId()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(this.getRessourceComposantes()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    List<Long> ids = new ArrayList<>();
                    for(RessourceComposante ressourceComposante:this.getRessourceComposantes())
                    {
                        ids.add(ressourceComposante.getId());
                    }
                    dest.setRessourceComposanteId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    List<RessourceComposante> ressourceComposantes = this.getRessourceComposantes();
                    List<RessourceComposanteDto> ressourceComposantesDto = new ArrayList<>();
                    for(RessourceComposante ressourceComposante:ressourceComposantes)
                    {
                        ressourceComposantesDto.add(ressourceComposante.toDto(onlyId));
                    }
                    dest.setRessourceComposante(ressourceComposantesDto);
                    List<Long> ids = new ArrayList<>();
                    for(RessourceComposante ressourceComposante:this.getRessourceComposantes())
                    {
                        ids.add(ressourceComposante.getId());
                    }
                    dest.setRessourceComposanteId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(this.getVehicules()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    List<Long> ids = new ArrayList<>();
                    for(Vehicule ressourceComposante:this.getVehicules())
                    {
                        ids.add(ressourceComposante.getId());
                    }
                    dest.setVehiculesId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    List<Vehicule> ressourceComposantes = this.getVehicules();
                    List<VehiculeDto> ressourceComposantesDto = new ArrayList<>();
                    for(Vehicule ressourceComposante:ressourceComposantes)
                    {
                        ressourceComposantesDto.add(ressourceComposante.toDto(onlyId));
                    }
                    dest.setVehicules(ressourceComposantesDto);
                    List<Long> ids = new ArrayList<>();
                    for(Vehicule ressourceComposante:this.getVehicules())
                    {
                        ids.add(ressourceComposante.getId());
                    }
                    dest.setVehiculesId(ids);
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
        if(this.getType()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setTypeRessourceId(Long.valueOf(this.getType().getValeur()));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setTypeRessource(this.getType().toDto(onlyId));
                    dest.setTypeRessourceId(Long.valueOf(this.getType().getValeur()));
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
        return dest;
    }

    public Ressource Save(ServiceDefinitions ref, Boolean cascade) {
        Ressource addr;
        try {
            addr = (Ressource)ref.getRessourceService().CreateOrUpdateOrGet(this);
            if (cascade == Boolean.TRUE) {

                List<RessourceComposante> ressourceComposantes = getRessourceComposantes();
                for (int c = 0; c < ressourceComposantes.size(); c++) {
                    RessourceComposante ressourceComposante = ressourceComposantes.get(c);
                    ressourceComposante.setRessource(addr);
                    RessourceComposante savedRessourceComposante = ressourceComposante.Save(ref, cascade);
                    if (savedRessourceComposante != null) {
                        ressourceComposantes.set(c, savedRessourceComposante);
                    }
                    setRessourceComposantes(ressourceComposantes);
                }
            }

            return addr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ressource ressource = (Ressource) o;
        return Objects.equals(nom, ressource.nom) && Objects.equals(type, ressource.type) && Objects.equals(statut, ressource.statut) && Objects.equals(ressourceComposantes, ressource.ressourceComposantes) && Objects.equals(centre, ressource.centre) && Objects.equals(urgence, ressource.urgence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, type, statut, ressourceComposantes, centre, urgence);
    }
}
