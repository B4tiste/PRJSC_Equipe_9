package Mapper;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseModelDto;
import ModelDto.*;
import ModelDto.SensorRelated.*;
import Models.*;
import Models.SensorRelated.*;
import org.modelmapper.ModelMapper;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseMapper {

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
        this.DoMapping();


    }
    public void DoMapping()
    {
        for (var ob : baseMapType) {
            mapType.put(ob.getTypeA(), ob.getTypeB());
            mapTypeReverse.put(ob.getTypeB(), ob.getTypeA());
        }
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
        this.baseMapType.add(new MapInst<TypeDto, Type>(TypeDto.class, Type.class));
        this.baseMapType.add(new MapInst<UrgenceDto, Urgence>(UrgenceDto.class, Urgence.class));
        this.baseMapType.add(new MapInst<VehiculeDto, Vehicule>(VehiculeDto.class, Vehicule.class));

        this.baseMapType.add(new MapInst<CapteurDonneesDto, CapteurDonnees>(CapteurDonneesDto.class, CapteurDonnees.class));
        this.baseMapType.add(new MapInst<CapteurDto, Capteur>(CapteurDto.class, Capteur.class));
        this.baseMapType.add(new MapInst<CapteurTypeDto, CapteurType>(CapteurTypeDto.class, CapteurType.class));
        this.baseMapType.add(new MapInst<EtatDto, Etat>(EtatDto.class, Etat.class));
        this.baseMapType.add(new MapInst<MicrocontrollerDto, Microcontroller>(MicrocontrollerDto.class, Microcontroller.class));
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