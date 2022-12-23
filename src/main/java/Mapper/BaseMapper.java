package Mapper;

import BaseReferentiel.RepositoryDefinitions;
import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseModelDto;
import ModelDto.*;
import ModelDto.SensorRelated.*;
import Models.*;
import Models.SensorRelated.*;
import Repositories.Type.TypeUrgenceRepository;
import Repositories.UrgenceRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseMapper extends RepositoryDefinitions {

    private ModelMapper mapper;

    public List<MapInst<? extends IBaseModelDto, ? extends IBaseModel>> baseMapType =
            new ArrayList<MapInst<? extends IBaseModelDto, ?  extends IBaseModel>>();

    public HashMap<Class<? extends IBaseModelDto>,Class<? extends IBaseModel>> mapType =
            new HashMap<>();

    public HashMap<Class<? extends IBaseModel>, Class<? extends IBaseModelDto>> mapTypeReverse =
            new HashMap<>();

    public BaseMapper()
    {
        this.mapper = new ModelMapper();
        this.Init();
        this.InitMapRules();
        this.DoMapping();
    }

    public void DoMapping()
    {
        for (var ob : baseMapType) {
            mapType.put(ob.getTypeA(), ob.getTypeB());
            mapTypeReverse.put(ob.getTypeB(), ob.getTypeA());
        }
    }


    public void InitMapRules()
    {
        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.mapper.createTypeMap(UrgenceDto.class, Urgence.class)
                .addMappings(mapper->mapper.using(ctx->{
                    Long id = (Long)ctx.getSource();
                    return id != null ? this.incidentRepository.findById(id).orElse(null) : null;
                }).map(UrgenceDto::getIncidentDto, Urgence::setIncident))
                .addMappings(mapper->mapper.using(ctx->{
                    Long id = (Long)ctx.getSource();
                    return id != null ? this.typeUrgenceRepository.findById(id).orElse(null) : null;
                }).map(UrgenceDto::getTypeRessourceDto, Urgence::setType))
                .addMappings(mapper->mapper.using(ctx->{
                    Long id = (Long)ctx.getSource();
                    return id != null ? this.statutRepository.findById(id).orElse(null) : null;
                }).map(UrgenceDto::getStatutDto, Urgence::setStatut));

        this.mapper.createTypeMap(ModelDto.SensorRelated.CapteurDto.class, Models.SensorRelated.Capteur.class)
                .addMappings(mapper -> mapper.using(ctx -> {
                    Long id = (Long)ctx.getSource();
                    return id != null ? this.microcontrollerRepository.findById(id).orElse(null) : null;
                }).map(ModelDto.SensorRelated.CapteurDto::getMicrocontroller, Models.SensorRelated.Capteur::setMicrocontroller))
                .addMappings(mapper -> mapper.using(ctx -> {
                    Long id = (Long)ctx.getSource();
                    return id != null ? this.typeCapteurRepository.findById(id).orElse(null) : null;
                }).map(ModelDto.SensorRelated.CapteurDto::getSensorType, Models.SensorRelated.Capteur::setCapteurType));

        this.mapper.createTypeMap(CentreDto.class, Centre.class)
                .addMappings(mapper -> mapper.using(ctx -> {
                    Long id = (Long)ctx.getSource();
                    return id != null ? ressourceComposanteRepository.findById(id).orElse(null) : null;
                }).map(CentreDto::getRessourceComposanteDtos, Centre::setRessourceComposantes));

        this.mapper.createTypeMap(RessourceDto.class, Ressource.class)
                .addMappings(mapper->mapper.using(ctx->{
                    Long id = (Long)ctx.getSource();
                    return this.typeRessourceRepository.findById(id).orElse(null);
                }).map(RessourceDto::getTypeRessourceDto, Ressource::setType))
                .addMappings(mapper->mapper.using(ctx->{
                    Long id = (Long)ctx.getSource();
                    return this.statutRepository.findById(id).orElse(null);
                }).map(RessourceDto::getStatutDto, Ressource::setStatut))
                .addMappings(mapper->mapper.using(ctx->{
                    Long id = (Long)ctx.getSource();
                    return this.urgenceRepository.findById(id).orElse(null);
                }).map(RessourceDto::getUrgenceDto, Ressource::setUrgence));

        this.mapper.createTypeMap(PersonneDto.class, Personne.class)
                .addMappings(mapper->mapper.using(ctx->{
                    Long id = (Long)ctx.getSource();
                    return roleRepository.findById(id).orElse(null);
                }).map(PersonneDto::getRoleDto, Personne::setRole));

        this.mapper.createTypeMap(IncidentDto.class, Incident.class)
                .addMappings(mapper->mapper.using(ctx->{
                    Long id = (Long)ctx.getSource();
                    return id != null ? this.adresseRepository.findById(id).orElse(null) : null;
                }).map(IncidentDto::getAdresseDto, Incident::setAdresse))
                .addMappings(mapper->mapper.using(ctx->{
                    Long id = (Long)ctx.getSource();
                    return id != null ? this.prioriteRepository.findById(id).orElse(null) : null;
                }).map(IncidentDto::getPrioriteDto, Incident::setPriorite));

    }

    public void Init()
    {
        this.baseMapType.add(new MapInst<AdresseDto, Adresse>(AdresseDto.class, Adresse.class));
        this.baseMapType.add(new MapInst<CentreDto, Centre>(CentreDto.class, Centre.class));
        this.baseMapType.add(new MapInst<IncidentDto, Incident>(IncidentDto.class, Incident.class));
        this.baseMapType.add(new MapInst<PersonneDto, Personne>(PersonneDto.class, Personne.class));
        this.baseMapType.add(new MapInst<PrioriteDto, Priorite>(PrioriteDto.class, Priorite.class));
        this.baseMapType.add(new MapInst<RessourceComposanteDto, RessourceComposante>(RessourceComposanteDto.class, RessourceComposante.class));
        this.baseMapType.add(new MapInst<RessourceDto, Ressource>(RessourceDto.class, Ressource.class));
        this.baseMapType.add(new MapInst<RoleDto, Role>(RoleDto.class, Role.class));
        this.baseMapType.add(new MapInst<StatutDto, Statut>(StatutDto.class, Statut.class));
        this.baseMapType.add(new MapInst<TypeRessourceDto, TypeRessource>(TypeRessourceDto.class, TypeRessource.class));
        this.baseMapType.add(new MapInst<TypeUrgenceDto, TypeUrgence>(TypeUrgenceDto.class, TypeUrgence.class));
        this.baseMapType.add(new MapInst<UrgenceDto, Urgence>(UrgenceDto.class, Urgence.class));
        this.baseMapType.add(new MapInst<VehiculeDto, Vehicule>(VehiculeDto.class, Vehicule.class));

        this.baseMapType.add(new MapInst<CapteurDonneesDto, CapteurDonnees>(CapteurDonneesDto.class, CapteurDonnees.class));
        this.baseMapType.add(new MapInst<CapteurDto, Models.SensorRelated.Capteur>(CapteurDto.class, Models.SensorRelated.Capteur.class));
        this.baseMapType.add(new MapInst<CapteurTypeDto, Models.SensorRelated.CapteurType>(CapteurTypeDto.class, Models.SensorRelated.CapteurType.class));
        this.baseMapType.add(new MapInst<EtatDto, Models.SensorRelated.Etat>(EtatDto.class, Models.SensorRelated.Etat.class));
        this.baseMapType.add(new MapInst<MicrocontrollerDto, Models.SensorRelated.Microcontroller>(MicrocontrollerDto.class, Models.SensorRelated.Microcontroller.class));
    }

    public ModelMapper GetModelMapper()
    {
        return this.mapper;
    }

    public void SetModelMapper(ModelMapper mapper)
    {
        this.mapper = mapper;
    }

    public IBaseModel map(IBaseModelDto model)
    {

        var type = mapType.get(model.getClass());

        if(type !=null)
        {
            try {
                var instance = this.mapper.map(model, type);
                return instance;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
        return null;

    }

    public IBaseModelDto reverseMap(IBaseModel model)
    {

        var type = mapTypeReverse.get(model.getClass());

        if(type !=null)
        {
            try {
                var instance = this.mapper.map(model, type);
                return instance;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
        return null;

    }
}