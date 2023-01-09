package emergency.controllers;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.baseReferentiel.ServiceDefinitions;
import emergency.enumDefinition.STATUT_RESSOURCE;
import emergency.enumDefinition.STATUT_URGENCE;
import emergency.modelDto.VehiculeDto;
import emergency.models.Vehicule;
import emergency.services.VehicleService;
import emergency.setupData.CoordinateGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/Vehicule")
public class VehiculeController extends emergency.controllers.BaseController {

    @Autowired
    public VehicleService vehiculeService;

    @Autowired
    public ServiceDefinitions services;

    public VehiculeController(ServiceDefinitions services, VehicleService vehiculeService)
    {
        ReferentielDefinitions.serviceDefinitions = services;
        this.services = services;
        this.vehiculeService = vehiculeService;
        this.setService(this.vehiculeService);
    }

    @GetMapping("/GetVehicules")
    public ResponseEntity<List<VehiculeDto>> GetVehicules(@RequestParam(value = "OnlyId", defaultValue = "false") boolean OnlyId)
    {
        try {
            var vehicules = this.vehiculeService.GetAll();

            List<VehiculeDto> vehicules_d = new ArrayList<>();
            for(var vehicule : vehicules)
            {
                var vehicule_b = (Vehicule)vehicule;
                vehicules_d.add((VehiculeDto)vehicule_b.toDto(OnlyId));
            }

            return (new ResponseEntity<List<VehiculeDto>>(vehicules_d, HttpStatus.OK));
        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<VehiculeDto>>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/GetVehicule")
    public ResponseEntity<VehiculeDto> GetVehicule(
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId,
            @RequestParam(value = "Id", defaultValue = "0") Long Id
    )
    {
        try {

            var vehicule = (Vehicule)this.vehiculeService.getById(Id);
            return (new ResponseEntity<VehiculeDto>(vehicule.toDto(OnlyId), HttpStatus.OK));

        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<VehiculeDto>(HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/CreateVehicule")
    public ResponseEntity<VehiculeDto> CreateVehicule(
            @RequestBody VehiculeDto vehicule,
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId
    )
    {
        try {
            var vehicule_get = (Vehicule)this.vehiculeService.CreateOrUpdateOrGet(vehicule.toModel());
            return (new ResponseEntity<VehiculeDto>(vehicule_get.toDto(OnlyId), HttpStatus.OK));

        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<VehiculeDto>(HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/UpdateVehicules")
    public ResponseEntity<List<VehiculeDto>> UpdateVehicules(@RequestBody List<VehiculeDto> vehicules)
    {
        try {


            List<VehiculeDto> vehicules_d = new ArrayList<>();
            for(var vehicule : vehicules)
            {
                var vehicule_b = (VehiculeDto)vehicule;
                vehicules_d.add(vehicule_b.toModel().Save(this.services, Boolean.FALSE).toDto(Boolean.FALSE));
            }

            return (new ResponseEntity<List<VehiculeDto>>(vehicules_d, HttpStatus.OK));
        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<List<VehiculeDto>>(HttpStatus.NOT_FOUND));
        }
    }




    @PutMapping("/UpdateVehicule")
    public ResponseEntity<VehiculeDto> UpdateVehicule(
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId,
            @RequestBody VehiculeDto vehicule
    )
    {
        try {

            var vehicule_b = (Vehicule)vehicule.toModel().Save(this.services, Boolean.FALSE);

            return (new ResponseEntity<VehiculeDto>(vehicule_b.toDto(OnlyId), HttpStatus.OK));

        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<VehiculeDto>(HttpStatus.NOT_FOUND));
        }
    }

    @PutMapping("/UpdateVehiculePosition")
    public ResponseEntity<VehiculeDto> UpdateVehiculePosition(
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId,
            @RequestParam(value = "Id") Long Id,
            @RequestParam(value = "latitude") Double latitude,
            @RequestParam(value = "longitude") Double longitude


    )
    {
        try {

            var vehicule = (Vehicule)this.services.getVehicleService().getById(Id);

            if(vehicule != null)
            {
                if(vehicule.getRessource().getUrgence()!=null)
                {
                    var position_lat = vehicule.getRessource().getUrgence().getIncident().getLatitude();
                    var position_lng = vehicule.getRessource().getUrgence().getIncident().getLongitude();
                    vehicule.setLatitude(latitude);
                    vehicule.setLongitude(longitude);
                    double[ ] pos1 = {latitude, longitude};
                    double[ ] pos2 = {position_lat, position_lng};
                    var distance = CoordinateGenerator.calculateDistance(pos1,pos2);

                    if(distance<0.05)
                    {
                        vehicule.setStatut(ReferentielDefinitions.getStatut(STATUT_RESSOURCE.EN_EXECUTION.name()));
                    }
                    else{
                        vehicule.setStatut(ReferentielDefinitions.getStatut(STATUT_RESSOURCE.EN_DEPLACEMENT_ALLER.name()));
                    }
                    var veh = (Vehicule)this.services.getVehicleService().CreateOrUpdateOrGet(vehicule);

                    return (new ResponseEntity<VehiculeDto>(veh.toDto(OnlyId), HttpStatus.OK));
                }
                else if(vehicule.getRessource().getCentre()!=null)
                {
                    var position_lat = vehicule.getRessource().getCentre().getLatitude();
                    var position_lng = vehicule.getRessource().getCentre().getLongitude();
                    vehicule.setLatitude(latitude);
                    vehicule.setLongitude(longitude);
                    double[ ] pos1 = {latitude, longitude};
                    double[ ] pos2 = {position_lat, position_lng};
                    var distance = CoordinateGenerator.calculateDistance(pos1,pos2);
                    if(distance<0.05)
                    {
                        vehicule.setStatut(ReferentielDefinitions.getStatut(STATUT_RESSOURCE.GARE.name()));
                    }
                    else{
                        vehicule.setStatut(ReferentielDefinitions.getStatut(STATUT_RESSOURCE.En_DEPLACEMENT_RETOUR.name()));
                    }
                    var veh = (Vehicule)this.services.getVehicleService().CreateOrUpdateOrGet(vehicule);

                    return (new ResponseEntity<VehiculeDto>(veh.toDto(OnlyId), HttpStatus.OK));
                }

            }
            return (new ResponseEntity<VehiculeDto>(HttpStatus.NOT_FOUND));


        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<VehiculeDto>(HttpStatus.NOT_FOUND));
        }
    }


    @PutMapping("/UpdateVehiculeState")
    public ResponseEntity<VehiculeDto> UpdateVehiculeState(
            @RequestParam(value = "OnlyId", defaultValue = "false") Boolean OnlyId,
            @RequestParam(value = "Id") Long Id,
            @RequestParam(value = "latitude") Double latitude


    )
    {
        try {

            var vehicule = (Vehicule)this.services.getVehicleService().getById(Id);

            if(vehicule != null)
            {


            }
            return (new ResponseEntity<VehiculeDto>(HttpStatus.NOT_FOUND));


        } catch (Exception e) {
            e.printStackTrace();
            return (new ResponseEntity<VehiculeDto>(HttpStatus.NOT_FOUND));
        }
    }

}