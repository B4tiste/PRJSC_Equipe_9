package emergency.modelDto;


import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class UrgenceDto extends GBaseDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @JsonProperty("dateCreation")
    private Date dateCreation;


    @JsonProperty("dateUpdate")
    private Date dateUpdate;

    @NotNull
    @JsonProperty("titre")
    private String titre;

    @JsonProperty("incident")
    private IncidentDto incident;

    @JsonProperty("incidentId")
    private Long incidentId;

    @JsonProperty("type")
    private TypeUrgenceDto type;

    @JsonProperty("typeId")
    private Long typeId;

    @JsonProperty("statut")
    private StatutDto statut;

    @JsonProperty("statutId")
    private Long statutId;

    @JsonProperty("ressources")
    private List<RessourceDto> ressources;

    @JsonProperty("ressourcesId")
    private List<Long> ressourcesId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setIncident(IncidentDto incident) {
        this.incident = incident;
    }

    public void setIncidentId(Long incidentId) {
        this.incidentId = incidentId;
    }

    public void setType(TypeUrgenceDto type) {
        this.type = type;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public void setStatut(StatutDto statut) {
        this.statut = statut;
    }

    public void setStatutId(Long statutId) {
        this.statutId = statutId;
    }

    public void setRessources(List<RessourceDto> ressources) {
        this.ressources = ressources;
    }

    public void setRessourcesId(List<Long> ressourcesId) {
        this.ressourcesId = ressourcesId;
    }

    public Long getId() {
        return id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public String getTitre() {
        return titre;
    }

    public IncidentDto getIncident() {
        return incident;
    }

    public TypeUrgenceDto getType() {
        return type;
    }

    public StatutDto getStatut() {
        return statut;
    }

    public List<RessourceDto> getRessources() { return ressources; }

    public Long getIncidentId() {
        return incidentId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public Long getStatutId() {
        return statutId;
    }

    public List<Long> getRessourcesId() {
        return ressourcesId;
    }

    public Urgence toModel()
    {
        Urgence model = new Urgence();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setDateCreation(this.getDateCreation());
        model.setDateUpdate(this.getDateUpdate());
        model.setTitre(this.getTitre());
        if(this.getIncident()!=null)
        {
            try
            {
                var incident = (Incident) this.getIncident().toModel();
                incident.setUrgence(model);
                model.setIncident(incident);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(this.getIncidentId()!=null)
        {
            try
            {
                var incident = (Incident)this.getServices().getIncidentService().getById(this.getIncidentId());
                incident.setUrgence(model);
                model.setIncident(incident);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        if(this.getType()!=null)
        {
            try {
                model.setType((TypeUrgence) this.getType().toModel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(this.getTypeId()!=null)
        {
            try {
                model.setType(ReferentielDefinitions.getTypeUrgence(this.getTypeId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        List<Ressource> data = new ArrayList<>();
        Boolean data_present = Boolean.FALSE;
        if(this.getRessources()!=null)
        {
            if(this.getRessources().size()>0)
            {
                data_present = Boolean.TRUE;
                for(var ressource : this.getRessources())
                {
                    try {
                        var dest_ressource = (Ressource)ressource.toModel();
                        dest_ressource.setUrgence(model);
                        data.add(dest_ressource);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(this.getRessourcesId()!=null)
        {
            if(this.getRessourcesId().size()>0)
            {
                data_present = Boolean.TRUE;

                try {
                    var serv = this.getServices().getRessourceService();
                    var val = (List<Ressource>)(List<?>)serv.GetThem(this.getRessourcesId());
                    for(var d : val)
                    {
                        d.setUrgence(model);
                    }
                    data = val;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        model.setRessources(data);
        return model;
    }
}