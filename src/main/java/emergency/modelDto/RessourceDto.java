package emergency.modelDto;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RessourceDto extends GBaseDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("nom")
    private String nom;


    @JsonProperty("typeRessource")
    private TypeRessourceDto typeRessource;


    @JsonProperty("typeRessourceId")
    private Long typeRessourceId;


    @JsonProperty("statut")
    private StatutDto statut;

    @JsonProperty("statutId")
    private Long statutId;

    @JsonProperty("centre")
    private CentreDto centre;

    @JsonProperty("centreId")
    private Long centreId;

    @JsonProperty("ressourceComposante")
    private List<RessourceComposanteDto> ressourceComposante;

    @JsonProperty("ressourceComposanteId")
    private List<Long> ressourceComposanteId;

    @JsonProperty("vehicules")
    private List<VehiculeDto> vehicules;

    @JsonProperty("vehiculesId")
    private List<Long> vehiculesId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTypeRessource(TypeRessourceDto typeRessource) {
        this.typeRessource = typeRessource;
    }

    public void setTypeRessourceId(Long typeRessourceId) {
        this.typeRessourceId = typeRessourceId;
    }

    public void setStatut(StatutDto statut) {
        this.statut = statut;
    }

    public void setStatutId(Long statutId) {
        this.statutId = statutId;
    }

    public void setRessourceComposante(List<RessourceComposanteDto> ressourceComposante) {
        this.ressourceComposante = ressourceComposante;
    }

    public void setRessourceComposanteId(List<Long> ressourceComposanteId) {
        this.ressourceComposanteId = ressourceComposanteId;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public TypeRessourceDto getTypeRessource() {
        return typeRessource;
    }

    public StatutDto getStatut() {
        return statut;
    }

    public List<RessourceComposanteDto> getRessourceComposante() {
        return ressourceComposante;
    }

    public Long getTypeRessourceId() {
        return typeRessourceId;
    }

    public Long getStatutId() {
        return statutId;
    }

    public List<Long> getRessourceComposanteId() {
        return ressourceComposanteId;
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
    public List<VehiculeDto> getVehicules() {
        return vehicules;
    }

    public void setVehicules(List<VehiculeDto> vehicules) {
        this.vehicules = vehicules;
    }

    public List<Long> getVehiculesId() {
        return vehiculesId;
    }

    public void setVehiculesId(List<Long> vehiculesId) {
        this.vehiculesId = vehiculesId;
    }
    public Ressource toModel()
    {
        Ressource model = new Ressource();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setNom(this.getNom());
        if(this.getTypeRessource()!=null)
        {
            try {
                model.setType((TypeRessource) this.getTypeRessource().toModel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(this.getTypeRessourceId()!=null)
        {
            try {
                model.setType(ReferentielDefinitions.getTypeRessource(this.getTypeRessourceId()));
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
                model.setCentre((Centre)ReferentielDefinitions.serviceDefinitions.getCentreService().getById(this.getCentreId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<RessourceComposante> data = new ArrayList<>();
        Boolean data_present = Boolean.FALSE;
        if(this.getRessourceComposante()!=null)
        {
            if(this.getRessourceComposante().size()>0)
            {
                data_present = Boolean.TRUE;
                Boolean is_a_car = Boolean.FALSE;
                for(var ressourceComposante : this.getRessourceComposante())
                {
                    if(this.getVehiculesId()!=null)
                    {
                        if(this.getVehiculesId().contains(ressourceComposante.getId()))
                        {
                            is_a_car = Boolean.TRUE;
                        }

                    }
                    if(this.getVehicules()!=null)
                    {
                        for(var veh : this.getVehicules())
                        {
                            if(veh.getId()==ressourceComposante.getId())
                            {
                                is_a_car = Boolean.TRUE;
                            }
                        }

                    }
                    try {
                        if(is_a_car)
                        {
                            //It is a car, so avoid
                        }
                        else
                        {
                            var dest_ressourceComposante = (RessourceComposante)ressourceComposante.toModel();
                            dest_ressourceComposante.setRessource(model);
                            data.add(dest_ressourceComposante);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(this.getRessourceComposanteId()!=null)
        {
            if(this.getRessourceComposanteId().size()>0)
            {
                data_present = Boolean.TRUE;

                try {
                    var serv = this.getServices().getRessourceComposanteService();
                    var val = (List<RessourceComposante>)(List<?>)serv.GetThem(this.getRessourceComposanteId());
                    data = val;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //Vehicules
        List<Vehicule> datav = new ArrayList<>();
        data_present = Boolean.FALSE;
        if(this.getVehicules()!=null)
        {
            if(this.getVehicules().size()>0)
            {
                data_present = Boolean.TRUE;
                for(var ressourceComposante : this.getVehicules())
                {
                    try {
                        var dest_ressourceComposante = (Vehicule)ressourceComposante.toModel();
                        dest_ressourceComposante.setRessource(model);
                        datav.add(dest_ressourceComposante);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(this.getVehiculesId()!=null)
        {
            if(this.getVehiculesId().size()>0)
            {
                data_present = Boolean.TRUE;

                try {
                    var serv = this.getServices().getVehicleService();
                    var val = (List<Vehicule>)(List<?>)serv.GetThem(this.getVehiculesId());
                    datav = val;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        model.setVehicules(datav);
        model.setRessourceComposantes(data);
        return model;
    }
}