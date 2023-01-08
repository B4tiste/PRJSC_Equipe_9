package emergency.modelDto;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.Centre;
import emergency.models.Personne;
import emergency.models.RessourceComposante;
import com.fasterxml.jackson.annotation.JsonProperty;
import emergency.models.Statut;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class RessourceComposanteDto extends GBaseDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("nom")
    private String nom;

    @JsonProperty("statut")
    private StatutDto statut;


    @JsonProperty("centre")
    private CentreDto centre;


    @JsonProperty("centreId")
    private Long centreId;

    @JsonProperty("statutId")
    private Long statutId;


    @NotNull
    @JsonProperty("isAvailable")
    private boolean isAvailable;


    @JsonProperty("personnes")
    private List<PersonneDto> personnes;

    @JsonProperty("personnesId")
    private List<Long> personnesId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setPersonnes(List<PersonneDto> personnes) {
        this.personnes = personnes;
    }

    public void setPersonnesId(List<Long> personnesId) {
        this.personnesId = personnesId;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public List<PersonneDto> getPersonnes() {
        return personnes;
    }

    public List<Long> getPersonnesId() {
        return personnesId;
    }


    public StatutDto getStatut() {
        return statut;
    }

    public void setStatut(StatutDto statut) {
        this.statut = statut;
    }

    public Long getStatutId() {
        return statutId;
    }

    public void setStatutId(Long statutId) {
        this.statutId = statutId;
    }

    public CentreDto getCentre() {
        return centre;
    }

    public void setCentre(CentreDto centre) {
        this.centre = centre;
    }

    public Long getCentreId() {
        return centreId;
    }

    public void setCentreId(Long centreId) {
        this.centreId = centreId;
    }


    public RessourceComposante toModel()
    {
        RessourceComposante model = new RessourceComposante();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        try {
            model.setNom(this.getNom());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            model.setAvailable(this.isAvailable());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(this.getStatut()!=null)
        {
            try {
                model.setStatut((Statut) this.getStatut().toModel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(this.getStatutId()!=null)
        {
            try {
                model.setStatut(ReferentielDefinitions.getStatut(this.getStatutId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if(this.getCentre()!=null)
        {
            try {
                model.setCentre((Centre) this.getCentre().toModel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(this.getCentreId()!=null)
        {
            try {
                var serv = this.getServices().getCentreService();
                model.setCentre((Centre)serv.getById(this.getCentreId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<Personne> data = new ArrayList<>();
        Boolean data_present = Boolean.FALSE;
        if(this.getPersonnes()!=null)
        {
            if(this.getPersonnes().size()>0)
            {
                data_present = Boolean.TRUE;
                for(var personne : this.getPersonnes())
                {
                    try {
                        var dest_personne = (Personne)personne.toModel();
                        dest_personne.setRessourceComposante(model);
                        data.add(dest_personne);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(this.getPersonnesId()!=null)
        {
            if(this.getPersonnesId().size()>0)
            {
                data_present = Boolean.TRUE;

                try {
                    var serv = this.getServices().getPersonneService();
                    var val = (List<Personne>)(List<?>)serv.GetThem(this.getPersonnesId());
                    data = val;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        model.setPersonnes(data);
        return model;
    }
}